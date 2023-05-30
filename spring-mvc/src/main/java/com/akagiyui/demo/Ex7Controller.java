package com.akagiyui.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author AkagiYui
 */
@Controller
public class Ex7Controller {
    @GetMapping("/map")
    public String getMap(@RequestParam Map<String, ?> map, Model model) {
        model.addAttribute("name", map.get("name"));
        return "welcome";
    }

    @GetMapping("/model-map")
    public String getMap2(ModelMap map) {
        map.addAttribute("name", "AkagiYui");
        return "welcome";
    }
}
