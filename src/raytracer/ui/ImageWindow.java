package raytracer.ui;

import raytracer.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ImageWindow extends JFrame implements ActionListener
{
    private JMenu menu;
    private JMenuItem file, setting, close;
    private static JLabel label;
    private static JPanel imagePanel;
    static JFrame f;

    public ImageWindow(BufferedImage img)
    {
        // Initialize image as icon
        label = new JLabel(new ImageIcon(img));
        // Panel that houses the image
        imagePanel = new JPanel();
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
        // Open a new SettingsWindow and allow it to only be opened once
        setting.addActionListener(new ActionListener() {
            private SettingsWindow ww = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ww == null) {
                    ww = new SettingsWindow();
                }
                ww.f.setVisible(true);
            }
        });
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

        // Exits out of the program
        if (e.getSource() == close)
            System.exit(0);
    }

    public static void changeImg()
    {
        Render tracer = new Render();
        label.setIcon(new ImageIcon(tracer.draw()));
        f.revalidate();
        f.repaint();
    }
}
