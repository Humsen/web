package pers.husen.web.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.helper.StackTrace2Str;

/**
 * 错误捕获
 *
 * @author 何明胜
 *
 *         2017年11月7日
 */
public class WebFilter implements Filter {
	private final Logger logger = LogManager.getLogger(WebFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*HttpServletRequest svtRequest = (HttpServletRequest) request;
		HttpServletResponse svtResponse = (HttpServletResponse) response;
		StringBuffer resquestUrl = svtRequest.getRequestURL();
		
		String regStr = ".*www.hemingsheng.cn/$";
		String userAgent = svtRequest.getHeader("User-Agent");
		Boolean isMobile = JudgeIsMobile.isMobile(userAgent);
		// 如果是手机访问,且是访问hemingsheng.cn 转发到m.hemingsheng.cn
		if (Pattern.matches(regStr, resquestUrl) && isMobile) {
			
			svtResponse.sendRedirect("https://m.hemingsheng.cn");
			return;
		}
		//如果是手机访问www分享的链接，则替换成m
		String webPc = "www";
		if(isMobile && resquestUrl.indexOf(webPc) != -1) {
			String newResquestUrl = resquestUrl.toString().replace("www", "m");
			svtResponse.sendRedirect(newResquestUrl + "?" + svtRequest.getQueryString());
			return;
		}*/
		
		//异常捕获，继续后面的请求
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}