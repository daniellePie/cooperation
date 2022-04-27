package musicForum.controllor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloControllor {
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello, Spring Boot 2!";
    }
}
