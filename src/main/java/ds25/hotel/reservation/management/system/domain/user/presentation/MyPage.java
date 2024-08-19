package ds25.hotel.reservation.management.system.domain.user.presentation;

import ds25.hotel.reservation.management.system.global.config.ApplicationContextProvider;
import ds25.hotel.reservation.management.system.global.config.AuthContext;
import ds25.hotel.reservation.management.system.global.configuration.Singleton;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class MyPage extends JFrame implements ActionListener {

    JPanel centerPanel, SouthPanel;
    HotelReservationService hotelReservationService = ApplicationContextProvider.INSTANCE.getBean(HotelReservationService.class);
    JList hotelReservationList;
    JScrollPane hotelReservationListScroll;
    JButton btn_UserInfo, btn_logout;
    private DefaultListModel hotelReservationListModel;


    public MyPage() {

        setTitle("DS25 호텔 예약 관리 시스템");
        setLayout(new BorderLayout());
        hotelReservationListModel = new DefaultListModel();
        hotelReservationList = new JList(hotelReservationListModel);

        hotelReservationListScroll = new JScrollPane(hotelReservationList);
        hotelReservationListScroll.setPreferredSize(new Dimension(300, 300));


        hotelReservationService.findHotelReservationByUserId(AuthContext.INSTANCE.getUserEmail()).forEach(hotelReservation -> {
            hotelReservationListModel.addElement(hotelReservation);
            log.info("hotelReservation: {}", hotelReservation);
        });


        centerPanel = new JPanel(new GridLayout(-1, 1, 30, 30));

        centerPanel.add(new JLabel(AuthContext.INSTANCE.getUserEmail() + "님의 마이페이지 입니다."));

        centerPanel.add(new JLabel("예약내역"));

        hotelReservationList = new JList();


        btn_UserInfo = new JButton("회원정보 수정");
        btn_UserInfo.addActionListener(this);
        btn_UserInfo.setActionCommand("UserInfo");

        btn_logout = new JButton("로그아웃");
        btn_logout.addActionListener(this);
        btn_logout.setActionCommand("logout");


        SouthPanel = new JPanel(new BorderLayout());

        SouthPanel.add(btn_UserInfo, BorderLayout.EAST);
        SouthPanel.add(btn_logout, BorderLayout.WEST);


        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(SouthPanel, BorderLayout.SOUTH);
        add(hotelReservationListScroll, BorderLayout.CENTER);


        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("UserInfo")) {
            new UserInfo();
        }

        if (command.equals("logout")) {
            Singleton.getInstance().setUser(null);
            new LoginPage();
            dispose();
        }


    }
}
