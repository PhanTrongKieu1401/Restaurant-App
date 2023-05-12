package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.InvoiceDAO;
import model.DetailedInvoice;
import model.Invoice;
import model.Supplier;
import model.User;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Color;

public class PrintInvoiceFrm extends JFrame implements ActionListener{

	private static Invoice invoice;
	private JPanel contentPane;
	private JTextField txtSupplierName;
	private JTextField txtSupplierAddress;
	private JTextField txtSupplierEmail;
	private JTextField txtSupplierDescription;
	private JTable tblMaterial;
	private JTextField txtTotalAmount;
	private JButton btnCancel, btnPrint;
	private JFrame jFrame;
	private ArrayList<DetailedInvoice> listDetailedInvoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PrintInvoiceFrm frame = new PrintInvoiceFrm(invoice);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public PrintInvoiceFrm(Invoice invoice) {
		setTitle("Print invoice");
		this.invoice = invoice;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthsc = (int) screenSize.getWidth();
		int heightsc = (int) screenSize.getHeight();
		int width = 600;
		int height = 700;
		setSize(width, height);
		this.setLocation((widthsc-width)/2, (heightsc-height)/2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(Box.createRigidArea(new Dimension(0,height/40)));
		
		JPanel pnTitleInvoice = new JPanel();
		pnTitleInvoice.setSize(width,20);
		contentPane.add(pnTitleInvoice);
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		pnTitleInvoice.setLayout(new BoxLayout(pnTitleInvoice, BoxLayout.X_AXIS));
		
		JLabel lblTitle = new JLabel("Hóa đơn");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnTitleInvoice.add(lblTitle);
		
		JPanel pnTitleSupplier = new JPanel();
		pnTitleSupplier.setSize(new Dimension(600, height/20));
		contentPane.add(pnTitleSupplier);
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		pnTitleSupplier.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblTitleSupplier = new JLabel("Thông tin nhà cung cấp");
		lblTitleSupplier.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTitleSupplier.add(lblTitleSupplier);
		
		JPanel pnSupplier = new JPanel();
		pnSupplier.setSize(width,height/25);
		contentPane.add(pnSupplier);
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		pnSupplier.setLayout(new BoxLayout(pnSupplier, BoxLayout.Y_AXIS));
		
		JPanel pnGridSupplier = new JPanel();
		pnSupplier.add(pnGridSupplier);
		GridBagLayout gbl_pnGridSupplier = new GridBagLayout();
		gbl_pnGridSupplier.columnWidths = new int[] {width/4, width * 3/4};
		gbl_pnGridSupplier.rowHeights = new int[] {30, 30, 30, 30, 0};
		gbl_pnGridSupplier.columnWeights = new double[]{0.0, 0.0};
		gbl_pnGridSupplier.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnGridSupplier.setLayout(gbl_pnGridSupplier);
		
		JLabel lblSupplierName = new JLabel("Name");
		lblSupplierName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSupplierName = new GridBagConstraints();
		gbc_lblSupplierName.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplierName.gridx = 0;
		gbc_lblSupplierName.gridy = 0;
		pnGridSupplier.add(lblSupplierName, gbc_lblSupplierName);
		
		Supplier supplier = invoice.getSupplier();
		
		txtSupplierName = new JTextField(supplier.getName());
		txtSupplierName.setDisabledTextColor(Color.BLACK);
		txtSupplierName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSupplierName.setEnabled(false);
		GridBagConstraints gbc_txtSupplierName = new GridBagConstraints();
		gbc_txtSupplierName.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierName.insets = new Insets(0, 0, 5, 0);
		gbc_txtSupplierName.gridx = 1;
		gbc_txtSupplierName.gridy = 0;
		pnGridSupplier.add(txtSupplierName, gbc_txtSupplierName);
		txtSupplierName.setColumns(50);
		
		JLabel lblSupplierAddress = new JLabel("Address");
		lblSupplierAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSupplierAddress = new GridBagConstraints();
		gbc_lblSupplierAddress.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplierAddress.gridx = 0;
		gbc_lblSupplierAddress.gridy = 1;
		pnGridSupplier.add(lblSupplierAddress, gbc_lblSupplierAddress);
		
		txtSupplierAddress = new JTextField(supplier.getAddress());
		txtSupplierAddress.setDisabledTextColor(Color.BLACK);
		txtSupplierAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSupplierAddress.setEnabled(false);
		GridBagConstraints gbc_txtSupplierAddress = new GridBagConstraints();
		gbc_txtSupplierAddress.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtSupplierAddress.gridx = 1;
		gbc_txtSupplierAddress.gridy = 1;
		pnGridSupplier.add(txtSupplierAddress, gbc_txtSupplierAddress);
		txtSupplierAddress.setColumns(50);
		
		JLabel lblSupplierEmail = new JLabel("Email");
		lblSupplierEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSupplierEmail = new GridBagConstraints();
		gbc_lblSupplierEmail.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplierEmail.gridx = 0;
		gbc_lblSupplierEmail.gridy = 2;
		pnGridSupplier.add(lblSupplierEmail, gbc_lblSupplierEmail);
		
		txtSupplierEmail = new JTextField(supplier.getEmail());
		txtSupplierEmail.setDisabledTextColor(Color.BLACK);
		txtSupplierEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSupplierEmail.setEnabled(false);
		GridBagConstraints gbc_txtSupplierEmail = new GridBagConstraints();
		gbc_txtSupplierEmail.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtSupplierEmail.gridx = 1;
		gbc_txtSupplierEmail.gridy = 2;
		pnGridSupplier.add(txtSupplierEmail, gbc_txtSupplierEmail);
		txtSupplierEmail.setColumns(50);
		
		JLabel lblSupplierDescription = new JLabel("Description");
		lblSupplierDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSupplierDescription = new GridBagConstraints();
		gbc_lblSupplierDescription.fill = GridBagConstraints.BOTH;
		gbc_lblSupplierDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblSupplierDescription.gridx = 0;
		gbc_lblSupplierDescription.gridy = 3;
		pnGridSupplier.add(lblSupplierDescription, gbc_lblSupplierDescription);
		
		txtSupplierDescription = new JTextField(supplier.getDescription());
		txtSupplierDescription.setDisabledTextColor(Color.BLACK);
		txtSupplierDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSupplierDescription.setEnabled(false);
		GridBagConstraints gbc_txtSupplierDescription = new GridBagConstraints();
		gbc_txtSupplierDescription.fill = GridBagConstraints.BOTH;
		gbc_txtSupplierDescription.gridx = 1;
		gbc_txtSupplierDescription.gridy = 3;
		pnGridSupplier.add(txtSupplierDescription, gbc_txtSupplierDescription);
		txtSupplierDescription.setColumns(250);
		
		JPanel pnTitleTableMaterial = new JPanel();
		contentPane.add(pnTitleTableMaterial);
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		pnTitleTableMaterial.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitleTableMaterial = new JLabel("Danh sách nguyên liệu");
		lblTitleTableMaterial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTitleTableMaterial.add(lblTitleTableMaterial);
		
		JPanel pnTableMaterial = new JPanel();
		pnTableMaterial.setLayout(new BoxLayout(pnTableMaterial, BoxLayout.Y_AXIS));
		
		tblMaterial = new JTable();
		JScrollPane scrollPane = new JScrollPane(tblMaterial);
		tblMaterial.setFillsViewportHeight(false); 
		scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 450));
		pnTableMaterial.add(scrollPane);
		
