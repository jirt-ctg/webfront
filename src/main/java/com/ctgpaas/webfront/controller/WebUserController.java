package com.ctgpaas.webfront.controller;

import com.ctgpaas.webfront.dto.WebUser;
import com.ctgpaas.webfront.service.IWebUserService;
import com.github.pagehelper.PageInfo;
import com.ctgpaas.webfront.common.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebUserController {

    @Autowired
    private IWebUserService webUserService;

    @ApiOperation("查询用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用户名", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNum", value = "页码"),
        @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "每页记录数")
    })
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Response<PageInfo<WebUser>> query(@RequestParam String username,
                                             @RequestParam(required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageInfo<WebUser> pageInfo = webUserService.query(username, pageNum, pageSize);
        return Response.success(pageInfo);
    }

    @ApiOperation("新增用户")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public Response<Integer> save(@RequestBody WebUser user) {
        int i = webUserService.save(user);
        return Response.success(i);
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public Response<Integer> update(@RequestBody WebUser user) {
        int i = webUserService.update(user);
        return Response.success(i);
    }

    @ApiOperation("查询指定ID的用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Response<WebUser> selectById(@PathVariable Integer id) {
        WebUser user = webUserService.selectById(id);
        return Response.success(user);
    }

    @ApiOperation("删除指定ID的用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public Response<Integer> deleteById(@PathVariable Integer id) {
        int i = webUserService.deleteById(id);
        return Response.success(i);
    }

    @ApiOperation(value = "查询指定ID的用户", notes = "查询指定ID的用户]")
    @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "用户ID", required = true)
    @RequestMapping(value = "/simple_users/{id}", method = RequestMethod.GET)
    public WebUser selectUserById(@PathVariable Integer id) {
        return webUserService.selectUserById(id);
    }
}
