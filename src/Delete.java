
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Delete extends JFrame implements ActionListener
{
	private TextField tex1,tex2;
	private JButton jb1,jb2,jb3;

	String s6,s7;

	private static final long serialVersionUID = 1L;

	public Delete()
	{
		super("删除界面");
		this.setBounds(500,250,440,100);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(2,3));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		//第一行
		this.add(new Label("请输入要删除学生学号",Label.LEFT));
		this.tex1=new TextField(15);
		this.add(this.tex1);
		this.tex2=new TextField(15);
		this.add(this.tex2);
		tex2.setEnabled(false);
		//第二行
		this.jb1=new JButton("重新输入/再次删除");
		this.add(this.jb1);
		this.jb1.addActionListener(this );
		this.jb2=new JButton("删除");
		this.add(this.jb2);
		this.jb2.addActionListener(this );
		this.jb3=new JButton("退出");
		this.add(this.jb3);
		this.jb3.addActionListener(this );
	}

	public void actionPerformed(ActionEvent e)
	{
		String s1=tex1.getText();

		if(tex1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"输入栏不能为空！");
		}
		else
		{
			if(e.getSource()==jb2)
			{
				int p=Teacher.a;
				if(p==1)     //删除学生信息
				{
					try
					{
						Statement st=Connect.doConnect();
						if(st!=null)
						{
							String a0="select * from Student where Sno=\'"+s1+"\'";
							//在数据库中查询是否有与输入相对应的元组
							ResultSet rs=st.executeQuery(a0);
							if(rs.next())
							{
								String a = "delete from Student where Sno=\'"+s1+"\'";
								st.executeUpdate(a);
								//System.out.println("插入数据成功");
								String s7="恭喜你，删除成功！";
								tex2.setText(s7);
							}
							else
							{
								JOptionPane.showMessageDialog(this,"该学号不存在，删除失败！");
							}

						}
						else
						{
							JOptionPane.showMessageDialog(this,"数据库连接失败！");
							System.exit(0);
						}
						st.close();// 关闭命令对象连接
					}
					catch (SQLException e1)
					{
						e1.printStackTrace();
						//System.out.println("数据库连接错误");
						String s6="很遗憾，删除失败！";
						tex2.setText(s6);
						System.exit(0);
					}
				}

				else if(p==2)   //删除课程信息
				{
					try
					{
						Statement st=Connect.doConnect();
						if(st!=null)
						{
							String a0="select * from Course where Cno=\'"+s1+"\'";
							ResultSet rs=st.executeQuery(a0);
							if(rs.next())
							{
								String a = "delete from Course where Cno=\'"+s1+"\'";
								st.executeUpdate(a);
								//System.out.println("插入数据成功");
								String s7="恭喜你，删除成功！";
								tex2.setText(s7);
							}
							else
							{
								JOptionPane.showMessageDialog(this,"该课序号不存在，删除失败！");
							}

						}
						else
						{
							JOptionPane.showMessageDialog(this,"数据库连接失败！");
							System.exit(0);
						}
						st.close();// 关闭命令对象连接
					}
					catch (SQLException e1)
					{
						e1.printStackTrace();
						//System.out.println("数据库连接错误");
						String s6="很遗憾，删除失败！";
						tex2.setText(s6);
						System.exit(0);
					}
				}
			}
		}
		if(e.getSource()==jb1)
		{
			//JOptionPane.showMessageDialog(this,"eeeeee！");
			tex1.setText(null);
			tex2.setText(null);
		}
		if(e.getSource()==jb3)
		{
			new Teacher();
			dispose();
		}
	}

//	public static void main(String[] args)
//	{
//		new Delete();
//	}
}
