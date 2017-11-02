package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.dao.impl.CodeLibraryDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class CodeLibrarySvc {
	private CodeLibraryDaoImpl cImpl = new CodeLibraryDaoImpl();
	
	public int queryCodeTotalCount() {
		return cImpl.queryCodeTotalCount();
	}
	
	public ArrayList<CodeLibraryVo> queryBlogArticlePerPage(int pageSize, int pageNo){
		return cImpl.queryCodeLibraryPerPage(pageSize, pageNo);
	}
	
	public CodeLibraryVo queryPerCodeById(int codeId) {
		return cImpl.queryPerCodeById(codeId);
	}
	
	public int insertCodeLibrary(CodeLibraryVo cVo) {
		return cImpl.insertCodeLibrary(cVo);
	}
	
	public int updateCodeReadById(int codeId) {
		return cImpl.updateCodeReadById(codeId);
	}
}
