package com.elon.util;

import com.elon.entity.enm.ImageType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 2017/11/23 9:33.
 * <p>
 * Email: cheerUpPing@163.com
 */
public class ImageUtil {

    /**
     * 保存图片
     *
     * @param srcImage
     * @param format
     * @param rootPath
     * @param fileName 不包含文件后缀
     * @throws IOException
     */
    public static void saveImage(BufferedImage srcImage, ImageType format, String rootPath, String fileName) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(srcImage, format.getType(), byteArrayOutputStream);
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File file = new File(rootFile, fileName + "." + format.getType());
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedOutputStream b0f = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bytes = byteArrayOutputStream.toByteArray();
        b0f.write(bytes);
        b0f.close();
    }

    /**
     * 缩放图片,图片归一化
     *
     * @param image
     * @param toWidth  归一化到宽尺寸
     * @param toHeight 归一化到高尺寸
     * @return
     */
    public static BufferedImage reSquare(BufferedImage image, int toWidth, int toHeight) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dsc = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) dsc.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, toWidth, toHeight);// 填充整个屏幕
        g.dispose();
        int w1 = (toWidth - w) / 2;
        int h1 = (toHeight - h) / 2;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                dsc.setRGB(x + w1, y + h1, image.getRGB(x, y));
            }
        }
        return dsc;
    }


    /**
     * 按照固定宽高原图压缩
     *
     * @param srcImage
     * @param width
     * @param height
     * @throws IOException
     */
    public static BufferedImage resize(BufferedImage srcImage, int width, int height) throws IOException {
        Image image = srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();
        return tag;
    }

    public static void main(String[] args) throws IOException {
        /*BufferedImage image = ImageIO.read(new File("d:/opencv_image/delete_blue.png"));
        System.out.println(image.getWidth() + " " + image.getHeight());
        BufferedImage result = resize(image, 75, 100);
        saveImage(result, ImageType.JPG, "d:/opencv_image", "d.jpg");*/
        for (int i=0;i<10;i++){
            System.out.println((int) (Math.random() * 10 + 20));
        }
    }
}
