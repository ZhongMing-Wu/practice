package com.plateform.dao;

import com.plateform.entity.Advice;
import java.util.List;

public interface AdviceMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Advice record);
    Advice selectByPrimaryKey(Integer id);
    List<Advice> selectAll();
    int updateByPrimaryKey(Advice record);

    List<Advice> selectByString(Advice record);

}