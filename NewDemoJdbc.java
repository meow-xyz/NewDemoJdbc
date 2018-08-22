package com.objis.newdemojdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class NewDemoJdbc {

	public static void main(String[] args) {
		//connection
		String url = "jdbc:mysql://localhost/formation";
		String user = "root";
		String pwd = "o08080";
		// on met tous les operation risque dans le try
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = (Connection) DriverManager.getConnection(url, user,pwd);
			//creation d'un statement a partir d'objet connection
			Statement st = (Statement) cn.createStatement();
			//notre requete
			String sql = "SELECT * FROM formation.CLIENTS";
			//execution requete
			ResultSet result = st.executeQuery(sql);
			
			int ncVar;
			String nomcVar;
			String villeVar;
			
			while (result.next()) {
				//recuperer le nc
				ncVar = result.getInt("nc");
				//recuperer le nomc
				nomcVar = result.getString("nomc");
				//recuperer ville
				villeVar = result.getString("ville");
				
				System.out.println("Numero client: " + ncVar + ", Nom Client " + nomcVar + ", Ville " + villeVar);
			}
		} 
		//dans le catch on met les exeptions
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
