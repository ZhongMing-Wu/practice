package com.plateform.contorller.file;

import java.net.URLEncoder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plateform.service.implement.ExportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExcelDownLoadController {

    @Autowired
    private ExportServiceImpl exportService;

    @RequestMapping(value="/downUserExcel")
    @CrossOrigin
    public  void downUserExcel(HttpServletResponse response, HttpServletRequest request) throws Exception{
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("用户请求信息表.xls", "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try{
            ServletOutputStream out=response.getOutputStream();
            String[] titles ={ "问题编号", "提问用户编号", "问题提出时间","问题描述","问题分类","约束条件","提交状态",
            "平台受理人","受理时间","未受理原因","合作加盟商编号","合作合同金额","问题解决时间","平台对用户评价","平台对加盟商评价"};
            exportService.export(titles,out,1);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("导出信息失败");
        }
    }
    @RequestMapping(value="/downMemberExcel")
    @CrossOrigin
    public  void downMemberExcel(HttpServletResponse response, HttpServletRequest request) throws Exception{
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("加盟商信息表.xls", "UTF-8"));
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try{
            ServletOutputStream out=response.getOutputStream();
            String[] titles={"加盟商编号","加盟商名称","加盟商地址","加盟商联系方式（电话）",
                    "加盟商联系方式（邮箱）", "电子邮箱","联系人","联系人手机号","单位性质","所属行业",
                    "加盟时间","提供服务次数","成功提供服务次数","加盟协议地址"};
            exportService.export(titles,out,2);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("导出信息失败");
        }
    }
}
