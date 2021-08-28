package ink.kangaroo.file.domain;

import com.alibaba.nacos.shaded.com.google.protobuf.ServiceException;
import ink.kangaroo.common.core.utils.StringUtils;
import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/25 0:16
 */
@Data
public class Key {
    private String method;
    private String uri;
    private String iv;
    private String value;

    private Boolean isByte;
    //密钥字节
    private byte[] keyBytes = new byte[16];
    /**
     * 解密ts
     *
     * @param sSrc   ts文件字节数组
     * @param length
     * @param sKey   密钥
     * @return 解密后的字节数组
     */
    public byte[] decrypt(byte[] sSrc, int length, String sKey, String iv, String method) throws Exception {
        if (StringUtils.isNotEmpty(method) && !method.contains("AES")) {
            throw new ServiceException("未知的算法！");
        }
        // 判断Key是否正确
        if (StringUtils.isEmpty(sKey)) {
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16 && !isByte) {
            throw new ServiceException("Key长度不是16位！");
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec keySpec = new SecretKeySpec(isByte ? keyBytes : sKey.getBytes(StandardCharsets.UTF_8), "AES");
        byte[] ivByte;
        if (iv.startsWith("0x")) {
            ivByte = StringUtils.hexStringToByteArray(iv.substring(2));
        } else {
            ivByte = iv.getBytes();
        }
        if (ivByte.length != 16) {
            ivByte = new byte[16];
        }
        //如果m3u8有IV标签，那么IvParameterSpec构造函数就把IV标签后的内容转成字节数组传进去
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(ivByte);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
        return cipher.doFinal(sSrc, 0, length);
    }

}
