package com.munsi.action.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.async.util.PortalUtil;
import com.munsi.pojo.auth.AccessUser;
import com.munsi.service.AccessUserServeice;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.set")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginAction.class);

	private AccessUserServeice accessUserServeice;

	@Override
	public void init() throws ServletException {
		super.init();
		Object object = ObjectFactory.getInstance(ObjectEnum.ACCESS_USER_SERVICE);
		if (object instanceof AccessUserServeice) {
			accessUserServeice = (AccessUserServeice) object;
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String operation = request.getParameter("operation");
		if (operation != null && operation.equalsIgnoreCase("logout")) {
			session.invalidate();
			response.sendRedirect("");
			return;
		} else {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			AccessUser accessUser = accessUserServeice.authenticate(userName, password);

			if (accessUser == null) {
				session.setAttribute("SERVER_MESSAGE", "Invalid credentials - authentication failed!");
				response.sendRedirect("");
				return;
			} else {
				PortalUtil.setLoggedUserInSession(request, accessUser);
				response.sendRedirect("hometmp.jsp");
				return;
			}
		}
	}
}
