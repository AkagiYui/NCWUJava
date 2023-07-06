package com.dzf.framework.mybatis.mapperAgency;

import com.akagiyui.mapper.UserMapper;
import org.junit.jupiter.api.Test;

/**
 * @author AkagiYui
 */

class DynamicAgencyFactoryTest {

    @Test
    void getInfMapper() {
        UserMapper goodDao = DynamicAgencyFactory.getInfMapper(UserMapper.class);
//        System.out.println(goodDao);
        goodDao.selectUser("202019901");
    }
}
