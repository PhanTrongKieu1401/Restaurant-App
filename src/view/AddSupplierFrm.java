package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SupplierDAO;
import model.DetailedInvoice;
import model.Invoice;
import model.Supplier;
import model.User;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AddSupplierFrm extends JFrame implements ActionListener{

	private static User user;
	private JPanel contentPane;
	private JTextField txtSupplierName;
	private JTextField txtSupplierAddress;
	private JTextField txtSupplierEmail;
	private JTextField txtSupplierDescription;
	private JButton btnReset, btnSave;
	private JFrame jFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AddSupplierFrm frame = new AddSupplierFrm(user);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public AddSupplierFrm(User user) {
		setTitle("Add supplier");
		this.user = user;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthsc = (int) screenSize.getWidth();
		int heightsc = (int) screenSize.getHeight();
		int width = 600;
		int height = 400;
		setSize(width, height);
		this.setLocation((widthsc-width)/2, (heightsc-height)/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(Box.createRigidArea(new Dimension(0,0)));
		
		
		JPanel pnSupplierName = new JPanel();
		contentPane.add(pnSupplierName);
		pnSupplierName.setSize(new Dimension(width-20,height/5));
		GridBagLayout gbl_pnSupplierName = new GridBagLayout();
		gbl_pnSupplierName.columnWidths = new int[] {120, 356};
		gbl_pnSupplierName.rowHeights = new int[] {50, 0};
		gbl_pnSupplierName.columnWeights = new double[]{0.0, 0.0};
		gbl_pnSupplierName.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnSupplierName.setLayout(gbl_pnSupplierName);
		
		JLabel lblSupplierName = new JLabel("Name");
		lblSupplierName.setPreferredSize(new Dimension(100, 13));
		lblSupplierName.setMinimumSize(new Dimension(100, 13));
		lblSupplierName.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSupplierName = new GridBagConstraints();
		gbc_lblSupplierName.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierName.insets = new Insets(0, 0, 0, 5);
		gbc_lblSupplierName.gridx = 0;
		gbc_lblSupplierName.gridy = 0;
		pnSupplierName.add(lblSupplierName, gbc_lblSupplierName);
		
		txtSupplierName = new JTextField();
		txtSupplierName.setMinimumSize(new Dimension(50, 19));
		txtSupplierName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtSupplierName = new GridBagConstraints();
		gbc_txtSupplierName.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierName.gridx = 1;
		gbc_txtSupplierName.gridy = 0;
		pnSupplierName.add(txtSupplierName, gbc_txtSupplierName);
		txtSupplierName.setColumns(40); 
		//cần kiểm tra tồn tại
//		txtSupplierName.addFocusListener(new FocusAdapter() {
//			
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(txtSupplierName.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(jFrame, "Vui lòng nhập trường này!");
//				}
//				
//			}
//		});
		
		JPanel pnSupplierAddress = new JPanel();
		contentPane.add(pnSupplierAddress);
		GridBagLayout gbl_pnSupplierAddress = new GridBagLayout();
		gbl_pnSupplierAddress.columnWidths = new int[] {120, 356};
		gbl_pnSupplierAddress.rowHeights = new int[] {50, 0};
		gbl_pnSupplierAddress.columnWeights = new double[]{0.0, 0.0};
		gbl_pnSupplierAddress.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnSupplierAddress.setLayout(gbl_pnSupplierAddress);
		
		JLabel lblSupplierAddress = new JLabel("Address");
		lblSupplierAddress.setPreferredSize(new Dimension(100, 13));
		lblSupplierAddress.setMinimumSize(new Dimension(100, 13));
		lblSupplierAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSupplierAddress = new GridBagConstraints();
		gbc_lblSupplierAddress.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierAddress.insets = new Insets(0, 0, 0, 5);
		gbc_lblSupplierAddress.gridx = 0;
		gbc_lblSupplierAddress.gridy = 0;
		pnSupplierAddress.add(lblSupplierAddress, gbc_lblSupplierAddress);
		
		txtSupplierAddress = new JTextField();
		txtSupplierAddress.setMinimumSize(new Dimension(50, 19));
		txtSupplierAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtSupplierAddress = new GridBagConstraints();
		gbc_txtSupplierAddress.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierAddress.gridx = 1;
		gbc_txtSupplierAddress.gridy = 0;
		pnSupplierAddress.add(txtSupplierAddress, gbc_txtSupplierAddress);
		txtSupplierAddress.setColumns(40);
//		txtSupplierAddress.addFocusListener(new FocusAdapter() {
//			
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(txtSupplierAddress.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(jFrame, "Vui lòng nhập trường này!");
//				}
//				
//			}
//		});
		
		JPanel pnSupplierEmail = new JPanel();
		contentPane.add(pnSupplierEmail);
		GridBagLayout gbl_pnSupplierEmail = new GridBagLayout();
		gbl_pnSupplierEmail.columnWidths = new int[] {120, 356};
		gbl_pnSupplierEmail.rowHeights = new int[] {50, 0};
		gbl_pnSupplierEmail.columnWeights = new double[]{0.0, 0.0};
		gbl_pnSupplierEmail.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnSupplierEmail.setLayout(gbl_pnSupplierEmail);
		
		JLabel lblSupplierEmail = new JLabel("Email");
		lblSupplierEmail.setPreferredSize(new Dimension(100, 13));
		lblSupplierEmail.setMinimumSize(new Dimension(100, 13));
		lblSupplierEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSupplierEmail = new GridBagConstraints();
		gbc_lblSupplierEmail.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierEmail.insets = new Insets(0, 0, 0, 5);
		gbc_lblSupplierEmail.gridx = 0;
		gbc_lblSupplierEmail.gridy = 0;
		pnSupplierEmail.add(lblSupplierEmail, gbc_lblSupplierEmail);
		
		txtSupplierEmail = new JTextField();
		txtSupplierEmail.setMinimumSize(new Dimension(50, 19));
		txtSupplierEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtSupplierEmail = new GridBagConstraints();
		gbc_txtSupplierEmail.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierEmail.gridx = 1;
		gbc_txtSupplierEmail.gridy = 0;
		pnSupplierEmail.add(txtSupplierEmail, gbc_txtSupplierEmail);
		txtSupplierEmail.setColumns(40);
//		txtSupplierEmail.addFocusListener(new FocusAdapter() {
//			String email = txtSupplierEmail.getText();
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(!isValidEmail(email)) {
//					JOptionPane.showMessageDialog(jFrame, "Trường này phải là email!");
//				}			
//			}
//		});
		
		JPanel pnSupplierDescription = new JPanel();
		contentPane.add(pnSupplierDescription);
		GridBagLayout gbl_pnSupplierDescription = new GridBagLayout();
		gbl_pnSupplierDescription.columnWidths = new int[] {120, 356};
		gbl_pnSupplierDescription.rowHeights = new int[] {50, 0};
		gbl_pnSupplierDescription.columnWeights = new double[]{0.0, 0.0};
		gbl_pnSupplierDescription.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnSupplierDescription.setLayout(gbl_pnSupplierDescription);
		
		JLabel lblSupplierDescription = new JLabel("Description");
		lblSupplierDescription.setPreferredSize(new Dimension(100, 13));
		lblSupplierDescription.setMinimumSize(new Dimension(100, 13));
		lblSupplierDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSupplierDescription = new GridBagConstraints();
		gbc_lblSupplierDescription.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblSupplierDescription.gridx = 0;
		gbc_lblSupplierDescription.gridy = 0;
		pnSupplierDescription.add(lblSupplierDescription, gbc_lblSupplierDescription);
		
		txtSupplierDescription = new JTextField();
		txtSupplierDescription.setMinimumSize(new Dimension(50, 19));
		txtSupplierDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtSupplierDescription = new GridBagConstraints();
		gbc_txtSupplierDescription.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierDescription.gridx = 1;
		gbc_txtSupplierDescription.gridy = 0;
		pnSupplierDescription.add(txtSupplierDescription, gbc_txtSupplierDescription);
		txtSupplierDescription.setColumns(40);
		
		JPanel pnAction = new JPanel();
		contentPane.add(pnAction);
		GridBagLayout gbl_pnAction = new GridBagLayout();
		gbl_pnAction.columnWidths = new int[] {100, 100};
		gbl_pnAction.rowHeights = new int[] {40, 0};
		gbl_pnAction.columnWeights = new double[]{0.0, 0.0};
		gbl_pnAction.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnAction.setLayout(gbl_pnAction);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReset.addActionListener(this);
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.BOTH;
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 0;
		gbc_btnReset.gridy = 0;
		pnAction.add(btnReset, gbc_btnReset);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.addActionListener(this);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		pnAction.add(btnSave, gbc_btnSave);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnChecked = (JButton) e.getSource();
		if(btnChecked.equals(btnSave)) {
			String name = txtSupplierName.getText().trim();
			String address = txtSupplierAddress.getText().trim();
			String email = txtSupplierEmail.getText().trim();
			String description = txtSupplierDescription.getText().trim();
			
			if(name.isEmpty() || address.isEmpty() || email.isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "Vui lòng điền đầy đủ thông tin!");
				return;
			}
			SupplierDAO sd = new SupplierDAO();
			Supplier supplier = new Supplier();
			supplier.setName(name);
			supplier.setAddress(address);
			supplier.setEmail(email);
			supplier.setDescription(description);
			if(sd.addSupplier(supplier)) {
				JOptionPane.showMessageDialog(jFrame, "Thêm nhà cung cấp thành công!");
				Invoice invoice = new Invoice();
				invoice.setUser(user);
				invoice.setSupplier(supplier);
				invoice.setDetailedInvoices(new ArrayList<DetailedInvoice>());
				(new SearchMaterialFrm(invoice)).setVisible(true);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(jFrame, "Thêm nhà cung cấp không thành công!");
				(new SearchSupplierFrm(user)).setVisible(true);
				this.dispose();
			}		
		}
		
	}
	
	public static boolean isValidEmail(String email) {
        // Biểu thức chính quy kiểm tra định dạng email
        String emailRegex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
