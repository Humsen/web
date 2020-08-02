package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.dao.MessageAreaDao;
import pers.husen.web.dao.impl.MessageAreaDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月25日
 */
public class MessageAreaSvc implements MessageAreaDao{
	private static final MessageAreaDaoImpl messageAreaDaoImpl = new MessageAreaDaoImpl();
	
	@Override
	public ArrayList<MessageAreaVo> queryAllMessageArea(int messageId) {
		return messageAreaDaoImpl.queryAllMessageArea(messageId);
	}
	
	@Override
	public int insertMessageNew(MessageAreaVo mVo) {
		return messageAreaDaoImpl.insertMessageNew(mVo);
	}
}
