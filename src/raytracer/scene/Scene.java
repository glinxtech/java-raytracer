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

        shapes.add(new Sphere(
                new Point(40, 6.0, 35.0),
                3.0,
                Material.bluePlastic
        ));

        /* lights.add(new PointLight(
                new Point(60.0, 250.0, 400.0),
                0.6
        )); */

        lights.add(new PointLight(
                        new Point(30, 10.0, 15.0),
                        100
                )
        );

        lights.add(new PointLight(
                        new Point(-5, 5, 8),
                        80
                )
        );

        lights.add(new PointLight(
                        new Point(40, 6, 4),
                        50
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