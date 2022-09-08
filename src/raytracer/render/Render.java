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
       return this.draw(new Scene(), new Camera(1280, 720));
    }

    private Colour trace(Ray ray, Scene scene)
    {
        Colour outputColour = new Colour(0, 0, 0);

        double distance = 5000.0;
        HitResult hit = null;

        for (Shape s : scene.getShapes())
        {
            HitResult h = s.hitShape(ray, distance);

            if (h != null)
            {
                hit = h;
                distance = hit.getDistance();
            }
        }

        if (hit == null)
            return outputColour;

        Material currentMaterial = hit.getShape().getMaterial();

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

            boolean inLight = true;

            for (Shape s : scene.getShapes())
            {
                HitResult h = s.hitShape(lightRay, lightRay.getDirection().norm());

                if (h != null)
                {
                    inLight = false;
                    break;
                }
            }

            if (inLight)
            {
                double diffuse = Vector.dotProduct(lightRay.getDirection(), hit.getNormal());
                Colour adjusted = Colour.multiply(l.getIntensity(), diffuse);

                outputColour.add(adjusted.multiply(currentMaterial.getDiffuse()));
            }
        }
        return outputColour;
    }
}
