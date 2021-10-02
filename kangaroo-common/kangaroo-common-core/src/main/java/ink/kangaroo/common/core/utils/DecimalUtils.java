package ink.kangaroo.common.core.utils;

import java.util.Stack;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/1 17:09
 */
public class DecimalUtils {

    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N) {
        if (N > array.length) {
            return null;
        }
        long rest = number;
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[Long.valueOf((rest % N)).intValue()]);
            rest = rest / N;
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0" : result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        if (N > array.length) {
            return 0;
        }
        char[] ch = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }
}
