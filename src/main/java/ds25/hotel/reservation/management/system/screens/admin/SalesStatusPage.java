package ds25.hotel.reservation.management.system.screens.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesStatusPage extends JFrame {
    private JPanel mainPanel;
    private JTable table;

    public SalesStatusPage() {
        super("매출 현황 페이지");

        String[] columnNames = {"Room Name", "Total Sales"};
        Object[][] data = {{"Room A", 1}, {"Room B", 2}, {"Room C", 3}};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setSize(600, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SalesStatusPage();
    }
}