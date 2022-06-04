package musicForum.utils;

import org.bytedeco.javacpp.Loader;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现音频的见切，拼接，合并，实现File和MultipartFile的相关操作
 * 统一要求320kbps？？
 * 限制音轨数，限制总时长，限制每个音轨能放的音频数
 */
public class MusicUtils {
    /**
     * ffmpeg -i wave.mp3
     * 利用ffmpeg获取mp3语音文件精确时长(秒) mp3
     * 测试通过，精确到一位小数比较准
     *
     * @param filePath 文件路径
     * @return double 文件时长
     */
    public static Double getMp3Duration(String filePath) {
        List<String> command = new ArrayList<>();
        //获取JavaCV中的ffmpeg本地库的调用路径
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        command.add(ffmpeg);
        command.add("-i");
        command.add(filePath);
        //执行并获得命令行输出
        String join = String.join(" ", command);
        System.out.println(join);
        ProcessBuilder  process = new ProcessBuilder(command);
        //输出重定向
        process.redirectErrorStream(true);
        try {
            Process p= process.start();
            BufferedReader buf = null;
            String line = null;
            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb= new StringBuilder();
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }
            p.waitFor();
            String out = sb.toString();
            //System.out.println("输出："+sb);
            //正则匹配找到时间
            Pattern pattern = Pattern.compile("(?i)Duration:\\s*(\\d+\\s*):(\\d+\\s*):(\\d+\\s*.\\d+\\s*)");
            Matcher matcher = pattern.matcher(out);
            if(matcher.find()){
                //System.out.println("匹配字符串："+matcher.group(0));
                String hour = matcher.group(1);
                String minute = matcher.group(2);
                String second = matcher.group(3);
                return (Double.parseDouble(hour)*60+Double.parseDouble(minute))*60
                        + Double.parseDouble(second);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1.1;
    }
    /**
     * 开启进程执行ffmpeg
     *
     */
    public  static  void   execute(List<String> command) {
        try {
            String join = String.join(" ", command);
            System.out.println(join);
            ProcessBuilder  process = new ProcessBuilder(command);
            process.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 拼接mp3列表
     * 测试通过
     *
     * @param filePaths 文件路径列表
     * @return String 输出文件路径
     */
    public static String concatMp3List(List<String> filePaths,String outFilePath){
        List<String> command = new ArrayList<>();
        //获取JavaCV中的ffmpeg本地库的调用路径
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        command.add(ffmpeg);
        command.add("-i");
        //处理传入-i的多输入参数
        StringBuilder concatArg = new StringBuilder();
        concatArg.append("concat:");
        for (String filePath : filePaths) {
            concatArg.append(filePath);
            concatArg.append("|");
        }
        concatArg.deleteCharAt(concatArg.length()-1);
        command.add(concatArg.toString());
        command.add("-acodec");
        command.add("copy");
        command.add(outFilePath);
        execute(command);
        return outFilePath;
    }
    //改变比特率ffmpeg -i input.mp3 -b:a 128k output.mp3
    /**
     * 生成任意长度的空音频
     * 测试通过
     *
     * @param filePath 生成音频路径
     * @param s 时间长度
     * @return String 输出文件路径
     */
    public static String generateNullMp3(String filePath,Double s){
        List<String> command = new ArrayList<>();
        //获取JavaCV中的ffmpeg本地库的调用路径
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        command.add(ffmpeg);
        command.add("-f");
        command.add("lavfi");
        command.add("-i");
        command.add("anullsrc");
        command.add("-t");
        command.add(s.toString());
        command.add(filePath);
        execute(command);
        return filePath;
    }
    /**
     * 生成任意长度的空音频
     * 测试通过
     *
     * @param filePaths 音频路径集合
     * @param outFilePath 输出路径
     * @return String 输出文件路径
     */
    public static String mixMp3List(List<String> filePaths,String outFilePath){
        List<String> command = new ArrayList<>();
        //获取JavaCV中的ffmpeg本地库的调用路径
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        command.add(ffmpeg);
        //将输入的读取速率降低到输入的本地帧速率，适合直播
        //command.add("-re");
        for (String filePath : filePaths) {
            command.add("-i");
            command.add(filePath);
        }
        command.add("-filter_complex");
        String amix = "amix=inputs="+filePaths.size()+":duration=first:dropout_transition=2";
        command.add(amix);
        command.add(outFilePath);
        execute(command);
        return outFilePath;
    }
    /**
     * 使用null补空的concat
     * 测试通过
     *
     * @param filePaths 音频路径集合
     * @param startTime 开始时间集合
     * @param duration 持续时间集合
     * @param sumTime 总时长
     * @param outFilePath 输出路径
     * @param temFilePath 存放临时空音频的文件夹
     * @return String 输出文件路径
     *
     */
    public static String concatMp3ListWithNull(List<String> filePaths, List<Double> startTime,
                                               List<Double> duration, Double sumTime,
                                               String outFilePath,String temFilePath){
        //根据时间创建空音频后传给其他参数处理
        ArrayList<String> filePaths_new = new ArrayList<>();
        Double now = 0.0;
        String temFile;
        for (int i = 0; i < filePaths.size(); i++) {
            temFile = temFilePath+"_"+i+".mp3";
            Double d = startTime.get(i) - now;
            generateNullMp3(temFile,d);
            filePaths_new.add(temFile);
            filePaths_new.add(filePaths.get(i));
            now = startTime.get(i) + duration.get(i);
        }
        temFile = temFilePath+"_"+filePaths.size()+".mp3";
        Double d = sumTime - now;
        generateNullMp3(temFile,d);
        filePaths_new.add(temFile);
        return concatMp3List(filePaths_new,outFilePath);
    }

}
