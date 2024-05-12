import java.awt.event.*;
import javax.swing.*;

import java.sql.*;

public class Output extends JFrame
{

	private static final long serialVersionUID = 1L;

	private JScrollPane scpDemo;   //滚动面板
	private JTable tabDemo;            //表格组件
	private JButton jbt,jbt1;
	int b=0;       //做一个判断区别，主要在查询平均成绩时用
	ResultSet r;
	Statement st;

	public Output()
	{
		super("输出显示");
		this.setSize(666, 500);
		this.setLayout(null);
		this.setLocation(340, 70);

		this .scpDemo=new JScrollPane();
		this .scpDemo.setBounds(27, 15, 600, 360);
		this .jbt=new JButton("显示数据");
		this .jbt.setBounds(130, 400, 106, 30);
		this .jbt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				jbt_ActionPerformed(ae);

			}
		});
		this .jbt1=new JButton("退出");
		this .jbt1.setBounds(410, 400, 106, 30);
		this .jbt1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent a)
			{
				try
				{
					jbt1_ActionPerformed(a);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}

			}
		});
		add(this .scpDemo);
		add(this .jbt);
		add(this .jbt1);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());
	}

	public void jbt_ActionPerformed(ActionEvent ae)
	{
		st=Connect.doConnect();
		int a=Search.p;       //p值不同，输出面板表格不同
		try
		{
			if(st==null)
			{
				JOptionPane.showMessageDialog(null, "数据库连接失败！");
				System.exit(0);
			}
			r=st.executeQuery(Search.Ss);
			int count=0;    //记录上一条语句查询得到的元组数量
			while(r.next())
			{
				count++;
			}
			r=st.executeQuery(Search.Ss);
			/*这里划重点，必须要再执行一次，经过上面的 while 循环，r 已经到最后了，
			 * 不重新执行的话，输出结果是空值，就是最后的表格数目信息是对的，但没有任何值*/
			if(a==1)  //学生信息的输出
			{
				Object[][] array=new Object[count][5];
				count=0;
				while(r.next())
				{
					array[count][0]=r.getString("Sno");
					array[count][1]=r.getString("Sname");
					array[count][2]=Integer.valueOf(r.getInt("Sage"));
					array[count][3]=r.getString("Ssex");
					array[count][4]=r.getString("Sdept");
					count++;
				}
				String[] title={"学号 ","姓名 ","年龄 "," 性别"," 系别"};
				this.tabDemo=new JTable(array,title);
			}

			else if(a==2)  //学生成绩
			{
				Object[][] array=new Object[count][3];
				count=0;
				while(r.next())
				{
					array[count][0]=r.getString("Sno");
					array[count][1]=r.getString("Cno");
					array[count][2]=Integer.valueOf(r.getInt("Grade"));
					count++;
				}
				String[] title={"学号 ","课程号 ","成绩"};
				this.tabDemo=new JTable(array,title);
			}

			else if(a==3)  //平均成绩
			{
				b=1;
				Object[][] array=new Object[count][2];
				count=0;
				while(r.next())
				{
					array[count][0]=r.getString("Cno");
					array[count][1]=Double.valueOf(r.getDouble("A_Grade"));
					count++;
				}
				String[] title={"课程号 ","平均成绩"};
				this.tabDemo=new JTable(array,title);
			}

			else if(a==4)  //课程表
			{
				b=2;
				Object[][] array=new Object[count][5];
				count=0;
				while(r.next())
				{
					array[count][0]=r.getString("Cno");
					array[count][1]=r.getString("Cname");
					array[count][2]=r.getString("Cpno");
					array[count][3]=Integer.valueOf(r.getInt("Ccredit"));
					array[count][4]=Integer.valueOf(r.getInt("Cnum"));
					count++;
				}
				String[] title={"课程号 ","课程名 ","先修课 "," 学分","课余量"};
				this.tabDemo=new JTable(array,title);
			}

			this .tabDemo.getTableHeader();
			this .scpDemo.getViewport().add(tabDemo);
		}
		catch (SQLException e1)
		{
			JOptionPane.showMessageDialog(null, "数据源错误","错误",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	public void jbt1_ActionPerformed(ActionEvent a) throws SQLException
	{
		if(Login.a==1)
		{
			if(b==1)
			{
				String s00="drop view C_G";
				st.executeUpdate(s00);
			}
			new Student();
			dispose();
		}
		else if(Login.a==2)
		{
			if(b==1)
			{
				String s00="drop view C_G";
				st.executeUpdate(s00);
			}
			new Teacher();
			dispose();
		}
	}

//	public static void main(String[] args){	new Output();}

}
