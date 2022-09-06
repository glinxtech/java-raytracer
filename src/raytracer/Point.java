package raytracer;

import raytracer.base.BaseCoordinates;

/**
 * Represents a point in 3d space with 3 co-ordinates.
 */
public class Point extends BaseCoordinates
{
    public Point(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
