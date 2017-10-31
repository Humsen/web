package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.dao.CodeLibraryDao;
import pers.husen.web.dbutil.DbInsertUtils;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.DbUpdateUtils;
import pers.husen.web.dbutil.mappingdb.CodeLibraryMapping;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class CodeLibraryDaoImpl implements CodeLibraryDao {
	@Override
	public int queryCodeTotalCount() {
		String sql = "SELECT count(*) as count FROM code_library";
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		return DbQueryUtils.queryCountByCondition(sql, paramList);
	}

	@Override
	public ArrayList<CodeLibraryVo> queryCodeLibraryPerPage(int pageSize, int pageNo) {
		String sql = "SELECT code_id, code_title, code_date, code_author, code_summary, code_read, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT
				+ " FROM code_library"
				+ " ORDER BY code_date DESC LIMIT " + pageSize + " OFFSET " + (pageNo-1)*pageSize;
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		return DbQueryUtils.queryCodeLibrarys(sql, paramList);
	}

	@Override
	public CodeLibraryVo queryPerCodeById(int codeId) {
		String sql = "SELECT code_id, code_title, code_date, code_author, code_summary, code_read, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT + ", "
				+ CodeLibraryMapping.CODE_MD_CONTENT
				+ " FROM code_library WHERE code_id = ? ";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(codeId);
		
		return DbQueryUtils.queryCodeLibrarys(sql, paramList).get(0);
	}

	@Override
	public int insertCodeLibrary(CodeLibraryVo cVo) {
		String sql = "INSERT INTO code_library (code_title, code_date, code_summary, code_author, code_read, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT + ", "
				+ CodeLibraryMapping.CODE_MD_CONTENT
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = cVo.getCodeTitle()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeDate()) != null ? obj : new Date());
		paramList.add((obj = cVo.getCodeSummary()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeAuthor()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeRead()) != null ? obj : 0);
		paramList.add((obj = cVo.getCodeHtmlContent()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeMdContent()) != null ? obj : "");
		
		return DbInsertUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public int updateCodeReadById(int codeId) {
		String sql = "UPDATE code_library SET code_read = ((SELECT code_read FROM code_library where code_id = ?) + 1) WHERE code_id = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(codeId);
		paramList.add(codeId);
		
		return DbUpdateUtils.updateRecordByParam(sql, paramList);
	}
}