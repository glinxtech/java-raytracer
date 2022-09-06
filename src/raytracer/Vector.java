package raytracer;

import raytracer.base.*;

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

    public Vector(Point first, Point second)
    {
        this.x = second.getX() - first.getX();
        this.y = second.getY() - first.getY();
        this.z = second.getZ() - first.getZ();
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
