package pers.husen.web.servlet.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.po.AccessAtatisticsPo;

/**
 * 获取网站访问统计
 *
 * @author 何明胜
 *
 * 2017年10月18日
 */
@WebServlet(urlPatterns="/accessAtatistics.hms")
public class AccessAtatisticsSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccessAtatisticsSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	
    	AccessAtatisticsPo aPo = new AccessAtatisticsPo();
		aPo.setAccessToday((int) this.getServletContext().getAttribute("visitToday"));
		aPo.setAccessTotal((int) this.getServletContext().getAttribute("visitTotal"));
		aPo.setOnlineCurrent((int) this.getServletContext().getAttribute("onlineCount"));
		
		String json = JSONObject.fromObject(aPo).toString();
		PrintWriter out = response.getWriter();
		out.println(json);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}