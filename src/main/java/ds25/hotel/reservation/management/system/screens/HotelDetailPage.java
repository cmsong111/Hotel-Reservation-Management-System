package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.pattern.proxy.ProxyImage;
import ds25.hotel.reservation.management.system.screens.auth.MyPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class HotelDetailPage extends JFrame implements ActionListener, ListSelectionListener {
    private final HotelService hotelService = SpringBridge.getInstance().getBean(HotelService.class);
    private final HotelRoomTypeService hotelRoomTypeService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);

    Optional<HotelDto> hotelDto;

    private JList hotelRoomList;
    private DefaultListModel hotelRoomListModel;
    private JScrollPane hotelRoomListScrollPane;

    private Panel centerPanel;
    LoginPanel loginPanel;
    JLabel userLabel, hotelImageLabel;
    JTextArea hotelDetailTextArea, hotelRoomTextArea;
    JButton btn_RoomDetail;

    private final Map<String, ImageIcon> SeoulimageMap;
    private final Map<String, ImageIcon> BusanimageMap;
    private final Map<String, ImageIcon> JejuimageMap;

    public HotelDetailPage(long hotelIdx) {

        String[] SeoulList = {"그랜드 디럭스 룸 가격:600,000", "프리미어 룸 가격:800,000", "프리미어 스위트 룸 가격:960,000", "디럭스 스위트 룸 가격:520,000",
                 "코리안 프리미어 룸 가격:1,000,000", "코리안 시그니엘 프리미어 룸 가격:1,000,000", "코리안 스위트 룸 가격:600,000", "프레지덴셜 스위트 룸 가격:1,000,000", "로얄 스위트 룸 가격:2,500,000"};

        String[] BusanList = {"그랜드 디럭스 미포 하버뷰 룸 가격:300,000", "프리미어 더블 룸 가격:300,000", "프리미어 더블 오션뷰 룸 가격:370,000", "프리미어 트윈 미포 하버뷰 룸 가격:340,000",
                             "프리미어 패밀리 트윈 룸 가격:370,000", "시그니엘 프리미어 더블 오션뷰 룸 가격:440,000", "시그니엘 프리미어 더블 미포 하버뷰 룸 가격:390,000", "프레지덴셜 스위트 오션뷰 룸 가격:480,000", "로얄 스위트 오션뷰 룸 가격:720,000"};

        String[] JejuList = {"디럭스 룸 가격:293,700", "샤롯데 룸 가격:378,000", "코너 스위트 룸 가격:441,000", "프리미어 온돌 룸 가격:372,000", "프리미어 룸 가격:399,900",
                             "헬로키티 키즈 룸 가격:372,000", "풀빌라 스위트 룸 가격:1,281,000", "프레지덴셜 스위트 룸 가격:1,981,000", "로얄 스위트 룸 가격:11,000,000"};


        SeoulimageMap = SeoulcreateImageMap(SeoulList);
        BusanimageMap = BusancreateImageMap(BusanList);
        JejuimageMap = JejucreateImageMap(JejuList);
        hotelRoomListModel = new DefaultListModel();

        if(hotelIdx == 1){
            hotelRoomList = new JList(SeoulList);
        }else if(hotelIdx == 2){
            hotelRoomList = new JList(BusanList);
        }else if(hotelIdx == 3){
            hotelRoomList = new JList(JejuList);
        }
        hotelRoomList.setCellRenderer(new HotelListRenderer());
        hotelRoomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelRoomList.addListSelectionListener(this);


        hotelRoomListScrollPane = new JScrollPane(hotelRoomList);
        hotelRoomListScrollPane.setPreferredSize(new Dimension(250, 80));

        hotelRoomTypeService.findHotelRoomByHotelIdx(hotelIdx).forEach(hotelRoomTypeDto -> {
            hotelRoomListModel.addElement(hotelRoomTypeDto);
        });


        setTitle("DS25 호텔 예약 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        hotelDto = hotelService.findHotel(hotelIdx);
        if (hotelDto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "존재하지 않는 호텔입니다.");
            return;
        }

        centerPanel = new Panel(new BorderLayout());

        hotelImageLabel = new JLabel(new ProxyImage(hotelDto.get().getImages().get(0).getImage()));


        hotelDetailTextArea = new JTextArea(hotelService.findHotel(hotelIdx).toString());
        hotelDetailTextArea.setEditable(true);
        hotelDetailTextArea.setLineWrap(true);

        hotelRoomTextArea = new JTextArea(hotelRoomTypeService.findHotelRoomByHotelIdx(hotelIdx).toString());
        hotelRoomTextArea.setEditable(true);
        hotelRoomTextArea.setLineWrap(true);

        btn_RoomDetail = new JButton("객실 상세보기");
        btn_RoomDetail.addActionListener(this);
        btn_RoomDetail.setActionCommand("roomDetail");

        hotelRoomTextArea = new JTextArea("1");
        hotelRoomTextArea.setEditable(true);


        centerPanel.add(hotelImageLabel, BorderLayout.NORTH);
        centerPanel.add(hotelRoomListScrollPane, BorderLayout.CENTER);


        add(centerPanel, BorderLayout.CENTER);
        add(new NorthPanel(hotelDto.get().getName()), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        loginPanel = new LoginPanel();
        loginPanel.btn_logout.addActionListener(this);
        loginPanel.btn_myPage.addActionListener(this);
        loginPanel.btn_myPage.setActionCommand("myPage");
        add(loginPanel, BorderLayout.SOUTH);

        setSize(800, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Map<String, ImageIcon> SeoulcreateImageMap(String[] seoulList) {
        Map<String, ImageIcon> Seoul = new HashMap<>();
        try {
            Seoul.put("그랜드 디럭스 룸 가격:600,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-01-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("프리미어 룸 가격:800,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("프리미어 스위트 룸 가격:960,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("디럭스 스위트 룸 가격:520,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-01-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("코리안 프리미어 룸 가격:1,000,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/premier/2849-4-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("코리안 시그니엘 프리미어 룸 가격:1,000,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/deluxe/2838-01-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("코리안 스위트 룸 가격:600,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3293-1-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("프레지덴셜 스위트 룸 가격:1,000,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3292-5-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));
            Seoul.put("로얄 스위트 룸 가격:2,500,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/seoul/accommodation/suite/3291-2-2000-roo-LTSG.jpg.thumb.1920.1920.jpg")));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return Seoul;
    }

    private Map<String, ImageIcon> BusancreateImageMap(String[] BusanList) {
        Map<String, ImageIcon> Busan = new HashMap<>();
        try {
            Busan.put("그랜드 디럭스 미포 하버뷰 룸 가격:300,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/grand/2738-02-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("프리미어 더블 룸 가격:300,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-1-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("프리미어 더블 오션뷰 룸 가격:370,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-4-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("프리미어 트윈 미포 하버뷰 룸 가격:340,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/20210122-5-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("프리미어 패밀리 트윈 룸 가격:370,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2743-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("시그니엘 프리미어 더블 오션뷰 룸 가격:440,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/premier/2740-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("시그니엘 프리미어 더블 미포 하버뷰 룸 가격:390,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/signiel-premier/20200711-5-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("프레지덴셜 스위트 오션뷰 룸 가격:480,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/presidential-suite/20200716-3-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));
            Busan.put("로얄 스위트 오션뷰 룸 가격:720,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/signiel/busan/rooms/suite/royal-suite/20210121-2-2000-roo-LTSB.jpg.thumb.1920.1920.jpg")));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return Busan;
    }

    private Map<String, ImageIcon> JejucreateImageMap(String[] JejuList) {
        Map<String, ImageIcon> Jeju = new HashMap<>();
        try {
            Jeju.put("디럭스 룸 가격:293,700", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/standard/superior/180804-1-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg")));
            Jeju.put("샤롯데 룸 가격:378,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/3549-01-2000-acc-LTJE.jpg.thumb.1920.1920.jpg")));
            Jeju.put("코너 스위트 룸 가격:441,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/charlotte-deluxe-room-(terrace-ocean-view)/2829-12-2000-acc-LTJE.jpg.thumb.1920.1920.jpg")));
            Jeju.put("프리미어 온돌 룸 가격:372,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/deluxe/ondol/1773-5-2000-acc-LTJE.jpg.thumb.1920.1920.jpg")));
            Jeju.put("프리미어 룸 가격:399,900", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/premier/premier/1020-2000-roo-LTJE.jpg.thumb.1920.1920.jpg")));
            Jeju.put("헬로키티 키즈 룸 가격:372,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/character/kids/921-1-2000-acc-LTJE.jpg.thumb.1920.1920.jpg")));
            Jeju.put("풀빌라 스위트 룸 가격:1,281,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/pool/180804-67-2000-acc-jeju-hotel.jpg.thumb.1920.1920.jpg")));
            Jeju.put("프레지덴셜 스위트 룸 가격:1,981,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/presidential/4179-04-2000-acc-LTJE.jpg.thumb.1920.1920.jpg")));
            Jeju.put("로얄 스위트 룸 가격:11,000,000", new ImageIcon(new URL("https://www.lottehotel.com/content/dam/lotte-hotel/lotte/jeju/accommodation/suite/royal/4179-01-2000-acc-LTJE.jpg.thumb.1920.1920.jpg")));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return Jeju;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("back")) {
            log.info("back 버튼 클릭");
            this.dispose();
            new HotelSelectionPage();
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
        if (e.getSource() == hotelRoomList) {
            log.info(e.getSource().toString());
            log.info(hotelRoomList.getSelectedValue().toString());
            int selectedIndex = hotelRoomList.getSelectedIndex();
            if(selectedIndex != -1){
                new HotelRoomDetailPage((long) (selectedIndex + 1));
                this.dispose();
            }
        }
    }

    public class HotelListRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(

                    list, value, index, isSelected, cellHasFocus);
            ImageIcon Seoul = SeoulimageMap.get((String) value);
            ImageIcon Busan = BusanimageMap.get((String) value);
            ImageIcon Jeju = JejuimageMap.get((String) value);

            if (Seoul != null) {
                // 이미지 크기 조정
                int maxWidth = 150; // 원하는 최대 가로 크기
                int maxHeight = 150; // 원하는 최대 세로 크기

                // 이미지 크기 조정
                ImageIcon SeoulscaledIcon = getScaledImageIcon(Seoul, maxWidth, maxHeight);

                label.setIcon(SeoulscaledIcon);
                label.setHorizontalTextPosition(JLabel.RIGHT);
            }
            if (Busan != null) {
                // 이미지 크기 조정
                int maxWidth = 150; // 원하는 최대 가로 크기
                int maxHeight = 150; // 원하는 최대 세로 크기

                // 이미지 크기 조정
                ImageIcon BusanscaledIcon = getScaledImageIcon(Busan, maxWidth, maxHeight);


                label.setIcon(BusanscaledIcon);
                label.setHorizontalTextPosition(JLabel.RIGHT);
            }

            if (Jeju != null) {
                // 이미지 크기 조정
                int maxWidth = 150; // 원하는 최대 가로 크기
                int maxHeight = 150; // 원하는 최대 세로 크기

                // 이미지 크기 조정
                ImageIcon JejuscaledIcon = getScaledImageIcon(Jeju, maxWidth, maxHeight);

                label.setIcon(JejuscaledIcon);
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
}
