package raytracer.scene;

import java.awt.*;
import javax.swing.*;

public class Background extends JPanel
{
    Image background;

    public Background()
    {
        ImageIcon obj = new ImageIcon("/src/textures/windows.png");
        background = obj.getImage();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0,0 , null);
    }

}
