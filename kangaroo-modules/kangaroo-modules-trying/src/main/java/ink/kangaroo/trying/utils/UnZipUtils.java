package ink.kangaroo.trying.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class UnZipUtils {
    static String real = "ZYXLXP";//真实密码
    static String pass = "";//执行循环操作找出来的与真实密码相等的字符串
    static String prod = "";//中间产生的字符串
    static String[] pool = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {
        //生成6位密码
        String source = "F:\\JAVA核心知识点整理_大白菜博客(www.cmsblogs.cn).zip";
        String dest = "F:\\";

//循环遍历出数组中的元素，列出数组中的字符可以组成的所有一位字符串，共62^1种可能
//
//        for (int i = 0; i < 62; i++) {
//            prod = pool[i] + "";
//            if (UnZipUtils.unZip(source, dest, prod)) {
//                System.out.println("执行" + (i + 1) + "次操作,找到真实密码，为" + pass);
//                break;
//            }
//        }
//
////列出数组中的字符可以组成的所有两位字符串，共62^2种可能
//        if (pass.equals("")) {//在上一级未找到
//            for (int i = 0; i < 62; i++) {
//                for (int j = 0; j < 62; j++) {
//                    prod = String.valueOf(pool[i] + pool[j]);
//                    if (UnZipUtils.unZip(source, dest, prod)) {
//                        System.out.println("执行" + (i + 1) + "次操作,找到真实密码，为" + pass);
//                        break;
//                    }
//                }
//            }
//        }
////列出数组中的字符可以组成的所有三位字符串，共62^3种可能
//        if (pass.equals("")) {//在上一级未找到
//            for (int i = 0; i < 62; i++) {
//                for (int j = 0; j < 62; j++) {
//                    for (int k = 0; k < 62; k++) {
//                        prod = String.valueOf(pool[i] + pool[j] + pool[k]);
//                        if (UnZipUtils.unZip(source, dest, prod)) {
//                            System.out.println("执行" + (i + 1) + "次操作,找到真实密码，为" + pass);
//                            break;
//                        }
//                    }
//                }
//
//            }
//        }
////列出数组中的字符可以组成的所有4位字符串，共62^4种可能
//        if (pass.equals("")) {//在上一级未找到
//            for (int i = 0; i < 62; i++) {
//                for (int j = 0; j < 62; j++) {
//                    for (int k = 0; k < 62; k++) {
//                        for (int l = 0; l < 62; l++) {
//                            prod = String.valueOf(pool[i] + pool[j] + pool[k] + pool[l]);
//                            if (UnZipUtils.unZip(source, dest, prod)) {
//                                System.out.println("执行" + (i + 1) + "次操作,找到真实密码，为" + pass);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
////列出数组中的字符可以组成的所有5位字符串，共62^5种可能
//        if (pass.equals("")) {//在上一级未找到
//            for (int i = 0; i < 62; i++) {
//                for (int j = 0; j < 62; j++) {
//                    for (int k = 0; k < 62; k++) {
//                        for (int l = 0; l < 62; l++) {
//                            for (int m = 0; m < 62; m++) {
//                                prod = String.valueOf(pool[i] + pool[j] + pool[k] + pool[l] + pool[m]);
//                                if (UnZipUtils.unZip(source, dest, prod)) {
//                                    System.out.println("执行" + (i + 1) + "次操作,找到真实密码，为" + pass);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//列出数组中的字符可以组成的所有6位字符串，共62^6种可能
        if (pass.equals("")) {//在上一级未找到
            for (int i = 0; i < 62; i++) {
                for (int j = 0; j < 62; j++) {
                    for (int k = 0; k < 62; k++) {
                        for (int l = 0; l < 62; l++) {
                            for (int m = 0; m < 62; m++) {
                                for (int n = 0; n < 62; n++) {
                                    prod = pool[i] + pool[j] + pool[k] + pool[l] + pool[m] + pool[n];
                                    if (UnZipUtils.unZip(source, dest, prod)) {
                                        System.out.println("执行" + (i + 1) + "次操作,找到真实密码，为" + pass);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

    }


    /**
     * @param source   原始文件路径
     * @param dest     解压路径
     * @param password 解压文件密码(可以为空)
     */
    public static boolean unZip(String source, String dest, String password) {
        try {
            File zipFile = new File(source);
            ZipFile zFile = new ZipFile(zipFile);
            zFile.setFileNameCharset("GBK");
            File destDir = new File(dest);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            if (zFile.isEncrypted()) {
                zFile.setPassword(password.toCharArray());
            }
            zFile.extractAll(dest);
            List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            File[] extractedFiles = new File[extractedFileList.size()];
            extractedFileList.toArray(extractedFiles);
        } catch (ZipException e) {
            System.out.println("密码：" + password + "|错误");
            return false;
        }
        return true;
    }
}