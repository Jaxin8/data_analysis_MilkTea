package com.dark.api.constant;

public class StatusConst {

    //项目状态
    public static final Byte PENDING=0;  //待审核

    public static final Byte UNPASSED=1;  //未通过

    public static final Byte WAITING_ENGINEER=2;  //待分配工程师

    public static final Byte WAITING_VAILD=3;  //待工程师确认状态

    public static final Byte ONGOING=4;  //进行中

    public static final Byte END =5;  //结束


    //支付状态
    public static final Byte WATING_PAY =0;  //待支付

    public static final Byte PAYED =1;  //已支付



    //用户账号状态
    public static final Byte INACTIVE_STATUS=0;  //冻结

    public static final Byte ACTIVE_STATUS=1;    //已激活

    public static final String INACTIVE_STATUS_NAME="inactive";

    public static final String ACTIVE_STATUS_NAME="active";


    //工程师状态
    public static final Byte DEFAULT_LEVEL=3;
    public static final Byte DELETE_STATUS=2;

}
