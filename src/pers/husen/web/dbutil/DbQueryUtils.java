package pers.husen.web.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.dbutil.assist.DbConnectUtils;
import pers.husen.web.dbutil.assist.SetPsParamUtils;
import pers.husen.web.dbutil.assist.TypeTransformUtils;

/**
 * 数据库查询工具类
 * 
 * @author : 何明胜
 *
 *         2017年9月21日
 */
public class DbQueryUtils {
	private static final Logger logger = LogManager.getLogger(DbQueryUtils.class.getName());

	/**
	 * 根据条件查询博客文章
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static ArrayList<BlogArticleVo> queryBlogArticles(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BlogArticleVo> blogArticleVos = new ArrayList<BlogArticleVo>();

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				blogArticleVos.add(TypeTransformUtils.resultSet2BlogBean(rs));
			}
			logger.info("Success query, return records's count : " + blogArticleVos.size());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return blogArticleVos;
	}

	/**
	 * 根据条件查询代码库代码
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static ArrayList<CodeLibraryVo> queryCodeLibrarys(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CodeLibraryVo> cVos = new ArrayList<CodeLibraryVo>();

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				cVos.add(TypeTransformUtils.resultSet2CodeBean(rs));
			}
			logger.info("Success query, return records's count : " + cVos.size());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return cVos;
	}

	/**
	 * 根据条件查询下载区数量
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static UserInfoVo queryUserInfo(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfoVo uVo = new UserInfoVo();

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			if (rs.next()) {
				uVo = TypeTransformUtils.resultSet2UserBean(rs);
			}
			logger.info("Success query, return value : " + uVo);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return uVo;
	}
	
	/**
	 * 根据条件查询下载区数量
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static ArrayList<FileDownloadVo> queryFileDownload(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<FileDownloadVo> fVos = new ArrayList<FileDownloadVo>();

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				fVos.add(TypeTransformUtils.resultSet2FileBean(rs));
			}
			logger.info("Success query, return records's count : " + fVos.size());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return fVos;
	}

	/**
	 * 根据条件查询数量
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int queryCountByCondition(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);

			if (paramList != null && paramList.size() != 0) {
				ps = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}
			logger.info(ps);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}
			logger.info("Success query, count : " + count);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
			return -1;
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		
		return count;
	}

	/**
	 * 查询所有留言
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static ArrayList<MessageAreaVo> queryAllMessageArea(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MessageAreaVo> messageAreaVos = new ArrayList<MessageAreaVo>();

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				messageAreaVos.add(TypeTransformUtils.resyltSet2MessageBean(rs));
			}
			logger.info("Success query, return records's count : " + messageAreaVos.size());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return messageAreaVos;
	}

	/**
	 * 根据用户名查询密码
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static String queryPasswordByUserName(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			if (rs.next()) {
				logger.info("Success query, pass validation !");
				return rs.getString("user_password");
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return null;
	}

	public static int queryMaxId(String sql) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			logger.info(ps);
			rs = ps.executeQuery();
			if (rs.next()) {
				int maxId = rs.getInt(1); 
				logger.info("Success query, maxId : " + maxId);
				return maxId;
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return 0;
	}

	/**
	 * 查询网站总访问量
	 * 
	 * @return
	 */
	public static int queryVisitTotal(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			if (rs.next()) {
				int visitTotal = rs.getInt("count");
				logger.info("Success query, visit total : " + visitTotal);
				return visitTotal;
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		return 0;
	}
	
	/**
	 * 查询最新的新版特性
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static ArrayList<ReleaseFeatureVo> queryLatestReleaseFeature(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReleaseFeatureVo> rVos = new ArrayList<ReleaseFeatureVo>();

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				rVos.add(TypeTransformUtils.resultSet2ReleaseBean(rs));
			}
			logger.info("Success query, return records's count : " + rVos.size());
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return rVos;
	}
}