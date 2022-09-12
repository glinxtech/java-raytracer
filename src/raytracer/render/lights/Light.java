package raytracer.render.lights;

import raytracer.render.Colour;
import raytracer.render.Point;

public abstract class Light
{
    protected Point position;
    protected Colour colour;
    protected double intensity;

    public Point getPosition()
    {
        return this.position;
    }

    public Colour getColour()
    {
        return this.colour;
    }

    public double getIntensity()
    {
        return this.intensity;
    }
}
