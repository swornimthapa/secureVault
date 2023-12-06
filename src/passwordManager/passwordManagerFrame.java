package passwordManager;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;

import UI_elements.RoundedButton;
import UI_elements.RoundedLabel;
import UI_elements.RoundedPanel;

public class passwordManagerFrame implements ActionListener {
    String username;
    String password;
    JFrame frame = new JFrame("Password Manager");
    JButton saveButton;
    JButton showAllButton;
    JButton btnNewButton;
    JButton findButton;
    RoundedButton generateButton;
    JTextField findtextfield;
    JTextField servicesTextfiled;

    JTextField generatedPassword;

    DefaultTableModel tableModel;

    DefaultTableModel selectedtableModel;
    JTable selectedfiledetailstable;

    public passwordManagerFrame(String username , String password){
        this.username = username;
        this.password = password;

        frame.setResizable(false);
        frame.setSize(900, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);

        initializecomponents();
    }

    private void initializecomponents() {
        RoundedLabel generatelable1 = new RoundedLabel("Generate Password",30);
        generatelable1.setBounds(50,35,330,70);
        generatelable1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        generatelable1.setForeground(Color.white);
        generatelable1.setBackground(Color.decode("#660001"));
        generatelable1.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        frame.getContentPane().add(generatelable1);

        RoundedLabel findpasswordlable1 = new RoundedLabel("Find Password",30);
        findpasswordlable1.setBounds(490,35,330,70);
        findpasswordlable1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        findpasswordlable1.setForeground(Color.white);
        findpasswordlable1.setBackground(Color.decode("#00563E"));
        findpasswordlable1.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        frame.getContentPane().add(findpasswordlable1);

        RoundedPanel generatepasswordpanel1 = new RoundedPanel(30);
        generatepasswordpanel1.setBounds(20,20,400,100);
        generatepasswordpanel1.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(generatepasswordpanel1);

        RoundedPanel findpasswordpanel1 = new RoundedPanel(30);
        findpasswordpanel1.setBounds(460,20,400,100);
        findpasswordpanel1.setBackground(Color.decode("#013221"));
        frame.getContentPane().add(findpasswordpanel1);



        RoundedLabel serviceslabel = new RoundedLabel("Services",30);
        serviceslabel.setBounds(40,150,100,30);
        serviceslabel.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        serviceslabel.setForeground(Color.white);
        serviceslabel.setBackground(Color.decode("#660001"));
        serviceslabel.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        frame.getContentPane().add(serviceslabel);


        servicesTextfiled = new JTextField();
        servicesTextfiled.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        servicesTextfiled.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        servicesTextfiled.setBounds(150, 150, 200, 30);
        frame.getContentPane().add(servicesTextfiled);

        generateButton = new RoundedButton("Generate",30);
        generateButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        generateButton.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        generateButton.setBounds(45,200,90,30);
        generateButton.setBackground(Color.decode("#660001"));
        generateButton.setForeground(Color.white);
        generateButton.addActionListener(this);
        frame.getContentPane().add(generateButton);

        generatedPassword = new JTextField();
        generatedPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        generatedPassword.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        generatedPassword.setBounds(150, 200, 200, 30);
        frame.getContentPane().add(generatedPassword);


        saveButton = new RoundedButton("Save",30);
        saveButton.setBounds(150, 370, 150, 30);
        saveButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        saveButton.setForeground(Color.white);
        saveButton.setBackground(Color.decode("#660001"));
        saveButton.setBorder(BorderFactory.createLineBorder(Color.decode("#3D0C01")));
        saveButton.addActionListener(this);
        frame.getContentPane().add(saveButton);



        RoundedLabel findlabel = new RoundedLabel("Find",30);
        findlabel.setBounds(480, 150, 100, 30);
        findlabel.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        findlabel.setForeground(Color.white);
        findlabel.setBackground(Color.decode("#00563E"));
        findlabel.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        frame.getContentPane().add(findlabel);


        findtextfield = new JTextField();
        findtextfield.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        findtextfield.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        findtextfield.setBounds(590, 150, 200, 30);
        frame.getContentPane().add( findtextfield );



        findButton = new RoundedButton("Find",30);
        findButton.setBounds(500, 200, 100, 30);
        findButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        findButton.setForeground(Color.white);
        findButton.setBackground(Color.decode("#00563E"));
        findButton.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        findButton.addActionListener(this);
        frame.getContentPane().add(findButton);

        showAllButton = new RoundedButton("Show All",30);
        showAllButton.setBounds(630, 200, 100, 30);
        showAllButton.setFont(new Font("Times New Roman", Font.TYPE1_FONT, 15));
        showAllButton.setForeground(Color.white);
        showAllButton.setBackground(Color.decode("#00563E"));
        showAllButton.setBorder(BorderFactory.createLineBorder(Color.decode("#013221")));
        showAllButton.addActionListener(this);
        frame.getContentPane().add(showAllButton);


        selectedtableModel=new DefaultTableModel();
        selectedtableModel.addColumn("Services");
        selectedtableModel.addColumn("Password");
        selectedfiledetailstable=new JTable(selectedtableModel){
            public TableCellEditor getCellEditor(int row, int column) {
                return null; // Return null cell editor to make cells non-editable
            }
        };
        selectedfiledetailstable.setBackground(Color.white);
        JScrollPane selectedtablescrollpane = new JScrollPane(selectedfiledetailstable);
        selectedtablescrollpane.setBackground(Color.white);
        selectedtablescrollpane.getViewport().setBackground(Color.WHITE);
        selectedtablescrollpane.setBounds(463,235,390,190);
        selectedtablescrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//            selectedtablescrollpane.setBorder(null);
        //        filedetailstable.addMouseListener(this);
        frame.add(selectedtablescrollpane);

        RoundedPanel generatepasswordpanel2 = new RoundedPanel(30);
        generatepasswordpanel2.setBounds(20,140,400,300);
        generatepasswordpanel2.setBackground(Color.decode("#3D0C01"));
        frame.getContentPane().add(generatepasswordpanel2);

        RoundedPanel findpasswordpanel2 = new RoundedPanel(30);
        findpasswordpanel2.setBounds(460,140,400,300);
        findpasswordpanel2.setBackground(Color.decode("#013221"));
        frame.getContentPane().add(findpasswordpanel2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            generateRandomPassword();
        }

        if(e.getSource() == findButton){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost/securevault";
                Connection connection = (Connection) DriverManager.getConnection(url, "root", "Sw@mysql001");

                PreparedStatement st = connection.prepareStatement("SELECT services, servicespassword FROM servicestable WHERE susername=? AND suserpassword=? AND services=?");
                st.setString(1, username);
                st.setString(2, password);
                st.setString(3,  findtextfield.getText());
                ResultSet resultSet = st.executeQuery();
                selectedtableModel.setRowCount(0);

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    String service = resultSet.getString("services");
                    String userPassword = resultSet.getString("servicespassword");
                    selectedtableModel.addRow(new Object[]{service, userPassword});
                }

                // Set the updated model to the table
                selectedfiledetailstable.setModel(selectedtableModel);

                // Close the result set and statement
                resultSet.close();
                st.close();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

        if (e.getSource() == showAllButton) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost/securevault";
                Connection connection = (Connection) DriverManager.getConnection(url, "root", "Sw@mysql001");
                PreparedStatement st = connection.prepareStatement("SELECT services, servicespassword FROM servicestable WHERE susername=? AND suserpassword=?");
                st.setString(1, username);
                st.setString(2, password);

                ResultSet resultSet = st.executeQuery();

                // Clear existing data in the table model
                selectedtableModel.setRowCount(0);
                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    String service = resultSet.getString("services");
                    String userPassword = resultSet.getString("servicespassword");



                    //decrypting userPassword
                    String keyString = password;


                    // Generate a valid 256-bit AES key from the key string
                    byte[] keyBytes = keyString.getBytes("UTF-8");
                    MessageDigest sha = MessageDigest.getInstance("SHA-256");
                    keyBytes = sha.digest(keyBytes);
                    keyBytes = Arrays.copyOf(keyBytes, 32); // 256 bits = 32 bytes

// Create a secret key specification from the key bytes
                    Key secretKey = new SecretKeySpec(keyBytes, "AES");

                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);

// Decrypt the password
                    System.out.println("Encoded Password from Database: " + userPassword);
                    byte[] decryptedPasswordBytes = cipher.doFinal(Base64.getDecoder().decode(userPassword));

