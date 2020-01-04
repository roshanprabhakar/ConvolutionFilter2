package Home;

import java.awt.image.BufferedImage;

import static Home.ImageUtils.*;

public class Main {

    static ImageUtils ui = new ImageUtils();

    //NEGATIVE
    static double[][] filter = new double[][]{
//            {-2, -1, -2},
//            {-1, 10, -1},
//            {-2, -1, -2}


            {((double) 1) / 1, ((double) 1) / 1, ((double) 1) / 1},
            {((double) 1) / 1, ((double) -1000) / 1, ((double) 1) / 1},
            {((double) 1) / 1, ((double) 1) / 1, ((double) 1) / 1}
    };

    public static void main(String... args) throws InterruptedException {

        BufferedImage image = convertToGrayscale(read("temp.jpg"));

        display("original", image);

        ConvolutionFilter convolutionFilter = new ConvolutionFilter(filter);
        int[][] convolved = convolutionFilter.convolveImage(getRaw(image));

        display("convolved", getConstructed(convolved));

        Thread.sleep(100000);

    }
}