		//hiện bảng nguyên liệu
		listDetailedInvoice = invoice.getDetailedInvoices();
//		Collections.sort(listDetailedInvoice);
//		invoice.setDetailedInvoices(listDetailedInvoice);
		String[] columnNames = {"Id", "Name", "Price", "Quantity", "Amount"};
		String[][] value = new String[listDetailedInvoice.size()][5];
		for(int i=0; i<listDetailedInvoice.size(); i++){
			value[i][0] = listDetailedInvoice.get(i).getMaterial().getId() + "";
			value[i][1] = listDetailedInvoice.get(i).getMaterial().getName();
			value[i][2] = listDetailedInvoice.get(i).getPrice() + "";
			value[i][3] = listDetailedInvoice.get(i).getQuantity() + "";
			value[i][4] = listDetailedInvoice.get(i).getAmount() + "";
		}
		DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tblMaterial.setModel(tableModel);
		contentPane.revalidate();
		contentPane.repaint();
		
		contentPane.add(pnTableMaterial);
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel pnTotalAmount = new JPanel();
		contentPane.add(pnTotalAmount);
		contentPane.add(Box.createRigidArea(new Dimension(0,10)));
		GridBagLayout gbl_pnTotalAmount = new GridBagLayout();
		gbl_pnTotalAmount.columnWidths = new int[]{width/2, width/2, 0};
		gbl_pnTotalAmount.rowHeights = new int[] {30};
		gbl_pnTotalAmount.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnTotalAmount.rowWeights = new double[]{0.0};
		pnTotalAmount.setLayout(gbl_pnTotalAmount);
		
