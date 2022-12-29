package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Categories;
import Uti.ConnectDB;

public class DAO {
	public List<Categories> getAllCategories()
	{
		List<Categories> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = ConnectDB.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM categories where parent_id is null");
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				list.add(new Categories(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3)
						));
			}
		}catch(Exception e)
		{
			e.getMessage();
		}
		return list;
	}
	public void deleteCategory(String id)
	{
		String query="DELETE FROM categories WHERE id= ? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = ConnectDB.getConnection();
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		}catch(Exception e)
		{
			e.getStackTrace();
		}
	}
}
