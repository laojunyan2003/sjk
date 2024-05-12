
import java.sql.*;
import javax.swing.*;

public class Search
{
	public  int i;   //接收信息，1：来自学生端；2：来自教师端
	public  int j;   //接受各自选项框的选择

	static ResultSet rs;

	static String Ss;   //向其他类传输字符串时的静态变量
	static int u;   //登录检验时的返回值

	static int p,q,m;

	static Statement st=Connect.doConnect();

	public static void doSearch(int i,int j) throws SQLException
	{
		if(st!=null)
		{
			if(i==0)   //登陆检验
			{
				switch(j)
				{
					case 0:  //学生登陆检验
						String  s0="select * from Student where Sno=\'"+Login.s1+"\' and scode=\'"+Login.s2+"\'";
						ResultSet rs=st.executeQuery(s0);
						if(rs.next())
						{
							u=1;
						}
						else
						{
							u=0;
						}
						break;
					case 1:   //教师登陆检验
						String  s1="select * from Teacher where Tno=\'"+Login.s1+"\' and Tcode=\'"+Login.s2+"\'";
						ResultSet rs1=st.executeQuery(s1);
						if(rs1.next())
						{
							u=1;
						}
						else
						{
							u=0;
						}
						break;
				}
			}
			else if(i==1)   //学生端操作
			{
				switch(j)
				{
					case 4:   //课程表显示
						String s1="select * from Course";
						rs=st.executeQuery(s1);
						if(rs.next())
						{
							Ss=s1;
							p=4;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 2:  //学生成绩
						String s2="select * from SC where Grade >0 and Sno=\'"+Login.s1+"\'";
						//st.execute(s1);
						rs=st.executeQuery(s2);
						if(rs.next())
						{
							Ss=s2;
							p=2;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 3:  //学生信息
						String s3="select Sno,Sname,Sage,Ssex,Sdept from Student where Sno=\'"+Login.s1+"\'";
						rs=st.executeQuery(s3);
						if(rs.next())
						{
							Ss=s3;
							p=1;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 1:  //???不知道查什么的
						String s4="select * from SC where Grade =0 and Sno=\'"+Login.s1+"\'";
						rs=st.executeQuery(s4);
						if(rs.next())
						{
							Ss=s4;
							p=2;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
				}
			}

			else if(i==2)  //教师端操作
			{
				switch(j)
				{
					case  1:   //全部学生成绩
						String s1="select * from SC where Grade>0";
						//st.execute(s1);
						rs=st.executeQuery(s1);
						if(rs.next())
						{
							Ss=s1;
							p=2;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case  2:   //全部学生信息
						String s2="select Sno,Sname,Sage,Ssex,Sdept from Student ";
						rs=st.executeQuery(s2);
						if(rs.next())
						{
							Ss=s2;
							p=1;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
//				case  3:
//					q=1;
//					m=1;
//				    new Input();
////					String ss=Input.getString();
////					String s3="select Cno,Grade from SC  where Sno=\' "+ss+"\'";
////					rs=st.executeQuery(s3);
////					if(rs.next())
////					 {
////						Ss=s3;
////	                    new Output();
////	                  }
////	                else
////	                {
////	                    JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
////	                }
//					break;
//				case  4:
//					new Input();
//					String sss=Input.s;
//					String s4="select * from Student  where Sno=\' "+sss+"\'";
//					rs=st.executeQuery(s4);
//					if(rs.next())
//					 {
//						Ss=s4;
//	                        new Output();
//	                  }
//	                else
//	                {
//	                    JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
//	                }
//					break;
					case  3:   //全部课程的平均成绩
						String s00="create view C_G(Cno,A_Grade)  "+
								" as  "+
								"  (SELECT  Cno,avg(Grade)  FROM SC group by Cno)";     //建立视图
						st.executeUpdate(s00);
						String s5="SELECT *  FROM C_G where A_Grade >0";
						rs=st.executeQuery(s5);
						if(rs.next())
						{
							Ss=s5;
							p=3;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
//				case  6:
//					new Input();
//					String ss1=Input.s;
//					String s6="SELECT  Cno,avg(Grade)  FROM SC group by Cno having Cno=\'"+ss1+"\'";
//					rs=st.executeQuery(s6);
//					if(rs.next())
//					 {
//						Ss=s6;
//	                        new Output();
//	                  }
//	                else
//	                {
//	                    JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
//	                }
//					break;
					case 4:   //选课结果
						String s7="select * from SC ";
						//String s7="select * from SC where Grade is not null";
						rs=st.executeQuery(s7);
						if(rs.next())
						{
							Ss=s7;
							p=2;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case  5:    //课表信息查询
						String s8="select * from Course";
						rs=st.executeQuery(s8);
						if(rs.next())
						{
							Ss=s8;
							p=4;
							new Output();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"查询失败，请检查输入!","错误!",JOptionPane.ERROR_MESSAGE);
						}
						break;
					case  6:    //更改学生信息
						Insert.x=1;
						new Insert();
						break;
					case 7:   //更改学生成绩
						JOptionPane.showMessageDialog(null,"成绩仅可为整数！");
						new Change_Grade();
						break;
					case 8:   //更改密码
						new Change_code();
						break;
					case 9:  //插入课程
						new Insert_C();
						break;
					case  10:   //删除课程
						new Delete();
						break;
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "数据库连接失败！");
			System.exit(0);
		}
	}

//	public static void main(String[] args) {	}

}
