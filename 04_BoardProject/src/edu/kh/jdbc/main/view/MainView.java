package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.MemberView;

import static edu.kh.jdbc.common.Session.*;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	
	private MemberView memberView = new MemberView(); 
	
	/**
	 *  메인 메뉴 출력 View
	 */
	public void mainMenu() {
		int input = 0;
		
		do {
			try {
				if(loginMember == null) { // 로그인 X
					System.out.println("\n===== 회원제 게시판 프로그램 =====\n");
					
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료");

					System.out.print("\n메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
						case 1: login(); break;
						case 2: signUp(); break;
						case 0: System.out.println("\n=== 프로그램 종료 === \n"); break;
						default: System.out.println("\n === 메뉴 번호만 입력해주세요 ===\n"); break;
					}
				} else { // 로그인 O
					System.out.println("\n=== 로그인 메뉴 ===\n");
					
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("3. 로그아웃");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
				
					switch(input) {
						case 1: memberView.memberMenu(); break;
						case 2: /* 게시판기능view */ break;
						case 3: 
							System.out.println("\n=== 로그아웃 되었습니다 ===\n");
							loginMember = null;
							break;
						case 0: System.out.println("\n=== 프로그램 종료 === \n"); break;
						default: System.out.println("\n === 메뉴 번호만 입력해주세요 ===\n"); break;
					}
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				e.printStackTrace();
				input = -1;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}while(input != 0);
	}

	
	/** 
	 * 로그인
	 */
	public void login() throws Exception{
		System.out.println("\n[로그인]\n");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		try {
			loginMember = service.login(memberId, memberPw);
			
			if(loginMember == null) {
				System.out.println("\n*** 아이디/비밀번호가 일치하지 않습니다 ***\n");
			} else {
				System.out.println("\n===== " + loginMember.getMemberName() + "님 환영합니다 =====\n");
			}
		} catch(Exception e) {
			System.out.println("\n*** 로그인 중 예외발생 ***\n");
			e.printStackTrace();
		}
		
	}


	/**
	 * 회원가입
	 */
	public void signUp() throws Exception{
		System.out.println("\n[회원가입]\n");
		
		String memberId = null;
		String memberPw = null;
		String pwConfirm = null;
		String memberName = null;
		String memberGender = null;
		
		try {
			// 아이디 입력
			// DB에 탈퇴하지 않은 회원 중
			// 입력한 아이디와 같은 아이디가 존재하면 중복으로 판정
			// 중복이 입력되지 않을 때까지 무한 반복
			
			while(true) {
				System.out.print("아이디 입력 : ");
				memberId = sc.next();
				
				// 아이디 중복 확인 서비스 호출
				// -> 중복인 경우 1, 아닌 경우 0 반환
				int result = service.idDuplicationCheck(memberId);
				
				if(result == 0) {
					System.out.println("\n=== 사용 가능한 아이디입니다 ===\n");
					break;
				} else {
					System.out.println("\n*** 이미 사용 중인 아이디입니다 ***\n");
				}
			}
			
			while(true) {
				System.out.print("비밀번호 입력 : ");
				memberPw = sc.next();
				System.out.print("비밀번호 확인 : ");
				pwConfirm = sc.next();
				
				if(memberPw.equals(pwConfirm)) {
					System.out.println("\n=== 비밀번호 일치 ===\n");
					break;
				} else {
					System.out.println("\n*** 비밀번호가 일치하지 않습니다 ***\n");
				}
			}
			
			System.out.print("이름 : ");
			memberName = sc.next();
			
			while(true) {				
				System.out.print("성별(M/F) : ");
				memberGender = sc.next().toUpperCase();
				
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				} else {
					System.out.println("\n*** M 또는 F만 입력해주세요 ***\n");
				}
			}
			
			Member mem = new Member();
			mem.setMemberId(memberId);
			mem.setMemberPw(memberPw);
			mem.setMemberName(memberName);
			mem.setMemberGender(memberGender);
			
			int result = service.signUp(mem);
			
			if(result > 0) {
				System.out.println("\n=== 회원가입 성공 ===\n");
			} else {
				System.out.println("\n*** 회원가입 실패 ***\n");
			}
			
		} catch(Exception e) {
			System.out.println("\n*** 회원 가입 중 예외 발생 ***\n");
			e.printStackTrace();
		}
	}
	
}