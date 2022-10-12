package raytracer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ImageWindow extends JFrame implements ActionListener
{
    private JMenu menu;
    private JMenuItem file, setting, close;
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

        // Set up menubar
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("File");
        file = new JMenuItem("Open File");
        file.addActionListener(this);
        setting = new JMenuItem("Settings");
        setting.addActionListener(this);
        close = new JMenuItem("Exit");
        close.addActionListener(this);
        menu.add(file);
        menu.add(setting);
        menu.add(close);
        mb.add(menu);
        f.setJMenuBar(mb);


        // Reveal window
        f.setVisible(true);

        // This makes our application able to be shutdown by closing this window
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // Actions for the menu bar
    public void actionPerformed(ActionEvent e)
    {
        // If-statement for each action, as switches don't like ActionEvent

        // Opens file chooser
        if (e.getSource() == file)
        {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(this);
            // TODO: Finish when able to load objects
        }

        // Opens settings menu
        if (e.getSource() == setting)
            new SettingsWindow();

        // Exits out of the program
        if (e.getSource() == close)
            System.exit(0);
    }
}
