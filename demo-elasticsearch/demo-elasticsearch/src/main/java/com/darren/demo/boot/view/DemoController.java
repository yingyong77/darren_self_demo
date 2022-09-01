package com.darren.demo.boot.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : darren
 * @date : 2022/8/29
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    private Map map = new HashMap<>();

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        map.put("ddd", "dd");
        return "hello";
    }
}
