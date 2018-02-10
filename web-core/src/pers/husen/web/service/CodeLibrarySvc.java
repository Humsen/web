package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.dao.CodeLibraryDao;
import pers.husen.web.dao.impl.CodeLibraryDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class CodeLibrarySvc implements CodeLibraryDao{
	private CodeLibraryDaoImpl cImpl = new CodeLibraryDaoImpl();
	
	@Override
	public int queryCodeTotalCount(CodeLibraryVo cVo) {
		return cImpl.queryCodeTotalCount(cVo);
	}
	
	@Override
	public CodeLibraryVo queryPerCodeById(int codeId) {
		return cImpl.queryPerCodeById(codeId);
	}
	
	@Override
	public int insertCodeLibrary(CodeLibraryVo cVo) {
		return cImpl.insertCodeLibrary(cVo);
	}
	
	@Override
	public int updateCodeReadById(int codeId) {
		return cImpl.updateCodeReadById(codeId);
	}
	
	@Override
	public int updateCodeById(CodeLibraryVo cVo) {
		return cImpl.updateCodeById(cVo);
	}
	
	@Override
	public int logicDeleteCodeById(int codeId) {
		return cImpl.logicDeleteCodeById(codeId);
	}

	@Override
	public ArrayList<CodeLibraryVo> queryCodeLibraryPerPage(CodeLibraryVo cVo, int pageSize, int pageNo) {
		return cImpl.queryCodeLibraryPerPage(cVo, pageSize, pageNo);
	}

	@Override
	public CodeLibraryVo queryPreviousCode(int codeId) {
		return cImpl.queryPreviousCode(codeId);
	}

	@Override
	public CodeLibraryVo queryNextCode(int codeId) {
		return cImpl.queryNextCode(codeId);
	}
}