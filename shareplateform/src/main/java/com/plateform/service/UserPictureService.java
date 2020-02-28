package com.plateform.service;

import com.plateform.entity.UserPicture;

public interface UserPictureService {
    int savePicture(UserPicture userPicture);

    UserPicture selectPicture(String userNo, String picType);

    int updateinfor(UserPicture userPicture);

    int deleteInfor(UserPicture userPicture);
}
