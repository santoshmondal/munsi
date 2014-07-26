package com.munsi.action.master;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.munsi.pojo.master.Area;
import com.munsi.pojo.master.Beat;
import com.munsi.pojo.master.Product;
import com.munsi.pojo.master.ProductScheme;
import com.munsi.service.AreaServeice;
import com.munsi.service.BeatServeice;
import com.munsi.service.ProductSchemeService;
import com.munsi.service.ProductServeice;
import com.munsi.util.CommonUtil;
import com.munsi.util.Constants;
import com.munsi.util.Constants.UIOperations;
import com.munsi.util.ObjectFactory;
import com.munsi.util.ObjectFactory.ObjectEnum;

/**
 * Servlet implementation class MainAccount
 */
@WebServlet("/beatmaster.action")
public class BeatMasterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(BeatMasterAction.class);
	
	private BeatServeice beatService;
	private AreaServeice areaServeice;
	
	/**
	 * Initialise all services here, Every request should not initialise them. 
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		
		Object object = ObjectFactory.getInstance(ObjectEnum.BEAT_SERVICE);
		if (object instanceof BeatServeice ) {
			beatService = (BeatServeice ) object;	
		}
		else{
			throw new ServletException("BeatService not initialized !");
		}
		
		object = ObjectFactory.getInstance(ObjectEnum.AREA_SERVICE);
		if (object instanceof AreaServeice ) {
			areaServeice = (AreaServeice ) object;
		}
		else{
			throw new ServletException("AreaServeice not initialized !");
		}
		
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessWithException(request,response);
	}

	private void doProcessWithException(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			doProcess(request,response);
		}catch(Exception e){
			LOG.error(e);
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		String json = "";
		String operation = request.getParameter(Constants.OPERATION);

		if(operation != null){
			String id = request.getParameter(Constants.COLLECTION_KEY);
			String area_id = request.getParameter("areaId");
			
			Beat beat =  new Beat();
			BeanUtils.populate(beat, request.getParameterMap() );
			
			Constants.UIOperations opEnum  = UIOperations.valueOf(operation.toUpperCase());
			switch (opEnum) {
			case ADD:
				Area area = areaServeice.get(area_id);
				beat.setArea(area);
				beatService.create(beat);	
				break;
			case EDIT :
					if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
						if(area_id!=null){
							area = areaServeice.get(area_id);
							beat.setArea(area);
						}
						beat.set_id(id);
						beatService.update(beat);
					} 
				break;	
				
			case DELETE :
				if(id != null && !id.equalsIgnoreCase(Constants.JQGRID_EMPTY)) {
					beatService.delete(id);
				}
				break;

			case VIEW :
				List<Beat> btList = beatService.getBeatListByArea(area_id);
				json = CommonUtil.objectToJson(btList);
				json = json.replaceAll("_id", "id");
				break;	
				
			case VIEW_ALL :
				
				List<Beat> btList1 = beatService.getAll();
				json = CommonUtil.objectToJson(btList1);
				json = json.replaceAll("_id", "id");
				break;	
				
			default:
				break;
			}
		}
		
		out.write(json);
		out.close();
	}

}
