package com.hyh.dang.dao;

import com.hyh.dang.entity.Jijin;
import com.hyh.dang.entity.JijinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JijinMapper {
    long countByExample(JijinExample example);

    int deleteByExample(JijinExample example);

    int insert(Jijin record);

    int insertSelective(Jijin record);

    List<Jijin> selectByExample(JijinExample example);

    int updateByExampleSelective(@Param("record") Jijin record, @Param("example") JijinExample example);

    int updateByExample(@Param("record") Jijin record, @Param("example") JijinExample example);
}