package com.estudio.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.async.simulations.SantoshUtil;
import com.async.util.Constants;
import com.async.util.Constants.MasterTypeEnum;
import com.async.util.Constants.UIOperations;

/**
 * Servlet implementation class CommonAction
 */
@WebServlet("/commonaction.do")
public class CommonAction extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(CommonAction.class);

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	private void doProcessWithException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (RuntimeException me) {
			LOG.error(me);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

		PrintWriter out = response.getWriter();
		String json = "";
		String operation = request.getParameter(Constants.OPERATION);
		
		if (operation != null ) {

			Constants.UIOperations opEnum = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum){
			case FETCH:
				if(!((String)request.getParameter("service")).equalsIgnoreCase("")){
					if(request.getParameter("key") != null && !((String)request.getParameter("key")).equalsIgnoreCase("")){
							json = SantoshUtil.getIDTextFormat((String)request.getParameter("service"), (String)request.getParameter("key"));
					}
					else{
						if(request.getParameter("get") != null && request.getParameter("get").equalsIgnoreCase("all")){
							Map<String, Object> whereFields = new HashMap<String, Object>();
							whereFields.put("_id", request.getParameter("cust_mobile"));
							json = SantoshUtil.getAllValue(whereFields);
						}else if(request.getParameter("get") != null ){
							Map<String, Object> whereFields = new HashMap<String, Object>();
							whereFields.put("type", (String)request.getParameter("service"));
							whereFields.put("size",request.getParameter("size"));
							if(request.getParameterMap().containsKey("quality"))
								whereFields.put("quality",request.getParameter("quality"));
							if(request.getParameterMap().containsKey("frameNumber"))
								whereFields.put("frameNumber",request.getParameter("frameNumber"));
								
							json = SantoshUtil.getValue(whereFields, request.getParameter("get").toString());							
						}
					}
				}
				
				break;
			default:
				
			}

		out.write(json);
		out.close();
		}
	}
}
