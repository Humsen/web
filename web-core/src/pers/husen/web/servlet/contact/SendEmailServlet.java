package pers.husen.web.servlet.contact;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.common.helper.SendEmailHelper;

/**
 * 发送邮件
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet(urlPatterns="/sendEmail.hms")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendEmailServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SendEmailHelper sendEmail = new SendEmailHelper();
		String senderName = request.getParameter("contactName");
		String senderEmail = request.getParameter("contactEmail");
		String senderPhone = request.getParameter("contactPhone");
		String senderContent = request.getParameter("contactContent");
		
		int result = sendEmail.sendEmail2Admin(senderName, senderEmail, senderPhone, senderContent);
		
		PrintWriter out = response.getWriter();
		out.println(result);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}