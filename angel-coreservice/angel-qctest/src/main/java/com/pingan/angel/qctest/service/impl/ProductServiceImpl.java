package com.pingan.angel.qctest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mysql.OrderProductEntity;
import com.pingan.angel.qctest.dao.ProductDao;
import com.pingan.angel.qctest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public OrderProductEntity findByProductId(String proId) {
        return productDao.selectById(proId);
    }

    @Override
    public OrderProductEntity findbyCode(String proId) {
        return productDao.selectOne(Wrappers.<OrderProductEntity>query().lambda().eq(OrderProductEntity::getCode,proId));
    }
}
