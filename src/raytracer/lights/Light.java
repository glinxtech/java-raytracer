package raytracer.lights;

import raytracer.*;

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
