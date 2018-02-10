package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.dao.MessageAreaDao;
import pers.husen.web.dbutil.DbManipulationUtils;
import pers.husen.web.dbutil.DbQueryUtils;

/**
 * @author 何明胜
 *
 * 2017年9月25日
 */
public class MessageAreaDaoImpl implements MessageAreaDao {
	@Override
	public ArrayList<MessageAreaVo> queryAllMessageArea(int messageId) {
		String sql = "SELECT message_id,message_parent,message_belong,message_content,message_date,message_email,message_username "
				+ "FROM message_area where message_belong = ? order by message_date";
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(messageId);
		
		return DbQueryUtils.queryBeanListByParam(sql, paramList, MessageAreaVo.class);
	}

	@Override
	public int insertMessageNew(MessageAreaVo mVo) {
		String sql = "INSERT INTO message_area (message_parent, message_belong, message_content, message_date, message_email, message_username) " 
				+"VALUES (?, ?, ?, ?, ?, ?)";
			
		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = mVo.getMessageParent()) != null ? obj : "");
		paramList.add((obj = mVo.getMessageBelong()) != null ? obj : new Date());
		paramList.add((obj = mVo.getMessageContent()) != null ? obj : "");
		paramList.add((obj = mVo.getMessageDate()) != null ? obj : "");
		paramList.add((obj = mVo.getMessageEmail()) != null ? obj : "");
		paramList.add((obj = mVo.getMessageUsername()) != null ? obj : 0);
		
		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}
}