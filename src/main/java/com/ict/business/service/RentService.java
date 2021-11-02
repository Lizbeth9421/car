package com.ict.business.service;

import com.ict.business.domain.Rent;
import com.ict.system.util.DataGridView;

/**
 * @Author: Lizbeth9421
 * @Date: 2021/08/09/16:56
 */
public interface RentService {


    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);

    /**
     * 用于查询所有出租单信息
     * 包含分页和模糊查询
     */
    DataGridView queryAllRent(Rent rent);
}
