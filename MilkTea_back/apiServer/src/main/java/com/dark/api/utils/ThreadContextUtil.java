package com.dark.api.utils;

public class ThreadContextUtil {

    //作用:就是给当前线程绑定数据的
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
//    private static ThreadLocal<String> threadLocal_Name = new ThreadLocal<>();

    public static void setData(Long id){
        threadLocal.set(id);
//        threadLocal_Name.set(name);
    }

    public static Long getData(){
        return threadLocal.get();
    }


    public static void removeData(){
        threadLocal.remove();
    }


}
