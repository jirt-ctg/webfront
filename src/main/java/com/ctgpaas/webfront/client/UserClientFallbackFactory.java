package com.ctgpaas.webfront.client;

import com.ctgpaas.webfront.common.Response;
import com.ctgpaas.webfront.dto.WebUser;
import com.github.pagehelper.PageInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserClientFallbackFactory implements FallbackFactory<IUserClient> {

    private static Logger logger = LoggerFactory.getLogger(UserClientFallbackFactory.class);

    @Override
    public IUserClient create(Throwable cause) {
        return new IUserClient() {
            @Override
            public Response<PageInfo<WebUser>> query(String username, int pageNum, int pageSize) {
                logger.error("UserClient fallback reason: {}", cause.getMessage());
                return Response.success(null);
            }

            @Override
            public Response<Integer> save(WebUser user) {
                return Response.success(0);
            }

            @Override
            public Response<Integer> update(WebUser user) {
                return Response.success(0);
            }

            @Override
            public Response<WebUser> selectById(Integer id) {
                return Response.success(null);
            }

            @Override
            public Response<Integer> deleteById(Integer id) {
                return Response.success(0);
            }

            @Override
            public WebUser selectUserById(Integer id) {
                return null;
            }
        };
    }
}
