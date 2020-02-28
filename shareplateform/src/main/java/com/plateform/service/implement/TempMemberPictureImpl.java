package com.plateform.service.implement;

import com.plateform.dao.TempMemberPictureMapper;
import com.plateform.entity.MemberPicture;
import com.plateform.service.TempMemberPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempMemberPictureImpl implements TempMemberPictureService {

    @Autowired
    private TempMemberPictureMapper TempMemberPictureMapper;

    @Override
    public int savePicture(MemberPicture memberPicture) {
        return TempMemberPictureMapper.SavePicture(memberPicture);
    }

    @Override
    public MemberPicture selectPicture(String memberNo, String picType) {
        return TempMemberPictureMapper.selectPicture(memberNo, picType);
    }

    @Override
    public int updateinfor(MemberPicture memberPicture) {
        return TempMemberPictureMapper.updateinfor(memberPicture);
    }

    @Override
    public int deleteInfor(MemberPicture memberPicture) {
        return TempMemberPictureMapper.deleteInfor(memberPicture);
    }

    public List<MemberPicture> selectByMemberNo(String memberNo) {
        return TempMemberPictureMapper.selectByMemberNo(memberNo);
    }
}
