package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Comment;
import edu.kh.jdbc.board.model.service.BoardService;
import static edu.kh.jdbc.common.Session.*;

public class BoardView {

	private Scanner sc = new Scanner(System.in);
	private BoardService service = new BoardService();
	
	// 댓글 화면 출력 객체
	private CommentView commentView = new CommentView();
	
	public void boardMenu() {
		int input = -1;
		
		do {
			try {
				System.out.println("\n===== 게시판 기능 =====\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
				System.out.println("3. 게시글 작성");
				// 제목, 내용(StringBuffer 이용) 입력
				// -> 게시글 삽입 서비스(제목, 내용, 로그인 회원 번호) 호출
				
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println();
				
				switch(input) {
				case 1: selectAllBoard(); break; // 게시글 목록 조회
				case 2: selectBoard(); break; // 게시글 상세 조회
				case 3: insertBoard(); break; // 게시글 등록(삽입)
				
				case 9:
					System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n");
					break;
				
				case 0:
					System.out.println("\n=== 프로그램 종료 ===\n");
					System.exit(0);
				default: System.out.println("\n*** 메뉴 번호만 입력 해주세요 ***\n"); 
				}
				
				System.out.println();
				
			}catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다***\n");
				sc.nextLine(); // 입력버퍼에 잘못된 문자열 제거
				input = -1; // while문 종료 방지
			}
			
		}while(input != 9);
	}

	/**
	 * 1. 게시글 목록 조회
	 */
	public void selectAllBoard() {
		System.out.println("\n=== 게시글 목록 조회 ===\n");
		
		List<Board> boardList = null;
		
		try {
			boardList = service.selectAllBoard();
			
			if(boardList.isEmpty()) {
				System.out.println("\n*** 조회된 게시글이 존재하지 않습니다. ***\n");
				return;
			} else {
				for(Board board : boardList) {
					System.out.println(board);
				}
			}
			
		} catch(Exception e) {
			System.out.println("\n*** 게시글 목록 조회 중 예외 발생 ***\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 2. 게시글 상세 조회 + 댓글 목록 조회
	 */
	public void selectBoard() {
		System.out.println("\n=== 게시글 상세 조회 ===\n");
		
		// 게시글 번호 입력
		// 1) 번호가 일치하는 게시글이 있으면 조회
		//    -> 조회수 증가(단, 자신이 작성한 게시글일 경우 조회수 증가X)
		//    -> 자신이 작성한 게시글일 경우 수정/삭제 기능 노출
		// + 댓글 목록 / 댓글 기능 추가 예정
		
		// 2) 번호가 일치하는 게시글 없으면
		//    -> 해당 게시글이 존재하지 않습니다
		
		System.out.print("게시글 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		
		// 게시글 상세 조회 서비스 호출
		try {
			Board board = service.selectBoard(input, loginMember.getMemberNo());
			
			if(board == null) {
				System.out.println("\n*** 해당 게시글이 존재하지 않습니다 ***\n");
				return;
			} 
			
			System.out.println("--------------------------------------------------------");
			System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
			System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
					board.getMemberName(), board.getCreateDate(), board.getReadCount());
			System.out.println("--------------------------------------------------------\n");
			System.out.println(board.getBoardContent());
			System.out.println("--------------------------------------------------------");
			
			// ****************************************************************************
			// 해당 게시글의 댓글 목록 조회
			if(!board.getCommentList().isEmpty()) {
				for(Comment c : board.getCommentList()) {
					System.out.println(c);
					System.out.println("--------------------------------------------------------");
				}
			}
			
			// 댓글 메뉴 출력
			// 1) 댓글 등록 - 누가 몇 번 게시글에 작성하는가?
			// 2) 댓글 수정 - 누가 몇 번 게시글에 있는 몇 번 댓글을 수정할 것인가?
			// 3) 댓글 삭제 - 누가 몇 번 게시글에 잇는 몇 번 댓글을 삭제할 것인가?
			commentView.commentMenu(input);
			
			// ****************************************************************************
			
			// 여기서부터
//			if(board.getMemberNo() == loginMember.getMemberNo()) {
//				System.out.println("1. 수정 / 2. 삭제");
//				System.out.print("\n선택 : ");
//				int input2 = sc.nextInt();
//				sc.nextLine();
//				
//				if(input2 == 1) {
//					System.out.print("\n수정할 제목 입력 : ");
//					String boardTitle = sc.nextLine();
//					
//					StringBuilder sb = new StringBuilder();
//					
//					while(true) {
//						System.out.print("수정할 내용 입력(종료 q만 입력) : ");
//						String content = sc.nextLine();
//						
//						if(content.equals("q")) {
//							break;
//						}
//						
//						sb.append(content).append(" \n");
//					}
//					
//					System.out.print("수정한 게시글을 저장하시겠습니까? (Y/N) : ");
//					char charInput = sc.next().toUpperCase().charAt(0);
//					
//					if(charInput == 'Y') {
//						int result = service.updateBoard(input, boardTitle, sb);
//						if(result > 0) {
//							System.out.println("\n=== 게시글 수정 완료 ===\n");
//							return;							
//						} else {
//							System.out.println("\n*** 게시글 수정 실패 ***\n");
//							return;
//						}
//					} 
//					if(charInput == 'N') {
//						System.out.println("\n*** 게시글 수정 취소 ***\n");
//						return;
//					}
//				}
//				
//				if(input2 == 2) {
//					System.out.print("\n정말로 삭제하시겠습니까? (Y/N) : ");
//					char charInput2 = sc.next().toUpperCase().charAt(0);
//					
//					if(charInput2 == 'N') {
//						System.out.println("\n=== 게시글 삭제 취소 ===\n");
//						return;
//					}
//					
//					if(charInput2 == 'Y') {
//						System.out.print("비밀번호 확인 : ");
//						String Check = sc.next();
//						
//						int result = service.deleteBoard(input, Check);
//						
//						if(result > 0) {
//							System.out.println("\n=== 게시글 삭제 완료 ===\n");
//							
//							return;
//						} else {
//							System.out.println("\n*** 비밀번호가 일치하지 않습니다 ***\n");
//						}
//					}
//				}
//			}
			// 여기까지 나 혼자 시도해본 부분
			
			
			
		} catch(Exception e) {
			System.out.println("\n*** 게시글 상세 조회 중 예외 발생 ***\n");
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 3. 게시글 작성
	 */
	public void insertBoard() {
		System.out.println("\n=== 게시글 작성 ===\n");
		
		System.out.print("제목 입력 : ");
		String boardTitle = sc.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			System.out.print("내용 입력(종료 q만 입력) : ");
			String content = sc.nextLine();
			
			if(content.equals("q")) {
				break;
			}
			
			sb.append(content).append(" \n");
		}
		
		try {
			System.out.print("작성한 게시글을 저장하시겠습니까? (Y/N) : ");
			char input = sc.next().toUpperCase().charAt(0);
			
			if(input == 'Y') {
				int result = service.insertBoard(boardTitle, sb, loginMember.getMemberNo());
				
				if(result > 0) {
					System.out.println("\n=== 게시글 저장 완료 ===\n");
				} else {
					System.out.println("\n*** 게시글 저장 실패 ***\n");
				}
			} 
			
			if(input == 'N') {
				System.out.println("\n*** 게시글 저장 취소 ***\n");
			}
		} catch(Exception e) {
			System.out.println("\n*** 게시글 작성 중 예외 발생 ***\n");
			e.printStackTrace();
		}
	}
	
	
	

	
}
