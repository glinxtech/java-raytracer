package raytracer;

/**
 * Represents a 3 dimensional vector.
 */
public class Vector extends Point
{
    public Vector(double x, double y, double z)
    {
        super(x, y, z);
    }

    public Vector(Point endPoint)
    {
        this(endPoint.getX(), endPoint.getY(), endPoint.getZ());
    }

    public Vector(Point p1, Point p2)
    {
        this(Vector.subtract(p2, p1));
    }

    public static Vector add(Point point, Vector vector)
    {
        double newX = point.getX() + vector.getX();
        double newY = point.getY() + vector.getY();
        double newZ = point.getZ() + vector.getZ();
        return new Vector(newX, newY, newZ);
    }

    public Vector add(Vector vector)
    {
        return add(this, vector);
    }

    public static Vector subtract(Point v1, Point v2)
    {
        double newX = v1.getX() - v2.getX();
        double newY = v1.getY() - v2.getY();
        double newZ = v1.getZ() - v2.getZ();
        return new Vector(newX, newY, newZ);
    }

    public Vector subtract(Point point)
    {
        return subtract(this, point);
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

    public Vector unitVector()
    {
        double norm = norm();
        if (norm == 0)
        {
            throw new ArithmeticException("Norm equals 0. Unable to normalize vector.");
        }

        return new Vector(this.x / norm, this.y / norm, this.z / norm);
    }

    public Vector crossProduct(Vector v2)
    {
        double newX = this.getY() * v2.getZ() - this.getZ() * v2.getY();
        double newY = this.getZ() * v2.getX() - this.getX() * v2.getZ();
        double newZ = this.getX() * v2.getY() - this.getY() * v2.getX();

        return new Vector(newX, newY, newZ);
    }

}
