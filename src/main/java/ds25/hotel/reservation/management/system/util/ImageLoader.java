package ds25.hotel.reservation.management.system.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {
    /**
     * 인터넷으로 부터 이미지를 가져와서 ImageIcon으로 반환
     * 500 * 281 사이즈로 리사이징
     *
     * @param internetPath 이미지의 인터넷 경로
     * @return ImageIcon
     * @author 김남주
     */
    public static ImageIcon getImage(String internetPath) {
        try {
            URL url = new URL(internetPath);
            Image image = ImageIO.read(url).getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            int newWidth = 500;
            int newHeight = (int) (((double) newWidth / (double) width) * height);
            Image newImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            return new ImageIcon(newImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
