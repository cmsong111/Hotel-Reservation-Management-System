package ds25.hotel.reservation.management.system.screens.admin;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.screens.auth.LoginPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHotelSelectPage extends JFrame implements ActionListener, ListSelectionListener {

    private JLabel label;
    private Panel panel;
    private JButton btn_logout;
    private final JScrollPane hotelListScrollPane;
    private JPanel hotelListPanel;
    private DefaultListModel hotelListModel;
    private HotelService hotelService;
    private JList hotelList;

    public AdminHotelSelectPage() {
        hotelService = SpringBridge.getInstance().getBean(HotelService.class);

        hotelListModel = new DefaultListModel();
        hotelList = new JList(hotelListModel);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelList.addListSelectionListener(this);

        hotelListScrollPane = new JScrollPane(hotelList);
        hotelListScrollPane.setPreferredSize(new Dimension(250, 80));

        hotelService.findAllHotel().forEach(hotelDto -> {
            hotelListModel.addElement(hotelDto);
        });

        label = new JLabel("관리자 페이지");
        panel = new Panel();
        btn_logout = new JButton("로그아웃");

        btn_logout.setBounds(100, 100, 100, 100);
        btn_logout.addActionListener(this);
        btn_logout.setActionCommand("logout");


        setTitle("관리자 페이지");
        setLocationRelativeTo(null);
        setSize(500, 500);
        setVisible(true);
        setLayout(new BorderLayout());

        add(hotelListScrollPane, BorderLayout.CENTER);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(new NorthPanel("관리자 페이지 - 내 호텔 선택"), BorderLayout.NORTH);
        add(btn_logout, BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("logout")) {
            this.dispose();
            new LoginPage();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (e.getSource() == hotelList && hotelList.getSelectedIndex() != -1) {
            this.dispose();
            HotelDto hotelDto = (HotelDto) hotelList.getSelectedValue();
            new AdminMainPage(hotelDto.getIdx());
        }
    }
}

