package management.model.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import management.model.dao.ManagementDAO;

import static common.JDBCTemplate.*;

public class ManagementService {
	
	private ManagementDAO dao = new ManagementDAO(); 

	/** 동물 영역 생성 서비스
	 * @param type
	 * @param aceptNum
	 * @return
	 * @throws Exception
	 */
	public boolean createField(String type, int aceptNum) throws Exception {
		Connection conn = getConnection();
		
		boolean result = dao.createField(conn, type, aceptNum);

		File directory = new File("/workspace/04_JDBC/05_ZooProject_임성혁/src/field/model/dto");
		File directory2 = new File("/workspace/04_JDBC/05_ZooProject_임성혁/src/animal/model/dto");
		
		if(!directory.exists()) {
			directory.mkdirs();
			System.out.println("\n=== " + directory.getName() + "폴더가 생성되었습니다 ===\n");
			directory2.mkdirs();
			System.out.println("\n=== " + directory2.getName() + "폴더가 생성되었습니다 ===\n");
		}
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			
			fw = new FileWriter("/workspace/04_JDBC/05_ZooProject_임성혁/src/field/model/dto/" + type + "Field.java");
			bw = new BufferedWriter(fw);
			
			String fieldSet = "package field.model.dto;\r\n"
					+ "\r\n"
					+ "import java.util.List;\r\n"
					+ "import animal.model.dto." + type + ";\r\n"
					+ "\r\n"
					+ "public class " + type + "Field {\r\n"
					+ "\r\n"
					+ "	private String type;\r\n"
					+ "	private int aceptNumber;\r\n"
					+ "	private List<" + type + "> " + type.toLowerCase() + "List;\r\n"
					+ "	\r\n"
					+ "	public " + type + "Field() {}\r\n"
					+ "\r\n"
					+ "	public " + type + "Field(String type, int aceptNumber, List<" + type + "> " + type.toLowerCase() + "List) {\r\n"
					+ "		super();\r\n"
					+ "		this.type = type;\r\n"
					+ "		this.aceptNumber = aceptNumber;\r\n"
					+ "		this." + type.toLowerCase() + "List = " + type.toLowerCase() + "List;\r\n"
					+ "	}\r\n"
					+ "\r\n"
					+ "	@Override\r\n"
					+ "	public String toString() {\r\n"
					+ "		return \"" + type + "Field [type=\" + type + \", aceptNumber=\" + aceptNumber + \", " + type.toLowerCase() + "List=\" + " + type.toLowerCase() + "List + \"]\";\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "	public String getType() {\r\n"
					+ "		return type;\r\n"
					+ "	}\r\n"
					+ "	public void setType(String type) {\r\n"
					+ "		this.type = type;\r\n"
					+ "	}\r\n"
					+ "	public int getAceptNumber() {\r\n"
					+ "		return aceptNumber;\r\n"
					+ "	}\r\n"
					+ "	public void setAceptNumber(int aceptNumber) {\r\n"
					+ "		this.aceptNumber = aceptNumber;\r\n"
					+ "	}\r\n"
					+ "	public List<" + type + "> get" + type + "List() {\r\n"
					+ "		return " + type.toLowerCase() + "List;\r\n"
					+ "	}\r\n"
					+ "	public void set" + type + "List(List<" + type + "> " + type.toLowerCase() + "List) {\r\n"
					+ "		this." + type.toLowerCase() + "List = " + type.toLowerCase() + "List;\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "}";
			
			bw.write(fieldSet);
			
			bw.flush();
			
			fw = new FileWriter("/workspace/04_JDBC/05_ZooProject_임성혁/src/animal/model/dto/" + type + ".java");
			bw = new BufferedWriter(fw);
			
			String animalSet = "package animal.model.dto;\r\n"
					+ "\r\n"
					+ "import animal.model.dto." + type + ";\r\n"
					+ "\r\n"
					+ "public class " + type + " {\r\n"
					+ "\r\n"
					+ "	private String name;\r\n"
					+ "	private char gender;\r\n"
					+ "	private int age;\r\n"
					+ "	private int hungry;\r\n"
					+ "	private boolean alive;\r\n"
					+ "	\r\n"
					+ "	public " + type + "() {}\r\n"
					+ "\r\n"
					+ "	public " + type + "(String name, char gender, int age, int hungry, boolean alive) {\r\n"
					+ "		super();\r\n"
					+ "		this.name = name;\r\n"
					+ "		this.gender = gender;\r\n"
					+ "		this.age = age;\r\n"
					+ "		this.hungry = hungry;\r\n"
					+ "		this.alive = alive;\r\n"
					+ "	}\r\n"
					+ "\r\n"
					+ "	@Override\r\n"
					+ "	public String toString() {\r\n"
					+ "		return \"" + type + " [name=\" + name + \", gender=\" + gender + \", age=\" + age + \", hungry=\" + hungry + \", alive=\" + alive\r\n"
					+ "				+ \"]\";\r\n"
					+ "	}\r\n"
					+ "\r\n"
					+ "	public String getName() {\r\n"
					+ "		return name;\r\n"
					+ "	}\r\n"
					+ "	public void setName(String name) {\r\n"
					+ "		this.name = name;\r\n"
					+ "	}\r\n"
					+ "	public char getGender() {\r\n"
					+ "		return gender;\r\n"
					+ "	}\r\n"
					+ "	public void setGender(char gender) {\r\n"
					+ "		this.gender = gender;\r\n"
					+ "	}\r\n"
					+ "	public int getAge() {\r\n"
					+ "		return age;\r\n"
					+ "	}\r\n"
					+ "	public void setAge(int age) {\r\n"
					+ "		this.age = age;\r\n"
					+ "	}\r\n"
					+ "	public int getHungry() {\r\n"
					+ "		return hungry;\r\n"
					+ "	}\r\n"
					+ "	public void setHungry(int hungry) {\r\n"
					+ "		this.hungry = hungry;\r\n"
					+ "	}\r\n"
					+ "	public boolean isAlive() {\r\n"
					+ "		return alive;\r\n"
					+ "	}\r\n"
					+ "	public void setAlive(boolean alive) {\r\n"
					+ "		this.alive = alive;\r\n"
					+ "	}\r\n"
					+ "}";
			
			bw.write(animalSet);
			
			bw.flush();
			
			return true;
		} finally {
			try {
				if(bw != null) bw.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}