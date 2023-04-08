package ds25.hotel.reservation.management.system.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageLoader {
    public static ImageIcon getImage(String internetPath) {
        Image image = null;
        try {
            URL url = new URL(internetPath);
            return new ImageIcon(ImageIO.read(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
