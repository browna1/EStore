package com.zzh.estore.mapper;

import com.zzh.estore.entity.District;

import java.util.List;

/**
 * @author ：zzh
 * @description ：省市区接口
 * @date ：Created in 2022/1/12 9:58
 */
public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 该区域下所有地区
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
