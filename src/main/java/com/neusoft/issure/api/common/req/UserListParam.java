package com.neusoft.issure.api.common.req;

import lombok.Data;

@Data
public class UserListParam extends PageReq {
    private String mobile; //用户手机号
}
