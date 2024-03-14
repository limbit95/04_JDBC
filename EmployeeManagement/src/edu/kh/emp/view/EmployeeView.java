package edu.kh.emp.view;

import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.vo.Employee;

public class EmployeeView {
	
	EmployeeService service = new EmployeeService();
	
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {

		int input = 0;
		
		do {
			
			System.out.println("\n====== Employee ManageMent Program ======");
			System.out.println("1. 사원 등록");
			System.out.println("2. 사원 조회");
			System.out.println("3. 사원 수정");
			System.out.println("4. 사원 삭제");
			System.out.println("0. 프로그램 종료\n");
			
			System.out.print("메뉴 선택 : ");
			input = sc.nextInt();
			
			switch(input) {
				case 1: insertEmp(); break;
				case 2: selectEmp(); break;
				case 3: break;
				case 4: break;
				case 0: System.out.println("프로그램을 종료합니다."); break;
				default: System.out.println("잘못 입력하셨습니다."); break;
			}
			
		} while(input != 0);
		
	}
	
	
	/** 1. 사원 등록
	 * 
	 */
	public void insertEmp() {
		System.out.println("===== 사원 등록 =====");
		
		System.out.print("사원 번호 : ");
		int empId = sc.nextInt();
		System.out.print("이름 : ");
		String empName = sc.next();
		System.out.print("주민등록번호 : ");
		String empNo = sc.next();
		System.out.print("이메일 : ");
		String empEmail = sc.next();
		System.out.print("전화번호 : ");
		String empPhone = sc.next();
		System.out.print("부서명 : ");
		String empDeptTitle = sc.next();
		System.out.print("직급명 : ");
		String empJobName = sc.next();
		System.out.print("급여 : ");
		int empSalary = sc.nextInt();
		
		System.out.print("부서코드 : ");
		String empDeptCode = sc.next();
		System.out.print("직급코드 : ");
		String empJobCode = sc.next();
		System.out.print("급여 등급 : ");
		String empSalLevel= sc.next();
		System.out.print("보너스 : ");
		double empBonus = sc.nextDouble();
		System.out.print("사수 번호 : ");
		int empManagerId = sc.nextInt();
		
		Employee emp = new Employee(empId, empName, empNo, empEmail, empPhone, empDeptTitle,
		empJobName, empSalary, empDeptCode, empJobCode, empSalLevel, empBonus, empManagerId);

		try {
			int result = service.insert(emp);
			
			if(result > 0) {
				System.out.println("사원 등록 성공");
			} else {
				System.out.println("사원 등록 실패");
			}
		} catch(Exception e) {
			System.out.println("SQL 수행 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	
	/** 2. 사원 조회
	 * 
	 */
	public void selectEmp() {
		int input = 0;
		do {
			System.out.println("\n===== 사원 조회 =====");
			
			System.out.println("1. 전체 조회");
			System.out.println("2. 사번 조회");
			System.out.println("3. 이름 조회");
			System.out.println("4. 부서별 조회");
			System.out.println("5. 직급별 조회");
			System.out.println("0. 이전 메뉴\n");
			
			System.out.print("메뉴 선택 : ");
			input = sc.nextInt();
			
			
			
			switch(input) {
			case 1: service.selectAll(); break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 0: break;
			default: System.out.println("잘못 입력하셨습니다."); break;
			}
		} while(input != 0);
	}
	
	
}