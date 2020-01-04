package Home;

import java.awt.image.BufferedImage;

public class ConvolutionFilter {

    private double[][] filter;

    public ConvolutionFilter(double[][] filter) {
        this.filter = filter;
    }

    public int[][] convolveImage(int[][] image) {
        int[][] out = new int[image.length][image[0].length];

        int sum;
        for (int r = 0; r < image.length - filter.length; r++) {
            for (int c = 0; c < image[r].length - filter[0].length; c++) {

                sum = 0;
                for (int fr = 0; fr < filter.length; fr++) {
                    for (int fc = 0; fc < filter[fr].length; fc++) {
                        sum += image[r + fr][c + fc] * filter[fr][fc];
                    }
                }
                out[r][c] = sum;
            }
        }
        return out;
    }
}
