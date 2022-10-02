package raytracer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageWindow
{
    private JFrame f;

    public ImageWindow(BufferedImage img)
    {
        // Initialize image as icon
        JLabel label = new JLabel(new ImageIcon(img));
        // Panel that houses the image
        JPanel imagePanel = new JPanel();
        imagePanel.add(label);

        // Open a window with the size of our image
        f = new JFrame();
        f.setSize(img.getWidth(), img.getHeight());
        f.setLayout(new FlowLayout());

        // Add image to our window
        f.add(imagePanel);

        // Reveal window
        f.setVisible(true);

        // This makes our application able to be shutdown by closing this window
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
