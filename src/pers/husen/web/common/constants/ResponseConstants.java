package pers.husen.web.common.constants;

/**
 * 后端返回前端常量
 *
 * @author 何明胜
 *
 * 2017年11月8日
 */
public class ResponseConstants {
	/** 操作成功(如果是请求数量, 直接返回数量. 不为-1都为成功)  */
	public static final int RESPONSE_OPERATION_SUCCESS = 1;
	/** 操作失败 (不用0是因为如果有数量请求, 0不知道是失败还是成功)*/
	public static final int RESPONSE_OPERATION_FAILURE = -1;
}