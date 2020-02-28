package com.plateform.contorller.register;

import com.plateform.entity.*;
import com.plateform.service.implement.*;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminCheckController {


    private TransServiceImpl transServiceImpl;
    private TempMemberServiceImpl tempMemberServiceImpl;
    private TempHresServiceImpl tempHresServiceImpl;
    private TempSresServiceImpl tempSresServiceImpl;
    private TempTechServiceImpl tempTechServiceImpl;
    private MemberServiceImpl memberServiceImpl;
    private HresServiceImpl hresServiceImpl;
    private SresServiceImpl sresServiceImpl;
    private TechServiceImpl techServiceImpl;
    private LoginServiceImpl loginService;
    private TempMemberPictureImpl tempMemberPictureImpl;
    private MemberPictureImpl memberPictureImpl;

    @Autowired
    public void setTransServiceImpl(TransServiceImpl transServiceImpl) {
        this.transServiceImpl = transServiceImpl;
    }

    @Autowired
    public void setTempMemberServiceImpl(TempMemberServiceImpl tempMemberServiceImpl) {
        this.tempMemberServiceImpl = tempMemberServiceImpl;
    }

    @Autowired
    public void setTempHresServiceImpl(TempHresServiceImpl tempHresServiceImpl) {
        this.tempHresServiceImpl = tempHresServiceImpl;
    }

    @Autowired
    public void setTempSresServiceImpl(TempSresServiceImpl tempSresServiceImpl) {
        this.tempSresServiceImpl = tempSresServiceImpl;
    }

    @Autowired
    public void setTempTechServiceImpl(TempTechServiceImpl tempTechServiceImpl) {
        this.tempTechServiceImpl = tempTechServiceImpl;
    }

    @Autowired
    public void setMemberServiceImpl(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @Autowired
    public void setHresServiceImpl(HresServiceImpl hresServiceImpl) {
        this.hresServiceImpl = hresServiceImpl;
    }

    @Autowired
    public void setSresServiceImpl(SresServiceImpl sresServiceImpl) {
        this.sresServiceImpl = sresServiceImpl;
    }

    @Autowired
    public void setTechServiceImpl(TechServiceImpl techServiceImpl) {
        this.techServiceImpl = techServiceImpl;
    }

    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setTempSavePicture(TempMemberPictureImpl tempMemberPictureImpl) {
        this.tempMemberPictureImpl = tempMemberPictureImpl;
    }

    @Autowired
    public void setTempSavePicture(MemberPictureImpl memberPictureImpl) {
        this.memberPictureImpl = memberPictureImpl;
    }
    //1、管理员对申请加盟商进行审核
    @PostMapping("/setMemberApplyingStatus")
    public @ResponseBody
    ModelMap setMemberApplyingStatus(HttpServletRequest request, @RequestParam String memberNo, @RequestHeader String token, @RequestParam String adminAdvice, @RequestParam(required = false) String refuseReason) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;

        int returnVal;
        String realPath = request.getSession().getServletContext().getRealPath(File.separator+"images"+File.separator+"member");
        //adminAdvice为0表示拒绝,将事务表中的状态信息设置为未通过
        //adminAdvice为1表示同意，将事务表中的状态信息设置为已通过
        if(adminAdvice.equals("0")) {
            //returnVal=transServiceImpl.updateMemberStatusByMemberNo(memberNo,"未通过");
            List<Trans> listResult = transServiceImpl.selectByTransObjid(memberNo);
            if (listResult.isEmpty()) return ModelMapUtil.init(1,null,"不存在该事务");
            Trans result = listResult.get(0);
            result.setTransStatus("未通过");
            result.setRemark(refuseReason);
            transServiceImpl.updateByPrimaryKey(result);
            returnVal = 1;
        }
        else {
            returnVal=transServiceImpl.updateMemberStatusByMemberNo(memberNo,"已通过");
            List<Login> result = loginService.selectByLoginuser(memberNo);
            if (result.isEmpty())  return ModelMapUtil.init(1,null,"login中不存在该用户");
            Login newLogin = result.get(0);
            newLogin.setRole("Member");
            loginService.updateByPrimaryKey(newLogin);

            //将加盟商临时表中的信息全部更新到正式表中
            List<Member> tempMembers = tempMemberServiceImpl.selectByMemberNo(memberNo);
            List<Hres> tempHreses = tempHresServiceImpl.selectByMemberNo(memberNo);
            List<Sres> tempSreses = tempSresServiceImpl.selectByMemberNo(memberNo);
            List<Tech> tempTeches = tempTechServiceImpl.selectByMemberNo(memberNo);
            List<MemberPicture> tempMemberPicture = new ArrayList<>();
            for(int i = 1; i <= 3; i++) {
                if(tempMemberPictureImpl.selectPicture(memberNo, String.valueOf(i)) != null) {
                    tempMemberPicture.add(tempMemberPictureImpl.selectPicture(memberNo, String.valueOf(i)));
                }
            }
            List<Member> member = memberServiceImpl.selectByMemberNo(memberNo);
            if(member.size() == 0) {            //正式表中无该加盟商信息，则插入该加盟商信息
                if(!tempMembers.isEmpty())
                    memberServiceImpl.insert(tempMembers.get(0));
                for(int i = 0; i < tempHreses.size(); i++)
                    hresServiceImpl.insert(tempHreses.get(i));
                for(int i = 0; i < tempSreses.size(); i++)
                    sresServiceImpl.insert(tempSreses.get(i));
                for(int i = 0; i < tempTeches.size(); i++)
                    techServiceImpl.insert(tempTeches.get(i));
                for(int i = 0; i < tempMemberPicture.size(); i++){
                    memberPictureImpl.savePicture(tempMemberPicture.get(i));
                }
            }
            else {        //正式表中有该加盟商的信息，直接更新即可
                if(!tempMembers.isEmpty())
                    memberServiceImpl.updateByPrimaryKey(tempMembers.get(0));
                for(int i = 0; i < tempHreses.size(); i++) {
                    if(hresServiceImpl.selectByPrimaryKey(tempHreses.get(i).getId()) != null)
                        hresServiceImpl.updateByPrimaryKey(tempHreses.get(i));
                    else
                        hresServiceImpl.insert(tempHreses.get(i));
                }
                for(int i = 0; i < tempSreses.size(); i++){
                    if(sresServiceImpl.selectByPrimaryKey(tempSreses.get(i).getId()) != null)
                        sresServiceImpl.updateByPrimaryKey(tempSreses.get(i));
                    else
                        sresServiceImpl.insert(tempSreses.get(i));
                }
                for(int i = 0; i < tempTeches.size(); i++){
                    if(techServiceImpl.selectByPrimaryKey(tempTeches.get(i).getId()) != null)
                        techServiceImpl.updateByPrimaryKey(tempTeches.get(i));
                    else
                        techServiceImpl.insert(tempTeches.get(i));
                }
                for(int i = 0; i < tempMemberPicture.size(); i++) {
                    if(memberPictureImpl.selectPicture(tempMemberPicture.get(i).getMemberNo(), tempMemberPicture.get(i).getType()) != null) {
                        MemberPicture oldMemberPicture = memberPictureImpl.selectPicture(tempMemberPicture.get(i).getMemberNo(), tempMemberPicture.get(i).getType());
                        String deleteFilename = oldMemberPicture.getFilename();
                        File deleteFile = new File(realPath + File.separator + deleteFilename);
                        deleteFile.delete();
                        oldMemberPicture.setFilename(tempMemberPicture.get(i).getFilename());
                        memberPictureImpl.updateinfor(oldMemberPicture);
                    }
                    else {
                        memberPictureImpl.savePicture(tempMemberPicture.get(i));
                    }
                }
            }

            //删除临时表中该加盟商信息
            if(!tempMembers.isEmpty())
                tempMemberServiceImpl.deleteByPrimaryKey(tempMembers.get(0).getId());
            for(int i = 0; i < tempHreses.size(); i++)
                tempHresServiceImpl.deleteByPrimaryKey(tempHreses.get(i).getId());
            for(int i = 0; i < tempSreses.size(); i++)
                tempSresServiceImpl.deleteByPrimaryKey(tempSreses.get(i).getId());
            for(int i = 0; i < tempTeches.size(); i++)
                tempTechServiceImpl.deleteByPrimaryKey(tempTeches.get(i).getId());
            for(int i = 0; i < tempMemberPicture.size(); i++)
                tempMemberPictureImpl.deleteInfor(tempMemberPicture.get(i));
        }
        ModelMap modelMap=new ModelMap();
        if(returnVal == 1) {     //表明更新成功
            modelMap.addAttribute("code",0);
            modelMap.addAttribute("status",200);
            modelMap.addAttribute("msg","更新事务表成功");
        }
        else {     //表明更新失败失败
            modelMap.addAttribute("code",1);
            modelMap.addAttribute("status",200);
            modelMap.addAttribute("msg","更新事务表失败");
        }
        return modelMap;
    }
}
