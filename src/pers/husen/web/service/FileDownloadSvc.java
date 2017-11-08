package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.dao.FileDownloadDao;
import pers.husen.web.dao.impl.FileDownloadDaoImpl;

/**
 * @author 何明胜
 *
 *         2017年9月29日
 */
public class FileDownloadSvc implements FileDownloadDao{
	private FileDownloadDaoImpl fImpl = new FileDownloadDaoImpl();

	public int queryFileTotalCount() {
		return fImpl.queryFileTotalCount();
	}

	public ArrayList<FileDownloadVo> queryFileDownlaodPerPage(int pageSize, int pageNo) {
		return fImpl.queryFileDownlaodPerPage(pageSize, pageNo);
	}

	public FileDownloadVo queryPerFileById(int fileId) {
		return fImpl.queryPerFileById(fileId);
	}

	public int insertFileDownload(FileDownloadVo fVo) {
		return fImpl.insertFileDownload(fVo);
	}
	
	public int updateFileDownCountByUrl(String fileUrl) {
		return fImpl.updateFileDownCountByUrl(fileUrl);
	}
}