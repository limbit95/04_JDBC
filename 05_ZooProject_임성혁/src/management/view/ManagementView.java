package management.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import management.model.service.ManagementService;

public class ManagementView {

	private Scanner sc = new Scanner(System.in);
	private ManagementService managementService = new ManagementService();
	
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
		
		
		try {
			boolean result = managementService.createField(type, aceptNum);
			
		} catch (Exception e) {
			e.printStackTrace();
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