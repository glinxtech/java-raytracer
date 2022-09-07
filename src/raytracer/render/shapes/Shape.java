package raytracer.render.shapes;

import raytracer.render.HitResult;
import raytracer.render.Material;
import raytracer.render.Ray;

/**
 * Base class for the shapes in the scene
 */
public abstract class Shape
{
    protected Material material;

    public Material getMaterial()
    {
        return material;
    }

    // Math for intersection of a ray with the shape
    public abstract HitResult hitShape(Ray ray, double distance);
}
