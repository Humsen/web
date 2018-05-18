package pers.husen.web.common.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @desc MD5加密工具
 *
 * @author 何明胜
 *
 * @created 2017年12月29日 上午10:56:47
 */
public class  Md5EncryptHelper {
    private static final String KEY_MD5 = "MD5";
    /** 全局数组 */
    private static final String[] STRING_DIGITS = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     *  返回形式为数字跟字符串
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return STRING_DIGITS[iD1] + STRING_DIGITS[iD2];
    }

    /** 
     * 转换字节数组为16进制字串
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
    /**
     * MD5加密
     * @param strObj
     * @return
     * @throws Exception
     */
    public static String getMD5Code(String strObj){
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(KEY_MD5);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        // md.digest() 该函数返回值为存放哈希值结果的byte数组
        return byteToString(md.digest(strObj.getBytes()));
    }
    
    public static void main(String[] args) {
    	try {
			System.out.println(getMD5Code("123123"));
			System.out.println(getMD5Code("123123").length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}