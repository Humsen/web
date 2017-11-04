package pers.husen.web.dbutil.assist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.dbutil.mappingdb.BlogDetailsMapping;
import pers.husen.web.dbutil.mappingdb.CodeLibraryMapping;
import pers.husen.web.dbutil.mappingdb.FileDownloadMapping;
import pers.husen.web.dbutil.mappingdb.MessageAreaMapping;
import pers.husen.web.dbutil.mappingdb.ReleaseFeatureMapping;
import pers.husen.web.dbutil.mappingdb.UserInfoMapping;

/**
 * 博客文章工具类
 * 
 * @author 何明胜
 *
 * 2017年9月21日
 */
public class TypeTransformUtils {
	/************************************************************************************************
	 * ResultSET -> bean
	 ************************************************************************************************/
	 
	public static BlogArticleVo resultSet2BlogBean(ResultSet rs) throws SQLException{
		BlogArticleVo bVo = new BlogArticleVo();
		
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_ID)) {
			bVo.setBlogId(rs.getInt(BlogDetailsMapping.BLOG_ID));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_TITLE)) {
			bVo.setBlogTitle(rs.getString(BlogDetailsMapping.BLOG_TITLE));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_AUTHOR)) {
			bVo.setBlogAuthor(rs.getString(BlogDetailsMapping.BLOG_AUTHOR));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_SUMMARY)) {
			bVo.setBlogSummary(rs.getString(BlogDetailsMapping.BLOG_SUMMARY));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_ID)) {
			bVo.setBlogRead(rs.getInt(BlogDetailsMapping.BLOG_READ));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_HTML_CONTENT)) {
			bVo.setBlogHtmlContent(rs.getString(BlogDetailsMapping.BLOG_HTML_CONTENT));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_MD_CONTENT)) {
			bVo.setBlogMdContent(rs.getString(BlogDetailsMapping.BLOG_MD_CONTENT));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_LABEL)) {
			bVo.setBlogLabel(rs.getString(BlogDetailsMapping.BLOG_LABEL));
		}
		if(AssistUtils.isExistColumn(rs, BlogDetailsMapping.BLOG_DATE)) {
			Date date = new Date(rs.getTimestamp(BlogDetailsMapping.BLOG_DATE).getTime());
			bVo.setBlogDate(date);
		}
		
		return bVo;
	}
	
	public static MessageAreaVo resyltSet2MessageBean(ResultSet rs) throws SQLException {
		MessageAreaVo mVo = new MessageAreaVo();
		
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_ID)) {
			mVo.setMessageId(rs.getInt(MessageAreaMapping.MESSAGE_ID));
		}
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_PARENT)) {
			mVo.setMessageParent(rs.getInt(MessageAreaMapping.MESSAGE_PARENT));
		}
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_BELONG)) {
			mVo.setMessageBelong(rs.getInt(MessageAreaMapping.MESSAGE_BELONG));
		}
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_CONTENT)) {
			mVo.setMessageContent(rs.getString(MessageAreaMapping.MESSAGE_CONTENT));
		}
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_DATE)) {
			Date date = new Date(rs.getTimestamp(MessageAreaMapping.MESSAGE_DATE).getTime());
			mVo.setMessageDate(date);
		}
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_EMAIL)) {
			mVo.setMessageEmail(rs.getString("message_email"));
		}
		if(AssistUtils.isExistColumn(rs, MessageAreaMapping.MESSAGE_USERNAME)) {
			mVo.setMessageUsername(rs.getString("message_username"));
		}
		
		return mVo;
	}
	
	public static CodeLibraryVo resultSet2CodeBean(ResultSet rs) throws SQLException {
		CodeLibraryVo cVo = new CodeLibraryVo();
		
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_ID)) {
			cVo.setCodeId(rs.getInt(CodeLibraryMapping.CODE_ID));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_TITLE)) {
			cVo.setCodeTitle(rs.getString(CodeLibraryMapping.CODE_TITLE));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_AUTHOR)) {
			cVo.setCodeAuthor(rs.getString(CodeLibraryMapping.CODE_AUTHOR));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_SUMMARY)) {
			cVo.setCodeSummary(rs.getString(CodeLibraryMapping.CODE_SUMMARY));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_READ)) {
			cVo.setCodeRead(rs.getInt(CodeLibraryMapping.CODE_READ));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_HTML_CONTENT)) {
			cVo.setCodeHtmlContent(rs.getString(CodeLibraryMapping.CODE_HTML_CONTENT));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_DATE)) {
			Date date = new Date(rs.getTimestamp(CodeLibraryMapping.CODE_DATE).getTime());
			cVo.setCodeDate(date);
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_MD_CONTENT)) {
			cVo.setCodeMdContent(rs.getString(CodeLibraryMapping.CODE_MD_CONTENT));
		}
		if(AssistUtils.isExistColumn(rs, CodeLibraryMapping.CODE_LABEL)) {
			cVo.setCodeLabel(rs.getString(CodeLibraryMapping.CODE_LABEL));
		}
		
		return cVo;
	}
	
	public static FileDownloadVo resultSet2FileBean(ResultSet rs) throws SQLException {
		FileDownloadVo fVo = new FileDownloadVo();
		
		if(AssistUtils.isExistColumn(rs, FileDownloadMapping.FILE_ID)) {
			fVo.setFileId(rs.getInt(FileDownloadMapping.FILE_ID));
		}
		if(AssistUtils.isExistColumn(rs, FileDownloadMapping.FILE_NAME)) {
			fVo.setFileName(rs.getString(FileDownloadMapping.FILE_NAME));
		}
		if(AssistUtils.isExistColumn(rs, FileDownloadMapping.FILE_URL)) {
			fVo.setFileUrl(rs.getString(FileDownloadMapping.FILE_URL));
		}
		if(AssistUtils.isExistColumn(rs, FileDownloadMapping.FILE_UPLOAD_DATE)) {
			fVo.setFileUploadDate(rs.getTimestamp(FileDownloadMapping.FILE_UPLOAD_DATE));
		}
		if(AssistUtils.isExistColumn(rs, FileDownloadMapping.FILE_DOWNLOAD_COUNT)) {
			fVo.setFileDownloadCount(rs.getInt(FileDownloadMapping.FILE_DOWNLOAD_COUNT));
		}
		
		return fVo;
	}
	
	public static ReleaseFeatureVo resultSet2ReleaseBean(ResultSet rs) throws SQLException {
		ReleaseFeatureVo rVo = new ReleaseFeatureVo();
		
		if(AssistUtils.isExistColumn(rs, ReleaseFeatureMapping.RELEASE_AUTHOR)) {
			rVo.setReleaseAuthor(rs.getString(ReleaseFeatureMapping.RELEASE_AUTHOR));
		}
		if(AssistUtils.isExistColumn(rs, ReleaseFeatureMapping.RELEASE_DATE)) {
			Date date = new Date(rs.getTimestamp(ReleaseFeatureMapping.RELEASE_DATE).getTime());
			rVo.setReleaseDate(date);
		}
		if(AssistUtils.isExistColumn(rs, ReleaseFeatureMapping.RELEASE_NUMBER)) {
			rVo.setReleaseNumber(rs.getString(ReleaseFeatureMapping.RELEASE_NUMBER));
		}
		if(AssistUtils.isExistColumn(rs, ReleaseFeatureMapping.RELEASE_CONTENT)) {
			rVo.setReleaseContent(rs.getString(ReleaseFeatureMapping.RELEASE_CONTENT));
		}
		
		return rVo;
	}
	
	public static UserInfoVo resultSet2UserBean(ResultSet rs) throws SQLException {
		UserInfoVo uVo = new UserInfoVo();
		
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_ID)) {
			uVo.setUserId(rs.getInt(UserInfoMapping.USER_ID));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_NAME)) {
			uVo.setUserName(rs.getString(UserInfoMapping.USER_NAME));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_EMAIL)) {
			uVo.setUserEmail(rs.getString(UserInfoMapping.USER_EMAIL));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_PHONE)) {
			uVo.setUserPhone(rs.getString(UserInfoMapping.USER_PHONE));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_AGE)) {
			uVo.setUserAge(rs.getInt(UserInfoMapping.USER_AGE));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_SEX)) {
			uVo.setUserSex(rs.getInt(UserInfoMapping.USER_SEX));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USRE_HEAD_URL)) {
			uVo.setUserHeadUrl(rs.getString(UserInfoMapping.USRE_HEAD_URL));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_ADDRESS)) {
			uVo.setUserAddress(rs.getString(UserInfoMapping.USER_ADDRESS));
		}
		if(AssistUtils.isExistColumn(rs, UserInfoMapping.USER_NICK_NAME)) {
			uVo.setUserNickName(rs.getString(UserInfoMapping.USER_NICK_NAME));
		}

		return uVo;
	}
}