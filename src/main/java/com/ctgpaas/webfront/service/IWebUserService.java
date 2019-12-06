package com.ctgpaas.webfront.service;

import com.ctgpaas.webfront.dto.WebUser;
import com.github.pagehelper.PageInfo;

public interface IWebUserService {

    PageInfo<WebUser> query(String username, int pageNum, int pageSize);

    WebUser selectById(Integer id);

    int save(WebUser user);

    int update(WebUser user);

    int deleteById(Integer id);

    WebUser selectUserById(Integer id);
}
