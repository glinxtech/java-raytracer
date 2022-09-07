package ui;

import javax.swing.*;
import java.io.PrintStream;

public class ImageWindow
{
    private JFrame f;

    public ImageWindow()
    {
        f = new JFrame();
        f.setSize(1920,1080);
        f.setLayout(null);
        f.setVisible(true);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
