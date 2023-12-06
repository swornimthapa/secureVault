package login;

import javax.swing.*;
import java.awt.*;

public class login extends JFrame {
    public static void main(String[] args)  {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginFrame frame= new loginFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
