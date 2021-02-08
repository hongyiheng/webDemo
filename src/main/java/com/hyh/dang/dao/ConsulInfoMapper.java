package com.hyh.dang.dao;

import com.hyh.dang.entity.ConsulInfo;
import com.hyh.dang.entity.ConsulInfoExample;
import com.hyh.dang.entity.ConsulInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsulInfoMapper {
    long countByExample(ConsulInfoExample example);

    int deleteByExample(ConsulInfoExample example);

    int deleteByPrimaryKey(ConsulInfoKey key);

    int insert(ConsulInfo record);

    int insertSelective(ConsulInfo record);

    List<ConsulInfo> selectByExample(ConsulInfoExample example);

    ConsulInfo selectByPrimaryKey(ConsulInfoKey key);

    int updateByExampleSelective(@Param("record") ConsulInfo record, @Param("example") ConsulInfoExample example);

    int updateByExample(@Param("record") ConsulInfo record, @Param("example") ConsulInfoExample example);

    int updateByPrimaryKeySelective(ConsulInfo record);

    int updateByPrimaryKey(ConsulInfo record);
}