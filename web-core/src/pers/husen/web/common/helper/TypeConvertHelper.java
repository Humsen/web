package pers.husen.web.common.helper;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.bean.vo.ReleaseFeatureVo;

/**
 * 类型转换助手
 *
 * @author 何明胜
 *
 *         2017年10月21日
 */
public class TypeConvertHelper {
	/******************************************************
	 * JSON -> Bean 主要是日期转换问题
	 ******************************************************/
	
	
	/**
	 * 前端传过来的新博客参数转化成json后再转换成Bean
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static BlogArticleVo jsonObj2BlogBean(JSONObject jsonObject) {
		// 转换时间格式 String -> Date
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));

		return (BlogArticleVo) JSONObject.toBean(jsonObject, BlogArticleVo.class);
	}

	/**
	 * 前端传过来的新评论参数转化成json后再转换成Bean
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static MessageAreaVo jsonObj2MessageBean(JSONObject jsonObject) {
		// 转换时间格式 String->Date
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));

		return (MessageAreaVo) JSONObject.toBean(jsonObject, MessageAreaVo.class);
	}

	/**
	 * 前端传过来的代码参数转化成json后再转换成Bean
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static CodeLibraryVo jsonObj2CodeBean(JSONObject jsonObject) {
		// 转换时间格式 String->Date
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));

		return (CodeLibraryVo) JSONObject.toBean(jsonObject, CodeLibraryVo.class);
	}
	
	/**
	 * 前端传过来的版本特性参数转换成json后再转换成Bean
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static ReleaseFeatureVo jsonObj2ReleaseBean(JSONObject jsonObject) {
		// 转换时间格式 String->Date
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));

		return (ReleaseFeatureVo) JSONObject.toBean(jsonObject, ReleaseFeatureVo.class);
	}
}