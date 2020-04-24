package co.edu.app.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.edu.app.common.ConnectionManager;

public class MemberDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// 싱글톤 : static field
	static MemberDAO instance;

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}
	
	//로그인체크
	public boolean loginCheck(MemberVO member) {
		boolean result = false;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select username from member where userid = ? and userpw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getUserid());
			psmt.setString(2, member.getUserpw());

			rs = psmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return result;
	}

	// 등록
	public int insert(MemberVO member) {
		int r = 0;

		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();

			// 2. sql구문 준비
			String sql = "insert into member (userid, userpw, username,	jobid, hobby, info, gender, regdate)"
					+ " values ( ?, ?, ?, ?, ?, ?, ?, sysdate)";

			psmt = conn.prepareStatement(sql);

			// 3. 실행
			psmt.setString(1, member.getUserid());
			psmt.setString(2, member.getUserpw());
			psmt.setString(3, member.getUsername());
			psmt.setString(4, member.getJobid());
			psmt.setString(5, member.getHobby());
			psmt.setString(6, member.getInfo());
			psmt.setString(7, member.getGender());

			r = psmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 등록됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}

		return r;
	}

	// 수정
	public int update(MemberVO dto) {
		int r = 0;

		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();

			// 2. sql구문 준비
			String sql = "update member set userpw = ?,"
					+ "						username = ?,"
					+ "						jobid = ?,"
					+ "						hobby = ? ,"
					+ "						info = ?,"
					+ "						gender = ?,"
					+ " where userid = ? ";
			
			psmt = conn.prepareStatement(sql);

			// 3. 실행
			psmt.setString(1, dto.getUserpw());
			psmt.setString(2, dto.getUsername());
			psmt.setString(3, dto.getJobid());
			psmt.setString(4, dto.getHobby());
			psmt.setString(5, dto.getInfo());
			psmt.setString(6, dto.getGender());
			psmt.setString(7, dto.getUserid());
			
			r = psmt.executeUpdate();
			// 4. 결과처리
			System.out.println(r + " 건이 수정됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}

		return r;
	}

	// 삭제
	public int delete(MemberVO dto) {
		int r = 0;

		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			// 2. sql구문 준비
			String sql = "delete member where userid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserid());
			// 3. 실행
			r = psmt.executeUpdate();
			// 4. 결과처리
			System.out.println(r + " 건이 삭제됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}
		return r;
	}

	// 단건조회
	public MemberVO selectOne(MemberVO dto) {
		MemberVO member = new MemberVO();
		try {
			// 1. DB연결
			conn = ConnectionManager.getConnnect();
			// 2. sql구문 준비
			String sql = "select * from member where userid = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUserid());
			// 3. 실행
			rs = psmt.executeQuery();
			if (rs.next()) {
				member.setUserid(rs.getString("userid"));
				member.setUserpw(rs.getString("userpw"));
				member.setUsername(rs.getString("username"));
				member.setJobid(rs.getString("jobid"));
				member.setHobby(rs.getString("hobby"));
				member.setInfo(rs.getString("info"));
				member.setGender(rs.getString("gender"));
				member.setRegdate(rs.getString("regdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return member;
	}

	// 전체조회
	public List<MemberVO> selectAll() {
		List<MemberVO> datas = new ArrayList<MemberVO>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select * from member where userid = ? order by userid";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				MemberVO member = new MemberVO();

				member.setUserid(rs.getString("userid"));
				member.setUserpw(rs.getString("userpw"));
				member.setUsername(rs.getString("username"));
				member.setJobid(rs.getString("jobid"));
				member.setHobby(rs.getString("hobby"));
				member.setInfo(rs.getString("info"));
				member.setGender(rs.getString("gender"));
				member.setRegdate(rs.getString("regdate"));

				// dto를 리스트에 추가
				datas.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return datas;
	}
}
