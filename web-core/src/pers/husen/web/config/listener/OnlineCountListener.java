package pers.husen.web.config.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.service.VisitTotalSvc;

/**
 * 网站访问统计监听
 *
 * @author 何明胜
 *
 *         2017年10月20日
 */
@WebListener
public class OnlineCountListener implements HttpSessionListener {
	public OnlineCountListener() {}

	/**
	 * session创建时调用
	 */
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		ServletContext context = httpSessionEvent.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("onlineCount");
		if (count == null) {
			count = new Integer(1);
		} else {
			int co = count.intValue();
			count = new Integer(co + 1);
		}

		// 保存人数
		context.setAttribute("onlineCount", count);
		
		// 会话创建直接总访问量加1
		VisitTotalSvc vSvc = new VisitTotalSvc();
		vSvc.updateVisitCount();
		int today = vSvc.queryVisitToday();
		context.setAttribute("visitToday", new Integer(today));

		int total = vSvc.queryVisitTotal();
		context.setAttribute("visitTotal", new Integer(total));
		
		Logger logger = LogManager.getLogger(OnlineCountListener.class.getName());
		logger.info("onlineCount:" + context.getAttribute("onlineCount") + ", visitToday:" + 
				context.getAttribute("visitToday") + ", visitTotal:" + context.getAttribute("visitTotal"));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		ServletContext context = httpSessionEvent.getSession().getServletContext();
		Integer count = (Integer) context.getAttribute("onlineCount");
		int co = count.intValue();
		count = new Integer(co - 1);
		count = count >= 0 ? count : 0;
		context.setAttribute("onlineCount", count);
	}
}