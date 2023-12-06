package passwordManager;

import javax.swing.*;
import java.awt.*;

public class passwordManagerFrame {
    JFrame frame = new JFrame("Password Manager");
    public passwordManagerFrame(){
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
