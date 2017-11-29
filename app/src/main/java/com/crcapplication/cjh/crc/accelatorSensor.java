package com.crcapplication.cjh.crc;

import android.util.Log;

import static java.lang.Math.abs;

/**
 * Created by cjh on 2017-10-09.
 */

public class accelatorSensor {
    private int step;
    private double svm;
    private boolean highFlag;
    private boolean lowFlag;
    private double highThreshold;
    private double lowThreshold;
    private int sectionCount;
    private double min;
    private double max;
    private double sectionMin;
    private double sectionMax;
    private String nowTime;
    private String name;

    double g[];

    public int getStep(){
        return step;
    }

    //private int valueCount;
    //min, max는 sqlite에 저장, 호출 필요

    accelatorSensor(){
        name="step";
        step=0;
        highFlag=false;
        lowFlag=false;
        highThreshold=100;
        lowThreshold=80;
        sectionCount=0;
        min=80;
        max=100;
        sectionMin=9999;
        sectionMax=0;
        g =  new double[3];


    }
    public double getSvm(){
        return svm;
    }

    public void setAxis(String string){
        boolean wasMinu=false;
        boolean isMinu=false;
        boolean isG=false;
        int aToI=0;

        int index=0;
        for(int i=7;i<string.length();i++) {
            if (isG) {
                if (string.charAt(i) == '-' && !wasMinu)
                    wasMinu = true;
                else if (wasMinu) {
                    if (string.charAt(i) == '-') {
                        isMinu = true;
                    }
                    else if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
                        aToI = aToI * 10 + string.charAt(i) - '0';
                    }
                    else if (string.charAt(i) == 'g' || i==string.length()-1  ) {
                        g[index] = aToI;
                        if (isMinu)
                            g[index] *= -1;
                        g[index] *= 0.01;

                        index++;
                        wasMinu = false;
                        isMinu = false;
                        aToI = 0;
                        isG = true;
                    }
                }
            }
            else{
                if(string.charAt(i)=='g')
                    isG=true;
            }
        }
    }

	public void calculateStep(String string) {

        setAxis(string);
        svm=0;
        Log.d("LOWFLAG!"," "+ lowFlag);
        Log.d("highFLAG!"," "+ highFlag);

        for(int i=0;i<3;i++) {
            svm += abs(g[i]);
            g[i]=0;
        }
        Log.d("SVM!!!"," "+ svm);

        //if (valueCount < countThreshold) {
        if (svm < sectionMin)
            sectionMin = svm;
        if (svm > sectionMax)
            sectionMax = svm;


        sectionCount++;
        //valueCount++;
        //}
        //else {

        if (highFlag) {
            if (svm < lowThreshold) {
                lowFlag = true;
                Log.d("highFLAG is On"," "+ highFlag+"SVM : "+svm);
            }

        }
        else {
            if (svm > highThreshold) {
                highFlag = true;
                Log.d("lowFlag is On!"," "+ lowFlag+"SVM : "+ svm);
            }
        }

        if (highFlag && lowFlag) {


            //임계값 조절
            lowThreshold = ((min*sectionCount) + sectionMin) / (sectionCount + 1);
            highThreshold = ((max*sectionCount) + sectionMax) / (sectionCount + 1);

            Log.d("IS STEPP++","");
            step++;
            highFlag = false;
            lowFlag = false;
            sectionMin = 9999;
            sectionMax = 0;
            sectionCount = 0;


            Log.d("HithThreshold : ",""+highThreshold);
            Log.d("LowThreshold : ",""+lowThreshold);
        }
    }
    // min,max 를 DB에 저장, sectionCount도 저장
    //}
}
