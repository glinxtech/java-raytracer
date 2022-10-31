package raytracer.scene;

import java.util.ArrayList;
import java.util.stream.Collectors;

import raytracer.colour.Material;
import raytracer.math.Point;
import raytracer.shapes.*;
import raytracer.lights.*;

public class Scene
{
    public static ArrayList<Shape> shapes = new ArrayList<Shape>();
    public static ArrayList<Light> lights = new ArrayList<Light>();

    private ArrayList<Shape> unDupeShapes;
    private ArrayList<Light> unDupeLights;

    public Scene()
    {

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

        // Remove duplicate elements (not working)
        unDupeShapes = shapes.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        unDupeLights = lights.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Shape> getShapes()
    {
        return this.unDupeShapes;
    }

    public ArrayList<Light> getLights()
    {
        return this.unDupeLights;
    }
}