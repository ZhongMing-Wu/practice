package com.plateform.service;

import com.plateform.entity.MemberPicture;

public interface TempMemberPictureService {
    int savePicture(MemberPicture memberPicture);

    MemberPicture selectPicture(String memberNo, String picType);

    int updateinfor(MemberPicture memberPicture);

    int deleteInfor(MemberPicture memberPicture);
}
