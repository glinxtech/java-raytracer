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
                new Point(20.0, 0.0, 500.0),
                50.0,
                Material.redPlastic
        ));

        lights.add(new PointLight(
                new Point(60.0, 250.0, 300.0),
                0.75
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