                    selectedtableModel.addRow(new Object[]{service, new String(decryptedPasswordBytes, "UTF-8")});




//                    selectedtableModel.addRow(new Object[]{service, userPassword});
                }
                // Set the updated model to the table
                selectedfiledetailstable.setModel(selectedtableModel);

                // Close the result set and statement
                resultSet.close();
                st.close();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeyException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalBlockSizeException ex) {
                throw new RuntimeException(ex);
            } catch (BadPaddingException ex) {
                throw new RuntimeException(ex);
            }

        }




        if (e.getSource() == saveButton) {
//            System.out.println(username);
//            System.out.println(password);
            if (generatedPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(btnNewButton, "Click the generate button, to generate the password");
            }else {
                try {


                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost/securevault";
                    Connection connection = (Connection) DriverManager.getConnection(url, "root", "Sw@mysql001");


//                    String keyString = serverframe.getSendingsecretkey();
                    String keyString = password;
//                                System.out.println(serverframe.getSendingsecretkey());

//                     Generate a valid 256-bit AES key from the key string
                    byte[] keyBytes = keyString.getBytes("UTF-8");
                    MessageDigest sha = MessageDigest.getInstance("SHA-256");
                    keyBytes = sha.digest(keyBytes);
                    keyBytes = Arrays.copyOf(keyBytes, 32); // 256 bits = 32 bytes

                    // Create a secret key specification from the key bytes
                    Key secretKey = new SecretKeySpec(keyBytes, "AES");
//                                System.out.println(secretKey);

                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

                    // Encrypt the generated password
                    byte[] generatedPasswordBytes = cipher.doFinal(generatedPassword.getText().getBytes("UTF-8"));

                    // Convert the encrypted bytes to Base64 for storage
                    String encryptedPassword = Base64.getEncoder().encodeToString(generatedPasswordBytes);

                    PreparedStatement st = connection.prepareStatement("INSERT INTO servicestable (services, servicespassword, susername, suserpassword) VALUES (?, ?, ?, ?)");
                    st.setString(1, servicesTextfiled.getText());    // Assuming servicesTextfiled.getText() is the value for sername
                    st.setString(2, encryptedPassword);     // Assuming generatedPassword.getText() is the value for servicespassword
                    st.setString(3, username);                         // Assuming username is the value for susername
                    st.setString(4, password);                         // Assuming password is the value for suserpassword
                    int rowsAffected  = st.executeUpdate();
                    if(rowsAffected > 0){
                        JOptionPane.showMessageDialog(btnNewButton, "You have successfully saved the password");
                    }else {
                        JOptionPane.showMessageDialog(btnNewButton, "Failed to save the password");
                    }
                    st.close();
                    connection.close();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                }
            }


        }
    }

    private void generateRandomPassword() {
        int minLength = 8;
        int maxLength = 15;

        int randomStrLength = (int) (Math.random() * (maxLength - minLength + 1)) + minLength;
        char[] possibleCharacters = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?").toCharArray();
        SecureRandom random = new SecureRandom();
        StringBuilder randomStr = new StringBuilder(randomStrLength);

        // Ensure at least one uppercase letter, one lowercase letter, one digit, and one special character
        randomStr.append(possibleCharacters[random.nextInt(26)]); // Uppercase letter
        randomStr.append(possibleCharacters[26 + random.nextInt(26)]); // Lowercase letter
        randomStr.append(possibleCharacters[52 + random.nextInt(10)]); // Digit
        randomStr.append(possibleCharacters[62 + random.nextInt(14)]); // Special character

        // Fill the remaining characters randomly
        for (int i = 4; i < randomStrLength; i++) {
            randomStr.append(possibleCharacters[random.nextInt(possibleCharacters.length)]);
        }

        // Shuffle the characters to make the order random
        for (int i = randomStr.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = randomStr.charAt(index);
            randomStr.setCharAt(index, randomStr.charAt(i));
            randomStr.setCharAt(i, temp);
        }
        generatedPassword.setText(randomStr.toString());
        System.out.println("Random password is: " + randomStr.toString());
    }
}
