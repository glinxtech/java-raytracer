package raytracer;

public class Ray
{
    private Point origin;

    private Vector direction;

    public Ray(Point origin, Vector direction)
    {
        this.origin = origin;
        this.direction = direction;
    }

    public Point getOrigin()
    {
        return this.origin;
    }

    public Vector getDirection()
    {
        return this.direction;
    }
}
