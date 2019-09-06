package com.neusoft.issure.api.common;

import com.neusoft.issure.api.common.req.PageReq;
import com.neusoft.issure.api.common.req.UserListParam;
import com.neusoft.issure.common.Responsive.Responsive;
import com.neusoft.issure.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@Api(tags = "用户管理接口")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("用户列表")
    @GetMapping(value = "/admin/user/list")
    public Responsive getUserList(@Valid UserListParam req){
        return userService.GetUserList(req);
    }
}
