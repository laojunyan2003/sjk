import java.sql.*;

public class Connect
{
	public static Statement doConnect()
	{
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL?????????
        String connectDB = "jdbc:sqlserver://localhost:1433;DatabaseName=PPLP";// ?????
        try 
        {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// ????????????棬??????????????????
         } 
        catch (ClassNotFoundException e) 
        {
             e.printStackTrace();
             System.out.println("加载失败");
             //JOptionPane.showMessageDialog(this,"?????????????");
             System.exit(0);
         }
         System.out.println("加载失败");
         try 
         {
//        	 String user = "sa";
//             String password = "sa";
//             Connection con = DriverManager.getConnection(connectDB, user, password);// ????????????
             Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=PPLP;Encrypt=false");

             System.out.println("连接成功");
             Statement stmt = con.createStatement();
             return stmt;
		 } 
         catch (SQLException e) 
         {
               e.printStackTrace();
               System.out.println("连接失败");
               System.exit(0);
         }
		return null;		
	}
//	public static void main(String[] args) {
//		new Connect();
//	}

}