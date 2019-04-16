package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mysql.CoreCustomerSuperEntity;
import com.pingan.angel.qctest.dao.CustomerSuperDao;
import com.pingan.angel.qctest.service.CustomerSuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerSuperService")
public class CustomerSuperServiceImpl implements CustomerSuperService {

    @Autowired
    private CustomerSuperDao customerSuperDao;

    @Override
    public String findIdByCustomerCode(String customerCode) {
        CoreCustomerSuperEntity entity = customerSuperDao.selectOne(Wrappers.<CoreCustomerSuperEntity>query().lambda().eq(CoreCustomerSuperEntity::getCustomerCode, customerCode));
        if (entity == null) {
            return null;
        }
        return entity.getUserId();
    }
}
