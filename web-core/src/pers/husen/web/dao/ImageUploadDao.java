package pers.husen.web.dao;

import pers.husen.web.bean.vo.ImageUploadVo;

/**
 *
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
public interface ImageUploadDao {
	/**
	 * 插入新纪录到图片数据库
	 * @param iVo
	 * @return
	 */
	public int insertImageUpload(ImageUploadVo iVo);
}