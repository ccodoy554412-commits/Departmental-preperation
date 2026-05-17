package Departmental_test;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class pract4 extends JFrame {
   static JTextField nametxt,contxt; 
    static DefaultTableModel dtm;
    pract4(){
        setVisible(true);
        setSize(700,500);
        setTitle("title");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel name = new JLabel("Name");
        nametxt = new JTextField();
        add(name).setBounds(20,50,100,10);
        add(nametxt).setBounds(20,65,150,20);

        //contact
        JLabel cont = new JLabel("Contact Number");
        contxt = new JTextField();
        add(cont).setBounds(20,90,100,10);
        add(contxt).setBounds(20,105,150,20);

        //buttons
        JButton addbtn = new JButton("Add");
        add(addbtn).setBounds(20,120,100,20);
        
        //table
        String[] colms = {"Name","Contact Number"};
        dtm = new DefaultTableModel(colms,0);
        JTable table = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp).setBounds(200,30,350,250);

        //mouselistenter
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row = table.getSelectedRow();
                if(row!=-1){
                    nametxt.setText(dtm.getValueAt(row, 0).toString());
                    contxt.setText(dtm.getValueAt(row, 1).toString());
                }
            }
        });

        addbtn.addActionListener(e->{
            Object[] row = {
                nametxt.getText(),
                contxt.getText()
            };
            dtm.addRow(row);
            clear();
        });
        
    }
    public void clear(){
        nametxt.setText("");
        contxt.setText("");
    }
    public void read(){
       dtm.setRowCount(0); 
       File f = new File("self.txt");
       if(!f.exists())return;

       try(BufferedReader br = new BufferedReader(new FileReader(f))) {
        String line;
        while((line= br.readLine())!=null){
            String row[] = line.split("#");
            dtm.addRow(row);
        }
       } catch (Exception e) {
       }
       
    }
    public static void main(String[] args) {
        new pract4();    
    }
}
