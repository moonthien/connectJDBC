package dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.LopHoc;

public class LopHoc_Dao {
	public List<LopHoc> getAllLopHoc(){
		List<LopHoc> dslophoc = new ArrayList<LopHoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from lopHoc";
			Statement stament = con.createStatement();
			ResultSet rs = stament.executeQuery(sql);
			while(rs.next()) {
				dslophoc.add(new LopHoc(
						rs.getString("maLop"),
						rs.getString("tenLop"),
						rs.getString("giaoVienCN")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dslophoc;

	}
	public void addLophoc(LopHoc lop) {
		Connection con  = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into Lophoc values(?,?,?)");
			stmt.setString(1, lop.getMaLop());
			stmt.setString(2, lop.getTenLop());
			stmt.setString(3, lop.getGiaoVienCN());

			stmt.executeUpdate();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}

	public void xoa(String malop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from LopHoc where malop = ?";

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, malop);
			stmt.executeUpdate();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}


	public void sua(LopHoc lop) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "update LopHoc " 
				+ "set Tenlop =?," 
				+ "GiaovienCN =? " 
				+ "where maLop =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, lop.getTenLop());
			stmt.setString(2, lop.getGiaoVienCN());
			stmt.setString(3, lop.getMaLop());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	private void close(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}


}
