package raytracer.render;

import java.awt.image.BufferedImage;

import raytracer.render.scene.*;
import raytracer.render.shapes.*;
import raytracer.render.lights.*;

public class Render
{
    public BufferedImage draw(Scene scene, Camera camera)
    {
        // Image we're going to display in our Image Window
        BufferedImage img = new BufferedImage(camera.getWidth(), camera.getHeight(), BufferedImage.TYPE_INT_RGB);

        // Loop through all the pixels we're going to colour
        for (int y = 0; y < camera.getHeight(); y++)
        {
            for (int x = 0; x < camera.getWidth(); x++)
            {
                // Find the ray that goes through our image plane at this pixel
                Ray viewRay = camera.getRay(x, y);

                // Colour of our pixel
                Colour colour = trace(viewRay, scene);

                // Fill in the pixel with the given colour
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

            // Length of light ray projected onto the normal
            double lightProjection = Vector.dotProduct(lightRay.getDirection(), hit.getNormal());

            // If true, this ray goes through the shape
            if (lightProjection <= 0.0)
                continue;

            // Distance light traveled
            double d2 = lightRay.getDirection().dotProduct();

            // Normalize
            lightRay.getDirection().unitVector();

            boolean shadowRay = false;

            /**
             * If a shape is between this light and
             * our intersection point, this is a shadowRay.
             * Move onto the next light.
             */
            for (Shape s : scene.getShapes())
            {
                HitResult h = s.hitShape(lightRay, lightRay.getDirection().norm());

                if (h != null)
                {
                    shadowRay = true;
                    break;
                }
            }

            // This light ray reaches our shape
            if (!shadowRay)
            {
                // Angle of light ray to determine the intensity
                double lambert = Vector.dotProduct(lightRay.getDirection(), hit.getNormal());
                double lightPower = lambert * l.getIntensity() / d2;
                System.out.println(lightPower);
                Colour adjusted = Colour.multiply(l.getColour(), lightPower);

                outputColour.add(adjusted.multiply(currentMaterial.getDiffuse()));
            }
        }
        return outputColour;
    }
}
