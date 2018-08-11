package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.DbConstans;
import pers.husen.web.dao.CodeLibraryDao;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.DbManipulationUtils;
import pers.husen.web.dbutil.mappingdb.ArticleCategoryMapping;
import pers.husen.web.dbutil.mappingdb.CodeLibraryMapping;
import pers.husen.web.dbutil.mappingdb.UserInfoMapping;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class CodeLibraryDaoImpl implements CodeLibraryDao {
	@Override
	public int queryCodeTotalCount(CodeLibraryVo cVo) {
		String sql = "SELECT count(*) as count FROM code_library WHERE code_delete = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(DbConstans.FIELD_VALID_FLAG);
		
		if(cVo.getCodeTitle() != null && !cVo.getCodeTitle().trim().isEmpty()) {
			sql += " AND " + CodeLibraryMapping.CODE_TITLE + " REGEXP ? ";
			cVo.setCodeTitle(".*" + cVo.getCodeTitle() + ".*");
			paramList.add(cVo.getCodeTitle());
		}
		if(cVo.getCodeCategory() != -1) {
			sql += " AND " + CodeLibraryMapping.CODE_CATEGORY + " = ?";
			paramList.add(cVo.getCodeCategory());
		}
		
		return DbQueryUtils.queryIntByParam(sql, paramList);
	}

	@Override
	public ArrayList<CodeLibraryVo> queryCodeLibraryPerPage(CodeLibraryVo cVo, int pageSize, int pageNo) {
		String sql = "SELECT code_id, code_title, code_date, code_author, code_summary, code_read, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT + ", "
				+ UserInfoMapping.USER_NICK_NAME
				+ " FROM code_library, "
				+ UserInfoMapping.DB_NAME
				+ " WHERE code_delete = ? AND "
				+ CodeLibraryMapping.CODE_AUTHOR + " = " + UserInfoMapping.USER_NAME;
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(DbConstans.FIELD_VALID_FLAG);
		
		if(cVo.getCodeTitle() != null && !cVo.getCodeTitle().trim().isEmpty()) {
			sql += " AND " + CodeLibraryMapping.CODE_TITLE + " REGEXP  ? ";
			cVo.setCodeTitle(".*" + cVo.getCodeTitle() + ".*");
			paramList.add(cVo.getCodeTitle());
		}
		if(cVo.getCodeCategory() != -1) {
			sql += " AND " + CodeLibraryMapping.CODE_CATEGORY + " = ?";
			paramList.add(cVo.getCodeCategory());
		}
		
		sql += " ORDER BY code_date DESC LIMIT " + pageSize + " OFFSET " + (pageNo-1)*pageSize;
		
		return DbQueryUtils.queryBeanListByParam(sql, paramList, CodeLibraryVo.class);
	}

	@Override
	public CodeLibraryVo queryPerCodeById(int codeId) {
		String sql = "SELECT code_id, code_title, code_date, code_author, code_summary, code_read, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT + ", "
				+ CodeLibraryMapping.CODE_MD_CONTENT + ", "
				+ CodeLibraryMapping.CODE_LABEL + ", "
				+ CodeLibraryMapping.CODE_CATEGORY + ", "
				+ ArticleCategoryMapping.CATEGORY_NAME
				+ " FROM code_library, "
				+ ArticleCategoryMapping.DB_NAME
				+ " WHERE "
				+ CodeLibraryMapping.CODE_CATEGORY + " = " + ArticleCategoryMapping.CATEGORY_ID
				+ " AND code_id = ? AND code_delete = ?";
		
		sql = "SELECT A.*, B.user_nick_name FROM (" + sql + ") as A, user_info as B WHERE A.code_author = B.user_name";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(codeId);
		paramList.add(DbConstans.FIELD_VALID_FLAG);
		
		return DbQueryUtils.queryBeanListByParam(sql, paramList, CodeLibraryVo.class).get(0);
	}

	@Override
	public int insertCodeLibrary(CodeLibraryVo cVo) {
		String sql = "INSERT INTO code_library (code_title, code_date, code_summary, code_author, code_read, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT + ", "
				+ CodeLibraryMapping.CODE_MD_CONTENT + ", "
				+ CodeLibraryMapping.CODE_LABEL + ", "
				+ CodeLibraryMapping.CODE_CATEGORY + ", "
				+ CodeLibraryMapping.CODE_DELETE
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = cVo.getCodeTitle()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeDate()) != null ? obj : new Date());
		paramList.add((obj = cVo.getCodeSummary()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeAuthor()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeRead()) != null ? obj : 0);
		paramList.add((obj = cVo.getCodeHtmlContent()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeMdContent()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeLabel()) != null ? obj : "");
		paramList.add((obj = cVo.getCodeCategory()) != null ? obj : "");
		paramList.add(DbConstans.FIELD_VALID_FLAG);
		
		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public int updateCodeReadById(int codeId) {
		String sql = "UPDATE code_library SET code_read = ((SELECT selTmp.code_read FROM (select tmp.* from code_library tmp) AS selTmp where code_id = ?) + 1) WHERE code_id = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(codeId);
		paramList.add(codeId);
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}

	@Override
	public int updateCodeById(CodeLibraryVo cVo) {
		String sql = "UPDATE " + CodeLibraryMapping.DB_NAME + " SET "
				+ CodeLibraryMapping.CODE_TITLE + "=?, "
				+ CodeLibraryMapping.CODE_AUTHOR + "=?, "
				+ CodeLibraryMapping.CODE_SUMMARY + "=?, "
				+ CodeLibraryMapping.CODE_HTML_CONTENT + "=?, "
				+ CodeLibraryMapping.CODE_MD_CONTENT + "=?, "
				+ CodeLibraryMapping.CODE_LABEL + "=?, "
				+ CodeLibraryMapping.CODE_CATEGORY + "=? "
				+ "WHERE "
				+ CodeLibraryMapping.CODE_ID + "=?";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		
		paramList.add(cVo.getCodeTitle());
		paramList.add(cVo.getCodeAuthor());
		paramList.add(cVo.getCodeSummary());
		paramList.add(cVo.getCodeHtmlContent());
		paramList.add(cVo.getCodeMdContent());
		paramList.add(cVo.getCodeLabel());
		paramList.add(cVo.getCodeCategory());

		paramList.add(cVo.getCodeId());
		
		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}

	@Override
	public int logicDeleteCodeById(int codeId) {
		String sql = "UPDATE code_library SET code_delete = 1 WHERE code_id = ?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(codeId);
		
		return DbManipulationUtils.deleteRecordByParamLogic(sql, paramList);
	}

	@Override
	public CodeLibraryVo queryPreviousCode(int codeId) {
		String sql = "SELECT * FROM code_library WHERE code_delete = ? AND code_id < ? ORDER BY code_id DESC LIMIT 1";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(DbConstans.FIELD_VALID_FLAG);
		paramList.add(codeId);
		
		return DbQueryUtils.queryBeanByParam(sql, paramList, CodeLibraryVo.class);
	}
	
	@Override
	public CodeLibraryVo queryNextCode(int codeId) {
		String sql = "SELECT * FROM code_library WHERE code_delete = ? AND code_id > ? ORDER BY code_id LIMIT 1";
		
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(DbConstans.FIELD_VALID_FLAG);
		paramList.add(codeId);
		
		return DbQueryUtils.queryBeanByParam(sql, paramList, CodeLibraryVo.class);
	}
}