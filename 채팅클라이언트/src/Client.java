// Client.Java Java Chatting Client �� Nicknam, IP, Port ��ȣ �Է��ϰ� �����ϴ� �κ�

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {
	public String [][] saveIdPwd = {{"MOnjk","123"},
            {"NKP","123"}};

	private JPanel contentPane;
	private JTextField tf_ID; // ID�� �Է¹�����
	private JTextField tf_PWD;	//PWD�� �Է¹޴°�
//	private JTextField tf_IP; // IP�� �Է¹�����
//	private JTextField tf_PORT; //PORT�� �Է¹�����
	

	public Client() // ������
	{

		init();
		start();

	}

	public void init() // ȭ�� ����
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(53, 57, 90, 34);
		contentPane.add(lblNewLabel);

		tf_ID = new JTextField();
		tf_ID.setBounds(92, 64, 150, 21);
		contentPane.add(tf_ID);
		tf_ID.setColumns(10);
		
		JLabel lblPassWord = new JLabel("Password");
		lblPassWord.setBounds(12, 111, 90, 34);
		contentPane.add(lblPassWord);

		tf_PWD = new JTextField();
		tf_PWD.setBounds(92, 118, 150, 21);
		contentPane.add(tf_PWD);
		tf_PWD.setColumns(10);
		
		/*
		JLabel lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(12, 111, 90, 34);
		contentPane.add(lblServerIp);

		tf_IP = new JTextField();
		tf_IP.setColumns(10);
		tf_IP.setBounds(92, 118, 150, 21);
		contentPane.add(tf_IP);

		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(36, 178, 90, 34);
		contentPane.add(lblPort);

		tf_PORT = new JTextField();
		tf_PORT.setColumns(10);
		tf_PORT.setBounds(92, 185, 150, 21);
		contentPane.add(tf_PORT);
*/	
		JButton btnNewButton = new JButton("��    ��");
		btnNewButton.setBounds(36, 266, 206, 52);
		contentPane.add(btnNewButton);
		
		ConnectAction action = new ConnectAction();
		btnNewButton.addActionListener(action);
//		tf_PORT.addActionListener(action);
			
	}
	class ConnectAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String _id = tf_ID.getText().trim(); // ������ ���� �𸣴� ���� ���� trim() ���
//			String _ip = tf_IP.getText().trim(); // ������ ������ �𸣹Ƿ� ��������
			String _pwd = tf_PWD.getText().trim(); 
//			int _port=Integer.parseInt(tf_PORT.getText().trim()); // ������ ������ �� int������ ��ȯ 
						
			checkedIdPwd();	//id, pwd Ȯ���ϴ� �Լ� ȣ��
		}		
	}
	public void checkedIdPwd() {		//Id, Pwd Ȯ���ϴ� �Լ�
		
		String _id = tf_ID.getText().trim(); // ������ ���� �𸣴� ���� ���� trim() ���
//		String _ip = tf_IP.getText().trim(); // ������ ������ �𸣹Ƿ� ��������
		String _pwd = tf_PWD.getText().trim(); 
		
		for(int i=0; i<2; i++){
			
			if(saveIdPwd[i][0].equals(tf_ID.getText())){
				
				if(saveIdPwd[i][1].equals(tf_PWD.getText())) {
					MainView view = new MainView(_id,_pwd);
				    setVisible(false);
				    break;					 
				}
				
				else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
				}
					break;					
			}
		    
			else {
				if(i<2){ continue; }
				
				else{
				JOptionPane.showMessageDialog(null, "�������� �ʴ� ID�Դϴ�.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
				break;
				}
			}		//else�� ��
		}		//for�� ��	
	}
	
	public void start() // �̺�Ʈ ó��
	{

	}

}
