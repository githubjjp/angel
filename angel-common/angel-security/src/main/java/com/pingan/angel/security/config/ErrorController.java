package com.pingan.angel.security.config;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@RestController
@SessionAttributes("authorizationRequest")
@Slf4j
public class ErrorController {
	@RequestMapping("/oauth/error")
    public String error(@RequestParam Map<String, String> parameters){
        String url = parameters.get("redirect_uri");
        log.info("重定向: {}", url);
        return "redirect:" + url + "?error=1";
    }

}
