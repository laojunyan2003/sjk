import java.sql.*;

public class Connect
{
	public static Statement doConnect()
	{
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL?????????
        String connectDB = "jdbc:sqlserver://localhost:1433;DatabaseName=PPLP";// ?????
        try 
        {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");// ????????????��??????????????????
         } 
        catch (ClassNotFoundException e) 
        {
             e.printStackTrace();
             System.out.println("����ʧ��");
             //JOptionPane.showMessageDialog(this,"?????????????");
             System.exit(0);
         }
         System.out.println("����ʧ��");
         try 
         {
//        	 String user = "sa";
//             String password = "sa";
//             Connection con = DriverManager.getConnection(connectDB, user, password);// ????????????
             Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=PPLP;Encrypt=false");

             System.out.println("���ӳɹ�");
             Statement stmt = con.createStatement();
             return stmt;
		 } 
         catch (SQLException e) 
         {
               e.printStackTrace();
               System.out.println("����ʧ��");
               System.exit(0);
         }
		return null;		
	}
//	public static void main(String[] args) {
//		new Connect();
//	}

}