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
        shapes.add(new Sphere(
                new Point(400.0, 200.0, 50.0),
                100.0,
                Material.redPlastic
        ));

        lights.add(new PointLight(
                new Point(300.0, 500.0, 0.0),
                0.75
        ));
    }
}
