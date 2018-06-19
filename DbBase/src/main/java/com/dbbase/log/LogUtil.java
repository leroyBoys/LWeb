package com.dbbase.log;

/**
 * Created by leroy:656515489@qq.com
 * 2018/6/19.
 */
public class LogUtil {
    public static String getException(Exception e) {
        StringBuilder bs = new StringBuilder();
        StackTraceElement[] a = e.getStackTrace();
        bs.append("\n Message: ").append(e.fillInStackTrace()).append("");
        for (int i = 0; i < a.length; i++) {
            bs.append("\n ").append(a[i].getClassName()).append("(Line:").append(a[i].getLineNumber()).append(",Method:").append(a[i].getMethodName()).append(")");
        }
        return bs.toString();
    }

    public static void log(Class cls,String info){
        System.out.println(info);
    }

    public static void warn(Class cls,String info){
        System.out.println(info);
    }

    public static void error(Class cls,String info){
        System.err.println(info);
    }

    public static void error(Class cls,String info,Exception e){
        e.printStackTrace();
        System.out.println(info);
    }
}
