package pers.husen.web.servlet.module;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.common.helper.ReadH5Helper;
import pers.husen.web.config.ProjectDeployConfig;

@WebServlet(urlPatterns="/login.hms")
public class LoginSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginSvt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String htmlQualifiedName = ProjectDeployConfig.WEB_DEPLOY_PATH + "/plugins/plugins.html";
		ReadH5Helper.writeHtmlByName(htmlQualifiedName, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}