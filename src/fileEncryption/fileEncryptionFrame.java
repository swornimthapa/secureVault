package fileEncryption;


import UI_elements.RoundedButton;
import UI_elements.RoundedLabel;
import UI_elements.RoundedPanel;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class fileEncryptionFrame {
    JFrame frame;
    JButton chooseEncryptionFileBtn;
    JButton encryptBtn;

    JButton chooseDecryptionFileBtn;
    JButton decryptBtn;

    JTable filedetailstable;
    DefaultTableModel tableModel;
    DefaultTableModel selectedtableModel;
    JTable selectedfiledetailstable;
    JPanel forSendingstatus;
    JScrollPane sendingStatuscrollpane;
    JScrollPane ReceivingStatuscrollpane;
    JPanel forReceivingstatus;


    public fileEncryptionFrame(){


        frame= new JFrame("File Encryption");
        frame.setSize(1200,700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);
        initializeComponent();
    }

    private void initializeComponent() {
        RoundedLabel recivedfilelable = new RoundedLabel("Decryption",30);
        recivedfilelable.setBounds(820,25,150,30);

        recivedfilelable.setBackground(Color.decode("#00563E"));
        recivedfilelable.setForeground(Color.white);
        recivedfilelable.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        recivedfilelable.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(recivedfilelable);


        tableModel=new DefaultTableModel();
        tableModel.addColumn("Filename");
        tableModel.addColumn("File size");
        filedetailstable=new JTable(tableModel){
            public TableCellEditor getCellEditor(int row, int column) {
                return null;
            }
        };
        filedetailstable.setBackground(Color.white);

        JScrollPane tablescrollpane = new JScrollPane(filedetailstable);
        tablescrollpane.setBounds(640,60,500,200);
        tablescrollpane.setBackground(Color.white);
        tablescrollpane.getViewport().setBackground(Color.WHITE);
        tablescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        frame.add(tablescrollpane);

        chooseDecryptionFileBtn = new RoundedButton("Decrypt",30);
        chooseDecryptionFileBtn.setBounds(990,270,150,30);
        chooseDecryptionFileBtn.setBackground(Color.decode("#00563E"));
        chooseDecryptionFileBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        chooseDecryptionFileBtn.setForeground(Color.white);

        frame.getContentPane().add(chooseDecryptionFileBtn);

        decryptBtn = new RoundedButton("Choose File",30);
        decryptBtn.setBounds(830,270,150,30);
        decryptBtn.setBackground(Color.decode("#00563E"));
        decryptBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        decryptBtn.setForeground(Color.white);

        frame.getContentPane().add(decryptBtn);



        //for outgoing
        selectedtableModel=new DefaultTableModel();
        selectedtableModel.addColumn("Filename");
        selectedtableModel.addColumn("File size");
        selectedfiledetailstable=new JTable(selectedtableModel){
            public TableCellEditor getCellEditor(int row, int column) {
                return null; // Return null cell editor to make cells non-editable
            }
        };
        selectedfiledetailstable.setBackground(Color.white);
        JScrollPane selectedtablescrollpane = new JScrollPane(selectedfiledetailstable);
        selectedtablescrollpane.setBackground(Color.white);
        selectedtablescrollpane.getViewport().setBackground(Color.WHITE);
        selectedtablescrollpane.setBounds(40,60,500,200);
        selectedtablescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        frame.add(selectedtablescrollpane);


        RoundedLabel sendfilelabel = new RoundedLabel("Encryption",30);
        sendfilelabel.setBounds(220,25,150,30);
        sendfilelabel.setHorizontalAlignment(JLabel.CENTER);

        sendfilelabel.setBackground(Color.decode("#660001"));
        sendfilelabel.setForeground(Color.white);
        sendfilelabel.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        frame.getContentPane().add(sendfilelabel);



        chooseEncryptionFileBtn = new RoundedButton("Choose file",30);
        chooseEncryptionFileBtn.setBounds(230,270,150,30);
       chooseEncryptionFileBtn.setBackground(Color.decode("#660001"));
        chooseEncryptionFileBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        chooseEncryptionFileBtn.setForeground(Color.white);

        frame.getContentPane().add(chooseEncryptionFileBtn);


        encryptBtn = new RoundedButton("Encrypt",30);
        encryptBtn.setBounds(390,270,150,30);
        encryptBtn.setBackground(Color.decode("#660001"));
        encryptBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        encryptBtn.setForeground(Color.white);

       frame.getContentPane().add(encryptBtn);




        //for displaying connection status
//        forconnectionstatus = new JPanel();
//        forconnectionstatus.setBackground(Color.BLACK);
//        forconnectionstatus.setLayout(new BoxLayout(forconnectionstatus, BoxLayout.Y_AXIS));
//        connectionStatuscrollpane = new JScrollPane(forconnectionstatus);
//        connectionStatuscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//        connectionStatuscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        connectionStatuscrollpane.setBounds(20,565,1188,100);
//        connectionStatuscrollpane.getViewport().setBackground(Color.BLACK);
//        connectionStatuscrollpane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        frame.add(connectionStatuscrollpane);



        forSendingstatus = new JPanel();
        forSendingstatus.setBackground(Color.BLACK);
        forSendingstatus.setLayout(new BoxLayout(forSendingstatus, BoxLayout.Y_AXIS));

        sendingStatuscrollpane = new JScrollPane(forSendingstatus);
        sendingStatuscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        sendingStatuscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sendingStatuscrollpane.setBounds(40,360,500,150);
        sendingStatuscrollpane.getViewport().setBackground(Color.BLACK);
        //        sendingStatuscrollpane.setBackground(Color.BLACK);
        sendingStatuscrollpane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add( sendingStatuscrollpane);
                sendingStatuscrollpane.add(forSendingstatus);


        //for displaying receiving status
        forReceivingstatus = new JPanel();
        forReceivingstatus.setBackground(Color.BLACK);
        forReceivingstatus.setLayout(new BoxLayout(forReceivingstatus, BoxLayout.Y_AXIS));

        ReceivingStatuscrollpane = new JScrollPane(forReceivingstatus);
        ReceivingStatuscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        ReceivingStatuscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ReceivingStatuscrollpane.setBounds(640,360,500,150);
        ReceivingStatuscrollpane.getViewport().setBackground(Color.BLACK);
        ReceivingStatuscrollpane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add( ReceivingStatuscrollpane);


        RoundedPanel reveivedfilepanel = new RoundedPanel(30);
        reveivedfilepanel.setBounds(620,20,540,510);
        reveivedfilepanel.setBackground(Color.decode("#013221"));
        frame.getContentPane().add(reveivedfilepanel);

        RoundedPanel sendingfilepanel = new RoundedPanel(30);
        sendingfilepanel.setBounds(20,20,540,510);
        sendingfilepanel.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(sendingfilepanel);



    }

    public void displaySendingstatus(String filename,String type){
        JPanel jpstatus = new JPanel();
        jpstatus.setBackground(Color.BLACK);
        jpstatus.setLayout(new BoxLayout(jpstatus, BoxLayout.Y_AXIS));
        JLabel jlstatus = null;
        switch (type){
            case "FILE_INFO_SENT":
                jlstatus = new JLabel("SENT INFO : "+filename);
                break;
            case "FILE_CONTENT_SENDING":
                jlstatus = new JLabel("SENDING : "+filename+"..........");
                break;
            case "FILE_CONTENT_SENT":
                jlstatus = new JLabel("SENT : "+filename);
                break;


        }
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 15);
        jlstatus.setFont(timesNewRomanFont);
        jlstatus.setForeground(Color.RED);
        jlstatus.setBorder(new EmptyBorder(1,0,1,0));
        jpstatus.add(jlstatus);
        forSendingstatus.add(jpstatus);

        sendingStatuscrollpane.setViewportView(forSendingstatus);
    }
    public void displayReceivingStatus(String filename, String type){
        JPanel jpstatus = new JPanel();
        jpstatus.setBackground(Color.BLACK);
        jpstatus.setLayout(new BoxLayout(jpstatus, BoxLayout.Y_AXIS));
        JLabel jlstatus = null;
        switch (type){
            case "RECEIVING_CONTENT":
                jlstatus = new JLabel("DOWNLOADING : "+filename);
                break;
            case "RECEIVED_CONTENT":
                jlstatus = new JLabel("DOWNLOAD COMPLETED : "+filename);

        }
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 15);
        jlstatus.setFont(timesNewRomanFont);
        jlstatus.setForeground(Color.green);
        jlstatus.setBorder(new EmptyBorder(1,0,1,0));
        jpstatus.add(jlstatus);
        forReceivingstatus.add(jpstatus);

        ReceivingStatuscrollpane.setViewportView(forReceivingstatus);
    }
    public void showfiledetails(String filename, int filesize ){
        Object[] newRow = {filename,filesize};
        tableModel.addRow(newRow);
    }
    public void showSelecteffiledetails(File[] file){
        for(File filetemp :file){
            String filename = filetemp.getName();
            int filesize = (int) filetemp.length();
            System.out.println(filename+filesize);
            Object[] newRow = {filename,filesize};
            selectedtableModel.addRow(newRow);
        }
    }



}