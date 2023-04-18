package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.screens.auth.LoginPage;
import ds25.hotel.reservation.management.system.screens.auth.MyPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class HotelSelectionPage extends JFrame implements ActionListener, ListSelectionListener {
    // TODO: 호텔 상세 페이지로 이동하는 기능을 추가할 예정입니다.
    HotelService hotelService;

    private JList hotelList;
    private JScrollPane hotelListScrollPane;
    private JPanel hotelListPanel;
    private DefaultListModel hotelListModel;




    public HotelSelectionPage() {
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

        setTitle("DS25 호텔 예약 관리 시스템");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(hotelListScrollPane, BorderLayout.CENTER);

        LoginPanel loginPanel = new LoginPanel();
        loginPanel.btn_myPage.addActionListener(this);
        loginPanel.btn_logout.addActionListener(this);
        add(loginPanel, BorderLayout.SOUTH);

        setSize(600, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("back")) {
            log.info("로그아웃 버튼 클릭");
            this.dispose();
            new LoginPage();
        } else if (command.equals("myPage")) {
            log.info("마이페이지 버튼 클릭");
            new MyPage();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (e.getSource() == hotelList) {
            log.info("호텔 리스트에서 호텔 선택");
            HotelDto hotelDto = (HotelDto) hotelList.getSelectedValue();
            new HotelDetailPage(hotelDto.getIdx());
            this.dispose();
        }
    }
}
