package io.herald.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Controller handles http request like get, post,.... etc
@Controller

public class TotalController {
    @GetMapping("/")
    public String firstPage(){
        return "index"; //returns index.html page
    }
}
