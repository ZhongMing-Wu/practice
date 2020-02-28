package com.plateform.contorller.demand;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plateform.entity.Demand;
import com.plateform.entity.Order;
import com.plateform.entity.classset.EntityWithStatus;
import com.plateform.service.implement.DemandServiceImpl;
import com.plateform.service.implement.OrderServiceImpl;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminDemandController {

    private DemandServiceImpl demandService;
    private OrderServiceImpl orderService;

    @Autowired
    public void setDemandService(DemandServiceImpl demandService) {
        this.demandService = demandService;
    }

    @Autowired
    public void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    //1、管理员查看用户需求
    @GetMapping(value = "/manage/dao/demand")
    public @ResponseBody
    ModelMap getManageDaoDemand(@RequestHeader String token, @RequestParam int pageNum) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        PageHelper.startPage(pageNum,10);
        List<Demand> list = demandService.selectAll();
        //状态码解码
        for (Demand i: list) this.setOrderStatus(i);
        ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
        //返回总页数
        PageInfo page = new PageInfo(list);
        data.addAttribute("totalPages", page.getPages());
        //返回查询信息
        data.addAttribute("data",page);
        return data;
    }


    //2、管理员审核用户需求
    @PutMapping(value = "/demand/admin/check")
    public @ResponseBody ModelMap putDemandCheck(
            @RequestHeader String token,
            @RequestParam Integer id,
            @RequestParam String adminAdvice,
            @RequestParam(required = false) String refuseReason
    ){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        //查询数据库
        Demand demand = demandService.selectByPrimaryKey(id);
        if (null == demand) return ModelMapUtil.init(1,null,"所查询的需求不存在！");
        List<Order> orders = orderService.selectByDemandNo(demand.getDemandNo());
        if (orders.isEmpty()) return ModelMapUtil.init(1,null,"所查询的需求的订单不存在！");
        Order order = orders.get(0);
        //判断当前需求所处的阶段是否为待审核
        if (!order.getStatus().equals("1")) return ModelMapUtil.init(1,null,"所查询的需求并不在审核状态！");
        //在表中修改状态以及加盟商编号项目
        if ("0".equals(adminAdvice)) {
            order.setStatus("2");    //设置为"已被管理员拒绝"
            order.setAdminOpinion(refuseReason);   //说明拒绝原因
        } else {
            order.setStatus("3");     //设置为"待匹配"
        }
        orderService.updateByDemandNo(order);  //更新数据库中的信息
        setOrderStatus(order);
        return ModelMapUtil.init(0,null,"修改成功！");
    }


    //3、管理员提交匹配结果接口
    @PutMapping(value = "/demand/admin/match")
    public @ResponseBody ModelMap putMatchResult(
            @RequestHeader String token,
            @RequestParam String memberNo,
            @RequestParam Integer id
    ){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        Demand result = demandService.selectByPrimaryKey(id);
        //根据result中的问题编号查找对应的匹配表
        Order orderResult = orderService.selectByDemandNo(result.getDemandNo()).get(0);
        //当查询结果为空时
        if (null == orderResult) return ModelMapUtil.init(1,null,"所查询的需求不存在！");
        //判断当前需求所处的阶段是否为"待审核",或者"匹配已被用户拒绝",或者"匹配已被加盟商拒绝"
        if (!orderResult.getStatus().equals("3")
                && !orderResult.getStatus().equals("6")
                && !orderResult.getStatus().equals("7")) return ModelMapUtil.init(1,null,"所查询的需求并不在匹配状态！");
        //在表中修改状态以及加盟商编号项目
        orderResult.setMemberNo(memberNo);
        orderResult.setStatus("4");  //设置为"等待用户同意"
        orderService.updateByDemandNo(orderResult);
        setOrderStatus(orderResult);
        return ModelMapUtil.init(0,null,"修改成功！");
    }

    //管理员查询一个用户的需求表信息
    @GetMapping("/manage/dao/oneDemand")
    public @ResponseBody
    ModelMap getManageDaoOneDemand(@RequestHeader String token,@RequestParam int id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        Demand demand = demandService.selectByPrimaryKey(id);
        ModelMap modelMap = ModelMapUtil.init(0,null,"查询成功");
        modelMap.addAttribute("demand",demand);
        return modelMap;
    }


    //管理员查看所有订单
    @GetMapping("/order/admin/query")
    public @ResponseBody ModelMap query( @RequestHeader String token,@RequestParam int pageNum) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Admin"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        PageHelper.startPage(pageNum,10);
        List<Order> order = orderService.selectAll();
        ModelMap modelMap= ModelMapUtil.init(0, null, "查询成功");
        PageInfo page = new PageInfo(order);
        for(int  i = 0; i < order.size() ; i++)
            setOrderStatus(order.get(i));
        modelMap.addAttribute("totalPages", page.getPages());
        modelMap.addAttribute("data", order);
        return modelMap;
    }



    //管理员查看一个订单
    @GetMapping("/admin/query/order")
    public @ResponseBody ModelMap adminQuery(@RequestHeader String token,@RequestParam Integer id){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        Order order = orderService.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"所要查询的订单不存在！");
        ModelMap data= ModelMapUtil.init(0, null, "查询成功");
        setOrderStatus(order);
        data.addAttribute("data", order);
        return data;
    }

    //管理员删除订单

    @PutMapping("/order/admin/delete")
    public @ResponseBody ModelMap delete( @RequestParam (required = false)Integer id , @RequestHeader String token){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        Order order = orderService.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"所要删除的订单不存在！");
        orderService.deleteByPrimaryKey(id);
        return ModelMapUtil.init(1,null,"已删除！");
    }

    //针对管理员，设置返回给前端的status内容
    private void setOrderStatus(EntityWithStatus order) {
        switch (order.getStatus()) {
            case "1": order.setStatus("待审核"); break;
            case "2": order.setStatus("已拒绝"); break;
            case "3": order.setStatus("待匹配"); break;
            case "4": order.setStatus("等待用户同意"); break;
            case "5": order.setStatus("等待加盟商同意"); break;
            case "6": order.setStatus("已被用户拒绝"); break;
            case "7": order.setStatus("已被加盟商拒绝"); break;
            case "8": order.setStatus("匹配成功"); break;
            default: order.setStatus("错误信息");
        }
    }

    //-------------------------------------------------------------------------------
    //---------------------------管理员需求接口（新）-----------------------------------
    //-------------------------------------------------------------------------------

    //1.管理员查看需求接口
    @GetMapping(value = "/manage/demand")
    public @ResponseBody
    ModelMap getManageDemand(
            @RequestHeader String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String demandNo,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与主键id所对应的事务信息
        //没传入参数时查询所有

        if (null != demandNo) {
            List<Demand> result = demandService.selectByDemandNo(demandNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有找到查询的需求");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            //状态码解码
            this.setOrderStatus(result.get(0));
            data.addAttribute("data", result);
            data.addAttribute("dataType", "Demand");
            return data;
        } else if (null != id) {
            Demand oneResult = demandService.selectByPrimaryKey(id);
            //当查询数据为空时
            if (null == oneResult) return ModelMapUtil.init(1, null, "没有找到查询的需求");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            List<Demand> list = new ArrayList<>();
            //状态码解码
            this.setOrderStatus(oneResult);
            list.add(oneResult);
            data.addAttribute("data",  list);
            data.addAttribute("dataType", "Demand");
            return data;
        } else {
            if (page) {
                List<Demand> result = demandService.selectAll(pageNum,pageSize);
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                PageInfo pageInfo = PageInfo.of(result);
                //状态码解码
                for (Object i: (pageInfo.getList())) this.setOrderStatus((Demand) i);
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",pageInfo);
                data.addAttribute("dataType", "DemandPage");
                return data;
            } else {
                List<Demand> result = demandService.selectAll();
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                //状态码解码
                for (Demand i: result) this.setOrderStatus(i);
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",result);
                data.addAttribute("dataType", "Demand");
                return data;
            }
        }
    }


    //2.管理员查看订单接口
    @GetMapping(value = "/manage/order")
    public @ResponseBody
    ModelMap getManageOrder(
            @RequestHeader String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String demandNo,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与主键id所对应的事务信息
        //没传入参数时查询所有
        if (null != demandNo) {
            List<Order> result = orderService.selectByDemandNo(demandNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有找到查询的订单");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            //状态码解码
            this.setOrderStatus(result.get(0));
            data.addAttribute("data", result);
            data.addAttribute("dataType", "Order");
            return data;
        } else if (null != id) {
            Order oneResult = orderService.selectByPrimaryKey(id);
            //当查询数据为空时
            if (null == oneResult) return ModelMapUtil.init(1, null, "没有找到查询的订单");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            List<Order> list = new ArrayList<>();
            //状态码解码
            this.setOrderStatus(oneResult);
            list.add(oneResult);
            data.addAttribute("data",  list);
            data.addAttribute("dataType", "Order");
            return data;
        } else {
            if (page) {
                List<Order> result = orderService.selectAll(pageNum,pageSize);
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                PageInfo pageInfo = PageInfo.of(result);
                //状态码解码
                for (Object i: (pageInfo.getList())) this.setOrderStatus((Order) i);
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",pageInfo);
                data.addAttribute("dataType", "OrderPage");
                return data;
            } else {
                List<Order> result = orderService.selectAll();
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                //状态码解码
                for (Order i: result) this.setOrderStatus(i);
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",result);
                data.addAttribute("dataType", "Order");
                return data;
            }
        }
    }



    //3.管理员通过用户需求接口
    @PostMapping(value = "/manage/demand/accept")
    public @ResponseBody
    ModelMap postManageTransAccept(
            @RequestHeader String token,
            @RequestParam Integer id
    ) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        //查询数据库
        Demand demand = demandService.selectByPrimaryKey(id);
        if (null == demand) return ModelMapUtil.init(1,null,"所查询的需求不存在！");
        if (!demand.getStatus().equals("1")) return ModelMapUtil.initError("所查询的需求并不在审核状态！");
        //修改订单
        List<Order> orders = orderService.selectByDemandNo(demand.getDemandNo());
        //当订单为空时生成订单
        if (orders.isEmpty()) {
            Order order = new Order();
            order.setHandler(((String) permissionValidationMap.get("userId")));
            order.setGeneratedTime(new Date());
            order.setUserNo(demand.getUserNo());
            order.setDemandNo(demand.getDemandNo());
            order.setStatus("3");
            if (0 == orderService.insert(order)) return ModelMapUtil.initError("插入订单时出错！");
        } else {
            Order order = orders.get(0);
            order.setHandler(((String) permissionValidationMap.get("userId")));
            order.setStatus("3");
            if (0 == orderService.updateByPrimaryKey(order)) return ModelMapUtil.initError("更新订单时出错！");
        }
        //更新需求表中的信息
        demand.setStatus("3");
        demandService.updateByPrimaryKey(demand);
        return ModelMapUtil.init(0,null,"同意成功！");
    }

    //4.管理员拒绝用户需求接口
    @PostMapping(value = "/manage/demand/refuse")
    public @ResponseBody
    ModelMap postManageTransAccept(
            @RequestHeader String token,
            @RequestParam Integer id,
            @RequestParam(required = false) String refuseReason
    ) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        //查询数据库
        Demand demand = demandService.selectByPrimaryKey(id);
        if (null == demand) return ModelMapUtil.init(1,null,"所查询的需求不存在！");
        if (!demand.getStatus().equals("1")) return ModelMapUtil.initError("所查询的需求并不在审核状态！");
        //修改订单
        List<Order> orders = orderService.selectByDemandNo(demand.getDemandNo());
        //当订单为空时生成订单
        if (orders.isEmpty()) {
            Order order = new Order();
            order.setHandler(((String) permissionValidationMap.get("userId")));
            order.setGeneratedTime(new Date());
            order.setUserNo(demand.getUserNo());
            order.setDemandNo(demand.getDemandNo());
            if (null != refuseReason) order.setAdminOpinion(refuseReason);
            order.setStatus("2");
            if (null != refuseReason) order.setAdminOpinion(refuseReason);
            if (0 == orderService.insert(order)) return ModelMapUtil.initError("插入订单时出错！");
        } else {
            Order order = orders.get(0);
            if (null != refuseReason) order.setAdminOpinion(refuseReason);
            order.setHandler(((String) permissionValidationMap.get("userId")));
            if (null != refuseReason) order.setAdminOpinion(refuseReason);
            order.setStatus("2");
            if (0 == orderService.updateByPrimaryKey(order)) return ModelMapUtil.initError("更新订单时出错！");
        }
        //更新需求表中的信息
        demand.setStatus("2");
        if (null != refuseReason) demand.setRemark(refuseReason);
        demandService.updateByPrimaryKey(demand);
        return ModelMapUtil.init(0,null,"拒绝成功！");
    }

    //5、管理员匹配需求接口
    @PostMapping(value = "/manage/demand/match")
    public @ResponseBody ModelMap putDemandMatch(
            @RequestHeader String token,
            @RequestParam String memberNo,
            @RequestParam(required = false) String abilityNo,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String demandNo
    ){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        //数据库查询
        Demand demand;
        Order order;
        List<Demand> demands;
        List<Order> orders;
        if (null != demandNo) {
            //当传入demandNo时按需求编号查询
            demands = demandService.selectByDemandNo(demandNo);
            if (demands.isEmpty()) return ModelMapUtil.init(1,null,"所要匹配的需求不存在！");
            demand = demands.get(0);
            orders = orderService.selectByDemandNo(demandNo);
            if (orders.isEmpty()) return ModelMapUtil.init(1,null,"所要匹配的订单不存在！");
            order = orders.get(0);
        } else if (null != id) {
            //当传入id时按需求主键查询
            demand = demandService.selectByPrimaryKey(id);
            if (null == demand) return ModelMapUtil.init(1,null,"所要匹配的需求不存在！");
            orders = orderService.selectByDemandNo(demand.getDemandNo());
            if (orders.isEmpty()) return ModelMapUtil.init(1,null,"所要匹配的订单不存在！");
            order = orders.get(0);
        } else return ModelMapUtil.init(400,null,"没有传入有效的查询参数！");
        //判断当前需求所处的阶段是否为"待审核",或者"匹配已被用户拒绝",或者"匹配已被加盟商拒绝"
        if (!order.getStatus().equals("3")
                && !order.getStatus().equals("6")
                && !order.getStatus().equals("7")) return ModelMapUtil.initError("所查询的需求并不在匹配状态！");
        //在表中修改状态以及加盟商编号项目
        order.setMemberNo(memberNo);
        order.setAbilityNo(abilityNo);
        order.setStatus("4");  //设置为"等待用户同意"
        orderService.updateByPrimaryKey(order);
        demand.setStatus("4");
        demandService.updateByPrimaryKey(demand);
        return ModelMapUtil.init(0,null,"修改成功！");
    }

}

