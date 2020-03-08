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
    private Integer pageNum;
    //当前size
    private Integer pageSize;
    //总共页数
    private Integer totalPage;
    //总数
    private Integer total;
    //列表数据
    private List<T> list;

    /**
     * 将IPage分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> iPage) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(((Long) iPage.getPages()).intValue());
        result.setPageNum(((Long) iPage.getCurrent()).intValue());
        result.setPageSize(((Long) iPage.getSize()).intValue());
        result.setTotal(((Long) iPage.getTotal()).intValue());
        result.setList(iPage.getRecords());
        return result;
    }
}
