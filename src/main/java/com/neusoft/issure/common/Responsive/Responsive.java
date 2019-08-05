package com.neusoft.issure.common.Responsive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @Title:  Responsive.java
 * @Description:  通用返回结果
 * @author: jetty
 * @date:   2019/3/11 11:41
 * @version V1.0
 */
@ApiModel(description = "通用返回结果")
public interface Responsive {

    /**
     * 表示结果状态
     *
     * @return 状态码
     */
    @ApiModelProperty("状态码，0000表示正常")
    default String getStatus() {
        return "0000";
    }

    /**
     * 提示信息
     *
     * @return 提示
     */
    @ApiModelProperty("提示信息，通常是出错时告知前端使用")
    default String getMsg() {
        return null;
    }

    /**
     * 封装数据
     *
     * @return 数据
     */
    @ApiModelProperty("结果数据")
    default <T> T getData() {
        return null;
    }

    /**
     * 快速构造Responsive
     *
     * @return Responsive
     */
    static Responsive success() {
        return new Response("0000", null, null);
    }

    /**
     * 快速构造Responsive
     *
     * @param data 数据
     * @return Responsive
     */
    static Responsive success(Object data) {
        return new Response("0000", null, data);
    }

    /**
     * 快速构造Responsive
     *
     * @param status 状态码
     * @param msg    提示
     * @return Responsive
     */
    static Responsive of(String status, String msg) {
        return new Response(status, msg, null);
    }

    /**
     * 快速构造Responsive
     *
     * @param status 状态码实例
     * @return Responsive
     */
    static Responsive of(ResponseStatus status) {
        return new Response(status.getStatus(), status.getMsg(), null);
    }

    /**
     * 快速构造Responsive
     *
     * @param status 状态码
     * @param msg    提示
     * @param data   数据
     * @return Responsive
     */
    static Responsive of(String status, String msg, Object data) {
        return new Response(status, msg, data);
    }

    class Response implements Responsive {

        private String status;
        private String msg;
        private Object data;

        private Response(String status, String msg, Object data) {
            this.status = status;
            this.msg = msg;
            this.data = data;
        }

        @Override
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getData() {
            return (T) data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
