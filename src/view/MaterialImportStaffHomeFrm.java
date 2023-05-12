package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class MaterialImportStaffHomeFrm extends JFrame implements ActionListener{

	private static User user;
	private JPanel contentPane;
	private JButton btnManageMaterial, btnImportMaterial, btnManageInvoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MaterialImportStaffHomeFrm frame = new MaterialImportStaffHomeFrm(user);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MaterialImportStaffHomeFrm(User user) {
		this.user = user;
		setTitle("Material import staff home");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthsc = (int) screenSize.getWidth();
		int heightsc = (int) screenSize.getHeight();
		int width = 450;
		int height = 300;
		setSize(width, height);
		this.setLocation((widthsc-width)/2, (heightsc-height)/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		
		JPanel pnName = new JPanel();
		pnName.setSize(width, height/5);
		contentPane.add(pnName);
		pnName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSupplierName = new JLabel("Nhân viên nhập nguyên liệu: " + user.getName());
		lblSupplierName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSupplierName.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnName.add(lblSupplierName);
		
		JPanel pnManageMaterial = new JPanel();
		pnManageMaterial.setSize(width, height/5);
		contentPane.add(pnManageMaterial);
		
		btnManageMaterial = new JButton("Manage material");
		btnManageMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnManageMaterial.addActionListener(this);
		pnManageMaterial.add(btnManageMaterial);
		
		JPanel pnImportMaterial = new JPanel();
		pnImportMaterial.setSize(width, height/5);
		contentPane.add(pnImportMaterial);
		
		btnImportMaterial = new JButton("Import material");
		btnImportMaterial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnImportMaterial.addActionListener(this);
		pnImportMaterial.add(btnImportMaterial);
		
		JPanel pnManageInvoice = new JPanel();
		pnManageInvoice.setSize(width, height/5);
		contentPane.add(pnManageInvoice);
		
		btnManageInvoice = new JButton("Manage invoice");
		btnManageInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnManageInvoice.addActionListener(this);
		pnManageInvoice.add(btnManageInvoice);
		
		JPanel pnAction = new JPanel();
		contentPane.add(pnAction);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getSource() instanceof JButton)&&(((JButton)e.getSource()).equals(btnImportMaterial))) {
			(new SearchSupplierFrm(user)).setVisible(true);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "Chức năng này đang phát triển!");
		}
	}
}
