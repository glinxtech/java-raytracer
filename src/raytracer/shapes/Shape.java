package raytracer.shapes;

import raytracer.HitResult;
import raytracer.Material;
import raytracer.Ray;

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
