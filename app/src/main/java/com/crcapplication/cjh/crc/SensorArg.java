package com.crcapplication.cjh.crc;

/**
 * Created by cjh on 2017-09-07.
 */

public class SensorArg {
    private String sensorName;
    private String sensorValue;
    private String nowTime;


    public void setSensorName(String sensorName){
        this.sensorName=sensorName;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }


}
