package pers.husen.web.config.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.helper.JudgeIsMobile;
import pers.husen.web.common.helper.StackTrace2Str;

/**
 * 错误捕获
 *
 * @author 何明胜
 *
 *         2017年11月7日
 */
public class ExceptionFilter implements Filter {
	private final Logger logger = LogManager.getLogger(ExceptionFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 如果是手机访问,且是访问hemingshengcn. 转发到m.hemingsheng.cn
		HttpServletRequest svtRequest = (HttpServletRequest) request;
		StringBuffer resquestUrl = svtRequest.getRequestURL();
		
		String regStr = ".*www.hemingsheng.cn/$";
		String userAgent = svtRequest.getHeader("User-Agent");
		if (Pattern.matches(regStr, resquestUrl) && JudgeIsMobile.isMobile(userAgent)) {
			HttpServletResponse svtResponse = (HttpServletResponse) response;
			svtResponse.sendRedirect("https://m.hemingsheng.cn");
			return;
		}
		
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