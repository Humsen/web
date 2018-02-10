package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.ImageUploadVo;
import pers.husen.web.dao.ImageUploadDao;
import pers.husen.web.dbutil.DbManipulationUtils;

/**
 * @author 何明胜
 *
 * 2017年10月20日
 */
public class ImageUploadDaoImpl implements ImageUploadDao {

	@Override
	public int insertImageUpload(ImageUploadVo iVo) {
	String sql = "INSERT INTO image_upload (image_name, image_url, image_upload_date, image_type, image_download_count) VALUES (?, ?, ?, ?, ?)";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = iVo.getImageName()) != null ? obj : "");
		paramList.add((obj = iVo.getImageUrl()) != null ? obj : "");
		paramList.add((obj = iVo.getImageUploadDate()) != null ? obj : new Date());
		paramList.add((obj = iVo.getImageType()) != null ? obj : "");
		paramList.add((obj = iVo.getImageDownloadCount()) != null ? obj : 0);
		
		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}
}