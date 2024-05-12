import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Insert_C extends JFrame implements ActionListener
{
	private TextField jtx1,jtx2,jtx3,jtx4,jtx5;
	private JButton jb1,jb2,jb3;

	String s6,s7;

	private static final long serialVersionUID = 1L;

	public Insert_C()
	{
		super("请输入信息");
		this.setBounds(400,240,650,120);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(3,4));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		this.add(new Label("CNO",Label.CENTER));
		this.add(new Label("课程名",Label.CENTER));
		this.add(new Label("先修课",Label.CENTER));
		this.add(new Label("学分",Label.CENTER));
		//第二行
		this.jtx1=new TextField(15);
		this.add(this.jtx1);
		jtx1.setText("0");
		this.jtx2=new TextField(15);
		this.add(this.jtx2);
		this.jtx3=new TextField(15);
		this.add(this.jtx3);
		jtx3.setText("2");
		this.jtx4=new TextField(15);
		this.add(this.jtx4);
		jtx4.setText("0");
		//第三行
		this.jtx5=new TextField(15);
		this.add(this.jtx5);
		jtx5 .setEnabled(false);
		this.jb2=new JButton("重新输入/再次添加");
		this.add(this.jb2);
		this.jb2.addActionListener(this );

		this.jb1=new JButton("确定");
		this.add(this.jb1);
		this.jb1.addActionListener(this );
		this.jb3=new JButton("退出");
		this.add(this.jb3);
		this.jb3.addActionListener(this );
	}

	public void actionPerformed(ActionEvent e)
	{
		String s1=jtx1.getText();
		String s2=jtx2.getText();
		int s4=Integer.parseInt(jtx4.getText());
		String s3=jtx3.getText();

		if(jtx1.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"课序号不能为空！");
		}

		else
		{
			if(e.getSource()==jb1)    //确定按钮
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
							JOptionPane.showMessageDialog(this,"该课序号已存在，请勿重复添加！");
						}
						else
						{
							String a = "insert into Course (Cno,Cname,Cpno,Ccredit,Cnum)"
									+ " values(\'" + s1 +"\',\'"+ s2 +"\',\'" +s3+"\',\'" + s4 + "\',\'20\')";
							//插入课程信息，初始课余量默认20
							st.executeUpdate(a);
							//System.out.println("插入数据成功");
							String s7="恭喜你，插入成功！";
							jtx5.setText(s7);
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
					jtx5.setText(s6);
					System.exit(0);
				}
			}
		}

		if(e.getSource()==jb2)   //重新输入
		{
			jtx1.setText("0");
			jtx2.setText(null);
			jtx3.setText(null);
			jtx4.setText("0");
			jtx5.setText(null);
//				jtx6.setText(null);
		}

		else if(e.getSource()==jb3)       //退出
		{
			new Teacher();
			dispose();
		}
	}

//	public static void main(String[] args) {	new Insert_C();	}
}
