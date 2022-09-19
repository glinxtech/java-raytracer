package raytracer.shapes;

import raytracer.colour.Material;
import raytracer.math.Point;
import raytracer.math.Ray;
import raytracer.math.Vector;

public class Sphere extends Shape
{
    private Point center;
    private double radius;
    private double r2;

    public Sphere(Point center, double radius, Material material)
    {
        this.center = center;
        this.radius = radius;
        this.material = material;
        this.r2 = radius * radius; // Saves time later
    }

    public Point getCenter()
    {
        return this.center;
    }

    public double getRadius()
    {
        return this.radius;
    }

    public HitResult hitShape(Ray ray, double distance)
    {
        // Vector between ray origin and sphere center
        Vector a = new Vector(ray.getOrigin(), this.center);

        /**
         * Length of previous vector projected onto the view ray.
         * Line that runs along the view ray until it reaches the line
         * that is perpendicular to the view ray and runs between
         * the sphere center and view ray. Let's call the end of this
         * line point S.
         *
         * This forms a triangle between the view origin,
         * sphere center and the end of our new
         * line with a 90-degree angle, S.
         */
        double b = a.dotProduct(ray.getDirection());

        /**
         * Using pythagoras to find the square of that
         * line between the sphere center and S.
         */
        double delta2 = a.dotProduct() - b * b;

        // Quick way of checking if line b is even within sphere radius.
        if (delta2 > this.r2)
            return null;

        /**
         * We can form a new triangle between the sphere center,
         * our intersection point (radius of the circle) and S.
         * Which we can use pythagoras on again to find the
         * line between S and the intersection.
         */
        double c = Math.sqrt(this.r2 - delta2);

        /**
         * Line b minus c is the distance to
         * the intersection from the view origin
         */
        double c0 = b - c;
        double c1 = b + c;

        if (c0 > 0.1 && c0 < distance)
            distance = c0;
        else if (c1 > 0.1 && c1 < distance)
            distance = c1;
        else
            return null;

        // Intersection point
        Point hitPoint = Vector.add(ray.getOrigin(), Vector.multiply(ray.getDirection(), distance));

        // Normal to the surface of the object at point of intersection
        Vector normal = new Vector(this.center, hitPoint);

        try
        {
            normal = normal.unitVector();
        } catch (ArithmeticException e)
        {
            // Catch happens if the value is 0
        }

        return new HitResult(ray, this, distance, hitPoint, normal);
    }
}