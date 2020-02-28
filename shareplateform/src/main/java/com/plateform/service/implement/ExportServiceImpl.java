package com.plateform.service.implement;

import com.plateform.entity.Demand;
import com.plateform.service.ExportService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private DemandServiceImpl demandService;

    @Autowired
    private MemberServiceImpl memberService;

    //将会员或加盟商信息写入Excel表中，并传送给前端
    public void export(String[] titles, ServletOutputStream out, int requestType){
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
            Date useDate;
            if(requestType==1){
                //导出的表是会员请求信息表
                List<Demand> list=demandService.selectAll();
                for (int i = 0; i < list.size(); i++) {
                    row = hssfSheet.createRow(i + 1);
                    Demand information = list.get(i);

                    //问题编号
                    String demandNo = information.getDemandNo();
                    if (demandNo != null)
                        row.createCell(0).setCellValue(demandNo);
                    //提问用户编号
                    String userNo = information.getUserNo();
                    if (userNo != null)
                        row.createCell(1).setCellValue((new Double(userNo)));
                    //问题提出时间
                    useDate = information.getDemandTime();
                    if (useDate != null)
                        row.createCell(2).setCellValue(formatter.format(useDate));

                    //问题描述
                    String demandDescription = information.getDemandDescription();
                    if (demandDescription != null)
                        row.createCell(3).setCellValue(demandDescription);
                    //问题分类
                    String demandCategory = information.getDemandCategory();
                    if (demandCategory != null)
                        row.createCell(4).setCellValue(demandCategory);
                    //约束条件
                    String demandRestriction = information.getRestriction();
                    if (demandRestriction != null)
                        row.createCell(5).setCellValue(demandRestriction);
                }
            }
            else{
                ////导出的表是加盟商信息表
                //List<Member> list=memberService.selectAll();
                //for(int i=0;i<list.size();i++){
                //    row = hssfSheet.createRow(i+1);
                //    Member information=list.get(i);
                //    //依次为
                //    //"加盟商编号","加盟商名称","加盟商地址","加盟商联系方式（电话）","加盟商联系方式（邮箱）",
                //    // "电子邮箱","联系人","联系人手机号","单位性质","所属行业",
                //    // "加盟时间","提供服务次数","成功提供服务次数","加盟协议地址"
                //    String memberNo=information.getMemberNo();
                //    if(memberNo!=null)
                //        row.createCell(0).setCellValue((new Double(memberNo)));
                //
                //    String memberName=information.getMemberName();
                //    if(memberName!=null)
                //        row.createCell(1).setCellValue(memberName);
                //
                //    String memberAddr=information.getMemberAddr();
                //    if(memberAddr!=null)
                //        row.createCell(2).setCellValue(memberAddr);
                //
                //    String memberPhone=information.getMemberPhone();
                //    if(memberPhone!=null)
                //        row.createCell(3).setCellValue((new Double(memberPhone)));
                //
                //    String memberMail=information.getMemberMail();
                //    if(memberMail!=null)
                //        row.createCell(4).setCellValue(memberMail);
                //
                //    String memberEmail=information.getMemberEmail();
                //    if(memberEmail!=null)
                //        row.createCell(5).setCellValue(memberEmail);
                //
                //    String contact=information.getContact();
                //    if(contact!=null)
                //        row.createCell(6).setCellValue(contact);
                //
                //    String contactPhone=information.getContactPhone();
                //    if(contactPhone!=null)
                //        row.createCell(7).setCellValue((new Double(contactPhone)));
                //
                //    String unitProperty=information.getUnitProperty();
                //    if(unitProperty!=null)
                //        row.createCell(8).setCellValue(unitProperty);
                //
                //    String industryType=information.getIndustry();
                //    if(industryType!=null)
                //        row.createCell(9).setCellValue(industryType);
                //
                //    useDate=information.getJoinTime();
                //   if(useDate!=null)
                //        row.createCell(10).setCellValue(formatter.format(useDate));
                //
                //   int serviceTime = information.getServiceTime();
                //   row.createCell(11).setCellValue((new Double(serviceTime)));
                //
                //   int successTime = information.getServiceSuccessTime();
                //   row.createCell(12).setCellValue((new Double(successTime)));
                //
                //    String urlAddr=information.getProtocolUrl();
                //    if(urlAddr!=null)
                //        row.createCell(13).setCellValue(urlAddr);
                //}
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(out);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
