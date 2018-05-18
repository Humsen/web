package pers.husen.web.dao;

import java.util.ArrayList;

import pers.husen.web.bean.vo.MessageAreaVo;

/**
 * @author 何明胜
 *
 * 2017年9月25日
 */
public interface MessageAreaDao {
	/**
	 * 查询留言区所有留言
	 * 
	 * @param messageId
	 * @return
	 */
	public ArrayList<MessageAreaVo> queryAllMessageArea(int messageId);
	
	/**
	 * 插入新的留言
	 * @param mVo
	 * @return
	 */
	public int insertMessageNew(MessageAreaVo mVo);
}
