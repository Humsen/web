package pers.husen.web.service;

import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.dao.ReleaseFeatureDao;
import pers.husen.web.dao.impl.ReleaseFeatureDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年10月17日
 */
public class ReleaseFeatureSvc implements ReleaseFeatureDao{
	private ReleaseFeatureDaoImpl rImpl = new ReleaseFeatureDaoImpl();
	
	@Override
	public int insertReleaseFeature(ReleaseFeatureVo rVo) {
		return rImpl.insertReleaseFeature(rVo);
	}
	
	@Override
	public ReleaseFeatureVo queryLatestReleaseFeature() {
		return rImpl.queryLatestReleaseFeature();
	}

	@Override
	public ReleaseFeatureVo queryReleaseById(int releaseId) {
		return rImpl.queryReleaseById(releaseId);
	}

	@Override
	public int updateReleaseContentById(ReleaseFeatureVo rVo) {
		return rImpl.updateReleaseContentById(rVo);
	}
}