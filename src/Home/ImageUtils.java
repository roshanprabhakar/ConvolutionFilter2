package Home;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {

    private static Map<String, Integer> indexMap = Map.ofEntries(
            Map.entry("red", 0),
            Map.entry("green", 1),
            Map.entry("blue", 2)
    );

    public static int[][] getRaw(BufferedImage image) {
        int[][] out = new int[image.getHeight()][image.getWidth()];
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                out[r][c] = image.getRGB(c, r);
            }
        }
        return out;
    }

    public static BufferedImage getConstructed(int[][] image) {
        BufferedImage out = new BufferedImage(image[0].length, image.length, BufferedImage.TYPE_3BYTE_BGR);
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                out.setRGB(c, r, image[r][c]);
            }
        }
        return out;
    }

    public static BufferedImage convertToGrayscale(BufferedImage image) {
        BufferedImage greyed = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                Color pixel = new Color(image.getRGB(c, r));
                int average = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                Color filler = new Color(average, average, average);
                greyed.setRGB(c, r, filler.getRGB());
            }
        }
        return greyed;
    }

    public static BufferedImage getChannel(String channel, BufferedImage image) {
        BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        int[] parsed;
        int[] pixelChannel;
        Color pixel;
        for (int r = 0; r < image.getHeight(); r++) {
            for (int c = 0; c < image.getWidth(); c++) {
                pixel = new Color(image.getRGB(c, r));
                parsed = new int[] {pixel.getRed(), pixel.getGreen(), pixel.getBlue()};
                pixelChannel = new int[3];
                pixelChannel[indexMap.get(channel)] = parsed[indexMap.get(channel)];
                out.setRGB(c, r, getColor(pixelChannel).getRGB());
            }
        }
        return out;
    }

    public static BufferedImage read(String filepath) {
        try {
            return ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void display(String title, BufferedImage image) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        frame.setVisible(true);
    }

    private static Color getColor(int[] channels) {
        return new Color(channels[0], channels[1], channels[2]);
    }
}