		JLabel lblTotalAmount = new JLabel("Total amount:");
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTotalAmount = new GridBagConstraints();
		gbc_lblTotalAmount.fill = GridBagConstraints.BOTH;
		gbc_lblTotalAmount.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotalAmount.gridx = 0;
		gbc_lblTotalAmount.gridy = 0;
		pnTotalAmount.add(lblTotalAmount, gbc_lblTotalAmount);
		
		//tính tổng tiền hóa đơn
		float totalAmount = 0;
		for(DetailedInvoice detailedInvoice:listDetailedInvoice) {
			totalAmount += detailedInvoice.getQuantity() * detailedInvoice.getPrice();
		}
				
		txtTotalAmount = new JTextField(totalAmount+"");
		txtTotalAmount.setDisabledTextColor(Color.BLACK);
		txtTotalAmount.setEnabled(false);
		txtTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtTotalAmount = new GridBagConstraints();
		gbc_txtTotalAmount.fill = GridBagConstraints.BOTH;
		gbc_txtTotalAmount.gridx = 1;
		gbc_txtTotalAmount.gridy = 0;
		pnTotalAmount.add(txtTotalAmount, gbc_txtTotalAmount);
		txtTotalAmount.setColumns(25);
		
		JPanel pnAction = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnAction.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(pnAction);
		
//		btnCancel = new JButton("Cancel");
//		btnCancel.addActionListener(this);
//		btnCancel.setFont(new Font("Tahoma", Font.ITALIC, 14));
//		pnAction.add(btnCancel);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(this);
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnPrint);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnChecked = (JButton) e.getSource();
		if(btnChecked.equals(btnCancel)) {
			JOptionPane.showMessageDialog(jFrame, "Chức năng này đang phát triển!");
			return;
		}
		else {
			if(btnChecked.equals(btnPrint)) {
				InvoiceDAO id = new InvoiceDAO();
				Date date = new Date();
				invoice.setCreateDay(new java.sql.Date(date.getTime()));
//				System.out.println(invoice.getUser() + " " + invoice.getSupplier() + " " +invoice.getDetailedInvoices());
				
				if(id.addInvoice(invoice)) {
					JOptionPane.showMessageDialog(jFrame, "Lưu hóa đơn thành công!");
				}
				else {
					JOptionPane.showMessageDialog(jFrame, "Lưu hóa đơn không thành công!");
				}
				(new MaterialImportStaffHomeFrm(invoice.getUser())).setVisible(true);
				this.dispose();
			}
		}
	}

}
