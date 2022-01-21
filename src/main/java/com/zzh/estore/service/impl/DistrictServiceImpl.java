package com.zzh.estore.service.impl;

import com.zzh.estore.entity.District;
import com.zzh.estore.mapper.DistrictMapper;
import com.zzh.estore.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/12 10:15
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;


    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        // 在进行网络数据传输时，将无效数据设置为null，节省流量提高效率
        for (District d : list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
