package test.selenium.util;

/**
 * Created by yuanyuanyang1 on 6/30/2017.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    /*
    根据年月日时分加0-9的随机整数组合成barcode字符串
    */
    public static String getBarCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String random = Integer.toString((int)Math.round(Math.random() * 9 + 1));//获得0-9以内的随机数，表示为整数，并转换成字符串
        return sdf.format(new Date())+random;
    }
    public static void main(String[] args){
        getBarCode();
    }
}
