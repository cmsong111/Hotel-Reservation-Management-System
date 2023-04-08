package ds25.hotel.reservation.management.system.screens.widget;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {
    public NorthPanel() {
        JLabel label = new JLabel("Hotel Reservation Management System");
        Font font = label.getFont();
        float fontSize = 24.0f;
        label.setFont(font.deriveFont(fontSize));
        add(label);
    }

}
