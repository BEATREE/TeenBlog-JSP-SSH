package xzy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileName {
    public static String getFileName() {
    String fname = null;
    Date date = new Date();    
    SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");   
    fname=sf.format(date);
    fname+=(int)(Math.random()*100);//加2位随机数
    return fname;
 }
    public static String reName(String name) {
    	String type = name.substring(name.lastIndexOf("."));
        String fname = null;
        Date date = new Date();    
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");   
        fname=sf.format(date);
        fname+=(int)(Math.random()*100);//加2位随机数
        fname+=type;
        return fname;
     }
}
