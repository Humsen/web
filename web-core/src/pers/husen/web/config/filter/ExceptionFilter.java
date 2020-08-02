package pers.husen.web.config.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 错误捕获
 *
 * @author 何明胜
 * <p>
 * 2017年11月7日
 */
public class ExceptionFilter implements Filter {
    private final Logger logger = LogManager.getLogger(ExceptionFilter.class);

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}
}