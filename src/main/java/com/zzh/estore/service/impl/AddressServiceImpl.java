package com.zzh.estore.service.impl;

import com.zzh.estore.entity.Address;
import com.zzh.estore.mapper.AddressMapper;
import com.zzh.estore.service.IAddressService;
import com.zzh.estore.service.IDistrictService;
import com.zzh.estore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：zzh
 * @description ：
 * @date ：Created in 2022/1/11 15:57
 */
// 新增收货地址的实现类
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired // 添加用户的收货地址的业务层依赖于districtService业务层接口
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount=20;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // 调用收货地址统计的方法
        Integer cnt = addressMapper.countByUid(uid);
        if (cnt >= maxCount) {
            throw new AddressCountLimitException("地址数量超出上限");
        }

        String ProvinceName = districtService.getNameByCode(address.getProvinceCode());
        String CityName = districtService.getNameByCode(address.getCityCode());
        String AreaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(ProvinceName);
        address.setCityName(CityName);
        address.setAreaName(AreaName);

        address.setUid(uid);
        Integer isDefault = cnt == 0 ? 1 : 0;
        address.setIsDefault(isDefault);

        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer row = addressMapper.insert(address);
        if (row != 1) {
            throw new InsertException("插入地址信息时产生未知异常");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list){
//            address.setAid(null);
//            address.setUid(null);
//            address.setProvinceCode(null);
//            address.setCityCode(null);
//            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        // 检测当前获取到的收货地址数据的归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        // 先将所有收货地址设为非默认
        Integer row = addressMapper.updateNonDefault(uid);
        if (row < 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
        row = addressMapper.updateDefaultByAid(aid,username,new Date());
        if (row != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException：非法访问
            throw new AccessDeniedException("非常访问");
        }

        // 根据参数aid，调用deleteByAid()执行删除
        Integer rows1 = addressMapper.deleteByAid(aid);
        if (rows1 != 1) {
            throw new DeleteException("删除收货地址数据时出现未知错误，请联系系统管理员");
        }
//        // 判断查询结果中的isDefault是否为0
//        if (result.getIsDefault() == 0) {
//            return;
//        }
        // 调用持久层的countByUid()统计目前还有多少收货地址
        Integer count = addressMapper.countByUid(uid);
        // 判断目前的收货地址的数量是否为0
        if (count == 0) {
            return;
        }

        // 调用findLastModified()找出用户最近修改的收货地址数据
        Address lastModified = addressMapper.findLastModified(uid);
        // 从以上查询结果中找出aid属性值
        Integer lastModifiedAid = lastModified.getAid();
        // 调用持久层的updateDefaultByAid()方法执行设置默认收货地址，并获取返回的受影响的行数
        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows2 != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("地址不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);

        return address;
    }

}
