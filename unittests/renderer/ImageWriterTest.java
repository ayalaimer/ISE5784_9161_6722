package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    /**
     * Test method for writing an image with a pattern of black and pink pixels.
     * Creates an ImageWriter object, sets the colors of the pixels, and writes the image to a file.
     */
    @Test
    void testWriteToImage() {

        // Create an ImageWriter object with the specified name, width, and height
        ImageWriter imageWriter = new ImageWriter("Pink and black", 800, 500);

        // Loop through each pixel in the image
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                // If the pixel is on a multiple of 50, set it to black, otherwise set it to pink
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(j, i, new Color(0, 0, 0)); // set pixel to black
                else
                    imageWriter.writePixel(j, i, new Color(255, 0, 255)); // set pixel to pink
            }
        }

        // Write the image to a file
        imageWriter.writeToImage();
    }
}
