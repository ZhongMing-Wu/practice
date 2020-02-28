package com.plateform.dao;

import com.plateform.entity.Biddoc;
import java.util.List;

public interface BiddocMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Biddoc record);
    Biddoc selectByPrimaryKey(Integer id);
    List<Biddoc> selectAll();
    int updateByPrimaryKey(Biddoc record);

    List<Biddoc> selectByString(Biddoc record);
}