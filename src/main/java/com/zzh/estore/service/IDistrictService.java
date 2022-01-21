package com.zzh.estore.service;

import com.zzh.estore.entity.District;

import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/12 10:14
 */

public interface IDistrictService {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 该父代号下所有地区
     */
    List<District> getByParent(String parent);

    /**
     * 通过代号获取地区名
     * @param code 代号
     * @return 地区名
     */
    String getNameByCode(String code);
}
