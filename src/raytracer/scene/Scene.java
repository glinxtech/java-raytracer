package raytracer.scene;

import java.util.ArrayList;

import raytracer.colour.Material;
import raytracer.math.Point;
import raytracer.shapes.*;
import raytracer.lights.*;

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

        shapes.add(new Sphere(
                new Point(20, -5.0, 30.0),
                7.0,
                Material.greenPlastic
        ));

       lights.add(new PointLight(
                        new Point(30, 10.0, 15.0),
                        100
                )
        );

        lights.add(new PointLight(
                        new Point(-5, 12, 8),
                        60
                )
        );
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