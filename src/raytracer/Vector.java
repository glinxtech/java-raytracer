package raytracer;

import raytracer.base.*;

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
}
