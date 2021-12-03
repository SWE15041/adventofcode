import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yanni
 */
public class NumberUtil{

    public static Integer getNumber(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String x = matcher.replaceAll("");
        return Integer.valueOf(x);
    }

    public static int BinaryToDecimal(int binaryNumber){
        int decimal = 0;
        int p = 0;
        while(true){
            if(binaryNumber == 0){
                break;
            } else {
                int temp = binaryNumber%10;
                decimal += temp*Math.pow(2, p);
                binaryNumber = binaryNumber/10;
                p++;
            }
        }
        return decimal;
    }

    public static int bin2DecXiao(String binXStr) {
        double decX = 0.0; //位数
        int k = 0;
        for (int i = 0; i < binXStr.length(); i++) {
            int exp = binXStr.charAt(i) - '0';
            exp = -(i + 1) * exp;
            if (exp != 0) {
                decX += Math.pow(2, exp);
            }
        }
        System.out.println("二进制小数为;" + binXStr + "。\r\n其对应的十进制为：" + decX);
        return (int) decX;
    }
}
