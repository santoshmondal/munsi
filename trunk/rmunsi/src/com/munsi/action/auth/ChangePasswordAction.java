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
 * Servlet implementation class ChangePasswordAction
 */
@WebServlet("/changepassword.action")
public class ChangePasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LoginAction.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordAction() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confPassword = request.getParameter("confPassword");
		boolean flag = true;
		if (oldPassword == null || oldPassword.trim().length() == 0) {
			session.setAttribute("SERVER_MESSAGE", "Old password is required!");
			flag = false;
		}
		if (newPassword == null || newPassword.trim().length() == 0) {
			session.setAttribute("SERVER_MESSAGE", "New password is required!");
			flag = false;
		}
		if (confPassword == null || confPassword.trim().length() == 0) {
			session.setAttribute("SERVER_MESSAGE", "Confirm password is required!");
			flag = false;
		}
		if (!newPassword.equals(confPassword)) {
			session.setAttribute("SERVER_MESSAGE", "New password and confirm password does not match!");
			flag = false;
		}
		if (!flag) {
			response.sendRedirect("");
			return;
		}

		String userId = PortalUtil.getLoggedUserId(request);
		if (userId == null || userId.trim().length() == 0) {
			session.setAttribute("SERVER_MESSAGE", "Session Expired!");
			response.sendRedirect("");
			return;
		}
		AccessUser accessUser = accessUserServeice.get(userId);
		if (accessUser == null) {
			session.setAttribute("SERVER_MESSAGE", "User not found!");
			response.sendRedirect("");
			return;
		}

		if (!oldPassword.equals(accessUser.getPassword())) {
			session.setAttribute("SERVER_MESSAGE", "Old password is wrong!");
			response.sendRedirect("");
			return;
		}

		accessUser = new AccessUser();
		accessUser.set_id(userId);
		accessUser.setPassword(newPassword);
		accessUserServeice.update(accessUser);
		response.sendRedirect("");
		return;
	}

}
