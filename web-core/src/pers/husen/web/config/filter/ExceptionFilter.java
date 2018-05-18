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
 * 2017年11月7日
 */
public class ExceptionFilter implements Filter {
	private final Logger logger = LogManager.getLogger(ExceptionFilter.class);
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}