package ds25.hotel.reservation.management.system.pattern.proxy;

import ds25.hotel.reservation.management.system.util.ImageLoader;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class ProxyImage implements Icon {
    private final String imageUrl;
    private ImageIcon realImage;
    int width;
    int height;

    public ProxyImage(String imageUrl, int width, int height) {
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        getImageIcon().paintIcon(c, g, x, y);
    }

    @Override
    public int getIconWidth() {
        return getImageIcon().getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return getImageIcon().getIconHeight();
    }

    private ImageIcon getImageIcon() {
        if (realImage == null) {
            realImage = new ImageIcon(ImageLoader.getImage(imageUrl).getImage());
            realImage = new ImageIcon(realImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
        return realImage;
    }
}
