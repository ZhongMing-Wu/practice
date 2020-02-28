package com.plateform.dao;

import com.plateform.entity.MemberPicture;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MemberPictureMapper {

    @Insert("insert into spt_member_image(ID, MEMBER_NO, FILENAME, TYPE) values(#{id}, #{memberNo}, #{filename}, #{type})")
    int SavePicture(MemberPicture memberPicture);

    @Select("select * from spt_member_image where MEMBER_NO = #{memberNo} and TYPE = #{type}")
    @Results(id = "imageinfor", value = {
            @Result(id = true, column = "ID", property = "id"),
            @Result(column = "MEMBER_NO", property = "memberNo"),
            @Result(column = "FILENAME", property = "filename"),
            @Result(column = "TYPE", property = "type")
    })
    MemberPicture selectPicture(@Param("memberNo") String memberNo, @Param("type") String picType);

    @Update("update spt_member_image set FILENAME = #{filename} where ID = #{id}")
    int updateinfor(MemberPicture memberPicture);

    @Delete("delete from spt_member_image where FILENAME = #{filename} and MEMBER_NO = #{memberNo}")
    int deleteInfor(MemberPicture memberPicture);

    @Select("select * from spt_member_image where MEMBER_NO = #{memberNo}")
    List<MemberPicture> selectByMemberNo(@Param("memberNo") String memberNo);
}
