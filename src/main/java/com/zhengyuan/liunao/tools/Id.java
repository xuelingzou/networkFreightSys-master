package com.zhengyuan.liunao.tools;

// 此类用于登录时设置全局用ID，供后端调用，查询数据库
public class Id {
    static String ceid;   // 用户id
    static String coid;   // 货运公司id

    public static String getCeid() {
        return ceid;
    }

    public static void setCeid(String ceid) {
        Id.ceid = ceid;
    }

    public static String getCoid() {
        return coid;
    }

    public static void setCoid(String coid) {
        Id.coid = coid;
    }
}