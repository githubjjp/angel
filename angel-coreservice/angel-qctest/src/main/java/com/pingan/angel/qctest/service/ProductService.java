package com.pingan.angel.qctest.service;

import com.pingan.angel.admin.api.mysql.OrderProductEntity;

public interface ProductService {

    OrderProductEntity findByProductId(String proId);

    OrderProductEntity findbyCode(String proId);
}
