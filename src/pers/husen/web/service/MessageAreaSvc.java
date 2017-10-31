package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.dao.impl.MessageAreaDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月25日
 */
public class MessageAreaSvc {
	private MessageAreaDaoImpl mImpl = new MessageAreaDaoImpl();
	
	public ArrayList<MessageAreaVo> queryAllMessageArea(int messageId) {
		return mImpl.queryAllMessageArea(messageId);
	}
	
	public int insertMessageNew(MessageAreaVo mVo) {
		return mImpl.insertMessageNew(mVo);
	}
}
