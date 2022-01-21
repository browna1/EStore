package com.zzh.estore.mapper;

import com.zzh.estore.entity.Address;
import com.zzh.estore.entity.District;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ：zzh
 * @description ：收货地址持久层接口
 * @date ：Created in 2022/1/11 15:24
 */
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户id统计收货地址数量
     * @param uid 用户id
     * @return 该用户收货地址数量
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户id查询用户的收货地址
     * @param uid 用户id
     * @return 该用户所有收货地址
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据地址id查询地址数据
     * @param aid 地址id
     * @return 此条id对应地址数据，找不到返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户id设置收货地址为非默认
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根据地址id将地址设为默认
     * @param aid 地址id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid( @Param("aid") Integer aid,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据地址id删除地址数据
     * @param aid 地址id
     * @return 受影响的行数
     */
    Integer deleteByAid( @Param("aid") Integer aid);

    /**
     * 根据用户id查询当前用户最后一次修改的地址数据
     * @param uid 用户id
     * @return 最后一次修改的地址数据
     */
    Address findLastModified(Integer uid);
}
