package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.dao.FileDownloadDao;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.DbManipulationUtils;

/**
 * @author 何明胜
 *
 *         2017年9月29日
 */
public class FileDownloadDaoImpl implements FileDownloadDao {
	@Override
	public int queryFileTotalCount() {
		String sql = "SELECT count(*) as count FROM file_download";
		ArrayList<Object> paramList = new ArrayList<Object>();

		return DbQueryUtils.queryIntByParam(sql, paramList);
	}

	@Override
	public ArrayList<FileDownloadVo> queryFileDownlaodPerPage(int pageSize, int pageNo) {
		String sql = "SELECT file_id, file_name, file_url, file_upload_date, file_download_count FROM file_download" + " ORDER BY file_download_count DESC LIMIT " + pageSize + " OFFSET "
				+ (pageNo - 1) * pageSize;

		ArrayList<Object> paramList = new ArrayList<Object>();

		return DbQueryUtils.queryBeanListByParam(sql, paramList, FileDownloadVo.class);
	}

	@Override
	public FileDownloadVo queryPerFileById(int fileId) {
		String sql = "SELECT file_id, file_name, file_url, file_upload_date, file_download_count FROM file_download" + " WHERE file_id = ? ";

		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(fileId);

		return DbQueryUtils.queryBeanListByParam(sql, paramList, FileDownloadVo.class).get(0);
	}

	@Override
	public int insertFileDownload(FileDownloadVo fVo) {
		String sql = "INSERT INTO file_download (file_name, file_url, file_upload_date, file_download_count) VALUES (?, ?, ?, ?)";

		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = fVo.getFileName()) != null ? obj : new Date());
		paramList.add((obj = fVo.getFileUrl()) != null ? obj : "");
		paramList.add((obj = fVo.getFileUploadDate()) != null ? obj : "");
		paramList.add((obj = fVo.getFileDownloadCount()) != null ? obj : "");

		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public int updateFileDownCountByUrl(String fileUrl) {
		ArrayList<Object> paramList = new ArrayList<Object>();

		String selectSql = "SELECT file_download_count FROM file_download WHERE file_url = ?";
		paramList.add(fileUrl);
		FileDownloadVo fileDownloadVo = DbQueryUtils.queryBeanListByParam(selectSql, paramList, FileDownloadVo.class).get(0);

		int fileDownloadCount = fileDownloadVo.getFileDownloadCount() + 1;

		String sql = "UPDATE file_download SET file_download_count = ? WHERE file_url = ?";
		paramList.clear();
		paramList.add(fileDownloadCount);
		paramList.add(fileUrl);

		return DbManipulationUtils.updateRecordByParam(sql, paramList);
	}
}