package es.tresw.db.dao;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;

public class DatabaseExport
{
	 public static void main(String[] args) throws Exception
	 {

		 DatabaseExport.generateSchema();
		// DatabaseExport.export();		 
	 }
	 
	 public static void export() throws Exception
	 {
	        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
	        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pistea", "root", "mpsbart");
	        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
	        connection.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
	        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
	        IDataSet fullDataSet = connection.createDataSet();
	        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
	 }
	 
	 public static void generateSchema() throws Exception
	 {
		  // database connection
	        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
	        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pistea", "root", "");
	        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
	        connection.getConfig().setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
	        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

	        // write DTD file
	        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("src/test/resources/pistea.dtd"));
	 }
}
