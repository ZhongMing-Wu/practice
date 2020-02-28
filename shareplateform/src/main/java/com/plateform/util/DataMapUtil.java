package com.plateform.util;

import com.plateform.entity.Hres;
import com.plateform.entity.Member;
import com.plateform.entity.Sres;
import com.plateform.entity.Tech;
import java.util.Map;

public class DataMapUtil {

    //将加盟商实体信息写入map中并返回
    public static void getMemberInfor(Member member, Map<String, Object> dataMap) {
        if(member.getMemberName() != null)
            dataMap.put("memberName", member.getMemberName());
        else
            dataMap.put("memberName", "");

        if(member.getMemberAbbr() != null)
            dataMap.put("memberAbbr", member.getMemberAbbr());
        else
            dataMap.put("memberAbbr", "");

        if(member.getMemberRegisterAddr() != null)
            dataMap.put("memberRegisterAddr", member.getMemberRegisterAddr());
        else
            dataMap.put("memberRegisterAddr", "");

        if(member.getMemberAddr() != null)
            dataMap.put("memberAddr", member.getMemberAddr());
        else
            dataMap.put("memberAddr", "");

        if(member.getCorporation() != null)
            dataMap.put("corporation", member.getCorporation());
        else
            dataMap.put("corporation", "");

        if(member.getTopManager() != null)
            dataMap.put("topManager", member.getTopManager());
        else
            dataMap.put("topManager", "");

        if(member.getContact() != null)
            dataMap.put("contact", member.getContact());
        else
            dataMap.put("contact", "");

        if(member.getContactInformation() != null)
            dataMap.put("telephone", member.getContactInformation());
        else
            dataMap.put("telephone", "");

        if(member.getUnitProperty() != null)
            dataMap.put("unitProperty", member.getUnitProperty());
        else
            dataMap.put("unitProperty", "");

        if(member.getIndustry() != null)
            dataMap.put("industry", member.getIndustry());
        else
            dataMap.put("industry", "");

        if(member.getBusinessArea() != null)
            dataMap.put("businessArea", member.getBusinessArea());
        else
            dataMap.put("businessArea", "");

        if(member.getBusinessExpire() != null)
            dataMap.put("time", member.getBusinessExpire());
        else
            dataMap.put("time", "");

        if(member.getCaptial() != null)
            dataMap.put("money", member.getCaptial());
        else
            dataMap.put("money", "");

        if(member.getStaffCount() != null)
            dataMap.put("staff", member.getStaffCount());
        else
            dataMap.put("staff", "");

        if(member.getProductValue() != null)
            dataMap.put("one", member.getProductValue());
        else
            dataMap.put("one", "");

        dataMap.put("two", "");
        dataMap.put("three", "");

        if(member.getRemark() != null)
            dataMap.put("remark", member.getRemark());
        else
            dataMap.put("remark", "");

        if(member.getResDesc() != null)
            dataMap.put("resDesc", member.getResDesc());
        else
            dataMap.put("resDesc", "");

        if(member.getAbilityDesc() != null)
            dataMap.put("abilityDesc", member.getAbilityDesc());
        else
            dataMap.put("abilityDesc", "");
    }

