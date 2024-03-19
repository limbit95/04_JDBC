package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("Board-sql.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 1. 게시글 목록 조회 SQL 수행 DAO
	 * @param conn
	 * @return
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception{
		List<Board> list = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setCommentCount(rs.getInt("COMMENT_COUNT"));
				
				list.add(board);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}

	/** 2. 게시글 상세 조회 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return
	 */
	public Board selectBoard(Connection conn, int input) throws Exception{
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	/** 조회수 증가 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return
	 */
	public int updateReadCount(Connection conn, int input) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReadCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 3. 게시글 작성 SQL 수행 DAO
	 * @param conn
	 * @param sb
	 * @param memberNo
	 * @return
	 */
	public int insertBoard(Connection conn, String boardTitle, StringBuilder sb, int memberNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, sb.toString());
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 본인이 작성한 게시글 수정 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return
	 */
	public int updateBoard(Connection conn, int input, String boardTitle, StringBuilder sb) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, sb.toString());
			pstmt.setInt(3, input);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 본인이 작성한 게시글 삭제 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return
	 */
	public int deleteBoard(Connection conn, int input, String Check) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			pstmt.setString(2, Check);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}