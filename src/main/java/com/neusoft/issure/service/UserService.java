package com.neusoft.issure.service;

import com.neusoft.issure.api.common.req.LoginReq;
import com.neusoft.issure.api.common.req.UserListParam;
import com.neusoft.issure.common.Responsive.Responsive;
import com.neusoft.issure.domain.User;
import com.neusoft.issure.repository.UserDao;
import com.neusoft.issure.util.JwtUtils;
import com.neusoft.issure.util.lang.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    /**
     * 获取用户列表
     */
    public Responsive GetUserList(UserListParam req){
        try {
            PageRequest pageRequest = PageRequest.of(req.getCurrentPage()-1,req.getPageSize());
            Page<User> user = userDao.findAll(pageRequest);
            return Responsive.success(user);
        }catch (Exception e){
            e.printStackTrace();
            return Responsive.of("","系统异常","");
        }
    }
}
