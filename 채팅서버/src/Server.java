// Java Chatting Server

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {

	private JPanel contentPane;
	private JTextField textField; // ����� PORT��ȣ �Է�
	private JButton Start; // ������ �����Ų ��ư
	JTextArea textArea; // Ŭ���̾�Ʈ �� ���� �޽��� ���

	private ServerSocket socket; //��������
	private Socket soc; // ������� 
	private int port = 30000; // ��Ʈ��ȣ
	
	
	
	private int personNum = 0;	//ȸ�������� ��	SaveIdPwd�Լ����� Client���� ���� id,pwd�� �����ϰ� ++�� �Ѵ�.
	
//id�� password�� �����Ѵ�.	
	
	private String[][] idAndpwd = new String[3][2];
	
	public void SaveIdPwd(/*String id, String pwd*/) {		//id, pwd ����..
		idAndpwd[0][0] = "MOnjk";			//id1
		idAndpwd[0][1] = "sung";		//pwd1
		idAndpwd[1][0] = "NKP";			//id2
		idAndpwd[1][1] = "sung";		//pwd2
		idAndpwd[2][0] = "1234";		//id3
		idAndpwd[2][1] = "univ";		//pwd3
		/*
		for(int i=0; i<personNum.length;i++) {	//ȸ������ �ϴ� �ҽ�,, ���� �ȵ�.
			for(int j=0; j<2; j++) {
				idAndpwd[i][j] = {id, pwd};
			}
		}
		*/
	}
	
	
	private Vector vc = new Vector(); // ����� ����ڸ� ������ ����

	public static void main(String[] args) {
	
					Server frame = new Server();
					frame.setVisible(true);
			
	}

	public Server() {

		init();

	}

	private void init() { // GUI�� �����ϴ� �޼ҵ�
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JScrollPane js = new JScrollPane();
				
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		js.setBounds(0, 0, 264, 254);
		contentPane.add(js);
		js.setViewportView(textArea);

		textField = new JTextField();
/*
		textField.setBounds(98, 264, 154, 37);
		contentPane.add(textField);
		textField.setColumns(10);
*/
//		JLabel lblNewLabel = new JLabel("��������..");
//		lblNewLabel.setBounds(12, 264, 98, 37);
//		contentPane.add(lblNewLabel);
		Start = new JButton("���� ����");
		Myaction action = new Myaction();
		Start.addActionListener(action); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
		textField.addActionListener(action);
		Start.setBounds(0, 325, 264, 37);
		contentPane.add(Start);
		textArea.setEditable(false); // textArea�� ����ڰ� ���� ���ϰԲ� ���´�.
		
	}
	
	class Myaction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			server_start(); // �ٷ� ��������
/*
			// �׼� �̺�Ʈ�� sendBtn�϶� �Ǵ� textField ���� Enter key ġ��
			if (e.getSource() == Start || e.getSource() == textField) 
			{
				if (textField.getText().equals("") || textField.getText().length()==0)// textField�� ���� ������� ������
				{
					textField.setText("��Ʈ��ȣ�� �Է����ּ���");
					textField.requestFocus(); // ��Ŀ���� �ٽ� textField�� �־��ش�
				} 			
				else 
				{
					try
					{
						port = Integer.parseInt(textField.getText()); // ���ڷ� �Է����� ������ ���� �߻� ��Ʈ�� ���� ����.		
						server_start(); // ����ڰ� ����ε� ��Ʈ��ȣ�� �־����� ���� ���������� �޼ҵ� ȣ��			
					}
					catch(Exception er)
					{
						//����ڰ� ���ڷ� �Է����� �ʾ����ÿ��� ���Է��� �䱸�Ѵ�
						textField.setText("���ڷ� �Է����ּ���");
						textField.requestFocus(); // ��Ŀ���� �ٽ� textField�� �־��ش�
					}	
				}// else �� ��
			}
*/
		}

	}

	private void server_start() {
		try {
			socket = new ServerSocket(port); // ������ ��Ʈ ���ºκ�
			Start.setText("����������");
			Start.setEnabled(false); // ������ ���̻� �����Ű�� �� �ϰ� ���´�
//			textField.setEnabled(false); // ���̻� ��Ʈ��ȣ ������ �ϰ� ���´�
			
			if(socket!=null) // socket �� ���������� ��������
			{
				Connection();
			}
			
		} catch (IOException e) {
			textArea.append("������ �̹� ������Դϴ�...\n");

		}

	}

	private void Connection() {
		Thread th = new Thread(new Runnable() { // ����� ������ ���� ������
			@Override
			public void run() {
				while (true) { // ����� ������ ����ؼ� �ޱ� ���� while��
					try {
						textArea.append("����� ���� �����...\n");
						soc = socket.accept(); // accept�� �Ͼ�� �������� ���� �����
						textArea.append("����� ����!!\n");
						UserInfo user = new UserInfo(soc, vc); // ����� ���� ������ �ݹ� ������Ƿ�, user Ŭ���� ���·� ��ü ����
	                                // �Ű������� ���� ����� ���ϰ�, ���͸� ��Ƶд�
						vc.add(user); // �ش� ���Ϳ� ����� ��ü�� �߰�
						user.start(); // ���� ��ü�� ������ ����
					} catch (IOException e) {
						textArea.append("!!!! accept ���� �߻�... !!!!\n");
					} 
				}
			}
		});
		th.start();
	}

	class UserInfo extends Thread {
		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;
		private Socket user_socket;
		private Vector user_vc;
		private String Nickname = "";

		public UserInfo(Socket soc, Vector vc) // �����ڸ޼ҵ�
		{
			// �Ű������� �Ѿ�� �ڷ� ����
			this.user_socket = soc;
			this.user_vc = vc;

			User_network();

		}

		public void User_network() {
			try {
				is = user_socket.getInputStream();
				dis = new DataInputStream(is);
				os = user_socket.getOutputStream();
				dos = new DataOutputStream(os);

				//Nickname = dis.readUTF(); // ������� �г��� �޴ºκ�
				byte[] b=new byte[128];
				dis.read(b);
				String Nickname = new String(b);
				Nickname = Nickname.trim();
				
				if (Nickname.equals("1192") || Nickname.equals("062")){
					textArea.append("ID " + Nickname + " ����\n");
					textArea.setCaretPosition(textArea.getText().length());	
					textArea.append("���� ����� ��  : " + (vc.size()+1) + "��\n");
					send_Message(Nickname + "�� ȯ���մϴ�."); // ����� ����ڿ��� ���������� �˸�
				}
				else {
					textArea.append("����");
					textArea.setCaretPosition(textArea.getText().length());
				}
			} catch (Exception e) {
				textArea.append("��Ʈ�� ���� ����\n");
				textArea.setCaretPosition(textArea.getText().length());
			}

		}
		
		public void User_checked() {	//id, pwd Ȯ��
			
		}
		

		public void InMessage(String str) {
			//textArea.append("����ڷκ��� ���� �޼��� : " + str+"\n");
			textArea.append(str + "\n");
			textArea.setCaretPosition(textArea.getText().length());
			// ����� �޼��� ó��
			broad_cast(str);
			
			
		}

		public void broad_cast(String str) {
			for (int i = 0; i < user_vc.size(); i++) {
				UserInfo imsi = (UserInfo) user_vc.elementAt(i);
				imsi.send_Message(str);

			}

		}

		public void send_Message(String str) {
			try {
				//dos.writeUTF(str);
				byte[] bb;		
				bb = str.getBytes();
//				CheckIdPwd cip = new CheckIdPwd(str);	//id, pwd Ȯ���ϴ� Ŭ���� ȣ��
				dos.write(bb); //.writeUTF(str);
			} 
			catch (IOException e) {
				textArea.append("�޽��� �۽� ���� �߻�\n");	
				textArea.setCaretPosition(textArea.getText().length());
			}
		}

		public void run() // ������ ����
		{

			while (true) {
				try {
					
					// ����ڿ��� �޴� �޼���
					byte[] b = new byte[128];
					dis.read(b);
					String msg = new String(b);
					msg = msg.trim();
					//String msg = dis.readUTF();
					InMessage(msg);
					
				} 
				catch (IOException e) 
				{
					
					try {
						dos.close();
						dis.close();
						user_socket.close();
						vc.removeElement( this ); // �������� ���� ��ü�� ���Ϳ��� �����
						textArea.append("���� ����� ��  : " + vc.size());
						textArea.append("\n����� ���� ������\n");
						textArea.setCaretPosition(textArea.getText().length());
						
						break;
					
					} catch (Exception ee) {
					
					}// catch�� ��
				}// �ٱ� catch����
			}					
		}// run�޼ҵ� ��
	} // ���� userinfoŬ������
	
/*	
	public CheckIdPwd extends Thread {		//���⼭ id pwd �˻��ؼ� �ѷ��ش�.

		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;
		private 
		
		public CheckIdPwd(String str) {
			
		}
				
	}
*/
}
