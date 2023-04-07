package com.fangzaibbs.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Controller
public class AuthController {
    private final Map<String, String> users = new HashMap<>();
    @GetMapping("/authentication")
    public String authentication() {
        return "authentication";
    }

    @PostMapping("/register")
    public String register(@RequestParam("regUsername") String username,
                           @RequestParam("regPassword") String password) {
        // 在这里实现注册逻辑
        try {
            saveRegistrationToFile(username, password);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

        return "authentication";
    }

    private void saveRegistrationToFile(String username, String password) throws IOException {
        String filePath = "registrations.txt"; // 您可以自定义保存注册信息的文件路径
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(username + "," + password);
            writer.newLine();
        }
    }
    @PostMapping("/login")
    public String login(@RequestParam("loginUsername") String username,
                        @RequestParam("loginPassword") String password) {
        // 在这里实现登录逻辑
        String storedPassword = users.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            // 如果登录成功，返回 "mainpage2"
            return "mainpage2";
        } else {
            // 否则，返回 "authentication"
            return "authentication";
        }
    }

}
