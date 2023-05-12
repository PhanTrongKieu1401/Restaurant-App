package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.MaterialDAO;
import dao.SupplierDAO;
import model.DetailedInvoice;
import model.Invoice;
import model.Material;
import model.Supplier;

public class AddMaterialFrm extends JFrame implements ActionListener{

	private static Invoice invoice;
	private JPanel contentPane;
	private JTextField txtMaterialName;
	private JTextField txtMaterialPrice;
	private JButton btnReset, btnSave;
	private JFrame jFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AddMaterialFrm frame = new AddMaterialFrm(invoice);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public AddMaterialFrm(Invoice invoice) {
		setTitle("Add material");
		this.invoice = invoice;
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
		
		
		JPanel pnMaterialName = new JPanel();
		contentPane.add(pnMaterialName);
		pnMaterialName.setSize(new Dimension(width-20,height/5));
		GridBagLayout gbl_pnMaterialName = new GridBagLayout();
		gbl_pnMaterialName.columnWidths = new int[] {120, 356};
		gbl_pnMaterialName.rowHeights = new int[] {50, 0};
		gbl_pnMaterialName.columnWeights = new double[]{0.0, 0.0};
		gbl_pnMaterialName.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnMaterialName.setLayout(gbl_pnMaterialName);
		
		JLabel lblMaterialName = new JLabel("Name");
		lblMaterialName.setPreferredSize(new Dimension(100, 13));
		lblMaterialName.setMinimumSize(new Dimension(100, 13));
		lblMaterialName.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblMaterialName = new GridBagConstraints();
		gbc_lblMaterialName.fill = GridBagConstraints.BOTH;
		gbc_lblMaterialName.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaterialName.gridx = 0;
		gbc_lblMaterialName.gridy = 0;
		pnMaterialName.add(lblMaterialName, gbc_lblMaterialName);
		
		txtMaterialName = new JTextField();
		txtMaterialName.setMinimumSize(new Dimension(50, 19));
		txtMaterialName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtMaterialName = new GridBagConstraints();
		gbc_txtMaterialName.fill = GridBagConstraints.BOTH;
		gbc_txtMaterialName.gridx = 1;
		gbc_txtMaterialName.gridy = 0;
		pnMaterialName.add(txtMaterialName, gbc_txtMaterialName);
		txtMaterialName.setColumns(40);
		//cần kiểm tra tồn tại
//		txtMaterialName.addFocusListener(new FocusAdapter() {
//			
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(txtMaterialName.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(jFrame, "Vui lòng nhập trường này!");
//				}
//				
//			}
//		});
		
		JPanel pnMaterialPrice = new JPanel();
		contentPane.add(pnMaterialPrice);
		GridBagLayout gbl_pnMaterialPrice = new GridBagLayout();
		gbl_pnMaterialPrice.columnWidths = new int[] {120, 356};
		gbl_pnMaterialPrice.rowHeights = new int[] {50, 0};
		gbl_pnMaterialPrice.columnWeights = new double[]{0.0, 0.0};
		gbl_pnMaterialPrice.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnMaterialPrice.setLayout(gbl_pnMaterialPrice);
		
		JLabel lblMaterialPrice = new JLabel("Price");
		lblMaterialPrice.setPreferredSize(new Dimension(100, 13));
		lblMaterialPrice.setMinimumSize(new Dimension(100, 13));
		lblMaterialPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblMaterialPrice = new GridBagConstraints();
		gbc_lblMaterialPrice.fill = GridBagConstraints.BOTH;
		gbc_lblMaterialPrice.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaterialPrice.gridx = 0;
		gbc_lblMaterialPrice.gridy = 0;
		pnMaterialPrice.add(lblMaterialPrice, gbc_lblMaterialPrice);
		
		txtMaterialPrice = new JTextField();
		txtMaterialPrice.setMinimumSize(new Dimension(50, 19));
		txtMaterialPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtMaterialPrice = new GridBagConstraints();
		gbc_txtMaterialPrice.fill = GridBagConstraints.BOTH;
		gbc_txtMaterialPrice.gridx = 1;
		gbc_txtMaterialPrice.gridy = 0;
		pnMaterialPrice.add(txtMaterialPrice, gbc_txtMaterialPrice);
		txtMaterialPrice.setColumns(40);
//		txtMaterialPrice.addFocusListener(new FocusAdapter() {
//			
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(txtMaterialPrice.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(jFrame, "Vui lòng nhập trường này!");
//				}
//				
//			}
//		});
		
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
			String name = txtMaterialName.getText().trim();
			String price = txtMaterialPrice.getText().trim();
			
			if(name.isEmpty() || price.isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "Vui lòng điền đầy đủ thông tin!");
				return;
			}
			MaterialDAO md = new MaterialDAO();
			Material material = new Material();
			material.setName(name);
			material.setPrice(Float.parseFloat(price));
			if(md.addMaterial(material)) {
				JOptionPane.showMessageDialog(jFrame, "Thêm nguyên liệu thành công!");
				DetailedInvoice detailedInvoice = new DetailedInvoice();		
				detailedInvoice.setMaterial(material);
				ArrayList<DetailedInvoice> detailedInvoices = invoice.getDetailedInvoices();
				detailedInvoices.add(detailedInvoice);
				(new ImportQuantityFrm(invoice)).setVisible(true);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(jFrame, "Thêm nguyên liệu không thành công!");
				(new SearchMaterialFrm(invoice)).setVisible(true);
				this.dispose();
			}
		}
		else {
			if(btnChecked.equals(btnReset)) {
				JOptionPane.showMessageDialog(jFrame, "Chức nắng đang được phát huy!");
			}
		}
	}
}
