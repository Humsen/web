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
	private static final FileDownloadDaoImpl fileDownloadDaoImpl = new FileDownloadDaoImpl();

	@Override
	public int queryFileTotalCount() {
		return fileDownloadDaoImpl.queryFileTotalCount();
	}

	@Override
	public ArrayList<FileDownloadVo> queryFileDownlaodPerPage(int pageSize, int pageNo) {
		return fileDownloadDaoImpl.queryFileDownlaodPerPage(pageSize, pageNo);
	}

	@Override
	public FileDownloadVo queryPerFileById(int fileId) {
		return fileDownloadDaoImpl.queryPerFileById(fileId);
	}

	@Override
	public int insertFileDownload(FileDownloadVo fVo) {
		return fileDownloadDaoImpl.insertFileDownload(fVo);
	}
	
	@Override
	public int updateFileDownCountByUrl(String fileUrl) {
		return fileDownloadDaoImpl.updateFileDownCountByUrl(fileUrl);
	}
}