package com.neusoft.issure.api.wechat;

import com.neusoft.issure.util.CheckUntil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class WeChatController {
    @GetMapping("/wx")
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String signature = httpServletRequest.getParameter("signature");
        String timestamp = httpServletRequest.getParameter("timestamp");
        String nonce = httpServletRequest.getParameter("nonce");
        String echoStr = httpServletRequest.getParameter("echoStr");
        if (CheckUntil.checkSignatures(signature, timestamp, nonce)) {
            return echoStr;
        }
        return null;
    }
}
