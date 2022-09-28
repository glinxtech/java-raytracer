/*
Uses Wavefront OBJ loader library
https://github.com/javagl/Obj

Read code modified from
https://github.com/javagl/ObjSamples/blob/master/src/main/java/de/javagl/obj/samples/ObjSample_13_RenderByMaterial.java
 */

package raytracer.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import de.javagl.obj.*;
import org.apache.commons.io.IOUtils;

public class ObjectReader
{
    public static void main() {
        // Prepare file to be read
        InputStream objInputStream;
        {
            try {
                objInputStream = new FileInputStream("./data/OBJ/sphere-with-satellite.obj");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Read file
        Obj objFile;
        {
            try {
                objFile = ObjReader.read(objInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                // Close the file stream once the file is read
                IOUtils.closeQuietly(objInputStream);
            }
        }

        // Convert object into renderable object
        Obj obj = ObjUtils.convertToRenderable(objFile);

        // Collect all materials into a list
        List<Mtl> allMtls = new ArrayList<Mtl>();
        for (String mtlFileName : obj.getMtlFileNames())
        {
            InputStream mtlInputStream;
            try {
                mtlInputStream = new FileInputStream("./data/" + mtlFileName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            List<Mtl> mtls;
            try {
                mtls = MtlReader.read(mtlInputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                IOUtils.closeQuietly(mtlInputStream);
            }
            allMtls.addAll(mtls);
        }


        // Split the OBJ into parts per material
        Map<String, Obj> materialGroups =
                ObjSplitting.splitByMaterialGroups(obj);

        for (Map.Entry<String, Obj> entry : materialGroups.entrySet())
        {
            String materialName = entry.getKey();
            Obj materialGroup = entry.getValue();

            // Find the MTL that defines the material with the current name
            Mtl mtl = findMtlForName(allMtls, materialName);

            sendToRenderer(mtl, materialGroup);
        }
    }

    private static void sendToRenderer(Mtl mtl, Obj obj)
    {
        FloatTuple diffuseColour = mtl.getKd();
        FloatTuple specularColour = mtl.getKs();
        double power = 20;
    }

    private static Mtl findMtlForName(Iterable<? extends Mtl> mtls, String name)
    {
        for (Mtl mtl : mtls)
        {
            if (mtl.getName().equals(name))
            {
                return mtl;
            }
        }
        return null;
    }
}
