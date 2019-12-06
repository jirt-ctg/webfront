package com.ctgpaas.webfront.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务调用响应结果")
@JsonInclude(Include.NON_NULL)
public class Response<T> {
    private static final Response SUCCESS = new Response();
    private static final int CODE_SUCCESS = 0;
    @ApiModelProperty("状态码")
    private int code = 0;
    @ApiModelProperty("返回消息")
    private String msg;
    @ApiModelProperty("返回数据对象")
    private T data;

    public Response() {
    }

    private Response(T data) {
        this.data = data;
    }

    public static Response success() {
        return SUCCESS;
    }

    public static <T> Response<T> success(T data) {
        return new Response(data);
    }

    public static Response fail(int code, String msg) {
        Response<Object> r = new Response();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == 0;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}