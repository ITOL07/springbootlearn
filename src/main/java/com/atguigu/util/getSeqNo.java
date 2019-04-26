package com.atguigu.util;

import com.atguigu.entity.User;
import com.atguigu.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 获取主键:返回17位时间戳+3位递增数(同一时间递增)
 */

public class getSeqNo {
    private static int addPart = 1;
    private static String result = "";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private static String lastDate = "";
    /**
     * 获取主键
     * @param length 长度
     * @return 返回8位时间戳+3位递增数
     */
    public synchronized static String getId(int length,int startIndex,int type) {
        //获取时间部分字符串
        Date now = new Date();
        String nowStr = sdf.format(now);

        System.out.println("nowStr===="+nowStr+"--------startIndex="+startIndex);
        //获取数字后缀值部分
        if (getSeqNo.lastDate.equals(nowStr)) {
            startIndex += 1;
            System.out.println("1111111111");
        } else {
            startIndex += 1;
            lastDate = nowStr;
            System.out.println("222222222222");
        }

        //CD201904200001
        if (length > 8) {
            result = nowStr+new DecimalFormat("0000").format(startIndex);
        } else {
            result = nowStr;
        }
        System.out.println("nowStr===="+nowStr+"--------startIndex="+startIndex);

        String prefix="";
        switch (type){
            case 1: prefix="HY"; break;
            case 2: prefix="JL"; break;
            case 3: prefix="CD"; break;
            case 4: prefix="KC"; break;
        }
        return prefix+result;
    }

    /**
     * 获取当天最大的id
     */

    public static String getDate8(Date date){
        return sdf.format(date);
    }

}
