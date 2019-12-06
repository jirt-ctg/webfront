package com.ctgpaas.webfront.client;

import com.ctgpaas.webfront.common.Response;
import com.ctgpaas.webfront.dto.WebUser;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

//下面FeignClient注解中value值需要填写微服务平台上创建的springboot+mybatis服务的名称
@FeignClient(value = "backend-demo", fallbackFactory = UserClientFallbackFactory.class, url="${backend-demo.url:}")
public interface IUserClient {

    @GetMapping("/users")
    Response<PageInfo<WebUser>> query(@RequestParam(name = "username") String username,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize);

    @PostMapping("/users")
    Response<Integer> save(@RequestBody WebUser user);

    @PutMapping("/users")
    Response<Integer> update(@RequestBody WebUser user);

    @GetMapping("/users/{id}")
    Response<WebUser> selectById(@PathVariable(name = "id") Integer id);

    @DeleteMapping("/users/{id}")
    Response<Integer> deleteById(@PathVariable(name = "id") Integer id);

    @GetMapping("/simple_users/{id}")
    WebUser selectUserById(@PathVariable(name = "id") Integer id);
}
