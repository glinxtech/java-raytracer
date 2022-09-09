package raytracer.render;

import java.awt.image.BufferedImage;

import raytracer.render.scene.*;
import raytracer.render.shapes.*;
import raytracer.render.lights.*;

public class Render
{
    public BufferedImage draw(Scene scene, Camera camera)
    {
        BufferedImage img = new BufferedImage(camera.getWidth(), camera.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < camera.getHeight(); y++)
        {
            for (int x = 0; x < camera.getWidth(); x++)
            {
                Ray viewRay = camera.getRay(x, y);

                Colour colour = trace(viewRay, scene);

                img.setRGB(x, y, colour.toPixel());
            }
        }
        return img;
    }

    public BufferedImage draw()
    {
       return this.draw(new Scene(), new Camera(new Point(50, 0, 0), new Vector(-1, 0, 0),90, 1280, 800));
    }

    private Colour trace(Ray ray, Scene scene)
    {
        // Background colour
        Colour outputColour = new Colour(0, 0, 0);

        // Render distance
        double distance = 5000.0;
        HitResult hit = null;

        // Check for intersection with every shape,
        // except if the distance is shorter than
        // the previous hit result
        for (Shape s : scene.getShapes())
        {
            HitResult h = s.hitShape(ray, distance);

            if (h != null)
            {
                hit = h;
                distance = hit.getDistance();
            }
        }

        // No shape to draw, next pixel
        if (hit == null)
            return outputColour;

        Material currentMaterial = hit.getShape().getMaterial();

        // Primitive ambient lighting
        outputColour.add(Colour.multiply(currentMaterial.getDiffuse(), new Colour(0.1, 0.1, 0.1)));

        for (Light l : scene.getLights())
        {
            Ray lightRay = new Ray(
                    hit.getHitPoint(),
                    new Vector(hit.getHitPoint(), l.getPosition())
                    );

            double lightProjection = Vector.dotProduct(lightRay.getDirection(), hit.getNormal());

            if (lightProjection <= 0.0)
                continue;

            lightRay.getDirection().unitVector();

            boolean shadowRay = false;

            for (Shape s : scene.getShapes())
            {
                HitResult h = s.hitShape(lightRay, lightRay.getDirection().norm());

                if (h != null)
                {
                    shadowRay = true;
                    break;
                }
            }

            if (!shadowRay)
            {
                double lambert = Vector.dotProduct(lightRay.getDirection(), hit.getNormal());
                Colour adjusted = Colour.multiply(l.getIntensity(), lambert);

                outputColour.add(adjusted.multiply(currentMaterial.getDiffuse()));
            }
        }
        return outputColour;
    }
}
