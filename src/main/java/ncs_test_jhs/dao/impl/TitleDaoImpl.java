package ncs_test_jhs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ncs_test_jhs.dao.TitleDao;
import ncs_test_jhs.ds.MySqlDataSource;
import ncs_test_jhs.dto.Title;
import ncs_test_jhs.util.LogUtil;

public class TitleDaoImpl implements TitleDao {

	private static final TitleDaoImpl instance = new TitleDaoImpl();
	
	
	public static TitleDaoImpl getInstance() {
		return instance;
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql="select titleno, titlename from title where titleno=?";
		
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, title.getTitleNo());
			
			try(ResultSet rs = pstmt.executeQuery()){
				LogUtil.prnLog(pstmt);
				if(rs.next()) {
					return getTitle(rs);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select titleno, titlename from title";
	
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			List<Title> list = new ArrayList<>();	
		  while(rs.next()) {
			  list.add(getTitle(rs));
		  }
		  return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int titleNo = rs.getInt("titleno");
		String titleName = rs.getString("titlename");
		return new Title(titleNo, titleName);
	}

	@Override
	public int insertTitle(Title title) {
		String sql="insert title values(?,?)";
		int res = 0;
		try(Connection con = MySqlDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);)
				{
		  pstmt.setInt(1, title.getTitleNo());
		  pstmt.setString(2, title.getTitleName());
		  res = pstmt.executeUpdate();
				
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;

	}

	@Override
	public int updateTitle(Title title) {
		String sql ="update title set titlename=? where titleno =?";
		int res =0;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)
				
		{  pstmt.setString(1, title.getTitleName());
		   pstmt.setInt(2, title.getTitleNo());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

		
	}

	@Override
	public int deleteTitle(Title title) {
		String sql = "delete from title where titleno = ?";
		int res = 0;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);)
		{
			pstmt.setInt(1, title.getTitleNo());
			res = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;

	}

	@Override
	public int selectTitleByAllForInt() {
		String sql = "select titleno, titlename from title";
		int idx = 1;
		try(Connection con = MySqlDataSource.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			
			List<Title> list = new ArrayList<>();	
		  while(rs.next()) {
			  list.add(getTitle(rs));
			  idx++;	  
		  }
		  
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idx;
	
	}

}
