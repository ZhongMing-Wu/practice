package com.plateform.contorller.file;

import com.plateform.entity.Demand;
import com.plateform.service.implement.DemandServiceImpl;
import com.plateform.service.implement.MemberServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelFileUploadController {
    @Autowired
    DemandServiceImpl demandService;

    @Autowired
    MemberServiceImpl memberService;

    //进行会员请求信息的上传
    @RequestMapping("/userInformationUpload")
    @CrossOrigin
    public @ResponseBody String userInformationUpload(MultipartFile uploadUserFile) throws Exception{
            if(uploadUserFile!=null){
                POIFSFileSystem fs=new POIFSFileSystem(uploadUserFile.getInputStream());
                //得到文档对象
                HSSFWorkbook workbook=new HSSFWorkbook(fs);
                //得到第一个表单
                HSSFSheet sheet=workbook.getSheetAt(0);
                if(sheet==null)
                    return "ExcelTable is empty";
                //得到最后一行的行号
                int lastRow=sheet.getLastRowNum();
                HSSFCell cell;
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
                List<Demand> list=new ArrayList();
                String useString;
                for(int i=1;i<=lastRow;i++){
                    Demand demand=new Demand();
                    HSSFRow row=sheet.getRow(i);
                    //设置问题编号
                    cell=  row.getCell(0);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        demand.setDemandNo(cell.getStringCellValue());
                    }

                    //设置提问用户编号
                    cell=  row.getCell(1);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        demand.setUserNo(String.valueOf(cell.getNumericCellValue()));
                    }

                    //设置问题提出时间
                    cell=  row.getCell(2);
                    if(cell!=null){
                        //如果表格类型是字符串，先转换成Date类型
                        if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
                            useString=cell.getStringCellValue();
                            if(useString.length()==10&&useString.charAt(4)=='/'&&useString.charAt(7)=='/')
                                demand.setDemandTime(formatter.parse(useString));
                            else
                                return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        }
                        else
                            demand.setDemandTime(cell.getDateCellValue());
                    }
                    else{
                        //如果表中未设置时间，则默认为当前时间
                        demand.setDemandTime(new Date());
                    }
                    //设置问题描述
                    cell=  row.getCell(3);
                    if(cell!=null) {
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        demand.setDemandDescription(cell.getStringCellValue().trim());
                    }

                    //设置问题分类
                    cell=row.getCell(4);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        demand.setDemandCategory(cell.getStringCellValue().trim());
                    }

                    //设置约束条件
                    cell=row.getCell(5);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        demand.setRestriction(cell.getStringCellValue().trim());
                    }
                    list.add(demand);
                }
                demandService.deleteAll();
                demandService.alterId();
                for(int i=0;i<list.size();i++)
                    demandService.insert(list.get(i));
                return "success";
            }
            else
                return "Excel表格为空";
    }

   /* //进行加盟商信息的上传
    @RequestMapping("/memberInformationUpload")
    @CrossOrigin
    public @ResponseBody String memberInformationUpload(MultipartFile uploadMemberFile) throws Exception{
            if(uploadMemberFile!=null) {
                POIFSFileSystem fs = new POIFSFileSystem(uploadMemberFile.getInputStream());
                //得到文档对象
                HSSFWorkbook workbook = new HSSFWorkbook(fs);
                //得到第一个表单
                HSSFSheet sheet = workbook.getSheetAt(0);
                if (sheet == null)
                    return "ExcelTable is empty";
                //得到最后一行的行号
                int lastRow = sheet.getLastRowNum();
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
                List<Member> list=new ArrayList();
                String useString;
                HSSFCell cell;
                for (int i = 1; i <= lastRow; i++) {
                    Member member = new Member();
                    HSSFRow row = sheet.getRow(i);

                    //通过useDouble和useString将Excel中的double数据转换成String
                    //设置会员编号
                    cell = row.getCell(0);
                    if(cell!=null){
                        //判断用户输入的内容是否正确
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setMemberNo(String.valueOf(cell.getNumericCellValue()));
                    }
                    //设置会员名称
                    cell = row.getCell(1);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setMemberName(cell.getStringCellValue().trim());
                    }

                    //设置会员地址
                    cell = row.getCell(2);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setMemberAddr(cell.getStringCellValue().trim());
                    }

                    //设置会员联系方式（电话）
                    cell = row.getCell(3);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setMemberPhone(String.valueOf(cell.getNumericCellValue()));
                    }
                    //设置会员联系方式（邮箱）
                    cell = row.getCell(4);
                   if(cell!=null){
                       if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                           return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                       member.setMemberMail(cell.getStringCellValue().trim());
                   }

                    //设置会员联系方式（电子邮箱）
                    cell = row.getCell(5);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setMemberEmail(cell.getStringCellValue().trim());
                    }

                    //设置联系人
                    cell = row.getCell(6);
                   if(cell!=null){
                       if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                           return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                       member.setContact(cell.getStringCellValue().trim());
                   }

                    //设置联系人联系方式（手机号）
                    cell = row.getCell(7);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setContactPhone(String.valueOf(cell.getNumericCellValue()));
                    }

                    //设置单位性质
                    cell = row.getCell(8);
                   if(cell!=null){
                       if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                           return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                       member.setUnitProperty(cell.getStringCellValue().trim());
                   }

                    //设置所属行业
                    cell = row.getCell(9);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setIndustry(cell.getStringCellValue().trim());
                    }

                    //设置加盟时间
                    cell = row.getCell(10);
                    if(cell!=null){
                        //如果表格类型是字符串，先转换成Date类型
                        if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
                            useString=cell.getStringCellValue();
                            if(useString.length()==10&&useString.charAt(4)=='/'&&useString.charAt(7)=='/')
                                member.setJoinTime(formatter.parse(useString));
                            else
                                return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        }
                        else
                            member.setJoinTime(cell.getDateCellValue());
                    }
                    else{
                        //如果表中未设置时间，则默认为当前时间
                        member.setJoinTime(new Date());
                    }
                    //设置平台提供服务次数
                    cell = row.getCell(11);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setServiceTime((new Double(cell.getNumericCellValue())).intValue());
                    }
                    else{
                        member.setServiceTime(0);
                    }

                    //设置服务成功次数
                    cell = row.getCell(12);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setServiceSuccessTime((new Double(cell.getNumericCellValue())).intValue());
                    }
                    else{
                        member.setServiceSuccessTime(0);
                    }


                    //设置加盟协议地址
                    cell = row.getCell(13);
                    if(cell!=null){
                        if(cell.getCellType()!=HSSFCell.CELL_TYPE_STRING)
                            return "Excel "+String.valueOf(i)+" cow,"+String.valueOf(cell.getColumnIndex())+" col "+"is wrong";
                        member.setProtocolUrl(cell.getStringCellValue().trim());
                    }

                    list.add(member);
                }
                memberService.deleteAll();
                memberService.alterId();
                for(int i=0;i<list.size();i++)
                    memberService.insert(list.get(i));
                return "success";
            }
            else{
                return "Excel表为空";
            }
        }*/
}
