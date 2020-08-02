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
	private static final CodeLibraryDaoImpl codeLibraryDaoImpl = new CodeLibraryDaoImpl();
	
	@Override
	public int queryCodeTotalCount(CodeLibraryVo cVo) {
		return codeLibraryDaoImpl.queryCodeTotalCount(cVo);
	}
	
	@Override
	public CodeLibraryVo queryPerCodeById(int codeId) {
		return codeLibraryDaoImpl.queryPerCodeById(codeId);
	}
	
	@Override
	public int insertCodeLibrary(CodeLibraryVo cVo) {
		return codeLibraryDaoImpl.insertCodeLibrary(cVo);
	}
	
	@Override
	public int updateCodeReadById(int codeId) {
		return codeLibraryDaoImpl.updateCodeReadById(codeId);
	}
	
	@Override
	public int updateCodeById(CodeLibraryVo cVo) {
		return codeLibraryDaoImpl.updateCodeById(cVo);
	}
	
	@Override
	public int logicDeleteCodeById(int codeId) {
		return codeLibraryDaoImpl.logicDeleteCodeById(codeId);
	}

	@Override
	public ArrayList<CodeLibraryVo> queryCodeLibraryPerPage(CodeLibraryVo cVo, int pageSize, int pageNo) {
		return codeLibraryDaoImpl.queryCodeLibraryPerPage(cVo, pageSize, pageNo);
	}

	@Override
	public CodeLibraryVo queryPreviousCode(int codeId) {
		return codeLibraryDaoImpl.queryPreviousCode(codeId);
	}

	@Override
	public CodeLibraryVo queryNextCode(int codeId) {
		return codeLibraryDaoImpl.queryNextCode(codeId);
	}
}