
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Student  extends JFrame implements ActionListener
{
	private JButton jb1,jb2,jb3,jb4;
	public JComboBox<String> jcom1;
	private static String[] oper={"选择查询项目","全部成绩","个人信息","本学期课表"};
	private static final long serialVersionUID = 1L;

	public Student()
	{
		super("学生登录界面");
		this.setBounds(600,240,300,100);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(2,3));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//第一行
		this.add(new Label("PLAES CHOOSE ",Label.CENTER));
		this.add(this .jcom1 =new JComboBox<String>(Student.oper));
		this .jcom1.addActionListener(this);
		this .jb1=new JButton("查询");
		this.add(this .jb1);
		this .jb1.addActionListener(this);
		//第二行
		this .jb2=new JButton("选课");
		this.add(this .jb2);
		this .jb2.addActionListener(this);
		this .jb3=new JButton("修改密码");
		this.add(this .jb3);
		this .jb3.addActionListener(this);
		this .jb4=new JButton("退出");
		this.add(this .jb4);
		this .jb4.addActionListener(this);

		this.setVisible(true);
		this.addWindowListener(new WinClose());

	}

	public void actionPerformed(ActionEvent e)
	{
		int i=jcom1.getSelectedIndex();    //接收下拉框的选择
		try
		{
			if (e.getSource()==jb4)   //退出
			{
				new Login();
				dispose();
			}

			else if(e.getSource()==jb2)    //选课
			{
				Search.doSearch(1, 1);
				new Select_C();
				dispose();
			}

			else if(e.getSource()==jb3)    //改密码
			{
				new Change_code();
				dispose();
			}

			else if(e.getSource()==jb1)    //查询
			{
				try
				{
					if(i!=0)
					{
						Search.doSearch(1, i+1);
						System.out.print(i);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(this,"请选择你想进行查询的选项！");
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}
		catch(SQLException w1)
		{
			w1.printStackTrace();
		}
	}

//	public static void main(String[] args)
//	{
//		new Student();
//	}
}
