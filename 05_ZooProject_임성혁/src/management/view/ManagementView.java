package management.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagementView {

	private Scanner sc = new Scanner(System.in);
	
	public void zooManagementMenu() {
		int input = 0;
		do {
			try{
				System.out.println("\n=== Zoo Management ===\n");
			
				System.out.println("1. Create Field"); // 동물 영역 만들기
				System.out.println("2. Select Field"); // 생성된 동물 영역 조회(동물 추가, 수정, 삭제)
				System.out.println("3. Update Field"); // 동물 영역 수정
				System.out.println("4. Delete Field"); // 동물 영역 삭제
				System.out.println("0. exit");
				
				System.out.print("\nselect menu : ");
				input = sc.nextInt();
				
				switch(input) {
					case 1: createField(); break;
					case 2: selectField(); break;
					case 3: updateField(); break;
					case 4: deleteField(); break;
					case 0: System.out.println("\n=== Management Exit ===\n"); break;
					default: System.out.println("\n*** 메뉴 번호만 입력해주세요 ***\n"); input = -1; break;
				}
			} catch(InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				e.printStackTrace();
			}
		}while(input != 0);
		
	}

	/**
	 * 동물 영역 생성
	 */
	public void createField() {
		System.out.println("\n=== Create Field ===\n");
		
		System.out.print("Animal Type : ");
		String type = sc.next();
		System.out.print("Acceptable Number : ");
		int aceptNum = sc.nextInt();
		
//		File file = new File("/workspace/04_JDBC/05_ZooProject_임성혁/src/field/model/dto/" + type + "Field.java");
//		File file2 = new File("/workspace/04_JDBC/05_ZooProject_임성혁/src/animal/model/dto/" + type + ".java");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
//			if(!file.exists()) {
//				if(file.createNewFile()) {
//					System.out.println("\n=== " + type + "영역이 생성되었습니다 ===\n");
//				}
//				if(file2.createNewFile()) {
//					System.out.println("\n=== " + type + "모델이 생성되었습니다 ===\n");
//				}
//			}
			
			fw = new FileWriter("/workspace/04_JDBC/05_ZooProject_임성혁/src/field/model/dto/" + type + "Field.java");
			bw = new BufferedWriter(fw);
			
			String fieldSet = "package field.model.dto;\r\n"
					+ "\r\n"
					+ "import java.util.List;\r\n"
					+ "import animal.model.dto.Tiger;\r\n"
					+ "\r\n"
					+ "public class TigerField {\r\n"
					+ "\r\n"
					+ "	private String type;\r\n"
					+ "	private int aceptNumber;\r\n"
					+ "	private List<Tiger> tigerList;\r\n"
					+ "	\r\n"
					+ "	public TigerField() {}\r\n"
					+ "\r\n"
					+ "	public TigerField(String type, int aceptNumber, List<Tiger> tigerList) {\r\n"
					+ "		super();\r\n"
					+ "		this.type = type;\r\n"
					+ "		this.aceptNumber = aceptNumber;\r\n"
					+ "		this.tigerList = tigerList;\r\n"
					+ "	}\r\n"
					+ "\r\n"
					+ "	@Override\r\n"
					+ "	public String toString() {\r\n"
					+ "		return \"TigerField [type=\" + type + \", aceptNumber=\" + aceptNumber + \", tigerList=\" + tigerList + \"]\";\r\n"
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
					+ "	public List<Tiger> getTigerList() {\r\n"
					+ "		return tigerList;\r\n"
					+ "	}\r\n"
					+ "	public void setTigerList(List<Tiger> tigerList) {\r\n"
					+ "		this.tigerList = tigerList;\r\n"
					+ "	}\r\n"
					+ "	\r\n"
					+ "}";
		
			
			fw = new FileWriter("/workspace/04_JDBC/05_ZooProject_임성혁/src/animal/model/dto/" + type + ".java");
			bw = new BufferedWriter(fw);
			
			String animalSet = "";
			
			bw.write(fieldSet);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bw != null) bw.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void selectField() {
		// TODO Auto-generated method stub
		
	}

	public void updateField() {
		// TODO Auto-generated method stub
		
	}

	public void deleteField() {
		// TODO Auto-generated method stub
		
	}
	
}