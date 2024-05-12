import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Change_code extends JFrame implements ActionListener
{
	Statement st;

	private TextField tx1,tx2,tx3,tx4;
	private JButton jbt1;

	private static final long serialVersionUID = 1L;

	public Change_code()
	{
		super("密码修改");
		this.setBounds(610,250,320,120);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(4,2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		//第一行
		this.add(new Label("RAW",Label.CENTER));
		this.tx1=new TextField(15);
		this.add(this.tx1);
		//第二行
		this.add(new Label("NEW",Label.CENTER));
		this.tx2=new TextField(15);
		this.add(this.tx2);
		//第三行
		this.add(new Label("DO AGIAN",Label.CENTER));
		this.tx3=new TextField(15);
		this.add(this.tx3);
		//第四行
		this.tx4=new TextField(15);
		this.add(this.tx4);
		tx4.setEnabled(false);
		this.jbt1 =new JButton("YES") ;
		this.add(this.jbt1);
		this.jbt1.addActionListener(this );
	}

	public void actionPerformed(ActionEvent e)
	{
		String s1=tx1.getText();
		String s2=tx2.getText();
		String s3=tx3.getText();

		if(s1.length()<=0||s2.length()<=0||s3.length()<=0)
		{
			JOptionPane.showMessageDialog(this,"输入不能有空值，请检查输入！");
		}
		else
		{
			if(e.getSource()==jbt1)
			{
				try
				{
					st=Connect.doConnect();
					int p=Login.a;
					String s = null;
					int x=s1.compareTo(Login.s2);     //检验原密码与登录密码是否相同
					if(x==0)
					{
						int y=s1.compareTo(s2);     //检验新密码与原密码是否相同
						if(y==0)
						{
							JOptionPane.showMessageDialog(this,"原密码与新密码不能相同，请重新输入！");
							tx2.setText(null);
						}
						else
						{
							int z=s2.compareTo(s3);     //检验两次输入的新密码是否相同
							if(z!=0)
							{
								JOptionPane.showMessageDialog(this,"两次输入的密码不一致，请重新输入！");
								tx3.setText(null);
							}
							else
							{
								if(p==1)     //修改学生密码
								{
									s="update Student  set Scode=\'"+s2+"\'  where Sno=\'"+Login.s1+"\'";
								}
								else if(p==2) //修改教师密码
								{
									s="update Teacher  set Tcode=\'"+s2+"\'  where Tno=\'"+Login.s1+"\'";
								}
								int q=st.executeUpdate(s);
								if(q!=0)
								{
									JOptionPane.showMessageDialog(this,"更改成功，请重新登录！");
									new Login();
									dispose();
								}
								else
								{
									JOptionPane.showMessageDialog(this,"更改失败，请重试！");
								}
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"原密码输入错误，请检查输入！");
					}
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		}
	}

//	public static void main(String[] args) {new Change_code();	}
}
