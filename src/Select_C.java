
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;

public class Select_C extends JFrame implements ActionListener
{
	private TextField tx1;
	private JButton jbt1,jbt2;

	private static final long serialVersionUID = 1L;

	public Select_C()
	{
		super("选课");
		this.setBounds(650,540,320,90);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(2,2));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WinClose());

		//第一行
		this.add(new Label("课程号",Label.CENTER));
		this.tx1=new TextField(15);
		this.add(this.tx1);
		//第二行
		this.jbt1=new JButton("选课");
		this.jbt2=new JButton("重选");
		this.add(this.jbt2);
		this.add(this.jbt1);
		this.jbt1.addActionListener(this);
		this.jbt2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		String s=tx1.getText();
		if(s.equals(""))
		{
			JOptionPane.showMessageDialog(this,"输入不能为空，请检查输入！");
		}
		else
		{
			try
			{
				Statement st=Connect.doConnect();
				ResultSet rt;

				if(e.getSource()==jbt1)   //选课
				{
					String s0="select * from Course where Cno=\'"+s+"\'";
					//从Course表中查询是否有与输入中所对应的课序号
					rt=st.executeQuery(s0);
					if(rt.next())
					{
						String s00="select * from SC where Sno=\'"+Login.s1+"\' and Cno=\'"+s+"\'";
						//检查该学生是否已选过该课程
						rt=st.executeQuery(s00);
						if(rt.next())
						{
							JOptionPane.showMessageDialog(this,"你已选过该课程，请勿重复选择！");
						}
						else
						{
							String s1="insert into SC values(\'"+Login.s1+"\',\'"+s+"\',\'0\') ";     //在SC表中插入选课信息
							int a=st.executeUpdate(s1);
							String s2="Update Course set Cnum=Cnum-1  where Cno=\'"+s+"\'";   //在Course表中将对应课序号的课程的课余量减一
							int b=st.executeUpdate(s2);
							//executeUpdate的返回值是一个整数，指示受影响的行数（即更新计数）。
							if(a!=0&&b!=0)
							{
								JOptionPane.showMessageDialog(this,"选课成功！");
								dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(this,"选课失败，请检查输入！");
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "该课程不存在，请重新选择！");
					}
				}
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			if(e.getSource()==jbt2)  //重选
			{
				tx1.setText(null);
			}
		}
	}

	//public static void main(String[] args) { new Select_C();	}



}
