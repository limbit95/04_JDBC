package edu.kh.jdbc.member.model.service;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	/** 회원 목록 조회 서비스
	 * @return
	 */
	public List<Member> selectMemberList() throws Exception{
		Connection conn = getConnection();
		
		List<Member> list = dao.selectMemberList(conn);
		
		close(conn);
		
		return list;
	}

	/** 내 정보 수정 서비스
	 * @param memberName
	 * @param memberGender
	 * @param loginMember
	 * @return
	 */
	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, memberName, memberGender, memberNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 비밀번호 변경 서비스
	 * @param newPw
	 * @param memberNo
	 * @return
	 */
	public int updatePassword(String newPw, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, newPw, memberNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	

	/** 4. 비밀번호 변경 강사님 코드
	 * @param current
	 * @param newPw1
	 * @param memberNo
	 * @return
	 */
	public int updatePassword2(String current, String newPw1, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updatePassword2(conn, current, newPw1, memberNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	

	/** 비밀번호 확인 서비스
	 * @param memberPw
	 * @return
	 */
	public int passwordCheck(String memberPw, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.passwordCheck(conn, memberPw, memberNo);
		
		close(conn);
		
		return result;
	}
	

	/** 회원 탈퇴 서비스
	 * @param memberNo
	 * @return
	 */
	public int deleteMember(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteMember(conn, memberNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;
	}

	
	/** 숫자 6자리 보안코드 생성 서비스
	 * @return
	 */
	public String createSecurityCode() {
		StringBuffer code = new StringBuffer();
		
		Random ran = new Random(); // 난수 생성 객체
		
		for(int i = 0; i < 6; i++) {
			int x = ran.nextInt(10); // 0 이상 10미만 정수
			code.append(x); // StringBuffe 마지막에 생성된 난수 x를 이어붙임 
		}

		return code.toString();
	}

	
	/** 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return
	 */
	public int unRegisterMember2(String memberPw, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.unRegisterMember2(conn, memberPw, memberNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	
	
	
}