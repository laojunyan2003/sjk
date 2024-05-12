
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Teacher extends JFrame implements ActionListener
{
	private JButton jb1,jb2,jb3,jb4,jb5;
	public JComboBox<String> jcom1,jcom2;
	private static String[] oper1={" ","全部学生成绩","全部学生信息","所有课程平均成绩","选课结果","课表查询"};
	private static String[] oper2={" ","学生信息","学生成绩","个人密码","增加课程","删除课程"};

	static int a;    //在删除信息时使用，1为删除学生信息，2为删除课程信息

	private static final long serialVersionUID = 1L;

	public Teacher()
	{
		super("教师登录界面");
		this.setBounds(600,240,320,180);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(4,2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		//第一行
		this.add(new Label("PLAESE CHOOSE",Label.CENTER));
		this .jb1=new JButton("增加学生信息");
		this.add(this .jb1);
		this .jb1.addActionListener(this);
		//第二行
		this.add(this .jcom1 =new JComboBox<String>(Teacher.oper1));
		this .jcom1.addActionListener(this);
		this .jb2=new JButton("查询");
		this.add(this .jb2);
		this .jb2.addActionListener(this);
		//第三行
		this.add(this .jcom2 =new JComboBox<String>(Teacher.oper2));
		this .jcom2.addActionListener(this);
		this .jb3=new JButton("修改");
		this.add(this .jb3);
		this .jb3.addActionListener(this);
		//第四行
		this .jb4=new JButton("删除学生信息");
		this.add(this .jb4);
		this .jb4.addActionListener(this);
		this .jb5=new JButton("退出");
		this.add(this .jb5);
		this .jb5.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		int i=this .jcom1.getSelectedIndex();     //接收查询下拉框的选择
		int j=this .jcom2.getSelectedIndex();    //接收修改下拉框的选择
		if(e.getSource()==jb1)    //插入学生信息
		{
			new Insert();
			dispose();
		}

		else if (e.getSource()==jb2)    //查询
		{
			try
			{
				if(i!=0)
				{
					Search.doSearch(2, i);
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

		else if (e.getSource()==jb3)    //修改
		{
			try
			{
				if(j!=0)
				{
					a=2;
					Search.doSearch(2, j+5);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"请选择你想进行修改的选项！");
				}
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}

		else if (e.getSource()==jb4)   // 删除学生信息
		{
			a=1;
			new Delete();
			dispose();
		}

		else if (e.getSource()==jb5)    //退出
		{
			new Login();
			dispose();
		}
	}

//	public static void main(String[] args)
//	{
//		new Teacher();
//	}

}
