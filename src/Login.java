
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Login  extends JFrame implements ActionListener,ComponentListener
{
	private TextField tx1;
	private JPasswordField jp;
	private JButton jb1,jb2;
	private MessageJDialog jdialog;
	private ComponentEvent event;

	static String s1,s2;
	static int a;     //区分操作来自于学生还是教师
	private static final long serialVersionUID = 1L;

	public Login()
	{
		super("登录界面");
		this.setBounds(600,240,320,120);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(3,2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//第一行
		this.add(new Label("id",Label.CENTER));
		this.tx1=new TextField(15);
		this.add(this.tx1);
		//第二行
		this.add(new Label("passward",Label.CENTER));
		this.jp=new JPasswordField(15);
		this.add(this.jp);
		//第三行
		//this.jb1 =new JButton("学生登陆") ;
		//,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,this.add(this.jb1);
		//,,,,,,,,,,,,,,,,,,,,,,,this.jb1.addActionListener(this );
		this.jb2 =new JButton("教师登陆") ;
		this.add(this.jb2);
		this.jb2.addActionListener(this );

		this.setVisible(true);
		this.addWindowListener(new WinClose());
	}


	public void componentMoved(ComponentEvent e) {	}

	public void componentShown(ComponentEvent e) {	}

	public void componentHidden(ComponentEvent e) {  }

	public void actionPerformed(ActionEvent e)
	{
		s1=tx1.getText();
		s2=new String(jp.getPassword());

		if(!s1.equals("")&& !s2.equals(""))
		{
			try
			{

				Statement st=Connect.doConnect();//*********出错!!!!!!!!!!!!!!
//				if(e.getSource()==jb1)    //学生登录
//				{
//					String a0="select * from student where Sno=\'"+s1+"\'";
//
//					//从Student表中查找是否有该学生的学号信息
//					ResultSet  rs=st.executeQuery(a0);
//					if(rs.next())
//					{
//						Search.doSearch(0, 0);
//						int l=Search.u;     //接收来自Search的查询结果，1为成功，0为失败
//						if(l==1)
//						{
//							JOptionPane.showMessageDialog(this,"登录成功！");
//							a=1;
//							new Student();
//							dispose();      //关闭当前窗口
//						}
//						else
//						{
//							JOptionPane.showMessageDialog(this,"用户名或密码输入有误，请检查输入！");
//						}
//					}
//					else
//					{
//						JOptionPane.showMessageDialog(this,"该用户名尚未注册，请尽快联系教师管理员进行注册！");
//					}
//				}else

				if(e.getSource()==jb2)    //教师登录
				{
					Search.doSearch(0, 1);
					int l=Search.u;
					if(l==1)
					{
						JOptionPane.showMessageDialog(this,"登录成功！");
						a=2;
						new Teacher();
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(this,"用户名或密码输入有误，请检查输入！");
					}
				}
			}
			catch (SQLException e2)
			{
				e2.printStackTrace();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"用户名和密码不能为空，请检查输入！");
		}
	}

	private class MessageJDialog extends JDialog
	{
		private static final long serialVersionUID = 1L;
		private JLabel jlable;
		private MessageJDialog()
		{
			super(Login.this,"提示",true);
			this.setSize(420, 110);
			this.jlable=new JLabel("",JLabel.CENTER);
			this.getContentPane().add(this.jlable);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			this.addComponentListener(Login.this);
		}
	}

	public void componentResized(ComponentEvent e)
	{
		event = null;
		Component comp=event.getComponent();
		int size=(comp.getWidth()+comp.getHeight())/40;
		Font font=new Font("宋体",1,size);
		if(comp instanceof JFrame)
		{
			int n=this.getContentPane().getComponentCount();
			for(int i=0;i<n;i++)
			{
				this.getContentPane().getComponent(i).setFont(font);
			}
		}
		else if(comp instanceof JDialog)
		{
			this.jdialog.jlable.setFont(font);
		}
	}

	public static void main(String[] args)
	{
		JOptionPane.showMessageDialog(null,"登录名为自己的职工号，初始密码为00000，请登陆后及时修改密码！");
		new Login();
	}
}
