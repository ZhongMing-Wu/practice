package com.plateform.service.implement;

import com.plateform.dao.MemberPictureMapper;
import com.plateform.entity.MemberPicture;
import com.plateform.service.MemberPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberPictureImpl implements MemberPictureService {

    @Autowired
    private MemberPictureMapper memberPictureMapper;

    @Override
    public int savePicture(MemberPicture memberPicture) {
        return memberPictureMapper.SavePicture(memberPicture);
    }

    @Override
    public MemberPicture selectPicture(String memberNo, String picType) {
        return memberPictureMapper.selectPicture(memberNo, picType);
    }

    @Override
    public int updateinfor(MemberPicture memberPicture) {
        return memberPictureMapper.updateinfor(memberPicture);
    }

    @Override
    public int deleteInfor(MemberPicture memberPicture) {
        return memberPictureMapper.deleteInfor(memberPicture);
    }

    public List<MemberPicture> selectByMemberNo(String memberNo) {
        return memberPictureMapper.selectByMemberNo(memberNo);
    }
}
