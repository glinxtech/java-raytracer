import raytracer.ui.ImageWindow;
import raytracer.render.*;

public class Main
{
    public static void main(String[] args)
    {
        Render tracer = new Render();
        new ImageWindow(tracer.draw());
    }
}
