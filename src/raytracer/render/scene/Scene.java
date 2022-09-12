package raytracer.render.scene;

import java.util.ArrayList;

import raytracer.render.Material;
import raytracer.render.Point;
import raytracer.render.shapes.*;
import raytracer.render.lights.*;

public class Scene
{
    private ArrayList<Shape> shapes;
    private ArrayList<Light> lights;

    public Scene()
    {
        shapes = new ArrayList<Shape>();
        lights = new ArrayList<Light>();

        shapes.add(new Sphere(
                new Point(0.0, 0.0, 20.0),
                5.0,
                Material.redPlastic
        ));

        /* lights.add(new PointLight(
                new Point(60.0, 250.0, 400.0),
                0.6
        )); */

        lights.add(new PointLight(
                new Point(5.0, 10.0, 20.0),
                40
        ));
    }

    public ArrayList<Shape> getShapes()
    {
        return this.shapes;
    }

    public ArrayList<Light> getLights()
    {
        return this.lights;
    }
}
