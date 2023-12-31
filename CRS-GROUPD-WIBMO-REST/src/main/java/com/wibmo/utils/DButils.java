/**
 * 
 */
package com.wibmo.utils;

/**
 * Class containing database connection Info.
 */




import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@Component
public class DButils {
private static Connection connection = null;
	
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
//            	Properties prop = new Properties();
//                InputStream inputStream = DButils.class.getClassLoader().getResourceAsStream("D:\\wibmo_microservice_project_repo\\CRS-GROUPD-WIBMO-REST\\src\\main\\resources");
//                prop.load(inputStream);
                String driver="com.mysql.cj.jdbc.Driver";
                String url="jdbc:mysql://localhost:3306/crs";
                String user="root";
                String password="samyak@24";
//                String driver = prop.getProperty("driver");
//                String url = prop.getProperty("url");
//                String user = prop.getProperty("user");
//                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
//            catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return connection;
    }
}
