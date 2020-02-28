package com.plateform.contorller.file;

import com.plateform.entity.Hres;
import com.plateform.entity.Member;
import com.plateform.entity.Sres;
import com.plateform.entity.Tech;
import com.plateform.service.implement.HresServiceImpl;
import com.plateform.service.implement.MemberServiceImpl;
import com.plateform.service.implement.SresServiceImpl;
import com.plateform.service.implement.TechServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import static com.plateform.util.createDocx.makeWord;
import static com.plateform.util.DataMapUtil.*;

@Controller
public class FileDownloadController {
    @Autowired
    private TechServiceImpl techService;
    @Autowired
    private SresServiceImpl sresService;
    @Autowired
    private MemberServiceImpl memberService;
    @Autowired
    private HresServiceImpl hresService;
    //模板文件的下载
    @GetMapping("/download/TemplateFile")
    public void DownloadTemplateFile(HttpServletRequest request
            , HttpServletResponse response, String filename) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath(File.separator+"Files");

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename+".docx", "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        File zip = new File(realPath+File.separator+filename+".zip");
        FileInputStream inStream = new FileInputStream(zip);
        byte[] buf = new byte[4096];
        int readLength;
        while (((readLength=inStream.read(buf)) != -1)) {
            response.getOutputStream().write(buf, 0, readLength);
        }
    }

    //加盟商各种文件的导出
    @GetMapping("/export/File")
    public void DownloadDataFile(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam String filename, @RequestParam String memberNo) throws Exception {
        response.setContentType("application/msword");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename+".docx", "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        Map<String, Object> dataMap = new HashMap<>();
        System.out.println(filename);
        if(filename.equals("Tech")){
            Tech tech = techService.selectByMemberNo(memberNo).get(0);
            getTechInfor(tech, dataMap);
        }
        else if(filename.equals("Sres")) {
            Sres sres = sresService.selectByMemberNo(memberNo).get(0);
            getSresInfor(sres, dataMap);
        }
        else if(filename.equals("member")) {
            Member member = memberService.selectByMemberNo(memberNo).get(0);
            getMemberInfor(member, dataMap);
        }
        else if(filename.equals("Hres")) {
            Hres hres = hresService.selectByMemberNo(memberNo).get(0);
            getHresInfor(hres, dataMap);
        }
        //获取模板文件所在路径
        String realPath = request.getSession().getServletContext().getRealPath(File.separator+"Files");
        makeWord(realPath, filename, response, dataMap);
    }
}
