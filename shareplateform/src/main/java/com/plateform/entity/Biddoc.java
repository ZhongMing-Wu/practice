package com.plateform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Biddoc {

    private Integer id;
    private String biddocNo;
    private String bidder;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date biddocTime;
    private String biddocContent;
    private String status;
    private String bidResult;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public String getBiddocNo() {
        return biddocNo;
    }
    public void setBiddocNo(String biddocNo) {
        this.biddocNo = biddocNo == null ? null : biddocNo.trim();
    }

    public String getBidder() {
        return bidder;
    }
    public void setBidder(String bidder) {
        this.bidder = bidder;
    }



    public Date getBiddocTime() {
        return biddocTime;
    }
    public void setBiddocTime(Date biddocTime) {
        this.biddocTime = biddocTime;
    }

    public String getBiddocContent() {
        return biddocContent;
    }
    public void setBiddocContent(String biddocContent) {
        this.biddocContent = biddocContent == null ? null : biddocContent.trim();
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }


    public String getBidResult() {
        return bidResult;
    }
    public void setBidResult(String bidResult) {
        this.bidResult = bidResult == null ? null : bidResult.trim();
    }
}