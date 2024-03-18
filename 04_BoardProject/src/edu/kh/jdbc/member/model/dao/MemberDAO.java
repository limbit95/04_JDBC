package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 회원 목록 조회 SQL 수행 DAO
	 * @param conn
	 * @return
	 */
	public List<Member> selectMemberList(Connection conn) throws Exception{
		List<Member> list = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectMemberList");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Member mem = new Member();
				
				mem.setMemberId(rs.getString("MEMBER_ID"));
				mem.setMemberName(rs.getString("MEMBER_NM"));
				mem.setMemberGender(rs.getString("MEMBER_GENDER"));
				
				list.add(mem);
			}
		}finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}

	/** 내 정보 수정 SQL 수행 DAO
	 * @param conn
	 * @param memberName
	 * @param memberGender
	 * @param loginMember
	 * @return
	 */
	public int updateMember(Connection conn, String memberName, String memberGender, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberGender);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 비밀번호 변경 SQL 수행 DAO
	 * @param conn
	 * @param newPw
	 * @param memberNo
	 * @return
	 */
	public int updatePassword(Connection conn, String newPw, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePassword");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 비밀번호 확인 SQL 수행 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return
	 */
	public int passwordCheck(Connection conn, String memberPw, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("passwordCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}