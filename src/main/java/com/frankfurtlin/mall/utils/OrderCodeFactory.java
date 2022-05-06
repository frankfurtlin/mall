package com.frankfurtlin.mall.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/6 17:53
 * 订单号生成工厂类
 */
public class OrderCodeFactory {
    /**
     * 获取16位订单号
     * @return 订单号
     */
    public String getOrderCode(){
        // 开头两位整数，标识机器
        String machineId = "11";

        // 中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());

        int code = Math.abs(UUID.randomUUID().toString().hashCode());

        return machineId + dayTime + String.format("%010d", code);
    }
}
