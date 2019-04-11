package com.pingan.angel.admin.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    // 传递的参数或是配置的参数
    private int currentPage; // 当前页
    private int pageSize; // 每页显示多少条记录

    // 查询数据库
    private List<T> recordList;// 本页的数据列表
    private int recordCount; // 总记录数

    // 计算
    private int pageCount; // 总页数
    private int beginPageIndex; // 页码列表的开始索引（包含）
    private int endPageIndex; // 页码列表的结束索引（包含）

    public PageResult(int currentPage, int pageSize, List recordList, int recordCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordList = recordList;
        this.recordCount = recordCount;
        pageCount = (recordCount + pageSize - 1) / pageSize;// 计算 pageCount
        // 计算 beginPageIndex 与 endPageIndex
        if (pageCount <= 10) {// >> 总页码小于等于10页时，全部显示
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }else {// >> 总页码大于10页时，就只显示当前页附近的共10个页码// 默认显示 前4页 + 当前页 + 后5页
            beginPageIndex = currentPage - 4; // 7 - 4 = 3;
            endPageIndex = currentPage + 5; // 7 + 5 = 12; --> 3 ~ 12
            if (beginPageIndex < 1) {// 如果前面不足4个页码时，则显示前10页
                beginPageIndex = 1;
                endPageIndex = 10;
            }else if (endPageIndex > pageCount) { // 如果后面不足5个页码时，则显示后10页
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 9;
            }
        }
    }
}
