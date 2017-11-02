package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.dao.ReleaseFeatureDao;
import pers.husen.web.dbutil.DbInsertUtils;
import pers.husen.web.dbutil.DbQueryUtils;

/**
 *
 *
 * @author 何明胜
 *
 * 2017年10月17日
 */
public class ReleaseFeatureDaoImpl implements ReleaseFeatureDao{
	@Override
	public int insertReleaseFeature(ReleaseFeatureVo rVo) {
		String sql = "INSERT INTO release_feature "
				+ "(release_author, release_date, release_number, release_content )"
				+ "VALUES (?, ?, ?, ?)"; 
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = rVo.getReleaseAuthor()) != null ? obj : new Date());
		paramList.add((obj = rVo.getReleaseDate()) != null ? obj : "");
		paramList.add((obj = rVo.getReleaseNumber()) != null ? obj : "");
		paramList.add((obj = rVo.getReleaseContent()) != null ? obj : "");
		
		return DbInsertUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public ReleaseFeatureVo queryLatestReleaseFeature() {
		String sql = "SELECT release_author, release_date, release_number, release_content FROM release_feature"
				+ " WHERE release_id = (SELECT max(release_id) FROM release_feature)";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		ArrayList<ReleaseFeatureVo> result = DbQueryUtils.queryLatestReleaseFeature(sql, paramList);
		
		if(result == null || result.size() == 0) {
			return new ReleaseFeatureVo();
		}
		
		return result.get(0); 
	}
}