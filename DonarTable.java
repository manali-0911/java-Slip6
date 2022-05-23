import java.sql.*;
import javax.swing.*;

public class DonarTable
{
	public static void main(String args[])
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://192.168.1.21/ty21","ty21","");
			System.out.println("DB Connection successful");
			
			JFrame f = new JFrame("Donar details");
			f.setVisible(true);
			f.setSize(400,400);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from Donar");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			int rows=0;
			while(rs.next())
			{
				rows++;
			}
			rows++;
			System.out.println("Rows ="+rows+ "Cols ="+cols);
			JTable t = new JTable(rows,cols);
			for(int i=1;i<=cols;i++)
			{
				t.setValueAt(rsmd.getColumnName(i),0,i-1);
			}
			rs.close();
			 rs = s.executeQuery("Select * from Donar");

			
			int row=1;
			while(rs.next())
			{
				t.setValueAt(new Integer(rs.getInt(1)),row,0);
				t.setValueAt(rs.getString(2),row,1);
				t.setValueAt(rs.getString(3),row,2);
				t.setValueAt(rs.getString(4),row,3);
				row++;
			}
                        f.add(t);
			f.setVisible(true);
			f.setSize(400,400);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
