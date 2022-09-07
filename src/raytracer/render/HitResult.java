package raytracer.render;

import raytracer.render.shapes.*;

/**
 * Data transfer object for ray-object intersections
 */
public class HitResult
{
    private Ray ray;
    private Shape shape;
    private double distance;
    private Point hitPoint;
    private Vector normal;

    public HitResult(Ray ray, Shape shape, double distance, Point hitPoint, Vector normal)
    {
        this.ray = ray;
        this.shape = shape;
        this.distance = distance;
        this.hitPoint = hitPoint;
        this.normal = normal;
    }

    public Ray getRay()
    {
        return this.ray;
    }

    public Shape getShape()
    {
        return this.shape;
    }

    public double getDistance()
    {
        return this.distance;
    }

    public Point getHitPoint()
    {
        return this.hitPoint;
    }

    public Vector getNormal()
    {
        return this.normal;
    }
}
