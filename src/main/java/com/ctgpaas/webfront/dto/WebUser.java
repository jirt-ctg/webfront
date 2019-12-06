package com.ctgpaas.webfront.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebUser {
    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("Email")
    private String email;
    public Integer getId() {
    return id;
    }

    public void setId(Integer id) {
    this.id = id;
    }

    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
    this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
    return password;
    }

    public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
    }
}