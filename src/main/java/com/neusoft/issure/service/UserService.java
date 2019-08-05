package com.neusoft.issure.service;

import com.neusoft.issure.api.common.req.LoginReq;
import com.neusoft.issure.common.Responsive.Responsive;
import com.neusoft.issure.domain.User;
import com.neusoft.issure.repository.UserDao;
import com.neusoft.issure.util.JwtUtils;
import com.neusoft.issure.util.lang.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    // 用户登录
    public Responsive UserLogin(LoginReq req){
       //获取手机号
       String mobile =req.getMobile();
       User user = userDao.findUserByMobile(mobile);
       if (null == user ){
            return Responsive.of("400","不存在该用户","''");
        }
        //如果用户不为空
        if(StringUtil.isNotNull(user)){
            Long id = user.getId(); //获取用户Id
            Map<String,Object> tokenMap = new HashMap<>();
            Map<String, String> resultMap = new HashMap<>();
            tokenMap.put("userId",user.getId());
            String token = JwtUtils.generateToken(tokenMap);
            resultMap.put("token", token);
            return Responsive.success(resultMap);
        }
        return Responsive.success();
    }
}
