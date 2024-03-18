package edu.kh.jdbc.main.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MainService {

	private MainDAO dao = new MainDAO();

	
	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		Connection conn = getConnection();
		
		Member mem = dao.login(conn, memberId, memberPw);
		
		close(conn);
		
		return mem;
	}

	

	/** 아이디 중복 검사 서비스
	 * @param memberId
	 * @return
	 */
	public int idDuplicationCheck(String memberId) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.idDuplicationCheck(conn, memberId);
		
		close(conn);
		
		return result;
	}
	
	
	/** 회원가입 서비스
	 * @param mem
	 * @return
	 */
	public int signUp(Member mem) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, mem);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
}