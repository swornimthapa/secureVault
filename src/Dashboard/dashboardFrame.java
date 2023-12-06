package Dashboard;

import UI_elements.RoundedButton;
import UI_elements.RoundedLabel;
import UI_elements.RoundedPanel;
import fileEncryption.fileEncryptionFrame;
import passwordManager.passwordManagerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboardFrame implements ActionListener {
    JFrame frame = new JFrame("Dash-Board");

    JButton openPasswordManagerButton;
    JButton openFileEncryptionButton;


    public dashboardFrame(){
        frame.setResizable(false);
        frame.setSize(900, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);

        initializecomponents();

    }

    private void initializecomponents() {

        RoundedLabel serverlable1 = new RoundedLabel("Password Manager",30);
        serverlable1.setBounds(50,35,330,70);
        serverlable1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        serverlable1.setForeground(Color.white);
        serverlable1.setBackground(Color.decode("#660001"));
        serverlable1.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        frame.getContentPane().add(serverlable1);

        RoundedLabel clientlable1 = new RoundedLabel("File Encryption",30);
        clientlable1.setBounds(490,35,330,70);
        clientlable1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        clientlable1.setForeground(Color.white);
        clientlable1.setBackground(Color.decode("#00563E"));
        clientlable1.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        frame.getContentPane().add(clientlable1);

        RoundedPanel serverpanel1 = new RoundedPanel(30);
        serverpanel1.setBounds(20,20,400,100);
        serverpanel1.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(serverpanel1);

        RoundedPanel clinetpanel1 = new RoundedPanel(30);
        clinetpanel1.setBounds(460,20,400,100);
        clinetpanel1.setBackground(Color.decode("#013221"));
        frame.getContentPane().add(clinetpanel1);


        // Create a JTextArea for the text paragraph
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);  // Enable line wrapping for long paragraphs
        textArea.setWrapStyleWord(true);  // Wrap on word boundaries
        textArea.setEditable(false);  // Make it uneditable
        textArea.setBackground(Color.decode("#3D0C01"));
        textArea.setForeground(Color.white);
        textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        // Add text to the JTextArea
        String paragraph = "A password manager is a specialized software tool designed to securely store and manage users login credentials, such as usernames and passwords, for various online accounts and services. Its primary purpose is to enhance digital security by eliminating the need for individuals to remember multiple complex passwords across different platforms.";
        textArea.setText(paragraph);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(Color.decode("#3D0C01"));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        scrollPane.setBounds(50,160,300,150);
        // Add the JScrollPane to the frame
        frame.add(scrollPane);



        JTextArea textArea1 = new JTextArea();
        textArea1.setLineWrap(true);  // Enable line wrapping for long paragraphs
        textArea1.setWrapStyleWord(true);  // Wrap on word boundaries
        textArea1.setEditable(false);  // Make it uneditable
        textArea1.setBackground(Color.decode("#013221"));
        textArea1.setForeground(Color.white);
        textArea1.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        // Add text to the JTextArea
        String paragraph1 = "A password manager is a specialized software tool designed to securely store and manage users login credentials, such as usernames and passwords, for various online accounts and services. Its primary purpose is to enhance digital security by eliminating the need for individuals to remember multiple complex passwords across different platforms.";
        textArea1.setText(paragraph1);

        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setBackground(Color.decode("#013221"));
        scrollPane1.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        scrollPane1.setBounds(480,160,300,150);
        // Add the JScrollPane to the frame
        frame.add(scrollPane1);


        openFileEncryptionButton = new RoundedButton("Open File Encryption",30);
        openFileEncryptionButton.setBounds(590, 370, 200, 30);
        openFileEncryptionButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        openFileEncryptionButton.setForeground(Color.white);
        openFileEncryptionButton.setBackground(Color.decode("#00563E"));
        openFileEncryptionButton.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        openFileEncryptionButton.addActionListener(this);
        frame.getContentPane().add(openFileEncryptionButton);

        openPasswordManagerButton = new RoundedButton("Open Password Manager",30);
        openPasswordManagerButton.setBounds(150, 370, 200, 30);
        openPasswordManagerButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        openPasswordManagerButton.setForeground(Color.white);
        openPasswordManagerButton.setBackground(Color.decode("#660001"));
        openPasswordManagerButton.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        openPasswordManagerButton.addActionListener(this);
        frame.getContentPane().add( openPasswordManagerButton);

        RoundedPanel serverpanel2 = new RoundedPanel(30);
        serverpanel2.setBounds(20,140,400,300);
        serverpanel2.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(serverpanel2);

        RoundedPanel clinetpanel2 = new RoundedPanel(30);
        clinetpanel2.setBounds(460,140,400,300);
        clinetpanel2.setBackground(Color.decode("#013221"));
        frame.getContentPane().add(clinetpanel2);

















//        UPPER PANEL FOR PASSWORD MANAGER
//        RoundedLabel passwordManagerLabel = new RoundedLabel("Password Manager",30);
//        passwordManagerLabel.setBounds(15,15,300,50);
//        passwordManagerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
//        passwordManagerLabel.setForeground(Color.white);
//        passwordManagerLabel.setBackground(Color.decode("#660001"));
//        passwordManagerLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
//        frame.getContentPane().add(passwordManagerLabel);
//
//        RoundedPanel passwordManagerMainPanel = new RoundedPanel(30);
//        passwordManagerMainPanel.setBounds(10,10,500,80);
//        passwordManagerMainPanel.setBackground(Color.decode("#3D0C01"));
//        frame.getContentPane().add(passwordManagerMainPanel);
//
////        LOWER PANEL FOR PASSWORD MANAGER
//
//        RoundedLabel ServiceLabel = new RoundedLabel("Application/Service",30);
//        ServiceLabel.setBounds(15,125,170,40);
//        ServiceLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
//        ServiceLabel.setForeground(Color.white);
//        ServiceLabel.setBackground(Color.decode("#660001"));
//        ServiceLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
//        frame.getContentPane().add(ServiceLabel);
//
//        JTextField ServiceTextfield = new JTextField();
//        ServiceTextfield.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//        ServiceTextfield.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
//        ServiceTextfield.setBounds(190, 125, 200, 40);
//        frame.getContentPane().add(ServiceTextfield);
//
//        RoundedButton  generatePasswordButton = new RoundedButton("Generate Password",30);
//        generatePasswordButton.setBounds(15, 180, 170, 30);
//        generatePasswordButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
//        generatePasswordButton.setForeground(Color.white);
//        generatePasswordButton.setBackground(Color.decode("#660001"));
//        generatePasswordButton.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
////        generatePasswordButton.addActionListener(this);
//        frame.getContentPane().add( generatePasswordButton);
//
//        JTextField generatedPassword = new JTextField();
//        generatedPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//        generatedPassword.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
//        generatedPassword.setBounds(190, 185, 200, 40);
//        frame.getContentPane().add(generatedPassword);
//
//
//        RoundedButton  savePasswordButton = new RoundedButton("Save Password",30);
//        savePasswordButton.setBounds(30, 220, 170, 30);
//        generatePasswordButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
//        savePasswordButton.setForeground(Color.white);
//        savePasswordButton.setBackground(Color.decode("#660001"));
//        savePasswordButton.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
////        generatePasswordButton.addActionListener(this);
//        frame.getContentPane().add( savePasswordButton);
//
//        RoundedLabel findPasswordLable = new RoundedLabel("Find Password",30);
//        findPasswordLable.setBounds(15,270,170,40);
//        findPasswordLable.setFont(new Font("Times New Roman", Font.BOLD, 15));
//        findPasswordLable.setForeground(Color.white);
//        findPasswordLable.setBackground(Color.decode("#660001"));
//        findPasswordLable.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
//        frame.getContentPane().add(findPasswordLable);
//
//        JTextField findPasswordTextfiled = new JTextField();
//        findPasswordTextfiled.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//        findPasswordTextfiled.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
//        findPasswordTextfiled.setBounds(190, 270, 200, 40);
//        frame.getContentPane().add(findPasswordTextfiled);
//
//
//
//        RoundedPanel passwordManagerPanel = new RoundedPanel(30);
//        passwordManagerPanel.setBounds(10,120,500,500);
//        passwordManagerPanel.setBackground(Color.decode("#3D0C01"));
//        frame.getContentPane().add(passwordManagerPanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openPasswordManagerButton) {
            EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                passwordManagerFrame passwordManagerFrame = new passwordManagerFrame();
                                frame.dispose();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        if (e.getSource() == openFileEncryptionButton) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        fileEncryptionFrame fileEncryptionFrame = new fileEncryptionFrame();
                        frame.dispose();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
