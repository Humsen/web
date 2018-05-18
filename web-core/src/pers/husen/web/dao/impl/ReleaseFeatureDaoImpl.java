package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.dao.ReleaseFeatureDao;
import pers.husen.web.dbutil.DbManipulationUtils;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.mappingdb.ReleaseFeatureMapping;

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
		
		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public ReleaseFeatureVo queryLatestReleaseFeature() {
		String sql = "SELECT release_author, release_date, release_number, release_content, "
				+ ReleaseFeatureMapping.RELEASE_ID
				+ " FROM release_feature"
				+ " WHERE release_id = (SELECT max(release_id) FROM release_feature)";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		ReleaseFeatureVo result = DbQueryUtils.queryBeanByParam(sql, paramList, ReleaseFeatureVo.class);
		
		return result; 
	}

	@Override
	public ReleaseFeatureVo queryReleaseById(int releaseId) {
		String sql = "SELECT release_author, release_date, release_number, release_content, "
				+ ReleaseFeatureMapping.RELEASE_ID
				+ " FROM release_feature"
				+ " WHERE release_id = ?";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(releaseId);
		
		ReleaseFeatureVo result = DbQueryUtils.queryBeanByParam(sql, paramList, ReleaseFeatureVo.class);
		
		return result; 
	}

	@Override
	public int updateReleaseContentById(ReleaseFeatureVo rVo) {
		String sql = "UPDATE " + ReleaseFeatureMapping.DB_NAME + " SET "
				+ ReleaseFeatureMapping.RELEASE_CONTENT + "=? "
				+ "WHERE "
				+ ReleaseFeatureMapping.RELEASE_ID + "=?";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(rVo.getReleaseContent());
		paramList.add(rVo.getReleaseId());
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}
}