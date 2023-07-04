package com.dzf.service;

import com.dzf.framework.mvc.annotation.bean.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AkagiYui
 */
@Service
@Slf4j
public class UserService {

    public void getUser() {
        log.debug("getUser");
    }
}
