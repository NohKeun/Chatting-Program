// MainView.java : Java Chatting Client �� �ٽɺκ�
// read keyboard --> write to network (Thread �� ó��)
// read network --> write to textArea

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTextField textField; // ���� �޼��� ���°�

	private String id;
	private String ip = "127.0.0.1";
	private int port = 30000;
	private Canvas canvas;
	private String pwd;
	
	JButton sendBtn; // ���۹�ư
	JTextArea textArea; // ���ŵ� �޼����� ��Ÿ�� ����

	private Socket socket; // �������
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	public MainView(String id, String pwd)// ������
	{
		this.id = id;
		this.pwd = pwd;

		init();
		start();
		
		

//		textArea.append("id : " + id + "  \n");

		network();

	}

	public void network() {
		// ������ ����
		try {
			socket = new Socket(ip, port);
			if (socket != null) // socket�� null���� �ƴҶ� ��! ����Ǿ�����
			{
				Connection(); // ���� �޼ҵ带 ȣ��
			}
		} catch (UnknownHostException e) {

		} catch (IOException e) {
			textArea.append("���� ���� ����!!\n");
		}

	}

	public void Connection() { // ���� ���� �޼ҵ� ����κ�

		try { // ��Ʈ�� ����
			is = socket.getInputStream();
			dis = new DataInputStream(is);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);

		} catch (IOException e) {
			textArea.append("��Ʈ�� ���� ����!!\n");
		}

		send_Message(id); // ���������� ����Ǹ� ���� id�� ����
		

		Thread th = new Thread(new Runnable() { // �����带 ������ �����κ��� �޼����� ����

					@SuppressWarnings("null")
					@Override
					public void run() {
						while (true) {

							try {
								byte[] b = new byte[128];
								dis.read(b);
								String msg = new String(b);
								msg = msg.trim();
								//ī�彺Ÿ�Ϸ� ����
								if(msg.contains("["+id+"]"))	
			                        textArea.append("\t\t"+msg + "\n");
			                        else
			                        textArea.append(msg + "\n");
								
								textArea.setCaretPosition(textArea.getText().length());
								
							} catch (IOException e) {
								textArea.append("�޼��� ���� ����!!\n");
								// ������ ���� ��ſ� ������ ������ ��� ������ �ݴ´�
								try {
									os.close();
									is.close();
									dos.close();
									dis.close();
									socket.close();
									break; // ���� �߻��ϸ� while�� ����
								} catch (IOException e1) {

								}

							}
						} // while�� ��

					}// run�޼ҵ� ��
				});

		th.start();

	}

	public void send_Message(String str) { // ������ �޼����� ������ �޼ҵ�
		try {
			byte[] bb;
			bb = str.getBytes();
			dos.write(bb); //.writeUTF(str);
		} catch (IOException e) {
			textArea.append("�޼��� �۽� ����!!\n");
		}
		
	}

	public void init() { // ȭ�鱸�� �޼ҵ�

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 272, 302);
		contentPane.add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		//textArea.setForeground(new Color(255,0,0));
		textArea.setDisabledTextColor(new Color(0,0,0));
		textField = new JTextField();
		textField.setBounds(0, 312, 155, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		sendBtn = new JButton("��   ��");
		sendBtn.setBounds(161, 312, 111, 42);
		contentPane.add(sendBtn);
		textArea.setEnabled(false); // ����ڰ� �������ϰ� ���´�
		setVisible(true);
	}

	public void start() { // �׼��̺�Ʈ ���� �޼ҵ�
		Myaction action = new Myaction();
		sendBtn.addActionListener(action); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
		textField.addActionListener(action);
	}

	class Myaction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����
	{
		@Override
		public void actionPerformed(ActionEvent e) {

			// �׼� �̺�Ʈ�� sendBtn�϶� �Ǵ� textField ���� Enter key ġ��
			if (e.getSource() == sendBtn || e.getSource() == textField) 
			{
				String msg = null;
				msg = String.format("[%s] %s\n", id, textField.getText());
				send_Message(msg);
				textField.setText(""); // �޼����� ������ ���� �޼��� ����â�� ����.
				textField.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
				
			}

		}

	}

}