package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelImageDto;
import ds25.hotel.reservation.management.system.screens.auth.LoginPage;
import ds25.hotel.reservation.management.system.screens.auth.MyPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import ds25.hotel.reservation.management.system.util.ImageLoader;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class HotelSelectionPage extends JFrame implements ActionListener, ListSelectionListener {
    private final HotelService hotelService;
    private JList<HotelDto> hotelJList;
    private JScrollPane hotelListScrollPane;
    private ArrayList<HotelDto> hotelDtoList;

    public HotelSelectionPage() {
        // 스프링 빈에서 HotelService 객체 주입
        hotelService = SpringBridge.getInstance().getBean(HotelService.class);

        // JList 에 표시할 호텔 목록 가져오기
        hotelDtoList = (ArrayList<HotelDto>) hotelService.findAllHotel();
        HotelDto[] hotelDtoArray =  hotelDtoList.toArray(new HotelDto[hotelDtoList.size()]);

        // JList 생성 및 설정
        hotelJList = new JList<>(hotelDtoArray);
        hotelJList.setCellRenderer(new HotelListRenderer());
        hotelJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelJList.addListSelectionListener(this);

        // JList 를 JScrollPane 에 넣어서 화면에 표시
        hotelListScrollPane = new JScrollPane(hotelJList);
        hotelListScrollPane.setPreferredSize(new Dimension(500, 600));

        setTitle("DS25 호텔 예약 관리 시스템");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pack();

        // 좌우 여백 설정
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(hotelListScrollPane, BorderLayout.CENTER);

        LoginPanel loginPanel = new LoginPanel();
        loginPanel.btn_myPage.addActionListener(this);
        loginPanel.btn_logout.addActionListener(this);
        add(loginPanel, BorderLayout.SOUTH);

        setSize(800, 1000);
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
        if (e.getSource() == hotelJList) {
            HotelDto selectedHotelDto = hotelDtoList.get(hotelJList.getSelectedIndex());
            log.info("선택한 호텔 정보: {}", selectedHotelDto);
            int selectedIndex = hotelJList.getSelectedIndex();
            if (selectedIndex != -1) {
                new HotelDetailPage(selectedHotelDto.getIdx());
                this.dispose();
            }
        }
    }
}

class HotelListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        HotelDto hotel = (HotelDto) value;
        HotelImageDto firstImage = hotel.getImages().get(0);
        ImageIcon icon = ImageLoader.getSizedImage(firstImage.getImage(), 130, 130);

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setText("<html><h2>" + hotel.getName() + "</h2><br>" + hotel.getDescription() + "</html>");
        label.setIcon(icon);

        return label;
    }
}

