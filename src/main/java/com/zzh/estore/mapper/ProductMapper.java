package com.zzh.estore.mapper;

import com.zzh.estore.entity.Product;

import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/14 12:46
 */
public interface ProductMapper {
    /**
     * 查询热销商品前四名
     * @return 热销商品前四名
     */
    List<Product> findHotList();

    Product findById(Integer id);
}
