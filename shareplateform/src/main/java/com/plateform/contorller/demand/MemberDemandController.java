package com.plateform.contorller.demand;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plateform.entity.Demand;
import com.plateform.entity.Order;


import com.plateform.entity.User;

import com.plateform.entity.classset.EntityWithStatus;
import com.plateform.service.implement.DemandServiceImpl;
import com.plateform.service.implement.OrderServiceImpl;
import com.plateform.service.implement.UserServiceImpl;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

@Controller
public class MemberDemandController {

    private DemandServiceImpl demandServiceImpl;
    private UserServiceImpl userServiceImpl;
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public void setDemandServiceImpl(DemandServiceImpl demandServiceImpl) {
        this.demandServiceImpl = demandServiceImpl;
    }

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    //1、加盟商查看order表状态为"待加盟商同意(5)或加盟商已同意(8)"的信息对应的用户需求
    @GetMapping("/checkUserInfor")
    public @ResponseBody
    ModelMap checkUserInfor(@RequestHeader String token, @RequestParam int pageNum) {

        //通过token验证身份,如果不是加盟商，返回null
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"Member"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;

        ModelMap modelMap;
        String memberNo = (String)permissionValidationMap.get("userId");
        //通过加盟商编号，找到对应的用户需求表,只有order表中用戶设置为同意，加盟商才能查看
        List<String> status = new ArrayList<>();
        status.add("5");
        status.add("8");
        PageHelper.startPage(pageNum, 10);
        List<Order> orders = orderServiceImpl.selectByMemberNoAndStatus(memberNo, status);

        PageInfo page = new PageInfo(orders);

        if(orders.isEmpty()) {
            modelMap = ModelMapUtil.init(0,null,"无任何信息");
            modelMap.addAttribute("totalPages", page.getPages());
        }
        else {
            List<Demand> demands = new ArrayList<>();
            for(int i = 0; i < orders.size(); i++) {
                demands.add(demandServiceImpl.selectByDemandNo(orders.get(i).getDemandNo()).get(0));
            }
            modelMap = ModelMapUtil.init(0,null,"查询成功");
            //返回总页数
            modelMap.addAttribute("totalPages", page.getPages());
            //返回查询信息
            modelMap.addAttribute("demands",demands);
        }
        return modelMap;
    }

    //2、 加盟商同意匹配用户
    @PutMapping("/query/agreeDemand")
    public @ResponseBody ModelMap agreeDemand (@RequestParam Integer id, @RequestHeader String token) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Member"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        //从数据库中查找需求，并确认身份
        String memberId = (String) permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"找不到该订单！");
        if (!memberId.equals(order.getMemberNo()))return ModelMapUtil.init(1,null,"该订单内容不可见！");
        //验证状态是否为"用户已同意"
        if (!order.getStatus().equals("5")) return ModelMapUtil.init(1,null,"该需求状态异常！");

        //加盟商同意则将status设置为"加盟商同意该匹配"
        order.setStatus("8");
        orderServiceImpl.updateByPrimaryKey(order);
//        if (!"审理中".equals(trans.getTransStatus())) (!memberId.equals(order.getMemberNo()))
        //将状态写入相关的demand条目中
        List<Demand> demands = demandServiceImpl.selectByDemandNo(order.getDemandNo());
        if (!demands.isEmpty()) {
            Demand demand = demands.get(0);
            demand.setStatus("8");
            demandServiceImpl.updateByPrimaryKey(demand);
        }
        return ModelMapUtil.init(0,null,"已同意！");
    }

    //3、加盟商拒绝匹配用户
    @PutMapping("/query/refuseDemand")
    public @ResponseBody ModelMap refuseDemand (@RequestParam Integer id, @RequestHeader String token) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Member"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        //从数据库中查找需求，并确认身份
        String memberId = (String) permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"找不到该订单！");
       if (!memberId.equals(order.getMemberNo())) return ModelMapUtil.init(1,null,"该订单内容不可见！");
        //验证状态是否为"用户已同意"
        if (!order.getStatus().equals("5")) return ModelMapUtil.init(1,null,"该需求状态异常！");
        //用户拒绝则将status设置为"匹配已被加盟商拒绝"
        order.setStatus("7");
        orderServiceImpl.updateByPrimaryKey(order);
        //将状态写入相关的demand条目中
        List<Demand> demands = demandServiceImpl.selectByDemandNo(order.getDemandNo());
        if (!demands.isEmpty()) {
            Demand demand = demands.get(0);
            demand.setStatus("7");
            demandServiceImpl.updateByPrimaryKey(demand);
        }
        return ModelMapUtil.init(0,null,"已拒绝！");
    }
    //4、加盟商查看其所有订单
    @GetMapping("/order/member/allQuery")
    public @ResponseBody ModelMap memberAllQuery (@RequestHeader String token , @RequestParam(required = false, defaultValue = "1") int pageNum){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Member"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;

        String memberID = (String) permissionValidationMap.get("userId");

        List<String> status = new ArrayList<>();
        status.add("5");
        status.add("7");
        status.add("8");

        PageHelper.startPage(pageNum,10);
        List<Order> orders = orderServiceImpl.selectByMemberNoAndStatus(memberID, status);
        ModelMap data= ModelMapUtil.init(0, null, "查询成功");
        PageInfo page = new PageInfo(orders);
        for(int i = 0; i < orders.size(); i++)
            setOrderStatus(orders.get(i));
        data.addAttribute("totalPages", page.getPages());
        data.addAttribute("orders", orders);
        return data;
    }
    //5、加盟商查看其一个订单
    @GetMapping("/order/member/oneQuery")
    public @ResponseBody ModelMap memberOneQuery(@RequestHeader String token, @RequestParam Integer id){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Member"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"找不到该订单！");
        if (!order.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"该订单内容不可见！");
        ModelMap data= ModelMapUtil.init(0, null, "查询成功");
        setOrderStatus(order);
        data.addAttribute("order", order);
        Demand demand = demandServiceImpl.selectByDemandNo(order.getDemandNo()).get(0);
        data.addAttribute("demand", demand);
        return data;
    }


    //针对加盟商，设置返回给前端的status内容
    private void setOrderStatus(EntityWithStatus order) {
        switch (order.getStatus()) {
            case "5": order.setStatus("待同意");break;
            case "7": order.setStatus("已拒绝"); break;
            case "8": order.setStatus("已同意"); break;
            default: order.setStatus("错误信息");
        }
    }
}
