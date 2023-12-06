package fileEncryption;

import javax.swing.*;
import java.awt.*;

public class fileEncryptionFrame {
    JFrame frame = new JFrame("File Encryption");
    public fileEncryptionFrame(){
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

    }
}
