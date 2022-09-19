import raytracer.scene.Render;
import raytracer.ui.ImageWindow;

public class Main
{
    public static void main(String[] args)
    {
        Render tracer = new Render();
        new ImageWindow(tracer.draw());
    }
}
