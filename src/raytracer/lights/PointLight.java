package raytracer.lights;

import raytracer.Colour;
import raytracer.Point;

public class PointLight extends Light
{
    /**
     * Coloured light
     * @param point position of light
     * @param colour colour of the light
     * @param intensity power of the light
     */
    public PointLight(Point point, Colour colour, double intensity)
    {
        this.position = point;
        this.colour = colour;
        this.intensity = intensity;
    }

    /**
     * White light
     * @param point position of light
     * @param intensity power of the light
     */
    public PointLight(Point point, double intensity)
    {
        this(point, new Colour(1, 1, 1), intensity);
    }
}
