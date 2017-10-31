package pers.husen.web.service;

import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.dao.impl.ReleaseFeatureDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年10月17日
 */
public class ReleaseFeatureSvc {
	private ReleaseFeatureDaoImpl rImpl = new ReleaseFeatureDaoImpl();
	
	public int insertReleaseFeature(ReleaseFeatureVo rVo) {
		return rImpl.insertReleaseFeature(rVo);
	}
	
	public ReleaseFeatureVo queryLatestReleaseFeature() {
		return rImpl.queryLatestReleaseFeature();
	}
}