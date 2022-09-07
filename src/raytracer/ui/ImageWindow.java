package raytracer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageWindow
{
    private JFrame f;

    public ImageWindow(BufferedImage img)
    {
        JLabel label = new JLabel(new ImageIcon(img));
        JPanel imagePanel = new JPanel();
        imagePanel.add(label);

        f = new JFrame();
        f.setSize(img.getWidth(), img.getHeight());
        f.setLayout(new FlowLayout());
        f.add(imagePanel);
        f.setVisible(true);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
