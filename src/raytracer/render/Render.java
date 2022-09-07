package raytracer.render;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render
{
    public BufferedImage draw()
    {
        int red = new Color(255,0,0).getRGB();

        BufferedImage result = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

        System.out.println("drawing...");

        for (int x = 0; x < 1000; x++)
        {
            for (int y = 0; y < 1000; y++)
            {
                result.setRGB(x, y, red);
            }
        }

        System.out.println("Done");

        return result;
    }
}
