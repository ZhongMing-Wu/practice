package com.plateform.contorller.file;

import com.plateform.entity.MemberPicture;
import com.plateform.entity.UserPicture;
import com.plateform.service.implement.MemberPictureImpl;
import com.plateform.service.implement.TempMemberPictureImpl;
import com.plateform.service.implement.UserPictureImpl;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PictureController {

    @Autowired
    private TempMemberPictureImpl tempMemberPictureImpl;

    @Autowired
    private MemberPictureImpl memberPictureImpl;

    @Autowired
    private UserPictureImpl userPictureImpl;


    //加盟商上传图片
    @CrossOrigin
    @RequestMapping(value = "/uploadPicture",method = POST)
    public @ResponseBody ModelMap uploadPicture(HttpServletRequest request
            , MultipartFile imageFile, @RequestHeader String token, @RequestParam String picType) throws  Exception {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember", "Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        String realPath = request.getSession().getServletContext().getRealPath(File.separator+"images"+File.separator+"member");
        String filename=imageFile.getOriginalFilename();
        filename= UUID.randomUUID().toString().replace("-","")+"_"+filename;
        imageFile.transferTo(new File(realPath,filename));

        //获取该加盟商的memberNo
        String memberNo = (String)permissionValidationMap.get("userId");
        MemberPicture memberPicture = new MemberPicture();
        memberPicture.setMemberNo(memberNo);
        memberPicture.setFilename(filename);
        //1表示传送过来的是身份证正面，2表示传送过来的是身份证反面，3表示传送过来的是营业执照
        memberPicture.setType(picType);

        MemberPicture tempMemberPicture = tempMemberPictureImpl.selectPicture(memberNo, picType);
        int returnVal;
        if(tempMemberPicture != null) {
            String deleteFilename = tempMemberPicture.getFilename();
            File deleteFile = new File(realPath + File.separator + deleteFilename);
            deleteFile.delete();
            memberPicture.setId(tempMemberPicture.getId());
            returnVal = tempMemberPictureImpl.updateinfor(memberPicture);
        }
        else {
            returnVal = tempMemberPictureImpl.savePicture(memberPicture);
        }
        if(returnVal != 0)
            return ModelMapUtil.init(0, null, "上传成功");
        else
            return ModelMapUtil.init(0, null, "上传失败");
    }

    //网页显示加盟商/用户的后端图片(目前未设置权限)
    @CrossOrigin
    @GetMapping("/displayPicture")
    public @ResponseBody String displayPicture(HttpServletRequest request
            , HttpServletResponse response, @RequestParam String number
            , @RequestParam String picType, @RequestParam String type) throws Exception{
        response.setContentType("image/jpeg/jpg/png/gif/bmp/tiff/svg"); // 设置返回内容格式
        //获取存储图片的目录的路径
        String realPath ;
        if(type.equals("1")) {
            realPath = request.getSession().getServletContext().getRealPath(File.separator + "images" + File.separator + "member");
            MemberPicture memberPicture = memberPictureImpl.selectPicture(number, picType);
            String filename;
            if(memberPicture != null)
                filename = memberPicture.getFilename();
            else
                return "memberPicture is empty";
            realPath = realPath+File.separator+filename;
        }
        else {
            realPath = request.getSession().getServletContext().getRealPath(File.separator + "images" + File.separator + "user");
            UserPicture userPicture = userPictureImpl.selectPicture(number, picType);
            String filename;
            if(userPicture != null)
                filename = userPicture.getFilename();
            else
                return "userPicture is empty";
            realPath = realPath+File.separator+filename;
        }
        File file = new File(realPath);

        FileInputStream fis = new FileInputStream(file);
        long size = file.length();
        byte[] temp = new byte[(int) size];
        fis.read(temp, 0, (int) size);
        fis.close();
        byte[] data = temp;
        return new Base64().encodeToString(data);
    }

    //加盟商/用户图片下载到本地
    @CrossOrigin
    @GetMapping("/downloadPicture")
    public void downloadPicture(HttpServletRequest request
            , HttpServletResponse response, @RequestParam String number
            , @RequestParam String picType, @RequestParam String type) throws  Exception{
        response.setContentType("image/jpeg/jpg/png/gif/bmp/tiff/svg");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("picture.jpg", "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        String realPath;
        if(type.equals("1")) {
            MemberPicture memberPicture = memberPictureImpl.selectPicture(number, picType);
            realPath = request.getSession().getServletContext().getRealPath(File.separator + "images" + File.separator + "member");
            realPath = realPath + File.separator + memberPicture.getFilename();
        }
        else {
            UserPicture userPicture = userPictureImpl.selectPicture(number, picType);
            realPath = request.getSession().getServletContext().getRealPath(File.separator + "images" + File.separator + "user");
            realPath = realPath + File.separator + userPicture.getFilename();
        }
        File file = new File(realPath);
        FileInputStream inStream = new FileInputStream(file);
        byte[] buf = new byte[4096];
        int readLength;
        while (((readLength=inStream.read(buf)) != -1)) {
            response.getOutputStream().write(buf, 0, readLength);
        }
    }

    //用户上传图片
    @CrossOrigin
    @RequestMapping(value = "/uploadUserPicture",method = POST)
    public @ResponseBody ModelMap uploadUserPicture(HttpServletRequest request
            , MultipartFile imageFile, @RequestHeader String token, @RequestParam String picType) throws  Exception {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"User","tempUser"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        String realPath = request.getSession().getServletContext().getRealPath(File.separator+"images"+File.separator+"user");
        String filename=imageFile.getOriginalFilename();
        filename= UUID.randomUUID().toString().replace("-","")+"_"+filename;
        imageFile.transferTo(new File(realPath,filename));

        //获取该用户的userNo
        String userNo = (String)permissionValidationMap.get("userId");
        UserPicture userPicture = new UserPicture();
        userPicture.setUserNo(userNo);
        userPicture.setFilename(filename);
        //1表示传送过来的是身份证正面，2表示传送过来的是身份证反面
        userPicture.setType(picType);

        UserPicture tempPicture = userPictureImpl.selectPicture(userNo, picType);
        int returnVal;
        if(tempPicture != null) {
            String deleteFilename = tempPicture.getFilename();
            File deleteFile = new File(realPath + File.separator + deleteFilename);
            deleteFile.delete();
            userPicture.setId(tempPicture.getId());
            returnVal = userPictureImpl.updateinfor(userPicture);
        }
        else {
            returnVal = userPictureImpl.savePicture(userPicture);
        }
        if(returnVal != 0)
            return ModelMapUtil.init(0, null, "上传成功");
        else
            return ModelMapUtil.init(0, null, "上传失败");
    }


}
