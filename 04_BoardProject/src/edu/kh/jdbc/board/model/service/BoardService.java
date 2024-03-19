package edu.kh.jdbc.board.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardService {

	private BoardDAO dao = new BoardDAO();

	/** 1. 게시글 목록 조회 서비스
	 * @return
	 */
	public List<Board> selectAllBoard() throws Exception{
		Connection conn = getConnection();
		
		List<Board> list = dao.selectAllBoard(conn);
		
		close(conn);
		
		return list;
	}

	/** 2. 게시글 상세 조회 서비스
	 * @param input
	 * @param memberNo
	 * @return
	 */
	public Board selectBoard(int input, int memberNo) throws Exception{
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. 게시글 상세 조회 DAO 메서드  호출
		Board board = dao.selectBoard(conn, input);
		
		// 3. 게시글이 조회된 경우
		if(board != null) {
			// 4. 조회수 증가 (단, 게시글 작성자와 로그인한 회원이 다를 경우에만 증가)
			if(board.getMemberNo() != memberNo) {
				// 5. 조회수 증가 DAO 메서드 호출 (UPDATE)
				int result = dao.updateReadCount(conn, input);
				
				// 6. 트랜잭션 제어 처리 + 데이터 동기화 처리
				if(result > 0) {
					commit(conn);
					
					// 조회된 board의 조회수 0
					// DB의 조회수는 1
					// -, 조회 결과인 board의 조회수도 1증가
					board.setReadCount(board.getReadCount() + 1);
				} else {
					rollback(conn);
				}
			}
		}
		
		close(conn);
		
		return board;
	}

	/** 3. 게시글 작성 서비스
	 * @param sb
	 * @param memberNo
	 * @return
	 */
	public int insertBoard(String boardTitle, StringBuilder sb, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertBoard(conn, boardTitle, sb, memberNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 본인이 작성한 게시글 수정 서비스
	 * @param input
	 * @param memberNo
	 * @return
	 */
	public int updateBoard(int input, String boardTitle, StringBuilder sb) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateBoard(conn, input, boardTitle, sb);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 본인이 작성한 게시글 삭제 서비스
	 * @param input
	 * @return
	 */
	public int deleteBoard(int input, String Check) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, input, Check);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
}