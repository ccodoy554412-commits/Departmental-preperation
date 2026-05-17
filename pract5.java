package Departmental_test;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
public class pract5 extends JFrame {

	static JTextField nametxt,addrtxt,larptxt;
	static DefaultTableModel dtm;
	pract5(){
		setVisible(true);
		setSize(700,500);
		setTitle("");
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//name
		JLabel name = new JLabel("Full Legal Name");
		nametxt = new JTextField();
		add(name).setBounds(20,80,120,15);
		add(nametxt).setBounds(20,95,150,20);
		
		//address
		JLabel addr = new JLabel("Address");
		addrtxt = new JTextField();
		add(addr).setBounds(20,120,100,11);
		add(addrtxt).setBounds(20,135,150,20);
		
		//does he larp
		JLabel larp = new JLabel("Larper?");
		larptxt = new JTextField();
		add(larp).setBounds(20,160,100,11);
		add(larptxt).setBounds(20,175,150,20);
		
		//buttons
		JButton addbtn = new JButton("Add");
		JButton updtbtn = new JButton("Update");
		JButton clrbtn = new JButton("Clear");
		JButton delbtn = new JButton("Delete");
		add(addbtn).setBounds(30,200,100,20);
		add(updtbtn).setBounds(30,230,100,20);
		add(clrbtn).setBounds(30,260,100,20);
		add(delbtn).setBounds(30,290,100,20);


		//table
		String[] colms = {"Name","Address","LarpMeter"};
		dtm = new DefaultTableModel(colms,0);
		JTable table = new JTable(dtm);
		JScrollPane jsp = new JScrollPane(table);
		add(jsp).setBounds(200,30,450,350);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					nametxt.setText(dtm.getValueAt(row, 0).toString());
					addrtxt.setText(dtm.getValueAt(row, 1).toString());
					larptxt.setText(dtm.getValueAt(row, 2).toString());
				}
			}
		});
		
		addbtn.addActionListener(e->{
			Object[] row = {
					nametxt.getText(),
					addrtxt.getText(),
					larptxt.getText()
			};
			dtm.addRow(row);
			clear();
		});
		
		clrbtn.addActionListener(e->clear());

	}
	public void clear() {
		nametxt.setText("");
		addrtxt.setText("");
		larptxt.setText("");

	}
	public void read() {
		dtm.setRowCount(0);
		File f = new File("larp.txt");
		if(!f.exists())return;
		try(BufferedReader br = new BufferedReader(new FileReader("larp.txt"))) {
			String line;
			while((line=br.readLine())!=null) {
				String row[] = line.split("#");
				dtm.addRow(row);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}

   public static void main(String[] args) {
       new pract5();
   } 
}
