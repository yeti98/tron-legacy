package com.linhnt.speedshoot.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LT on 14/07/2017.
 */
public class ImageUtils {
    public static BufferedImage read(String path) throws IOException {
        return ImageIO.read(new File(path));
    };

    public static BufferedImage readFromAsset(String path) throws IOException {
        return read(String.format("assets/images/%s", path));
    }

    public static List<BufferedImage> readMany(String... paths) throws IOException {
        List<BufferedImage> results = paths.length > 0 ? new ArrayList<>() : null;
        for(String path : paths) {
            results.add(read(path));
        }
        return results;
    }

    public static List<BufferedImage> readManyFromAsset(String... paths) throws IOException {
        List<BufferedImage> results = paths.length > 0 ? new ArrayList<>() : null;
        for(String path : paths) {
            results.add(readFromAsset(path));
        }
        return results;
    }

    public static List<BufferedImage> readAllInForder(String path) throws IOException {
        File folder = new File(path);
        List<String> listImageFileName = new ArrayList<>();
        for(File file : folder.listFiles()) {
            if (file.getName().matches(".*[png|PNG|jpg|JPG]")) {
                listImageFileName.add(file.getAbsolutePath());
            }
        }
        return readMany(listImageFileName.toArray(new String[listImageFileName.size()]));
    }

    public static List<BufferedImage> readAllInForderInAsset(String path) throws IOException {
        return readAllInForder(String.format("assets/images/%s", path));
    }

    public static BufferedImage flipHorizontal(BufferedImage source) {
        BufferedImage image = null;
        if(source != null) {
            image = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
            int width = source.getWidth();
            int height = source.getHeight();
            for(int i = 0; i < width; i++) {
                for(int j = 0; j < height; j++) {
                    image.setRGB(width - 1 - i, j, source.getRGB(i, j));
                }
            }
        }
        return image;
    }

    public static List<BufferedImage> flipHorizontal(List<BufferedImage> sources) {
        List<BufferedImage> images = null;
        if(sources != null) {
            images = new ArrayList<>();
            for(BufferedImage source : sources) {
                images.add(flipHorizontal(source));
            }
        }
        return images;
    }

    public static BufferedImage cloneImage(BufferedImage srcImage) {
        if(srcImage != null) {
            BufferedImage copyImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getType());
            Graphics2D graphics2D = copyImage.createGraphics();
            graphics2D.drawImage(srcImage, 0, 0, null);
            graphics2D.dispose();
            return copyImage;
        }
        return null;
    }
}
