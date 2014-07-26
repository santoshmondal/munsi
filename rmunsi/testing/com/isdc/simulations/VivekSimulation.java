package com.isdc.simulations;

import java.util.ArrayList;
import java.util.List;

import com.munsi.pojo.master.MainAccount;
import com.munsi.util.CommonUtil;

public class VivekSimulation {

	public static String getMainAccountJson() throws Exception {
		MainAccount ma= null;
		List<MainAccount> list = new ArrayList<MainAccount>();
		for(int i=0; i<100; i++) {
			ma= new MainAccount();
			ma.set_id(""+i);
			ma.setCode("" + i);
			ma.setName("vivek " + i);
			ma.setLocation("TE- Trading Expense -ve");
			list.add(ma);
		}

		return CommonUtil.objectToJson(list);
	}
	
}



