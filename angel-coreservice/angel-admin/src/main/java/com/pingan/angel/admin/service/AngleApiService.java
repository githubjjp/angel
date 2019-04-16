package com.pingan.angel.admin.service;

import com.pingan.angel.common.core.util.Result;

public interface AngleApiService {

    /**
     * 整机码和配件码关联
     *
     * @param snCode
     * @param barcodeId
     * @param activeId
     * @return
     */
    public Result snCodeUnion(String snCode, String barcodeId, String activeId);
}
