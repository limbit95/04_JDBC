package edu.kh.jdbc.member.model.service;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

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
	
	
	
}