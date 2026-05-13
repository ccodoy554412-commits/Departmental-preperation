package Departmental_test;

import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.table.*;
public class practice1 extends JFrame {
    static DefaultTableModel dtm;
    static JTextField nametxt,agetxt,dobtxt;
    practice1(){
        setVisible(true);
        setSize(700,500);
        setTitle("");
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //family reg
        //name
        JLabel name = new JLabel("Name");
        nametxt = new JTextField();
        add(name).setBounds(30,50,100,10);
        add(nametxt).setBounds(30,65,150,30);
        //age
        JLabel age = new JLabel("Age");
        agetxt = new JTextField();
        add(age).setBounds(30,100,100,10);
        add(agetxt).setBounds(30,115,150,30);
        //date of birth
        JLabel dob = new JLabel("Date of Birth");
        dobtxt = new JTextField();
        add(dob).setBounds(30,150,150,10);
        add(dobtxt).setBounds(30,165,150,30);

        //table
        String[] colms = {"Name","Age","Date of Birth"};
        dtm = new DefaultTableModel(colms,0);
        JTable table = new JTable(dtm);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp).setBounds(200,50,460,300);

        //buttons
        JButton addbtn = new JButton("Add");
        JButton updbtn = new JButton("Update");
        JButton delbtn = new JButton("Delete");
        add(addbtn).setBounds(30,250,100,30);
        add(updbtn).setBounds(30,285,100,30);
        add(delbtn).setBounds(30,320,100,30);

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(){
                int row = table.getSelectedRow();
                if(row!=-1){
                    nametxt.setText(dtm.getValueAt(row, 0).toString());
                    agetxt.setText(dtm.getValueAt(row, 1).toString());
                    dobtxt.setText(dtm.getValueAt(row, 2).toString());
                }
            }
        });

        addbtn.addActionListener(e->{
            try(FileWriter fw = new FileWriter("FamilyInfo.txt",true)) {
               Object[] rowdata ={
                nametxt.getText(),
                agetxt.getText(),
                dobtxt.getText()
               }; 

               fw.write(rowdata[0]+"*"+rowdata[1]+"*"+rowdata[2]);
               fw.close();

               read();
               clear();

            } catch (Exception z) {
            }
        });
    }
    public void clear(){
        nametxt.setText("");
        agetxt.setText("");
        dobtxt.setText("");
    }
    public void read(){
        dtm.setRowCount(0);
        File f = new File("FamilyInfo.txt");
        if(!f.exists()) return;

        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while((line = br.readLine())!= null){
                String row[] = line.split("\\*");
                dtm.addRow(row);
            }
            
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        new practice1();       
    }
}
