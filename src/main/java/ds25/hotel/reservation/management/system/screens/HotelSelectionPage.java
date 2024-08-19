package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.global.config.ApplicationContextProvider;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelImageDto;
import ds25.hotel.reservation.management.system.global.common.fragment.MyJMenuBar;
import ds25.hotel.reservation.management.system.pattern.proxy.ProxyImage;
import ds25.hotel.reservation.management.system.pattern.strategy.*;
import ds25.hotel.reservation.management.system.domain.user.presentation.LoginPage;
import ds25.hotel.reservation.management.system.domain.user.presentation.MyPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Slf4j
public class HotelSelectionPage extends JFrame implements ActionListener, ListSelectionListener {
    private final HotelService hotelService;
    private JList<HotelDto> hotelJList;
    private JScrollPane hotelListScrollPane;
    private ArrayList<HotelDto> hotelDtoList;
    private SortStrategy sortStrategy;
    private JComboBox<String> sortingComboBox;


    // Sorting method
    private void sortHotelList() {
        sortStrategy.sorting(hotelDtoList);
        hotelJList.setListData(hotelDtoList.toArray(new HotelDto[0]));
        hotelJList.updateUI();
    }

    public HotelSelectionPage() {
        // 스프링 빈에서 HotelService 객체 주입
        hotelService = ApplicationContextProvider.INSTANCE.getBean(HotelService.class);

        // 전략패턴 생성
        sortStrategy = new BuildDescSort();

        sortingComboBox = new JComboBox<>();
        sortingComboBox.addItem("Sort by Name Asc");
        sortingComboBox.addItem("Sort by Name Desc");
        sortingComboBox.addItem("Sort by Build Asc");
        sortingComboBox.addItem("Sort by Build Desc");

        sortingComboBox.addActionListener(this);

        // Add the combo box to the UI


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
        add(sortingComboBox, BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(hotelListScrollPane, BorderLayout.CENTER);

        this.setJMenuBar(new MyJMenuBar());
        this.setIconImage(new ImageIcon("src/main/resources/image/favicon-32x32.png").getImage());

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
        } else if (e.getSource() == sortingComboBox) {
            log.info("정렬 버튼 클릭");
            int selectedIndex = sortingComboBox.getSelectedIndex();
            if (selectedIndex == 0) {
                sortStrategy = new NameAscSort();
            } else if (selectedIndex == 1) {
                sortStrategy = new NameDescSort();
            } else if (selectedIndex == 2) {
                sortStrategy = new BuildAscSort();
            } else if (selectedIndex == 3) {
                sortStrategy = new BuildDescSort();
            }
            sortHotelList();
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
        ProxyImage icon = new ProxyImage(firstImage.getImage(), 130, 130);

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setText("<html><h2>" + hotel.getName() + "</h2><br>" + hotel.getDescription() + "</html>");
        label.setIcon(icon);

        return label;
    }
}

