package com.plateform.dao;

import com.plateform.entity.UserPicture;
import org.apache.ibatis.annotations.*;

public interface UserPictureMapper {
    @Insert("insert into spt_user_image(USER_NO, FILENAME, TYPE) values(#{userNo}, #{filename}, #{type})")
    int savePicture(UserPicture userPicture);

    @Select("select * from spt_user_image where USER_NO = #{userNo} and TYPE = #{type}")
    @Results(id = "imageinfor", value = {
            @Result(id = true, column = "ID", property = "id"),
            @Result(column = "USER_NO", property = "userNo"),
            @Result(column = "FILENAME", property = "filename"),
            @Result(column = "TYPE", property = "type")
    })
    UserPicture selectPicture(@Param("userNo") String memberNo, @Param("type") String picType);

    @Update("update spt_user_image set FILENAME = #{filename} where ID = #{id}")
    int updateinfor(UserPicture userPicture);

    @Delete("delete from spt_user_image where FILENAME = #{filename} and USER_NO = #{userNo}")
    int deleteInfor(UserPicture picture);
}
