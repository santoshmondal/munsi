/*package com.estudio.report;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

import com.jaspersoft.mongodb.MongoDbDataSource;
import com.jaspersoft.mongodb.connection.MongoDbConnection;

public class JasperReport1 {

    public static void main(String[] args) throws UnknownHostException {
        try {
            String mongoURI = "mongodb://localhost:27017/test";
            MongoDbConnection connection = null;

            Map<String, Object> parameters = new HashMap<String, Object>();
            try {
                connection = new MongoDbConnection(mongoURI,null,null);
                parameters.put(MongoDbDataSource.CONNECTION, connection);
                parameters.put("STATUSNAME","Raw");
                String sourceFileName = JasperReport1.class.getClassLoader().getResource("simpleMongo.jrxml").getFile();
    			
                JasperCompileManager.compileReportToFile(sourceFileName, "/usr/simpleMongo.jasper");
                String sourceJasperFileName = "/usr/simpleMongo.jasper";

                JasperFillManager.fillReportToFile(sourceJasperFileName, parameters);               

                String jrprintName = "/usr/report246.jrprint";//JasperReport1.class.getClassLoader().getResource("/usr/report246.jrprint").getFile();
    			JasperExportManager.exportReportToPdfFile(jrprintName, "/usr/datasource.pdf");
            } catch(Exception e) {
            	e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}*/