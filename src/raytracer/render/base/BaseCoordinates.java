package raytracer.render.base;

/**
 * Base class for vectors and points in space.
 */
public abstract class BaseCoordinates
{
    protected double x; // X component of vector or co-ordinate of point.
    protected double y; // Y component of vector or co-ordinate of point.
    protected double z; // Z component of vector or co-ordinate of point.

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
