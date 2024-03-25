package management.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ManagementDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public ManagementDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("management-sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 동물 영역 생성 SQL 수행 DAO
	 * @param conn
	 * @param type
	 * @param aceptNum
	 * @return
	 */
	public boolean createField(Connection conn, String type, int aceptNum) throws Exception{
		try {
			String sql = prop.getProperty("createField");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, type.toUpperCase());
			pstmt.setString(2, type.toUpperCase());
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result);
		} finally {
			
		}
		
		return true;
	}

	
	
}