    //将加盟商硬资源信息写入map中并返回
    public static void getHresInfor(Hres hres, Map<String, Object> dataMap) {
        if(hres.getAbilityDescription() != null)
            dataMap.put("abilityDescription", hres.getAbilityDescription());
        else
            dataMap.put("abilityDescription", "");

        if(hres.getHumanResource() != null)
            dataMap.put("humanResource", hres.getHumanResource());
        else
            dataMap.put("humanResource", "");

        if(hres.getFacilityResource() != null)
            dataMap.put("facilityResource", hres.getFacilityResource());
        else
            dataMap.put("facilityResource", "");

        if(hres.getEnvResource() != null)
            dataMap.put("envResource", hres.getEnvResource());
        else
            dataMap.put("envResource", "");

        if(hres.getSoftResource() != null)
            dataMap.put("softResource", hres.getSoftResource());
        else
            dataMap.put("softResource", "");

        if(hres.getOtherResource() != null)
            dataMap.put("otherResource", hres.getOtherResource());
        else
            dataMap.put("otherResource", "");

        if(hres.getSharingCondition() != null)
            dataMap.put("sharingCondition", hres.getSharingCondition());
        else
            dataMap.put("sharingCondition", "");

        if(hres.getCostEstimate() != null)
            dataMap.put("costEstimate", hres.getCostEstimate());
        else
            dataMap.put("costEstimate", "");

        if(hres.getRemark() != null)
            dataMap.put("remark", hres.getRemark());
        else
            dataMap.put("remark", "");
    }

    //将加盟商软资源信息写入map中并返回
    public static void getSresInfor(Sres sres, Map<String, Object> dataMap) {
        if(sres.getAbilityDescription() != null)
            dataMap.put("abilityDescription", sres.getAbilityDescription());
        else
            dataMap.put("abilityDescription", "");

        if(sres.getResearchDirection() != null)
            dataMap.put("researchDirection", sres.getResearchDirection());
        else
            dataMap.put("researchDirection", "");

        if(sres.getTeamResourceDesc() != null)
            dataMap.put("teamResourceDesc", sres.getTeamResourceDesc());
        else
            dataMap.put("teamResourceDesc", "");

        if(sres.getTeamBelongTo() != null)
            dataMap.put("teamBelongTo", sres.getTeamBelongTo());
        else
            dataMap.put("teamBelongTo", "");

        if(sres.getAbilityBase() != null)
            dataMap.put("abilityBase", sres.getAbilityBase());
        else
            dataMap.put("abilityBase", "");

        if(sres.getSharingCondition() != null)
            dataMap.put("sharingCondition", sres.getSharingCondition());
        else
            dataMap.put("sharingCondition", "");

        if(sres.getCostEstimate() != null)
            dataMap.put("costEstimate", sres.getCostEstimate());
        else
            dataMap.put("costEstimate", "");

        if(sres.getRemark() != null)
            dataMap.put("remark", sres.getRemark());
        else
            dataMap.put("remark", "");
    }

    //将加盟商新技术信息写入map中并返回
    public static void getTechInfor(Tech tech, Map<String, Object> dataMap) {

        if(tech.getAbilityDescription() != null)
            dataMap.put("abilityDescription", tech.getAbilityDescription());
        else
            dataMap.put("abilityDescription", "");

        if(tech.getAbilityCategory() != null)
            dataMap.put("abilityCategory", tech.getAbilityCategory());
        else
            dataMap.put("abilityCategory", "");

        if(tech.getProDirection() != null)
            dataMap.put("proDirection", tech.getProDirection());
        else
            dataMap.put("proDirection", "");

        if(tech.getPrinciple() != null)
            dataMap.put("principle", tech.getPrinciple());
        else
            dataMap.put("principle", "");

        if(tech.getAchivement() != null)
            dataMap.put("achivement", tech.getAchivement());
        else
            dataMap.put("achivement", "");

        if(tech.getApplyingArea() != null)
            dataMap.put("applyingArea", tech.getApplyingArea());
        else
            dataMap.put("applyingArea", "");

        if(tech.getCoopIntention() != null)
            dataMap.put("coopIntention", tech.getCoopIntention());
        else
            dataMap.put("coopIntention", "");

        if(tech.getRestriction() != null)
            dataMap.put("restriction", tech.getRestriction());
        else
            dataMap.put("restriction", "");

        if(tech.getCostEstimate() != null)
            dataMap.put("costEstimate", tech.getCostEstimate());
        else
            dataMap.put("costEstimate", "");

        if(tech.getRemark() != null)
            dataMap.put("remark", tech.getRemark());
        else
            dataMap.put("remark", "");
    }
}
