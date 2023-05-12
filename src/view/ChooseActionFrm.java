package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Invoice;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ChooseActionFrm extends JFrame implements ActionListener{

	private static Invoice invoice;
	private JPanel contentPane;
	private JButton btnImportMaterial, btnPrintInvoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ChooseActionFrm frame = new ChooseActionFrm(invoice);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public ChooseActionFrm(Invoice invoice) {
		setTitle("Choose action");
		this.invoice = invoice;
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
		
		JPanel pnNotification = new JPanel();
		contentPane.add(pnNotification);
		
		JLabel lblNotification = new JLabel("Thêm vào hóa đơn thành công!");
		lblNotification.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotification.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnNotification.add(lblNotification);
		
		JPanel pnAction = new JPanel();
		contentPane.add(pnAction);
		
		btnImportMaterial = new JButton("Import material");
		btnImportMaterial.addActionListener(this);
		btnImportMaterial.setMinimumSize(new Dimension(70, 30));
		btnImportMaterial.setMaximumSize(new Dimension(100, 30));
		btnImportMaterial.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnImportMaterial);
		
		btnPrintInvoice = new JButton("Print invoice");
		btnPrintInvoice.addActionListener(this);
		btnPrintInvoice.setMinimumSize(new Dimension(70, 30));
		btnPrintInvoice.setMaximumSize(new Dimension(100, 30));
		btnPrintInvoice.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnAction.add(btnPrintInvoice);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnChecked = (JButton) e.getSource();
		if(btnChecked.equals(btnImportMaterial)) {
			(new SearchMaterialFrm(invoice)).setVisible(true);
			this.dispose();	
		}
		else {
			if(btnChecked.equals(btnPrintInvoice)) {
				(new PrintInvoiceFrm(invoice)).setVisible(true);	
				this.dispose();
			}	
		}
	}	
}
