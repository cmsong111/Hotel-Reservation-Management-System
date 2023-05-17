/*package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
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
public class HotelImageListEx extends JFrame implements ActionListener, ListSelectionListener {
    HotelService hotelService;

    private JList hotelList;
    private JScrollPane hotelListScrollPane;
    private JPanel hotelListPanel;
    private DefaultListModel hotelListModel;

    // private final Map<String, ImageIcon> imageMap;

    public HotelImageListEx() {
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

        DefaultListModel<Icon> listModel = new DefaultListModel<>();
        // listModel.addElement(SpringBridge.getInstance().getBean());

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
        if (hotelList.getSelectedIndex() == -1) {
        } else { //선택되지 않은 경우 –1을 반환한다.
            //선택이 된 경우.
        }

        /*if (hotelList.getSelectedItem().equals(name) {
            // 항목 이름이 name인 선택된 경우
        }*/

   /*     if (hotelList.getSelectedValue()!=null) {
            //하나라도 선택된 경우
        }
    }

    // 이미지를 표시하는 셀 렌더러
   /*private class ImageCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon((Icon) value);
            label.setHorizontalTextPosition(BorderLayout.CENTER);
            label.setVerticalTextPosition(JLabel.SOUTH);
            return label;
        }
    } */

