package musicForum.controllor;

import musicForum.bean.testDate;
import musicForum.mapper.TestDateMapper;
import musicForum.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("test")
public class testControllor {
    @Autowired
    TestDateMapper testDateMapper;
    @GetMapping("insertTime")
    public Result insertTime(){
        testDate testdate = new testDate();
        testdate.setId(1L);
        testdate.setCreateDate(new Date());
        testDateMapper.insert(testdate);
        return Result.success(testdate);
    }
    @GetMapping("updateTime")
    public Result updateTime(){
        testDate testdate = new testDate();
        testdate.setId(1L);
        testdate.setCreateDate(new Date());
        testDateMapper.updateById(testdate);
        return Result.success(testdate);
    }
    @GetMapping("getTime")
    public Result getTime(){
        testDate testDate = testDateMapper.selectById(1L);
        return Result.success(testDate);
    }
}
