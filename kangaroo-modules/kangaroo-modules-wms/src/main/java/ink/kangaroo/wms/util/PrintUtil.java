package ink.kangaroo.wms.util;

public class PrintUtil {
    /**
     * 处理特殊字符
     **/
    public static String specialChar(String input) {
        if (input == null) {
            input = "";
        }
        String result = "";
        char[] data = input.toCharArray();
        for (int i = 0; i < data.length; i++) {
            if ((int) data[i] == 8232) {
                data[i] = ' ';
            }
        }
        result = new String(data);
        String bak = result.replaceAll(" ", "");
        if (bak.length() == 0) {
            result = bak;
        }

        return result;
    }
}
