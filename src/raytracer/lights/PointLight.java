package raytracer.lights;

import raytracer.*;

public class PointLight extends Light
{
    /**
     * White light
     * @param point position of light
     * @param intensity power of the light 0-1
     */
    public PointLight(Point point, double intensity)
    {
        this.position = point;
        this.intensity = new Colour(intensity, intensity, intensity);
    }

    /**
     * Coloured light
     * @param point position of light
     * @param colour colour of the light
     * @param intensity power of the light 0-1
     */
    public PointLight(Point point, Colour colour, double intensity)
    {
        this.position = point;
        this.intensity = colour.multiply(intensity);
    }
}
