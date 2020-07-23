package com.example.springexampletools.stringtool;
import org.apache.commons.lang3.StringUtils;
/**
 * @version 1.0
 * @ClassName: String
 */
public class StringUtilsTool {
    /**
     * 一方面判断了字符串“null”，另一方面对参数个数无限制，只要有一个参数是空则返回true。
     * @param args
     * @return
     */
    public static boolean isNullStr(String... args){
        boolean flag = false;
        for (String arg : args){
            if(StringUtils.isBlank(arg)||arg.equals("null")){
                flag = true;
                return flag;
            }
        }
        return flag;
    }
}
