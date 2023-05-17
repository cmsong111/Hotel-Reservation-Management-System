package ds25.hotel.reservation.management.system.pattern.proxy;

import ds25.hotel.reservation.management.system.util.ImageLoader;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
@Slf4j
public class ProxyImage implements Icon {
    private final String imageUrl;
    private final int width;
    private final int height;
    private ImageIcon realImage;
    private ImageIcon loadingImage;

    public ProxyImage(String imageUrl, int width, int height) {
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
        this.loadingImage = new ImageIcon("loading.gif");
        loadImageIcon();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (realImage != null) {
            realImage.paintIcon(c, g, x, y);
        } else {
            loadingImage.paintIcon(c, g, x, y);
        }
    }

    @Override
    public int getIconWidth() {
        return realImage != null ? realImage.getIconWidth() : loadingImage.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return realImage != null ? realImage.getIconHeight() : loadingImage.getIconHeight();
    }

    private void loadImageIcon() {
        if (realImage == null) {
            realImage = ImageLoader.getImage(imageUrl);
            if (realImage != null) {
                Image originalImage = realImage.getImage();
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                realImage = new ImageIcon(scaledImage);
            }
        }
    }
}

