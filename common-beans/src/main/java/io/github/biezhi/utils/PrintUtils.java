package io.github.biezhi.utils;

/**
 * @author biezhi
 * @date 2018/1/15
 */
public class PrintUtils {

    public static void print(String msg, Object out){
        System.out.println("------------------------- " + msg + " -------------------------");
        System.out.println("结果 => " + out.toString());
        System.out.println("------------------------- END -------------------------\r\n");
    }

}
