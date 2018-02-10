package pers.husen.web.common.helper;

/**
 * 获取指定长度的验证码
 *
 * @author 何明胜
 *
 *         2017年10月27日
 */
public class RandomCodeHelper {
	public static int producedRandomCode(double length) {
		int randomCode = (int)(Math.random() * Math.pow(10, length));
		//进制系统，这里是10进制
		int decimalSystem = 10;
		
		while(randomCode < Math.pow(decimalSystem, length-1) || randomCode >= Math.pow(decimalSystem, length)) {
			randomCode = (int)(Math.random() * Math.pow(10, length));
		}
		
		return randomCode;
	}
}