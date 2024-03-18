package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;
import static edu.kh.jdbc.common.Session.*;

/** 회원 전용 화면
 * 
 */
public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	private MemberService service = new MemberService();

	/**
	 * 회원 기능 메뉴 View
	 */
	public void memberMenu() {
		int input = 0;
		
		do {
			try {
				System.out.println("\n=== 회원 기능 ===\n");
				
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회(아이디, 이름, 성별");
				System.out.println("3. 내 정보 수정");
				System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
				System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE)");
				
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");
	
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				
				switch(input) {
					case 1: selectMyInfo(); break;
					case 2: selectMemberList(); break;
					case 3: updateMember(); break;
					case 4: updatePassword(); break;
					case 5: /*if(unRegisterMenu());*/ return; 
					case 9: System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n"); break;
					case 0: 
						System.out.println("\n===== 프로그램 종료 =====\n");
						// JVM 강제 종료 구문
						// 매개변수는 기본 0, 다른 숫자는 오류를 의미
						System.exit(0);
						break;
					default: System.out.println("\n*** 메뉴 번호만 입력해주세요 ***\n"); break;
				}
			} catch(InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine();
				input = -1;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}while(input != 9);
	}

	
	/**
	 * 1. 내 정보 조회
	 */
	public void selectMyInfo() {
		System.out.println("\n=== 내 정보 조회 ===\n");
		
		// 회원 번호, 아이디, 이름, 성별(남/여), 가입일
		// Sessiong.loginMember 이용
		System.out.println("회원 번호 : " + loginMember.getMemberNo());
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		if(loginMember.getMemberGender().equals("M")) {
			System.out.println("성별 : 남");
		} else {			
			System.out.println("성별 : 여");
		}
		System.out.println("가입일 : " + loginMember.getEnrollDate());
	}

	
	/**
	 * 2. 회원 목록 조회(아이디, 이름, 성별)
	 */
	public void selectMemberList() throws Exception{
		System.out.println("\n=== 회원 목록 조회 ===\n");
		
		List<Member> memberList = service.selectMemberList();
		
		if(memberList.isEmpty()) {
			System.out.println("\n*** 조회 결과가 없습니다 ***\n");
		} else {
			for(int i = 0; i < memberList.size(); i++) {
				System.out.printf("%d\t\t%s\t\t%s\t\t%s \n", 
				i + 1, 
				memberList.get(i).getMemberId(), 
				memberList.get(i).getMemberName(),
				memberList.get(i).getMemberGender());
			}
		}
	}

	
	/**
	 * 3. 내 정보 수정(이름, 성별)
	 */
	public void updateMember() throws Exception{
		System.out.println("\n=== 내 정보 수정 ===\n");
		
		System.out.print("수정할 이름 : ");
		String memberName = sc.next();
		
		String memberGender = null;
		
		while(true) {
			System.out.print("수정할 성별(M/F) : ");
			memberGender = sc.next().toUpperCase();
			if(memberGender.equals("M") || memberGender.equals("F")) {
				break;
			}else {
				System.out.println("\n*** M 또는 F를 입력해주세요 ***\n");
			}
		}
		
		try {	
			int result = service.updateMember(memberName, memberGender, loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n=== " + loginMember.getMemberId() + "님의 정보가 수정되었습니다 ===\n");
				loginMember.setMemberName(memberName);
				loginMember.setMemberGender(memberGender);
			} else {
				System.out.println("\n*** 수정 실패 ***\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)
	 */
	public void updatePassword() throws Exception{
		System.out.println("\n=== 비밀변호 변경 ===\n");
		
		String newPw = null;
		String pwConfirm = null;
		
		int cnt = 0;
		while(cnt < 3) {
			System.out.print("비밀번호 확인 : ");
			String memberPw = sc.next();
			
			int result = service.passwordCheck(memberPw, loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n=== 비밀번호 일치 ===\n");
				cnt = 0;
				break;
			} else {
				cnt++;
				System.out.println("\n*** 비밀번호가 일치하지 않습니다 ***");
				System.out.println("비밀번호 입력 시도 가능 횟수 : (" + cnt + "/3)\n");
			}
		}
		
		if(cnt == 3) {
			System.out.println("\n*** 비밀번호 입력 시도 횟수 초과로 10초 후에 다시 시도해주세요 ***\n");
			return;
		}
		
		while(true) {
			System.out.print("변경할 새 비밀번호 : ");
			newPw = sc.next();
			System.out.print("변경할 새 비밀번호 확인 : ");
			pwConfirm = sc.next();
			
			if(newPw.equals(pwConfirm)) {
				System.out.println("\n=== 비밀번호 일치 ===\n");
				break;
			} else {
				System.out.println("\n*** 비밀번호가 일치하지 않습니다 ***\n");
			}
		}
		
		int result = service.updatePassword(newPw, loginMember.getMemberNo());
		
		if(result > 0) {
			System.out.println("\n=== 비밀번호 수정 성공 ===\n");
			loginMember.setMemberPw(newPw);
		} else {
			System.out.println("\n*** 비밀번호 수정 실패 ***\n");
		}
	}
	
}