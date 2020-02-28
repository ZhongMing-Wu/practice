package com.plateform.contorller.demand;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plateform.entity.*;
import com.plateform.entity.classset.EntityWithStatus;
import com.plateform.service.implement.*;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static com.plateform.util.createRandomString.getRandomString;

@Controller
public class UserDemandController {


    private DemandServiceImpl demandServiceImpl;
    private HresServiceImpl hresServiceImpl;
    private SresServiceImpl sresServiceImpl;
    private TechServiceImpl techServiceImpl;
    private MemberServiceImpl memberServiceImpl;
    private  OrderServiceImpl orderServiceImpl;


    @Autowired
    public void setDemandServiceImpl(DemandServiceImpl demandServiceImpl) {
        this.demandServiceImpl = demandServiceImpl;
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
    public void setMemberServiceImpl(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @Autowired
    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
    //1、用户向需求表中插入新的需求
    @PostMapping("/insertUserDemandInfor")
    public @ResponseBody
    ModelMap insertUserDemandInfor(@RequestBody Demand demand, @RequestHeader String token){
        //通过token验证身份
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"User"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;
        //设置提交需求时间，默认为当前时间
        demand.setDemandTime(new Date());
        //通过token中的用户编号，设置用户编号
        demand.setUserNo((String)permissionValidationMap.get("userId"));
        //设置问题编号，问题编号自动生成，随机字符串+用户编号
        String randomStr=getRandomString(5);
        demand.setDemandNo(randomStr+demand.getUserNo());
        demand.setStatus("1");

        //插入数据库中
        int returnVal=demandServiceImpl.insertUserDemandInfor(demand);
        //表明插入成功
        if(returnVal==1){
            //在order表中插入一条该用户的订单信息
            Order order = new Order();
            //设置订单生成时间，默认当前时间
            order.setGeneratedTime(new Date());
            //通过token中的用户编号，设置用户编号
            order.setUserNo(demand.getUserNo());
            //设置问题编号
            order.setDemandNo(demand.getDemandNo());
            //设置状态为"待审核"
            order.setStatus("1");
            returnVal = orderServiceImpl.insertUserOrderInfor(order);
            if(returnVal == 1)
                return ModelMapUtil.init(0,200,"插入成功");
            else
                return ModelMapUtil.init(0,200,"插入失败");
        }
        //表明插入失败
        else{
            return ModelMapUtil.init(0,200,"插入失败");
        }
    }

    //2、用户向需求表中更新原有的需求
    @PostMapping("/updateUserDemandInfor")
    public @ResponseBody ModelMap updateUserDemandInfor(@RequestBody Demand demand, @RequestHeader String token){
        //通过token验证身份
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"User"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;
        //设置提交需求时间，默认为当前时间
        demand.setDemandTime(new Date());
        demand.setStatus("1");
        //更新数据库需求表中的数据
        int returnVal = demandServiceImpl.updateByDemandNo(demand);
        if(returnVal==1) {  //用户需求表明更新成功
            //查出对应的原来的订单信息
            Order oldOrder = orderServiceImpl.selectByDemandNo(demand.getDemandNo()).get(0);
            //在order表中更新一条该用户的订单信息
            Order order = new Order();
            //设置主键
            order.setId(oldOrder.getId());
            //设置订单生成时间，默认当前时间
            order.setGeneratedTime(new Date());
            //通过token中的用户编号，设置用户编号
            order.setUserNo(demand.getUserNo());
            //设置问题编号
            order.setDemandNo(demand.getDemandNo());
            //设置状态为"待审核"
            order.setStatus("1");
            returnVal = orderServiceImpl.updateByDemandNo(order);
            if(returnVal == 1)
                return ModelMapUtil.init(0,200,"更新成功");
            else
                return ModelMapUtil.init(0,200,"更新失败");
        }
        else {   //表明更新失败
            return ModelMapUtil.init(0,200,"更新失败");
        }
    }

    //3、匹配时，用户查看加盟商的所有信息
    @GetMapping("/queryMemberInfor")
    public @ResponseBody ModelMap queryMemberInfor (@RequestHeader String token,@RequestParam Integer id){
        //通过token验证（用户）
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"User"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;
        //通过订单表中的主键id找到相匹配的加盟商的编号
        String userNo = (String)permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"查找不到该订单！");
        if (!order.getUserNo().equals(userNo)) return ModelMapUtil.init(1,null,"该订单内容不可见！");
        String memberNo = order.getMemberNo();
        if (0 == memberNo.length()) return ModelMapUtil.init(1,null,"该需求没有加盟商！");
        ModelMap modelMap = ModelMapUtil.init(0,null,"查询成功！");
        List<Member> member = memberServiceImpl.selectByMemberNo(memberNo);
        List<Hres> hres = hresServiceImpl.selectByMemberNo(memberNo);
        List<Sres> sres = sresServiceImpl.selectByMemberNo(memberNo);
        List<Tech> tech = techServiceImpl.selectByMemberNo(memberNo);
        modelMap.addAttribute("member",member);
        modelMap.addAttribute("hres",hres);
        modelMap.addAttribute("sres",sres);
        modelMap.addAttribute("tech",tech);
        return modelMap;
    }

    //4、用户同意匹配
    @PutMapping("/queryMemberInfor/agreeDemand")
    public @ResponseBody ModelMap agreeDemand (@RequestParam Integer id, @RequestHeader String token) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"User"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        //从数据库中查找需求，并确认身份
        String userId = (String) permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"找不到该订单！");
        //查询相应的需求
        List<Demand> demands = demandServiceImpl.selectByDemandNo(order.getDemandNo());
        if (demands.isEmpty()) ModelMapUtil.initError("没有查询到订单对应的需求！");
        Demand demand = demands.get(0);
        if (!order.getUserNo().equals(userId)) return ModelMapUtil.init(1,null,"该订单内容不可见！");
        //验证状态是否为"等待用户同意"
        if (!order.getStatus().equals("4")) return ModelMapUtil.init(1,null,"该需求状态异常！");
        //用户同意则将status设置为"等待加盟商同意"
        order.setStatus("5");
        orderServiceImpl.updateByPrimaryKey(order);
        //同步设置状态
        demand.setStatus("5");
        if (0 == demandServiceImpl.updateByPrimaryKey(demand)) return ModelMapUtil.initError("更新需求时出错！");
        return ModelMapUtil.init(0,null,"已同意！");
    }

    //5、用户拒绝匹配
    @PutMapping("/queryMemberInfor/refuseDemand")
    public @ResponseBody ModelMap refuseDemand (@RequestParam Integer id, @RequestHeader String token) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"User"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        //从数据库中查找需求，并确认身份
        String userId = (String) permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order) return ModelMapUtil.init(1,null,"找不到该订单！");
        if (!order.getUserNo().equals(userId)) return ModelMapUtil.init(1,null,"该订单内容不可见！");
        //验证状态是否为"等待用户同意"
        if (!order.getStatus().equals("4")) return ModelMapUtil.init(1,null,"该需求状态异常！");
        //用户拒绝则将status设置为"匹配已被用户拒绝"
        order.setStatus("6");
        orderServiceImpl.updateByPrimaryKey(order);
        //将状态写入相关的demand条目中
        List<Demand> demands = demandServiceImpl.selectByDemandNo(order.getDemandNo());
        if (!demands.isEmpty()) {
            Demand demand = demands.get(0);
            demand.setStatus("6");
            demandServiceImpl.updateByPrimaryKey(demand);
        }
        return ModelMapUtil.init(0,null,"已拒绝！");
    }

    //6、用户查看自己的需求
    @GetMapping("/queryUserdemand")
    public @ResponseBody ModelMap queryUserdemand(@RequestHeader String token, @RequestParam(required = false,defaultValue = "1") int pageNum){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"User"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        //确认身份
        String userId = (String) permissionValidationMap.get("userId");
        List<Demand> list = demandServiceImpl.selectByUserNo(userId);
        if (list.isEmpty())
            return ModelMapUtil.init(1,null,"该用户没有需求！");
        ModelMap data;
        PageHelper.startPage(pageNum, 10);
        PageInfo page = new PageInfo(list);

        if (list.isEmpty()) {
             data = ModelMapUtil.init(1, null, "该用户没有需求！");
             data.addAttribute("totalPages", page.getPages());
             return data;
        }
        data = ModelMapUtil.init(0,null,"查询成功！");
        //返回总页数
        data.addAttribute("totalPages", page.getPages());
        //返回查询信息
        data.addAttribute("data",list);
        return data;
    }

    //7、用户查看其所有订单
    @GetMapping("/order/user/allQuery")
    public @ResponseBody ModelMap userAllQuery(@RequestHeader String token, @RequestParam(required = false,defaultValue = "1") int pageNum){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"User"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        String userId = (String) permissionValidationMap.get("userId");
        PageHelper.startPage(pageNum,10);
        List<Order> list = orderServiceImpl.selectByUserNo(userId);
        if (0 == list.size())
            return ModelMapUtil.init(1,null,"找不到该订单！");
        ModelMap data= ModelMapUtil.init(0, null, "查询成功");
        PageInfo page = new PageInfo(list);

        for(int i = 0; i< list.size(); i++)
            setOrderStatus(list.get(i));
        data.addAttribute("totalPages", page.getPages());
        data.addAttribute("data", list);
        return data;
    }

    //8、用户查看其一个订单
    @GetMapping("/order/user/oneQuery")
    public @ResponseBody ModelMap userOneQuery(@RequestHeader String token, @RequestParam Integer id){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"User"});
        if (permissionValidationMap instanceof ModelMap) return (ModelMap) permissionValidationMap;
        String userId = (String) permissionValidationMap.get("userId");
        Order order = orderServiceImpl.selectByPrimaryKey(id);
        if (null == order)
            return ModelMapUtil.init(1,null,"找不到该订单！");
        if (!order.getUserNo().equals(userId)) return ModelMapUtil.init(1,null,"该订单内容不可见！");
        setOrderStatus(order);
        ModelMap data= ModelMapUtil.init(0, null, "查询成功");
        data.addAttribute("data", order);
        return data;
    }


    //针对用户，设置返回给前端的status内容
    public  void setOrderStatus(EntityWithStatus order) {
        switch (order.getStatus()) {
            case "1": order.setStatus("待审核"); break;
            case "2": order.setStatus("已被管理员拒绝"); break;
            case "3": order.setStatus("待匹配"); break;
            case "4": order.setStatus("已匹配加盟商"); break;
            case "5": order.setStatus("等待加盟商同意");break;
            case "6": order.setStatus("待审核"); break;
            case "7": order.setStatus("加盟商拒绝该匹配"); break;
            case "8": order.setStatus("加盟商接受该匹配"); break;
            default: order.setStatus("错误信息");
        }
    }

}
