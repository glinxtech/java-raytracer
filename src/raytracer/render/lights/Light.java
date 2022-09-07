package raytracer.render.lights;

import raytracer.render.Colour;
import raytracer.render.Point;

public abstract class Light
{
    protected Point position;
    protected Colour intensity;

    public Point getPosition()
    {
        return this.position;
    }

    public Colour getIntensity()
    {
        return this.intensity;
    }
}
