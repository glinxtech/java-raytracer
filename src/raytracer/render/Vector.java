package raytracer.render;

import raytracer.render.base.*;

/**
 * Represents a 3 dimensional vector.
 */
public class Vector extends BaseCoordinates
{
    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Point endPoint)
    {
        this.x = endPoint.getX();
        this.y = endPoint.getY();
        this.z = endPoint.getZ();
    }

    public Vector(Point p1, Point p2)
    {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.z = p2.getZ() - p1.getZ();
    }

    public static Point add(Point point, Vector vector)
    {
        double newX = point.getX() + vector.getX();
        double newY = point.getY() + vector.getY();
        double newZ = point.getZ() + vector.getZ();
        return new Point(newX, newY, newZ);
    }

    public static Vector multiply(Vector v, double scalar)
    {
        return new Vector(scalar * v.getX(), scalar * v.getY(),scalar * v.getZ());
    }

    public Vector multiply(double scalar)
    {
        return multiply(this, scalar);
    }

    public static double dotProduct(Vector v1, Vector v2)
    {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }

    public double dotProduct(Vector v2)
    {
        return dotProduct(this, v2);
    }

    public double dotProduct()
    {
        return dotProduct(this, this);
    }

    public double norm()
    {
        return Math.sqrt(this.dotProduct());
    }

    public void unitVector()
    {
        double norm = norm();
        if (norm == 0)
        {
            throw new ArithmeticException("Norm equals 0. Unable to normalize vector.");
        }
        this.x = this.x / norm;
        this.y = this.y / norm;
        this.z = this.z / norm;
    }

    public Vector crossProduct(Vector v2)
    {
        double newX = this.getY() * v2.getZ() - this.getZ() * v2.getY();
        double newY = this.getZ() * v2.getX() - this.getX() * v2.getZ();
        double newZ = this.getX() * v2.getY() - this.getY() * v2.getX();

        return new Vector(newX, newY, newZ);
    }

}
