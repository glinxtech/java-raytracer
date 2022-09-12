package raytracer.lights;

import raytracer.Colour;
import raytracer.Point;

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
