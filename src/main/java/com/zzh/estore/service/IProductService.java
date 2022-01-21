package com.zzh.estore.service;

import com.zzh.estore.entity.Product;

import java.util.List;

/**
 * @author ：zzh
 * @description ：商品接口
 * @date ：Created in 2022/1/14 12:42
 */
public interface IProductService {
    List<Product> findHotList();

    Product findById(Integer id);
}
