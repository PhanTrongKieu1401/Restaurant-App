package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDAO;
import model.User;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LoginFrm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JFrame jFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginFrm frame = new LoginFrm();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public LoginFrm() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthsc = (int) screenSize.getWidth();
		int heightsc = (int) screenSize.getHeight();
		int width = 500;
		int height = 300;
		setSize(width, height);
		this.setLocation((widthsc-width)/2, (heightsc-height)/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(Box.createRigidArea(new Dimension(0,height/5)));
		
		JPanel pnUsername = new JPanel();
		pnUsername.setMinimumSize(new Dimension(50, 20));
		pnUsername.setLayout(new FlowLayout());
		pnUsername.setSize(width, height/5);
		contentPane.add(pnUsername);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnUsername.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnUsername.add(txtUsername);
		txtUsername.setColumns(25);
		
		JPanel pnPassword = new JPanel();
		pnPassword.setMinimumSize(new Dimension(50, 20));
		pnPassword.setLayout(new FlowLayout());
		pnUsername.setSize(width, height/5);
		contentPane.add(pnPassword);		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnPassword.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setColumns(25);
		pnPassword.add(txtPassword);
		
		JPanel pnLogin = new JPanel();
		contentPane.add(pnLogin);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnLogin.add(btnLogin);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getSource() instanceof JButton)&&(((JButton)e.getSource()).equals(btnLogin))) {
			if(txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
				JOptionPane.showMessageDialog(jFrame, "Vui lòng điền đầy đủ thông tin!", "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				User user = new User();
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				
				UserDAO ud = new UserDAO();
				if(ud.checkLogin(user)) {
					if(user.getPosition().equalsIgnoreCase("material_import_staff")) {
						(new MaterialImportStaffHomeFrm(user)).setVisible(true);
						this.dispose();
					}else
						JOptionPane.showMessageDialog(jFrame, "Chức năng của " + user.getPosition() + " đang được xây dựng!", "", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(jFrame, "Tên hoặc mật khẩu không chính xác!", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

}
