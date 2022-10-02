package raytracer.math;

/**
 * Represents a point in 3d space with 3 co-ordinates.
 */
public class Point
{
    protected double x; // X component of vector or co-ordinate of point.
    protected double y; // Y component of vector or co-ordinate of point.
    protected double z; // Z component of vector or co-ordinate of point.

    public Point(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @return x axis
     */
    public double getX()
    {
        return this.x;
    }

    /**
     *
     * @return y axis
     */
    public double getY()
    {
        return this.y;
    }

    /**
     *
     * @return z axis
     */
    public double getZ()
    {
        return this.z;
    }

}
