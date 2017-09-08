package com.crcapplication.cjh.crc;

/**
 * Created by cjh on 2017-09-07.
 */

public class SensorArg {
    private String seonsorName;
    private String seonsorValue;
    private String nowTime;

    public void setSeonsorName(String seonsorName){
        this.seonsorName=seonsorName;
    }

    public String getSeonsorValue() {
        return seonsorValue;
    }

    public String getSeonsorName() {
        return seonsorName;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setSeonsorValue(String seonsorValue) {
        this.seonsorValue = seonsorValue;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }
}
