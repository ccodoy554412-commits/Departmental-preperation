package Departmental_test;

import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.table.*;
public class pract3 extends JFrame {
    static JTextField nametxt,addrtxt,contxt;
    static DefaultTableModel dtm;
    pract3(){
        setVisible(true);
        setSize(500,350);
        setTitle("");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //name
        JLabel name = new JLabel("Full Name");
        nametxt = new JTextField();
        add(name).setBounds(20,60,100,10);
        add(nametxt).setBounds(20,75,150,20);

        //address
        JLabel address = new JLabel("Address");
        addrtxt = new JTextField();
        add(address).setBounds(20,100,100,10);
        add(addrtxt).setBounds(20,115,150,20);

        //contact
        JLabel cont = new JLabel("Contact Number");
        contxt = new JTextField();       
        add(cont).setBounds(20,140,100,10);
        add(contxt).setBounds(20,155,150,20);

        //button
        JButton addbtn = new JButton("Add");
        JButton delbtn = new JButton("Delete");
        JButton updtbtn = new JButton("Update");
        JButton clrbtn = new JButton("Clear");
        add(addbtn).setBounds(20,180,81,20);
        add(updtbtn).setBounds(20,205,81,20);
        add(delbtn).setBounds(20,230,81,20);
        add(clrbtn).setBounds(20,255,81,20);
        
        //table
        String[] colms = {"Name","Address","Contact Number"};
        dtm = new DefaultTableModel(colms,0);
        JTable table = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp).setBounds(180,50,290,250);

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseAdapter e){
                int row = table.getRowCount();
                if(row != -1 ){
                    nametxt.setText(dtm.getValueAt(row, 0).toString());
                    addrtxt.setText(dtm.getValueAt(row, 1).toString());
                    contxt.setText(dtm.getValueAt(row, 2).toString());
                }
            }
        });

    }
    public void read(){
        
    }
    public static void main(String[]args){
        new pract3();
    }
}
