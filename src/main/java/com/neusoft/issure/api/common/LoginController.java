package com.neusoft.issure.api.common;

import com.neusoft.issure.api.common.req.LoginReq;
import com.neusoft.issure.common.Responsive.Responsive;
import com.neusoft.issure.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "用户登陆接口")
public class LoginController {
    @Autowired
    UserService userService;
    @ApiOperation("用户手机号登录")

    @PostMapping(value = "/doLogin")
    public Responsive doLoginAdmin(@RequestBody LoginReq req){
        return userService.UserLogin(req);
    }
}
