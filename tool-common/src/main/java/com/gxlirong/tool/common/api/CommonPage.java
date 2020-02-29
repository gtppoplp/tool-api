package com.gxlirong.tool.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页数据封装类
 *
 * @author lirong
 */
@Data
public class CommonPage<T> {
    //当前页
    private Long pageNum;
    //当前size
    private Long pageSize;
    //总共页数
    private Long totalPage;
    //总数
    private Long total;
    //列表数据
    private List<T> list;

    /**
     * 将IPage分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> iPage) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(iPage.getPages());
        result.setPageNum(iPage.getCurrent());
        result.setPageSize(iPage.getSize());
        result.setTotal(iPage.getTotal());
        result.setList(iPage.getRecords());
        return result;
    }
}
