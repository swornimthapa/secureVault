package fileEncryption;


import UI_elements.RoundedButton;
import UI_elements.RoundedLabel;
import UI_elements.RoundedPanel;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class fileEncryptionFrame implements ActionListener , MouseListener{
    String username;
    String password;
    JFrame frame;
    JButton chooseEncryptionFileBtn;
    JButton encryptBtn;

    JButton chooseDecryptionFileBtn;
    JButton decryptBtn;

    JTable fordecryptionfiledetailstable;
    DefaultTableModel encryptiontableModel;
    DefaultTableModel decryptiontableModel;
    JTable forEncryptionfiledetailstable;
    JPanel forEncryptionstatusPanel;
    JScrollPane forEncryptionstatusScrollpane;
    JScrollPane decryptionStatuscrollpane;
    JPanel forDecryptionstatusPanel;

    File[] filetoencrypt;
    File[] filetodecrypt;
//    File selectedFiletoEncrypt;

    int previewSelectedrowindexForencryption;
    int previewSelectedrowindexFordecryption;
    Map<String, String> encryptionfileMap = new HashMap<>();
    Map<String, String> decrytionfileMap = new HashMap<>();

    public fileEncryptionFrame(String username , String password){
        this.username = username;
        this.password = password;

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
        RoundedLabel decryptionlable = new RoundedLabel("Decryption",30);
        decryptionlable.setBounds(820,25,150,30);

        decryptionlable.setBackground(Color.decode("#00563E"));
        decryptionlable.setForeground(Color.white);
        decryptionlable.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        decryptionlable.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(decryptionlable);


        decryptiontableModel=new DefaultTableModel();
        decryptiontableModel.addColumn("Filename");
        decryptiontableModel.addColumn("File size");
        fordecryptionfiledetailstable=new JTable(decryptiontableModel){
            public TableCellEditor getCellEditor(int row, int column) {
                return null;
            }
        };
        fordecryptionfiledetailstable.setBackground(Color.white);
        fordecryptionfiledetailstable.addMouseListener(this);
        JScrollPane tablescrollpane = new JScrollPane(fordecryptionfiledetailstable);
        tablescrollpane.setBounds(640,60,500,200);
        tablescrollpane.setBackground(Color.white);
        tablescrollpane.getViewport().setBackground(Color.WHITE);
        tablescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        frame.add(tablescrollpane);

        decryptBtn = new RoundedButton("Decrypt",30);
        decryptBtn.setBounds(990,270,150,30);
        decryptBtn.setBackground(Color.decode("#00563E"));
        decryptBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        decryptBtn.setForeground(Color.white);
        decryptBtn.addActionListener(this);
        frame.getContentPane().add(decryptBtn);

        chooseDecryptionFileBtn = new RoundedButton("Choose File",30);
        chooseDecryptionFileBtn.setBounds(830,270,150,30);
        chooseDecryptionFileBtn.setBackground(Color.decode("#00563E"));
        chooseDecryptionFileBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        chooseDecryptionFileBtn.setForeground(Color.white);
        chooseDecryptionFileBtn.addActionListener(this);
        frame.getContentPane().add(chooseDecryptionFileBtn);



//        //for Encryption
        encryptiontableModel=new DefaultTableModel();
        encryptiontableModel.addColumn("Filename");
        encryptiontableModel.addColumn("File size");
        forEncryptionfiledetailstable=new JTable(encryptiontableModel){
            public TableCellEditor getCellEditor(int row, int column) {
                return null; // Return null cell editor to make cells non-editable
            }
        };
        forEncryptionfiledetailstable.setBackground(Color.white);
        forEncryptionfiledetailstable.addMouseListener(this);
        JScrollPane selectedtablescrollpane = new JScrollPane(forEncryptionfiledetailstable);
        selectedtablescrollpane.setBackground(Color.white);
        selectedtablescrollpane.getViewport().setBackground(Color.WHITE);
        selectedtablescrollpane.setBounds(40,60,500,200);
        selectedtablescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        frame.add(selectedtablescrollpane);


        RoundedLabel encryptionlabel = new RoundedLabel("Encryption",30);
        encryptionlabel.setBounds(220,25,150,30);
        encryptionlabel.setHorizontalAlignment(JLabel.CENTER);

        encryptionlabel.setBackground(Color.decode("#660001"));
        encryptionlabel.setForeground(Color.white);
        encryptionlabel.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        frame.getContentPane().add(encryptionlabel);



        chooseEncryptionFileBtn = new RoundedButton("Choose file",30);
        chooseEncryptionFileBtn.setBounds(230,270,150,30);
        chooseEncryptionFileBtn.setBackground(Color.decode("#660001"));
        chooseEncryptionFileBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        chooseEncryptionFileBtn.setForeground(Color.white);
        chooseEncryptionFileBtn.addActionListener(this);
        frame.getContentPane().add(chooseEncryptionFileBtn);


        encryptBtn = new RoundedButton("Encrypt",30);
        encryptBtn.setBounds(390,270,150,30);
        encryptBtn.setBackground(Color.decode("#660001"));
        encryptBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        encryptBtn.setForeground(Color.white);
        encryptBtn.addActionListener(this);
        frame.getContentPane().add(encryptBtn);




        //for encryption display
        forEncryptionstatusPanel = new JPanel();
        forEncryptionstatusPanel.setBackground(Color.BLACK);
        forEncryptionstatusPanel.setLayout(new BoxLayout(forEncryptionstatusPanel, BoxLayout.Y_AXIS));
        forEncryptionstatusScrollpane = new JScrollPane(forEncryptionstatusPanel);
        forEncryptionstatusScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        forEncryptionstatusScrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        forEncryptionstatusScrollpane.setBounds(40,360,500,150);
        forEncryptionstatusScrollpane.getViewport().setBackground(Color.BLACK);
        //        sendingStatuscrollpane.setBackground(Color.BLACK);
        forEncryptionstatusScrollpane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add( forEncryptionstatusScrollpane);
        forEncryptionstatusScrollpane.add(forEncryptionstatusPanel);


        //for displaying receiving status
        forDecryptionstatusPanel = new JPanel();
        forDecryptionstatusPanel.setBackground(Color.BLACK);
        forDecryptionstatusPanel.setLayout(new BoxLayout(forDecryptionstatusPanel, BoxLayout.Y_AXIS));
        decryptionStatuscrollpane = new JScrollPane(forDecryptionstatusPanel);
        decryptionStatuscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        decryptionStatuscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        decryptionStatuscrollpane.setBounds(640,360,500,150);
        decryptionStatuscrollpane.getViewport().setBackground(Color.BLACK);
        decryptionStatuscrollpane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add( decryptionStatuscrollpane);
        decryptionStatuscrollpane.add(forDecryptionstatusPanel);

        RoundedPanel encryptionPanel = new RoundedPanel(30);
        encryptionPanel.setBounds(620,20,540,510);
        encryptionPanel.setBackground(Color.decode("#013221"));
        frame.getContentPane().add(encryptionPanel);

        RoundedPanel decryptionpanel = new RoundedPanel(30);
        decryptionpanel.setBounds(20,20,540,510);
        decryptionpanel.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(decryptionpanel);

    }

        public void showSelecteffiledetailsforEncryption(File[] file){
        for(File filetemp :file){
            String filename = filetemp.getName();
            int filesize = (int) filetemp.length();
//            System.out.println(filename+filesize);
            Object[] newRow = {filename,filesize};
            encryptiontableModel.addRow(newRow);

            encryptionfileMap.put(filetemp.getName(), filetemp.getAbsolutePath());

        }
    }
    public void showSelecteffiledetailsforDecrption(File[] file){
        for(File filetemp :file){
            String filename = filetemp.getName();
            int filesize = (int) filetemp.length();
//            System.out.println(filename+filesize);
            Object[] newRow = {filename,filesize};
            decryptiontableModel.addRow(newRow);
            decrytionfileMap.put(filetemp.getName(), filetemp.getAbsolutePath());

        }
    }

        public void displayEncryptingStatus(String filename, String type){
        JPanel jpstatus = new JPanel();
        jpstatus.setBackground(Color.BLACK);
        jpstatus.setLayout(new BoxLayout(jpstatus, BoxLayout.Y_AXIS));
        JLabel jlstatus = null;
        switch (type){
            case "ENCRYPTING":
                jlstatus = new JLabel("ENCRYPTING : "+filename);
                jlstatus.setForeground(Color.red);

                break;
            case "ENCRYPTION COMPLETED":
                jlstatus = new JLabel("ENCRYPTION COMPLETED : "+filename);
                jlstatus.setForeground(Color.green);


        }
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 15);
        jlstatus.setFont(timesNewRomanFont);
        jlstatus.setBorder(new EmptyBorder(1,0,1,0));
        jpstatus.add(jlstatus);
        forEncryptionstatusPanel.add(jpstatus);
        forEncryptionstatusScrollpane.setViewportView(forEncryptionstatusPanel);

    }

    public void displayDecryptingStatus(String filename, String type){
        JPanel jpstatus = new JPanel();
        jpstatus.setBackground(Color.BLACK);
        jpstatus.setLayout(new BoxLayout(jpstatus, BoxLayout.Y_AXIS));
        JLabel jlstatus = null;
        switch (type){
            case "DECRYPTING":
                jlstatus = new JLabel("DECRYPTING : "+filename);
                jlstatus.setForeground(Color.red);

                break;
            case "DECRYPTION COMPLETED":
                jlstatus = new JLabel("DECRYPTING COMPLETED : "+filename);
                jlstatus.setForeground(Color.green);


        }
        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 15);
        jlstatus.setFont(timesNewRomanFont);
        jlstatus.setBorder(new EmptyBorder(1,0,1,0));
        jpstatus.add(jlstatus);
        forDecryptionstatusPanel.add(jpstatus);
        decryptionStatuscrollpane.setViewportView(forDecryptionstatusPanel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseEncryptionFileBtn) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setMultiSelectionEnabled(true);
            jFileChooser.setDialogTitle(" Chose a file to encrypt");
            jFileChooser.setPreferredSize(new Dimension(600, 450));
            if (jFileChooser.showDialog(null, "open") == JFileChooser.APPROVE_OPTION) {
                encryptiontableModel.setRowCount(0);
                filetoencrypt = jFileChooser.getSelectedFiles();  //filetoencrytp will have the path
                showSelecteffiledetailsforEncryption(filetoencrypt);

                //


            }
        }

        if (e.getSource() == encryptBtn) {
            String filename = (String) encryptiontableModel.getValueAt(previewSelectedrowindexForencryption, 0);
            String filetoencryptPathforEncryption = encryptionfileMap.get(filename);
            System.out.println(filetoencryptPathforEncryption);
            File inputFile = new File(filetoencryptPathforEncryption);
            // Append "_encrypted" to the filename before the file extension
            String encryptedFileName = inputFile.getName().replaceFirst("[.][^.]+$", "_encrypted$0");
            File encryptedFile = new File(inputFile.getParent(), encryptedFileName);
            try {
                String keyString = password;
                byte[] keyBytes = keyString.getBytes("UTF-8");
                MessageDigest sha = MessageDigest.getInstance("SHA-256");
                keyBytes = sha.digest(keyBytes);
                keyBytes = Arrays.copyOf(keyBytes, 32); // 256 bits = 32 bytes
                Key secretKey = new SecretKeySpec(keyBytes, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                FileInputStream inputStream = new FileInputStream(inputFile);
                FileOutputStream outputStream = new FileOutputStream(encryptedFile); // Save the encrypted content to a new file

                int bytesRead;
                byte[] buffer = new byte[1024];
                displayEncryptingStatus(filename, "ENCRYPTING");
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byte[] output = cipher.update(buffer, 0, bytesRead);
//                    outputStream.write(output);
                    if (output != null)
                        outputStream.write(output);
                }
                byte[] output = cipher.doFinal();
                if (output != null){
                    outputStream.write(output);

                }
//                byte[] output = cipher.doFinal();
//                outputStream.write(output);

                // Close streams
                inputStream.close();
                outputStream.close();
                displayEncryptingStatus(filename, "ENCRYPTION COMPLETED");
                // Delete the original unencrypted file
                if (inputFile.delete()) {
                    System.out.println("File encrypted and original file deleted successfully.");
                } else {
                    System.out.println("Error deleting the original unencrypted file.");
                }

            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException |
                     IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                     FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        if (e.getSource() == chooseDecryptionFileBtn) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setMultiSelectionEnabled(true);
            jFileChooser.setDialogTitle(" Chose a file to decrypt");
            jFileChooser.setPreferredSize(new Dimension(600, 450));
            if (jFileChooser.showDialog(null, "open") == JFileChooser.APPROVE_OPTION) {
                decryptiontableModel.setRowCount(0);
                filetodecrypt = jFileChooser.getSelectedFiles();  //filetoencrytp will have the path
                showSelecteffiledetailsforDecrption(filetodecrypt);
            }
        }


        if (e.getSource() == decryptBtn) {

                String filename = (String) decryptiontableModel.getValueAt(previewSelectedrowindexFordecryption, 0);

                String filetodecryptPathforDecryption = decrytionfileMap.get(filename);
//            System.out.println(filetodecryptPathforDecryption);
                File inputFile = new File(filetodecryptPathforDecryption);
                // remove "_encrypted" from the filename before the file extension
                System.out.println(inputFile);
                String decryptedFileName = inputFile.getName().replaceFirst("(.*)_encrypted([.][^.]+)$", "$1$2");
                File decryptedFile = new File(inputFile.getParent(), decryptedFileName);


                String keyString = password;
                // Generate a valid 256-bit AES key from the key string
                try {
                    byte[] keyBytes = keyString.getBytes("UTF-8");
                    MessageDigest sha = MessageDigest.getInstance("SHA-256");
                    keyBytes = sha.digest(keyBytes);
                    keyBytes = Arrays.copyOf(keyBytes, 32); // 256 bits = 32 bytes

                    // Create a secret key specification from the key bytes
                    Key secretKey = new SecretKeySpec(keyBytes, "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                    FileInputStream inputStream = new FileInputStream(inputFile);
                    FileOutputStream outputStream = new FileOutputStream(decryptedFile); // Save the encrypted content to a new file


                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    displayDecryptingStatus(filename,"DECRYPTING");
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    byte[] output = cipher.update(buffer, 0, bytesRead);
//                    outputStream.write(output);
                        byte[] output = cipher.update(buffer, 0, bytesRead);
//                    outputStream.write(output);
                        if (output != null)
                            outputStream.write(output);
                    }
                    byte[] output = cipher.doFinal();
                    if (output != null){
                        outputStream.write(output);
                    }
                    inputStream.close();
                    outputStream.close();
                    displayDecryptingStatus(filename,"DECRYPTION COMPLETED");
                    if (inputFile.delete()) {
                        System.out.println("File encrypted and original file deleted successfully.");
                    } else {
                        System.out.println("Error deleting the original unencrypted file.");
                    }

                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                }


        }
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==forEncryptionfiledetailstable){
            previewSelectedrowindexForencryption=forEncryptionfiledetailstable.getSelectedRow();
//            System.out.println(previewSelectedrowindexForencryption);
        }
        if(e.getSource()==fordecryptionfiledetailstable){
            System.out.println("hello");
            System.out.println("hello");
            previewSelectedrowindexFordecryption=fordecryptionfiledetailstable.getSelectedRow();
            System.out.println(previewSelectedrowindexFordecryption);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}