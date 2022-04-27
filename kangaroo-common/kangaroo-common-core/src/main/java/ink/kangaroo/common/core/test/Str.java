package ink.kangaroo.common.core.test;

//import ink.kangaroo.common.core.text.PrintUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
public class Str {
    public static void main(String[] args) {

        String source ="upload\\tmp\\20220311\\b4b2a46d6f36fde85400621dc87cc4403m9s-36s.mp4";
        String target ="/upload/tmp/20220311/b4b2a46d6f36fde85400621dc87cc4403m9s-36s.mp4";
        // /upload/tmp/20220311/b4b2a46d6f36fde85400621dc87cc4403m9s-36s.mp4
//        String target = "/upload/avatar/20220307/a083d39f91de230e4f7dba4665566832/初一五育并举/初一五育并举.xls";
//        String regex = "/^[\\u2E80-\\u9FFF]+$/";
//        String regex = "(\\w:\\\\)?(\\w*\\\\)*\\w+\\.\\w{1,9}";
//        String regex = "(/[\\u4e00-\\u9fa5\\w]*){6}.(\\w{1,4})";
        source = "\\"+source;
        String regex = "(\\\\)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(source);
        String s = matcher.replaceAll("/");
        System.out.println(s);
       /* File file1 = new File("F:\\books\\20220307_200451.mp4");
        System.out.println(file1.exists());
        File file2 = new File("F:\\books\\111.mp4");
        System.out.println(file2.exists());
        String substring = "123.jj".substring(0, "123.jj".lastIndexOf("."));
        System.out.println(substring);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        System.out.println((char)8232);
//        System.out.println(PrintUtil.specialChar(str));
        //杈撳叆瀛楃涓� sadfasd
        str = str.replaceAll(" ", "");
        // 鍘婚櫎绌烘牸
        Pattern p = Pattern.compile("[`~鈽嗏槄!@#$%^&*()+=|{}':;,\\[\\]銆嬄�.<>/?~锛丂#锟�%鈥︹�︼紙锛夆�斺��+|{}銆愩�戔�橈紱锛氣�濃�溾�欍�傦紝銆侊紵]");
        //鍘婚櫎鐗规畩瀛楃
        Matcher m = p.matcher(str);
        str = m.replaceAll("").trim().replace(" ", "").replace("\\", "");
        //灏嗗尮閰嶇殑鐗规畩瀛楃杞彉涓虹┖
        System.out.println(str);
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            System.out.println(str.charAt(i));
        }*/
    }
}
