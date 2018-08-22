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
			
			//afficher une article de couleur rouge et poids >2000
			String sqlP = "SELECT COUL, PDS FROM PRODUITS WHERE COUL = 'rouge' AND PDS>2000";
			ResultSet resultP = st.executeQuery(sqlP);

			String coulVar;
			int pdsVar;

			while (resultP.next()) {
				coulVar = resultP.getString("coul");
				pdsVar = resultP.getInt("pds");

				System.out.println("Couleur de article: " + coulVar + ", poids d'article: " + pdsVar);
			}

			String sqlR = "SELECT REPRESENTANTS.NR FROM formation.REPRESENTANTS\r\n" + 
					"INNER JOIN formation.VENTES\r\n" + 
					"ON REPRESENTANTS.NR = VENTES.QI\r\n" + 
					"WHERE QI > 1";
			ResultSet resultR = st.executeQuery(sqlR);

			int nrVar;

			while (resultR.next()) {
				nrVar = resultR.getInt("nr");

				System.out.println("Numero de representant est " + nrVar);
			}

			String sqlV = "SELECT DISTINCT CLIENTS.NOMC FROM formation.CLIENTS\r\n" +
					"INNER JOIN formation.VENTES\r\n" +
					"ON CLIENTS.NC = VENTES.NC\r\n"+
					"WHERE VILLE = 'Lyon'\r\n"+
					"AND QI > 180";

			ResultSet resultV = st.executeQuery(sqlV);

			String nomcV;

			while (resultV.next()) {
				nomcV = resultV.getString("nomc");

				System.out.println("Le nom de client est " + nomcV);
			}
			
			String sqlCR = "SELECT DISTINCT REPRESENTANTS.NOMR, CLIENTS.NOMC FROM (((formation.VENTES\n" + 
					"INNER JOIN formation.PRODUITS ON VENTES.NP = PRODUITS.NP)\n" + 
					"INNER JOIN formation.REPRESENTANTS ON VENTES.NR = REPRESENTANTS.NR)\n" + 
					"INNER JOIN formation.CLIENTS ON VENTES.NC = CLIENTS.NC)\n" + 
					"WHERE PRODUITS.COUL = 'rouge'\n" + 
					"AND VENTES.QI > 100";
			
			ResultSet resultCR = st.executeQuery(sqlCR);
			
			String varCR;
			while (resultCR.next()) {
				varCR = resultCR.getString("nomc");
				System.out.println("Les noms de clients sont " + varCR);
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
