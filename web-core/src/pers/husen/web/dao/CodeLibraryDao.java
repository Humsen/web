package pers.husen.web.dao;

import java.util.ArrayList;

import pers.husen.web.bean.vo.CodeLibraryVo;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public interface CodeLibraryDao {
	/**
	 * 根据条件查询代码库数量
	 * @param cVo
	 * @return
	 */
	public int queryCodeTotalCount(CodeLibraryVo cVo);
	
	/**
	 * 根据条件查询某一页的代码库
	 * @param cVo
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public ArrayList<CodeLibraryVo> queryCodeLibraryPerPage(CodeLibraryVo cVo, int pageSize, int pageNo);
	
	/**
	 * 根据Id查询单独一处代码
	 * @param codeId
	 * @return
	 */
	public CodeLibraryVo queryPerCodeById(int codeId);
	
	/**
	 * 插入新纪录到代码库，返回id
	 * 
	 * @param cVo
	 * @return
	 */
	public int insertCodeLibrary(CodeLibraryVo cVo);
	
	/**
	 * 根据id更新代码库代码阅读次数
	 * @param codeId
	 * @return
	 */
	public int updateCodeReadById(int codeId);
	
	/**
	 * 根据id修改代码内容
	 * @param cVo
	 * @return
	 */
	public int updateCodeById(CodeLibraryVo cVo);
	
	/**
	 * 根据id逻辑删除代码
	 * @param blogId
	 * @return
	 */
	public int logicDeleteCodeById(int blogId);
	
	/**
	 * 根据id查找上一篇有效代码
	 * @param codeId
	 * @return
	 */
	public CodeLibraryVo queryPreviousCode(int codeId);
	
	/**
	 * 根据id查找下一篇有效代码
	 * @param codeId
	 * @return
	 */
	public CodeLibraryVo queryNextCode(int codeId);
}