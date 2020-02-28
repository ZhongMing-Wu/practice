package com.plateform.service.implement;

import com.plateform.dao.UserPictureMapper;
import com.plateform.entity.UserPicture;
import com.plateform.service.UserPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPictureImpl implements UserPictureService {

    @Autowired
    private UserPictureMapper userPictureMapper;

    @Override
    public int savePicture(UserPicture userPicture) {
        return userPictureMapper.savePicture(userPicture);
    }

    @Override
    public UserPicture selectPicture(String userNo, String picType) {
        return userPictureMapper.selectPicture(userNo, picType);
    }

    @Override
    public int updateinfor(UserPicture userPicture) {
        return userPictureMapper.updateinfor(userPicture);
    }

    @Override
    public int deleteInfor(UserPicture userPicture) {
        return userPictureMapper.deleteInfor(userPicture);
    }
}
