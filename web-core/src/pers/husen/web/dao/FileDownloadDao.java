package pers.husen.web.dao;

import java.util.ArrayList;

import pers.husen.web.bean.vo.FileDownloadVo;

/**
 * @author 何明胜
 *
 * 2017年9月29日
 */
public interface FileDownloadDao {
	/**
	 * 查询下载区数量
	 * 
	 * @return
	 */
	public int queryFileTotalCount();
	
	/**
	 * 查询某一页的下载区
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public ArrayList<FileDownloadVo> queryFileDownlaodPerPage(int pageSize, int pageNo);
	
	/**
	 * 根据Id查询查询某个下载
	 * @param fileId
	 * @return
	 */
	public FileDownloadVo queryPerFileById(int fileId);
	
	/**
	 * 插入新纪录到下载区
	 * 
	 * @param fVo
	 * @return
	 */
	public int insertFileDownload(FileDownloadVo fVo);
	
	/**
	 * 根据file_url更新文件下载次数
	 * @param fileUrl
	 * @return
	 */
	public int updateFileDownCountByUrl(String fileUrl);
}