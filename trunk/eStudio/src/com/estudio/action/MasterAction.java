package com.estudio.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.async.util.CommonUtil;
import com.async.util.Constants;
import com.async.util.Constants.UIOperations;
import com.async.util.ObjectFactory;
import com.async.util.ObjectFactory.ObjectEnum;
import com.estudio.pojo.master.Master;
import com.estudio.service.MasterService;

/**
 * Servlet implementation class InvoiceAction
 */
@WebServlet("/masteraction.do")
public class MasterAction extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(MasterAction.class);
	private static final long serialVersionUID = 1L;
	private MasterService masterService;

	@Override
	public void init() throws ServletException {
		super.init();
		Object object = ObjectFactory.getInstance(ObjectEnum.MASTER_SERVICE);
		if (object instanceof MasterService) {
			masterService = (MasterService) object;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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

		if (operation != null && masterService != null) {

			Constants.UIOperations opEnum = UIOperations.valueOf(operation.toUpperCase());
			Constants.MasterTypeEnum typeEnum = null;
			if (request.getParameter("type") != null)
				typeEnum = Constants.MasterTypeEnum.valueOf(request.getParameter("type").toUpperCase());

			switch (typeEnum) {
			case PHOTO:
				json = photoMasterOperation(opEnum, request.getParameterMap());
				break;
			case FRAME:
				json = frameMasterOperation(opEnum, request.getParameterMap());
				break;
			case LAMINATION:
				json = laminationMasterOperation(opEnum, request.getParameterMap());
				break;
			default:
				break;
			}
		}
		out.write(json);
		out.close();
	}

	private String photoMasterOperation(UIOperations opEnum, Map<String, String[]> parameterMap) {

		Master master = null;
		String json = "";
		switch (opEnum) {
		case ADD:
			master = new Master();
			if (parameterMap.containsKey("size"))
				master.setSize(parameterMap.get("size")[0]);
			if (parameterMap.containsKey("quality"))
				master.setQuality(parameterMap.get("quality")[0]);
			if (parameterMap.containsKey("price"))
				master.setPrice(Float.parseFloat(parameterMap.get("price")[0]));
			if (parameterMap.containsKey("description"))
				master.setDescription(parameterMap.get("description")[0]);
			master.setType(Constants.MasterTypeEnum.PHOTO.toString());
			masterService.create(master);
			break;
		case EDIT:
			master = new Master();
			if (parameterMap.containsKey("id"))
				master.set_id(parameterMap.get("id")[0]);
			if (parameterMap.containsKey("size"))
				master.setSize(parameterMap.get("size")[0]);
			if (parameterMap.containsKey("quality"))
				master.setQuality(parameterMap.get("quality")[0]);
			if (parameterMap.containsKey("price"))
				master.setPrice(Float.parseFloat(parameterMap.get("price")[0]));
			if (parameterMap.containsKey("description"))
				master.setDescription(parameterMap.get("description")[0]);

			masterService.update(master);
			break;
		case DELETE:
			if (parameterMap.containsKey("id")) {
				masterService.delete(parameterMap.get("id")[0]);
			}
			break;
		case VIEW_ALL:
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", Constants.MasterTypeEnum.PHOTO.toString());
			List<Master> allMaster = masterService.getAllByField(map);
			json = CommonUtil.objectToJson(allMaster);
			json = json.replaceAll("_id", "id");
			break;
		}
		return json;
	}

	private String frameMasterOperation(UIOperations opEnum, Map<String, String[]> parameterMap) {

		Master master = null;
		String json = "";
		switch (opEnum) {
		case ADD:
			master = new Master();
			if (parameterMap.containsKey("size"))
				master.setSize(parameterMap.get("size")[0]);
			if (parameterMap.containsKey("frameNumber"))
				master.setFrameNumber(parameterMap.get("frameNumber")[0]);
			if (parameterMap.containsKey("quality"))
				master.setQuality(parameterMap.get("quality")[0]);
			if (parameterMap.containsKey("price"))
				master.setPrice(Float.parseFloat(parameterMap.get("price")[0]));
			if (parameterMap.containsKey("direct"))
				master.setDirect(Float.parseFloat(parameterMap.get("direct")[0]));
			if (parameterMap.containsKey("mount"))
				master.setMount(Float.parseFloat(parameterMap.get("mount")[0]));
			if (parameterMap.containsKey("rightMount"))
				master.setRightMount(Float.parseFloat(parameterMap.get("rightMount")[0]));
			if (parameterMap.containsKey("leftMount"))
				master.setLeftMount(Float.parseFloat(parameterMap.get("leftMount")[0]));
			if (parameterMap.containsKey("goldMount"))
				master.setGoldMount(Float.parseFloat(parameterMap.get("goldMount")[0]));
			if (parameterMap.containsKey("pastting"))
				master.setPastting(Float.parseFloat(parameterMap.get("pastting")[0]));
			if (parameterMap.containsKey("description"))
				master.setDescription(parameterMap.get("description")[0]);
			master.setType(Constants.MasterTypeEnum.FRAME.toString());
			masterService.create(master);
			break;
		case EDIT:
			master = new Master();
			if (parameterMap.containsKey("id"))
				master.set_id(parameterMap.get("id")[0]);
			if (parameterMap.containsKey("frameNumber"))
				master.setFrameNumber(parameterMap.get("frameNumber")[0]);
			if (parameterMap.containsKey("size"))
				master.setSize(parameterMap.get("size")[0]);
			if (parameterMap.containsKey("quality"))
				master.setQuality(parameterMap.get("quality")[0]);
			if (parameterMap.containsKey("price"))
				master.setPrice(Float.parseFloat(parameterMap.get("price")[0]));
			if (parameterMap.containsKey("direct"))
				master.setDirect(Float.parseFloat(parameterMap.get("direct")[0]));
			if (parameterMap.containsKey("mount"))
				master.setMount(Float.parseFloat(parameterMap.get("mount")[0]));
			if (parameterMap.containsKey("rightMount"))
				master.setRightMount(Float.parseFloat(parameterMap.get("rightMount")[0]));
			if (parameterMap.containsKey("leftMount"))
				master.setLeftMount(Float.parseFloat(parameterMap.get("leftMount")[0]));
			if (parameterMap.containsKey("goldMount"))
				master.setGoldMount(Float.parseFloat(parameterMap.get("goldMount")[0]));
			if (parameterMap.containsKey("pastting"))
				master.setPastting(Float.parseFloat(parameterMap.get("pastting")[0]));
			if (parameterMap.containsKey("description"))
				master.setDescription(parameterMap.get("description")[0]);
			masterService.update(master);
			break;
		case DELETE:
			if (parameterMap.containsKey("id")) {
				masterService.delete(parameterMap.get("id")[0]);
			}
			break;
		case VIEW_ALL:
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", Constants.MasterTypeEnum.FRAME.toString());
			List<Master> allMaster = masterService.getAllByField(map);
			json = CommonUtil.objectToJson(allMaster);
			json = json.replaceAll("_id", "id");
			break;
		}
		return json;
	}

	private String laminationMasterOperation(UIOperations opEnum, Map<String, String[]> parameterMap) {
		Master master = null;
		String json = "";
		switch (opEnum) {
		case ADD:
			master = new Master();
			if (parameterMap.containsKey("size"))
				master.setSize(parameterMap.get("size")[0]);
			if (parameterMap.containsKey("quality"))
				master.setQuality(parameterMap.get("quality")[0]);
			if (parameterMap.containsKey("price"))
				master.setPrice(Float.parseFloat(parameterMap.get("price")[0]));
			if (parameterMap.containsKey("description"))
				master.setDescription(parameterMap.get("description")[0]);
			master.setType(Constants.MasterTypeEnum.LAMINATION.toString());
			masterService.create(master);
			break;
		case EDIT:
			master = new Master();
			if (parameterMap.containsKey("id"))
				master.set_id(parameterMap.get("id")[0]);
			if (parameterMap.containsKey("size"))
				master.setSize(parameterMap.get("size")[0]);
			if (parameterMap.containsKey("quality"))
				master.setQuality(parameterMap.get("quality")[0]);
			if (parameterMap.containsKey("price"))
				master.setPrice(Float.parseFloat(parameterMap.get("price")[0]));
			if (parameterMap.containsKey("description"))
				master.setDescription(parameterMap.get("description")[0]);

			masterService.update(master);
			break;
		case DELETE:
			if (parameterMap.containsKey("id")) {
				masterService.delete(parameterMap.get("id")[0]);
			}
			break;
		case VIEW_ALL:
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", Constants.MasterTypeEnum.LAMINATION.toString());
			List<Master> allMaster = masterService.getAllByField(map);
			json = CommonUtil.objectToJson(allMaster);
			json = json.replaceAll("_id", "id");
			break;
		}
		return json;
	}

}
