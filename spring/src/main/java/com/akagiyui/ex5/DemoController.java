package com.akagiyui.ex5;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author AkagiYui
 */

@Controller
public class DemoController {
    @GetMapping("/demo")
    public String welcome(Model model)	{
        model.addAttribute("name", "silent man");
        return "welcome";
    }

    @GetMapping("/")
    public String index()	{
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(String username, String password, Model model) {
        // 验证用户名和密码
        if ("AkagiYui".equals(username) && "1050314133".equals(password)) {
            model.addAttribute("name", username);
            return "welcome";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/raw")
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String protocol = request.getProtocol();
        String remoteAddr = request.getRemoteAddr();

        // 写入响应信息
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("Request Method : " + method);
        writer.println("Request URI : " + uri);
        writer.println("Request Protocol : " + protocol);
        writer.println("Client IP : " + remoteAddr);
        writer.flush();
    }

    @GetMapping("/model-view")
    public ModelAndView handleModelAndView() {
        // 构造ModelAndView对象
        ModelAndView mav = new ModelAndView("message");
        mav.addObject("message", "欢迎使用Spring MVC！");
        return mav;
    }

    @GetMapping("/response-entity")
    public ResponseEntity<String> handleResponseEntity() {
        // 构造JSON字符串
        String json = "{\"name\": \"小明\", \"age\": 20}";

        // 构造ResponseEntity对象
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }
}
