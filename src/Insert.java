
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Insert extends JFrame implements ActionListener
{
	private TextField jtx1,jtx2,jtx3,jtx4,jtx5,jtx6,jtx7;
	private JButton jb1,jb2,jb3;

	String s6,s7;
	static int x=0;    //做判断，看是输入学生信息，还是更改学生信息

	private static final long serialVersionUID = 1L;
	public Insert()
	{
		super("请输入信息");
		this.setBounds(380,240,750,120);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(3,5));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		//第一行
		this.add(new Label("ID",Label.CENTER));
		this.add(new Label("NAME",Label.CENTER));
		this.add(new Label("AGE",Label.CENTER));
		this.add(new Label("SEX",Label.CENTER));
		this.add(new Label("STUDY",Label.CENTER));
		//第二行
		this.jtx1=new TextField(15);
		this.add(this.jtx1);
		jtx1.setText("0");
		this.jtx2=new TextField(15);
		this.add(this.jtx2);
		this.jtx3=new TextField(15);
		this.add(this.jtx3);
		jtx3.setText("0");
		this.jtx4=new TextField(15);
		this.add(this.jtx4);
		this.jtx5=new TextField(15);
		this.add(this.jtx5);
		//第三行
		this.jb2=new JButton("重新输入/再次添加");
		this.add(this.jb2);
		this.jb2.addActionListener(this );
		this.jtx6=new TextField(15);
		this.add(this.jtx6);
		jtx6 .setEnabled(false);
		this.jb1=new JButton("确定");
		this.add(this.jb1);
		this.jb1.addActionListener(this );
		this.jtx7=new TextField(15);
		this.add(this.jtx7);
		jtx7 .setEnabled(false);
		this.jb3=new JButton("退出");
		this.add(this.jb3);
		this.jb3.addActionListener(this );
	}

	public void actionPerformed(ActionEvent e)
	{
		String s1=jtx1.getText();
		String s2=jtx2.getText();
		int s3=Integer.parseInt(jtx3.getText());
		String s4=jtx4.getText();
		String s5=jtx5.getText();

		if(jtx1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"学号栏不能为空！");
		}

		else
		{
			if(e.getSource()==jb1)
			{
				try
				{
					Statement st=Connect.doConnect();
					if(st!=null)
					{
						if(x==1)  //修改学生信息
						{
							String  a="select * from  Student where Sno=\'"+s1+"\'";
							ResultSet rs=st.executeQuery(a);
							if(rs.next())
							{
								String Sname=rs.getString("Sname");
								int Sage=Integer.valueOf(rs.getInt("Sage"));
								String Ssex=rs.getString("Ssex");
								String Sdept=rs.getString("Sdept");
								int i=Check(s1,s3, s4);
								if(i==0)//,,,,,,,,
								{
									JOptionPane.showMessageDialog(this,"学号应为9位数，学生年龄应在15至25岁，学生性别只能为‘男’或‘女’,请输入适当的学生信息！！");
								}
								else
								{
									int a1=s2.compareTo(Sname);
									if(a1!=0)    //新输入的Sname不同，更新
									{
										String b1="update Student set  Sname =\'"+s2+"\' where Sno=\'"+s1+"\'";
										st.executeUpdate(b1);
									}
									if(Sage!=s3)   //新输入的Sage不同，更新
									{
										String b2="update Student set  Sage =\'"+s3+"\' where Sno=\'"+s1+"\'";
										st.executeUpdate(b2);
									}
									int a3=s4.compareTo(Ssex);
									if(a3!=0)    //新输入的Ssex不同，更新
									{
										String b3="update Student set  Ssex =\'"+s4+"\' where Sno=\'"+s1+"\'";
										st.executeUpdate(b3);
									}
									int a4=s5.compareTo(Sdept);
									if(a4!=0)    //新输入的Sdept不同，更新
									{
										String b4="update Student set  Sdept =\'"+s5+"\' where Sno=\'"+s1+"\'";
										st.executeUpdate(b4);
									}
									String s7="恭喜你，更改成功！";
									jtx7.setText(s7);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(this,"学号输入错误，无法更改，请检查输入！");
							}
						}
						else   //增加学生信息
						{
							String  a="select * from  Student where Sno=\'"+s1+"\'";
							ResultSet rs=st.executeQuery(a);
							if(rs.next())
							{
								JOptionPane.showMessageDialog(this,"该学号已存在，请勿重复添加！");
							}
							else
							{
								int i=Check(s1,s3, s4);
								if(i==0)
								{
									JOptionPane.showMessageDialog(this,"学号应为9位数，学生年龄应在15至25岁，学生性别只能为‘男’或‘女’,请输入适当的学生信息！！");
								}

								else
								{
									String a1 = "insert into Student (Sno,Sname,Sage,Ssex,Sdept,Scode)"
											+ " values(\'" + s1 +"\',\'"+ s2 +"\',\'" +s3+"\',\'" + s4 + "\',\'" + s5 + "\',\'00000\')";
									//插入学生信息，初始学生密码为00000
									st.executeUpdate(a1);
									//System.out.println("插入数据成功");
									String s7="恭喜你，添加成功！";
									jtx7.setText(s7);
								}
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"数据库连接失败！");
						System.exit(0);
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
					//System.out.println("数据库连接错误");
					String s6="很遗憾，插入失败！";
					jtx6.setText(s6);
					System.exit(0);
				}
			}
		}

		if(e.getSource()==jb2)
		{
			jtx1.setText("0");
			jtx2.setText(null);
			jtx3.setText("0");
			jtx4.setText(null);
			jtx5.setText(null);
			jtx6.setText(null);
			jtx7.setText(null);
		}

		else if(e.getSource()==jb3)
		{
			new Teacher();
			dispose();
		}
	}

	public int Check(String Sno,int  Sage,String Ssex)   //检查学生年龄和性别输入是否合法
	{
		String n=Sno;
		int a=Sage;
		String aS=Ssex;
		if((a>=15&&a<=25)&&(aS.compareTo("男")==0||aS.compareTo("女")==0)&&(n.length()==9))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

//	public static void main(String[] args)
//	{
//		new Insert();
//	}
}
