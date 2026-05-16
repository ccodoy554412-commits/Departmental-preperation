package Departmental_test;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
public class practice2 extends JFrame {
    static JTextField nametxt, roomtxt, paymentxt;
    static DefaultTableModel dtm;

    practice2(){
        setVisible(true);
        setSize(700,450);
        setTitle("Residency");
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //name
        JLabel name = new JLabel("Name");
        nametxt = new JTextField();
        add(name).setBounds(20,50,100,10);
        add(nametxt).setBounds(20,65,150,20);

        //room number 
        JLabel room = new JLabel("Room Number");
        roomtxt = new JTextField();
        add(room).setBounds(20,90,100,10);
        add(roomtxt).setBounds(20,105,150,20);

        //payment
        JLabel payment = new JLabel("Ballance");
        paymentxt = new JTextField();
        add(payment).setBounds(20,130,100,10);
        add(paymentxt).setBounds(20,145,150,20);
        
        String[] cels = {"Name","Room Number","Ballance"};
        dtm = new DefaultTableModel(cels,0);
        JTable table = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp).setBounds(190,20,450,380);

        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                if(row != -1){
                    nametxt.setText(dtm.getValueAt(row, 0).toString());
                    roomtxt.setText(dtm.getValueAt(row, 1).toString());
                    paymentxt.setText(dtm.getValueAt(row, 2).toString());
                }
            }
        });
        //buttons
        JButton addbtn = new JButton("add");
        JButton updtbtn = new JButton("Update");
        JButton delbtn = new JButton("Delete");
        JButton clrbtn = new JButton("Clear");
        add(addbtn).setBounds(45,190,100,30);
        add(updtbtn).setBounds(45,230,100,30);
        add(clrbtn).setBounds(45,270,100,30);
        add(delbtn).setBounds(45,310,100,30);

        addbtn.addActionListener(e->{

            Object[] row = {
                nametxt.getText(),
                roomtxt.getText(),
                paymentxt.getText()
            };
            dtm.addRow(row);

            clear();

        });

        clrbtn.addActionListener(e-> clear());

    }
    public void clear(){
        nametxt.setText("");
        roomtxt.setText("");
        paymentxt.setText("");
    }
    public static void read(){
        dtm.setRowCount(0);
        try(BufferedReader br = new BufferedReader(new FileReader("payment.txt"))) {
            String line;
            while((line = br.readLine())!= null){
                String[] row = line.split("#");
                dtm.addRow(row);
            }
        } catch (Exception e) {
        }
    }
    
public static void main(String[] args) {
 new practice2();   
}
}
