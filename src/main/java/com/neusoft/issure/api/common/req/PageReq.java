package com.neusoft.issure.api.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;


/**
 * @Title:  PageReq.java
 * @Description:  分页请求的参数
 * @author: jetty
 * @date:   2019/3/11 11:40
 * @version V1.0
 */
@Data
@ApiModel(description = "分页请求的参数")
public class PageReq {

    @Min(value = 1, message = "页码至少为1")
    @ApiModelProperty(value = "页码，从1开始，默认1", example = "1")
    private Integer currentPage;
    @Min(value = 1, message = "分页大小至少为1")
    @ApiModelProperty(value = "分页大小，默认10", example = "10")
    private Integer pageSize;
    @ApiModelProperty(value = "是否需要分页", example = "true")
    private boolean enablePage = true;

    public PageReq() {
        currentPage = 1;
        pageSize = 10;
    }
    public PageReq(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

}
