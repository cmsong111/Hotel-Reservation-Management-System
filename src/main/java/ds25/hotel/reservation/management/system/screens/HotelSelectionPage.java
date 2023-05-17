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
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HotelSelectionPage extends JFrame implements ActionListener, ListSelectionListener {
    // TODO: 호텔 상세 페이지로 이동하는 기능을 추가할 예정입니다.
    HotelService hotelService;

    JList<HotelDto> hotelList;
    private JScrollPane hotelListScrollPane;
    private JPanel hotelListPanel;
    private DefaultListModel<HotelDto> hotelListModel;
    private final Map<String, ImageIcon> imageMap;


    public HotelSelectionPage() {
        hotelService = SpringBridge.getInstance().getBean(HotelService.class);

        ArrayList<HotelDto> hotelDtoList = (ArrayList<HotelDto>) hotelService.findAllHotel();
        String[] nameList = {"시그니엘 호텔 서울점", "시그니엘 호텔 부산점", "롯데 호텔 제주점"};
        imageMap = createImageMap(nameList);

        hotelListModel = new DefaultListModel<>();
        hotelList = new JList(nameList);
        hotelList.setCellRenderer(new HotelListRenderer());
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelList.addListSelectionListener(this);

        hotelListScrollPane = new JScrollPane(hotelList);
        hotelListScrollPane.setPreferredSize(new Dimension(500, 600));

        hotelService.findAllHotel().forEach(hotelDto -> {
            hotelListModel.addElement(hotelDto);
        });

        setTitle("DS25 호텔 예약 관리 시스템");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pack();

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

    private Map<String, ImageIcon> createImageMap(String[] list) {
        Map<String, ImageIcon> map = new HashMap<>();
        try {
            map.put("시그니엘 호텔 서울점", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/main/230119-01-2000-mai-LTSG.jpg.thumb.1920.1920.jpg")));
            map.put("시그니엘 호텔 부산점", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/promotion/packages/4722-01-1440-pkg-LTSB.jpg.thumb.1920.1920.jpg")));
            map.put("롯데 호텔 제주점", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/main/4427-01-560-mai-LTJE.jpg")));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public class HotelListRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            ImageIcon icon = imageMap.get((String) value);

            if (icon != null) {
                // 이미지 크기 조정
                int maxWidth = 200; // 원하는 최대 가로 크기
                int maxHeight = 200; // 원하는 최대 세로 크기

                // 이미지 크기 조정
                ImageIcon scaledIcon = getScaledImageIcon(icon, maxWidth, maxHeight);

                label.setIcon(scaledIcon);
                label.setHorizontalTextPosition(JLabel.RIGHT);
            }

            return label;
        }

        private ImageIcon getScaledImageIcon(ImageIcon icon, int maxWidth, int maxHeight) {
            Image image = icon.getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            if (width > maxWidth || height > maxHeight) {
                // 이미지의 크기가 원하는 크기보다 큰 경우에만 조정
                double widthRatio = (double) maxWidth / width;
                double heightRatio = (double) maxHeight / height;
                double scale = Math.min(widthRatio, heightRatio);
                int scaledWidth = (int) (width * scale);
                int scaledHeight = (int) (height * scale);
                Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }

            return icon;
        }
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
            int selectedIndex = hotelList.getSelectedIndex();
            if (selectedIndex != -1) {
                new HotelDetailPage(selectedIndex + 1);
                this.dispose();
            }
        }
    }
}