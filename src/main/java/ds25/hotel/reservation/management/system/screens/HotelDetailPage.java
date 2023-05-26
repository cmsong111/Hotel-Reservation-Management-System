package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import ds25.hotel.reservation.management.system.pattern.proxy.ProxyImage;
import ds25.hotel.reservation.management.system.screens.auth.MyPage;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class HotelDetailPage extends JFrame implements ActionListener, ListSelectionListener {
    private final HotelService hotelService = SpringBridge.getInstance().getBean(HotelService.class);
    private final HotelRoomTypeService hotelRoomTypeService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);

    Optional<HotelDto> hotelDto;
    private ArrayList<HotelRoomTypeDto> hotelRoomTypeDtoList;
    private JList<HotelRoomTypeDto> hotelRoomTypeJList;
    private DefaultListModel hotelRoomListModel;
    private JScrollPane hotelRoomListScrollPane;

    private Panel centerPanel;
    LoginPanel loginPanel;
    JLabel userLabel, hotelImageLabel;
    JTextArea hotelDetailTextArea, hotelRoomTextArea;
    JButton btn_RoomDetail, btn_reply;


    public HotelDetailPage(long hotelIdx) {
        hotelDto = hotelService.findHotel(hotelIdx);
        if (hotelDto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "존재하지 않는 호텔입니다.");
            return;
        }

        hotelRoomTypeDtoList = hotelRoomTypeService.findHotelRoomByHotelIdx(hotelIdx);

        hotelRoomTypeJList =new JList<HotelRoomTypeDto>(hotelRoomTypeDtoList.toArray(new HotelRoomTypeDto[hotelRoomTypeDtoList.size()])) ;

        hotelRoomListModel = new DefaultListModel();
        hotelRoomTypeJList.setCellRenderer(new HotelTypeRenderer());
        hotelRoomTypeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hotelRoomTypeJList.addListSelectionListener(this);


        hotelRoomListScrollPane = new JScrollPane(hotelRoomTypeJList);
        hotelRoomListScrollPane.setPreferredSize(new Dimension(250, 80));




        setTitle("DS25 호텔 예약 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        centerPanel = new Panel(new BorderLayout());

        hotelImageLabel = new JLabel(new ProxyImage(hotelDto.get().getImages().get(0).getImage(),500,281));


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


        btn_reply= new JButton("댓글");
        btn_reply.addActionListener(this);
        btn_reply.setActionCommand("reply");

        add(centerPanel, BorderLayout.CENTER);
        add(new NorthPanel(hotelDto.get().getName()), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        loginPanel = new LoginPanel();
        loginPanel.btn_logout.addActionListener(this);
        loginPanel.btn_myPage.addActionListener(this);
        loginPanel.btn_myPage.setActionCommand("myPage");
        loginPanel.add(btn_reply, BorderLayout.CENTER);
        add(loginPanel, BorderLayout.SOUTH);

        setSize(800, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
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
        } else if (command.equals("reply")){
            log.info("댓글 버튼 클릭");
            new HotelReviewPage(hotelDto.get().getIdx());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (e.getSource() == hotelRoomTypeJList) {
            int selectedIndex = hotelRoomTypeJList.getSelectedIndex();
            HotelRoomTypeDto selectedHotelRoomType = hotelRoomTypeDtoList.get(selectedIndex);
            log.info("선택된 호텔 객실 타입 : {}", selectedHotelRoomType);
            if (selectedIndex != -1) {
                new HotelRoomDetailPage(selectedHotelRoomType.getIdx());
                this.dispose();
            }
        }
    }
}

class HotelTypeRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        HotelRoomTypeDto  hotelRoomType = (HotelRoomTypeDto) value;
        ProxyImage icon = new ProxyImage(hotelRoomType.getImages().get(0).getImage(), 130, 130);

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setIcon(icon);
        label.setText("<html><h2>" + hotelRoomType.getName() + "</h2><br>" + hotelRoomType.getDescription() + "</html>");

        return label;
    }
}
