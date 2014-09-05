package com.munsi.action.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.munsi.pojo.auth.AccessUser;
import com.munsi.service.AccessUserServeice;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class SetupAction
 */
@WebServlet("/setup")
public class SetupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(SetupAction.class);
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
	public SetupAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long count = accessUserServeice.getCount();

		if (count == 0) {
			AccessUser accessUser = new AccessUser();
			accessUser.setApproved(true);
			accessUser.setPassword("Welcome@123");
			accessUser.setUserName("admin");
			accessUser.setName("Admin");
			accessUser.setRole("ADMIN");
			accessUserServeice.create(accessUser);
		}
		response.sendRedirect("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
