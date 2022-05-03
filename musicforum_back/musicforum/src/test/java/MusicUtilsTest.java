import musicForum.utils.MusicUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MusicUtilsTest {
    @Test
    public void getMp3Duration(){
        String filepath1 = "C:/Users/admin/Desktop/wave.mp3";
        String filepath2 = "C:/Users/admin/Desktop/stone.mp3";
        String filepath3 = "C:/Users/admin/Desktop/outTry.mp3";
        //精确到一位小数是比较准的
        System.out.println(MusicUtils.getMp3Duration(filepath1));
        System.out.println(MusicUtils.getMp3Duration(filepath2));
        System.out.println(MusicUtils.getMp3Duration(filepath3));
    }

    @Test
    public void concatMp3List(){
        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add("C:/Users/admin/Desktop/wave.mp3");
        filePaths.add("C:/Users/admin/Desktop/stone.mp3");
        String outFilePath = "C:/Users/admin/Desktop/outTry.mp3";
        MusicUtils.concatMp3List(filePaths,outFilePath);
    }
    @Test
    public void generateNullMp3(){
        String filepath = "C:/Users/admin/Desktop/nullTry.mp3";
        Double s = 5.4;
        MusicUtils.generateNullMp3(filepath,s);
    }
    @Test
    public void mixMp3List(){
        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add("C:/Users/admin/Desktop/wave.mp3");
        filePaths.add("C:/Users/admin/Desktop/stone.mp3");
        String outFilePath = "C:/Users/admin/Desktop/mixTry.mp3";
        MusicUtils.mixMp3List(filePaths,outFilePath);
    }
    @Test
    public void concatMp3ListWithNull() throws IOException {
        Path path = Paths.get("C:/Users/admin/Desktop/temAudio");
        Files.createDirectories(path);
        ArrayList<String> filePaths = new ArrayList<>();
        ArrayList<Double> startTime = new ArrayList<>();
        ArrayList<Double> duration = new ArrayList<>();
        filePaths.add("C:/Users/admin/Desktop/wave.mp3");
        filePaths.add("C:/Users/admin/Desktop/stone.mp3");
        startTime.add(2.0);
        startTime.add(43.0);
        duration.add(MusicUtils.getMp3Duration(filePaths.get(0)));
        duration.add(MusicUtils.getMp3Duration(filePaths.get(1)));
        Double sumTime = 60.0;
        String outFilePath = "C:/Users/admin/Desktop/concatWithNull.mp3";
        String temFilePath = "C:/Users/admin/Desktop/temAudio/try";
        MusicUtils.concatMp3ListWithNull(filePaths,startTime,duration,sumTime,
                outFilePath,temFilePath);
        File file = new File("C:/Users/admin/Desktop/temAudio");
        String[] files = file.list();
        if(files.length>0){
            for (String i:files) {
                new File("C:/Users/admin/Desktop/temAudio",i).delete();
            }
            //在删除全部文件后，将上层目录删除
            new File(file.getPath()).delete();
        }else{
            //若文件夹为空，则只删除上层文件夹
            new File(file.getPath()).delete();
        }
        //Files.deleteIfExists(path);
    }
}
