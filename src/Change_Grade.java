
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Change_Grade extends JFrame implements ActionListener
{
	private TextField tx1,tx2,tx3,tx4;
	private JButton jb1,jb2,jb3;

	String s6;

	private static final long serialVersionUID = 1L;

	public Change_Grade()
	{
		super("输入界面");
		this.setBounds(600,240,500,120);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(3,3));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		//第一行
		this.add(new Label("ID",Label.CENTER));
		this.add(new Label("COURSE ID",Label.CENTER));
		this.add(new Label("GRADE",Label.CENTER));
		//第二行
		this.tx1=new TextField(15);
		this.add(this.tx1);
		this.tx2=new TextField(15);
		this.add(this.tx2);
		this.tx3=new TextField(15);
		this.add(this.tx3);
		//第三行
		this.jb1=new JButton("重新输入");
		this.add(this.jb1);
		this.jb1.addActionListener(this );
		this.jb2=new JButton("确定");
		this.add(this.jb2);
		this.jb2.addActionListener(this );
		this.jb3=new JButton("退出");
		this.add(this.jb3);
		this.jb3.addActionListener(this );
	}

	public void actionPerformed(ActionEvent e)
	{
		String s1=tx1.getText();
		String s2=tx2.getText();
		String s3=tx3.getText();
		if(s1.equals("")||s2.equals("")||s3.equals(""))
		{
			JOptionPane.showMessageDialog(this, "输入不能为空！");
		}
		else
		{
			if(e.getSource()==jb2)
			{
				try
				{
					Statement st=Connect.doConnect();
					ResultSet rs;
					if(st!=null)
					{
						String a01="select * from Student where Sno=\'"+s1+"\'";
						//查询是否有与该学号对应的学生
						rs=st.executeQuery(a01);
						if(rs.next())
						{
							String a02="select * from Course where Cno=\'"+s2+"\'";
							rs=st.executeQuery(a02);
							if(rs.next())     //查询是否有与该课序号对应的课程信息
							{
								String a0="select * from SC where Sno=\'"+s1+"\' and Cno=\'"+s2+"\'";
								rs=st.executeQuery(a0);

								if(rs.next())     //查询该学生是否选修了该课程
								{
									//先删除对应的信息
									String a1="delete from SC  where Sno=\'"+s1+"\' and Cno=\'"+s2+"\'";
									int b1=st.executeUpdate(a1);
									if(b1!=0)
									{
										//重新插入信息
										String a2="insert into SC (Sno,Cno,Grade)  values (\'" + s1 +"\',\'"+ s2 +"\',\'" +s3+"\')" ;
										int b2=st.executeUpdate(a2);
										if(b2!=0)
										{
											JOptionPane.showMessageDialog(this, "修改成功!");
											new Teacher();
											dispose();
										}
										else
										{
											String a00="步骤二错误，修改失败";
											tx4.setText(a00);
										}
									}
									else
									{
										String a00="步骤一错误，修改失败";
										tx4.setText(a00);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(this, "该学生没有选修该课程，修改失败!");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(this, "该课程不存在，修改失败!");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(this, "该学生不存在，修改失败!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "数据库连接失败！");
					}
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
		if(e.getSource()==jb1)
		{
			tx1.setText(null);
			tx2.setText(null);
			tx3.setText(null);
		}
		else if(e.getSource()==jb3)
		{
			new Teacher();
			dispose();
		}
	}

//     public static void main(String[] args) {	new  Change_Grade();	}

}
