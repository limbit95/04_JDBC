package main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import management.view.ManagementView;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private ManagementView managementView = new ManagementView();
	
	
	public void mainMenu() {
		int input = 0;
		do {
			try {
				System.out.println("\n=== Zoo Tycoon ===\n");
			
				System.out.println("1. Go to the Zoo");
				System.out.println("2. Zoo Management");
				System.out.println("0. Program Exit");
				
				System.out.print("\nselect menu : ");
				input = sc.nextInt();
				
				switch(input) {
					case 1: enterZoo(); break;
					case 2: managementView.zooManagementMenu(); break;
					case 0: System.out.println("\n=== 프로그램을 종료합니다 ===\n"); break;
					default: System.out.println("\n*** 메뉴 번호만 입력해주세요 ***\n"); input = -1; break;
				}
			} catch(InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				e.printStackTrace();
			}
			
		}while(input != 0);
		
	}


	/**
	 * 1. 동물원 입장
	 */
	public void enterZoo() {
		System.out.println("\n=== Welcome to the Zoo Tycoon ===\n");
		
		System.out.println("");
	}

}