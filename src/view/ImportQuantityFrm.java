package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DetailedInvoice;
import model.Invoice;
import model.Material;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Color;

public class ImportQuantityFrm extends JFrame implements ActionListener{

	private static Invoice invoice;
	private JPanel contentPane;
	private JTextField txtMaterialName;
	private JTextField txtMaterialPrice;
	private JTextField txtMaterialQuantity;
	private JButton btnConfirm;
	private JFrame jFrame;
	private ArrayList<DetailedInvoice> detailedInvoices;
	private DetailedInvoice detailedInvoice;
	private Material material;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ImportQuantityFrm frame = new ImportQuantityFrm(invoice);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ImportQuantityFrm(Invoice invoice) {
		setTitle("Import quantity");
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
		
		//lấy giá trị material có trong invoice 
		detailedInvoices = invoice.getDetailedInvoices();
		detailedInvoice = detailedInvoices.get(detailedInvoices.size()-1);
		material = detailedInvoice.getMaterial();
		
		txtMaterialName = new JTextField(material.getName());
		txtMaterialName.setDisabledTextColor(Color.BLACK);
		txtMaterialName.setEnabled(false);
		txtMaterialName.setMinimumSize(new Dimension(50, 19));
		txtMaterialName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtMaterialName = new GridBagConstraints();
		gbc_txtMaterialName.fill = GridBagConstraints.BOTH;
		gbc_txtMaterialName.gridx = 1;
		gbc_txtMaterialName.gridy = 0;
		pnMaterialName.add(txtMaterialName, gbc_txtMaterialName);
		txtMaterialName.setColumns(40);
		
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
		
		txtMaterialPrice = new JTextField("" + material.getPrice());
		txtMaterialPrice.setDisabledTextColor(Color.BLACK);
		txtMaterialPrice.setEnabled(false);
		txtMaterialPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtMaterialPrice = new GridBagConstraints();
		gbc_txtMaterialPrice.fill = GridBagConstraints.BOTH;
		gbc_txtMaterialPrice.gridx = 1;
		gbc_txtMaterialPrice.gridy = 0;
		pnMaterialPrice.add(txtMaterialPrice, gbc_txtMaterialPrice);
		txtMaterialPrice.setColumns(40);
		
		JPanel pnMaterialQuantity = new JPanel();
		contentPane.add(pnMaterialQuantity);
		GridBagLayout gbl_pnMaterialQuantity = new GridBagLayout();
		gbl_pnMaterialQuantity.columnWidths = new int[] {120, 356};
		gbl_pnMaterialQuantity.rowHeights = new int[] {50, 0};
		gbl_pnMaterialQuantity.columnWeights = new double[]{0.0, 0.0};
		gbl_pnMaterialQuantity.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnMaterialQuantity.setLayout(gbl_pnMaterialQuantity);
		
		JLabel lblMaerialQuantity = new JLabel("Quantity");
		lblMaerialQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblMaerialQuantity = new GridBagConstraints();
		gbc_lblMaerialQuantity.fill = GridBagConstraints.BOTH;
		gbc_lblMaerialQuantity.insets = new Insets(0, 0, 0, 5);
		gbc_lblMaerialQuantity.gridx = 0;
		gbc_lblMaerialQuantity.gridy = 0;
		pnMaterialQuantity.add(lblMaerialQuantity, gbc_lblMaerialQuantity);
		
		txtMaterialQuantity = new JTextField();
		txtMaterialQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_txtMaterialQuantity = new GridBagConstraints();
		gbc_txtMaterialQuantity.fill = GridBagConstraints.BOTH;
		gbc_txtMaterialQuantity.gridx = 1;
		gbc_txtMaterialQuantity.gridy = 0;
		pnMaterialQuantity.add(txtMaterialQuantity, gbc_txtMaterialQuantity);
		txtMaterialQuantity.setColumns(40);
		//nhắc người dùng nhập số lượng
		txtMaterialQuantity.addFocusListener(new FocusAdapter() {			
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMaterialQuantity.getText().isEmpty()) {
					JOptionPane.showMessageDialog(jFrame, "Vui lòng nhập số lượng!");
				}				
			}
		});
		
		JPanel pnAction = new JPanel();
		contentPane.add(pnAction);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(this);
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnConfirm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnChecked = (JButton) e.getSource();
		if(btnChecked.equals(btnConfirm)) {
			String price = txtMaterialPrice.getText().trim();
			String quantity = txtMaterialQuantity.getText().trim();
			//nhắc người dùng nhập số lượng
			if(quantity.isEmpty()) {
				JOptionPane.showMessageDialog(jFrame, "Vui lòng nhập số lượng!");
				return;
			}
			
			int i=0;
			for(DetailedInvoice dtInvoice : detailedInvoices) {
				if(i<detailedInvoices.size()-1) {
					if(dtInvoice.getMaterial().equals(detailedInvoice.getMaterial())){
						detailedInvoice.setPrice(Float.parseFloat(price));
						detailedInvoice.setQuantity(Integer.parseInt(quantity) + dtInvoice.getQuantity());
						detailedInvoices.remove(dtInvoice);
						break;
					}
					else {
						i++;
					}
				}
				else {
					//gọi phương thức set của detailedInvoice
					detailedInvoice.setPrice(Float.parseFloat(price));
					detailedInvoice.setQuantity(Integer.parseInt(quantity));
				}
				
			}
			//tính giá trị amount của detailedInvoice
			float amount = detailedInvoice.getPrice() * detailedInvoice.getQuantity();
			detailedInvoice.setAmount(amount);
			(new ChooseActionFrm(invoice)).setVisible(true);
			this.dispose();
		}
	}

}
