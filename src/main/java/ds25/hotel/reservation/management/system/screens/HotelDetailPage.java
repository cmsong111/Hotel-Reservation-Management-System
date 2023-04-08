package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.entity.user.User;
import ds25.hotel.reservation.management.system.pattern.observer.Observable;
import ds25.hotel.reservation.management.system.pattern.observer.Observer;
import ds25.hotel.reservation.management.system.provider.UserProvider;
import ds25.hotel.reservation.management.system.screens.auth.LoginPage;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Slf4j
public class HotelDetailPage extends JFrame implements Observer, ActionListener {
    UserProvider userProvider = Singleton.getInstance().getUserProvider();
    HotelService hotelService = SpringBridge.getInstance().getBean(HotelService.class);
    HotelRoomService hotelRoomService = SpringBridge.getInstance().getBean(HotelRoomService.class);
    JLabel label = new JLabel("DS25 호텔 예약 관리 시스템");
    JLabel userLabel;
    JTextField number;
    JTextArea hotelDetailTextArea, hotelRoomTextArea;
    JButton btn_logout = new JButton("로그아웃");
    JButton btn_RoomDetail;
    JPanel panel = new JPanel();


    @Override
    public void update(Observable o, Object arg) {
        Optional<User> user = (Optional<User>) arg;
        if (user.isEmpty()) {
            log.info("로그아웃 되었습니다. 로그인 페이지로 이동합니다.");
            new LoginPage();
            userProvider.removeObserver(this);
            dispose();
        } else {
            userLabel.setText(user.get().getName() + "님 환영합니다.");
        }
    }

    public HotelDetailPage(long hotelIdx) {
        userProvider.registerObserver(this);

        setTitle("DS25 호텔 예약 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userLabel = new JLabel(userProvider.getUser().get().getName() + "님 환영합니다.");

        label.setBounds(100, 50, 400, 100);
        userLabel.setBounds(100, 100, 400, 100);
        btn_logout.setBounds(300, 50, 100, 100);

        btn_logout.addActionListener(this);
        btn_logout.setActionCommand("logout");

        hotelDetailTextArea = new JTextArea(hotelService.findHotel(hotelIdx).toString());
        hotelDetailTextArea.setBounds(0, 200, 800, 150);
        hotelDetailTextArea.setEditable(true);
        hotelDetailTextArea.setLineWrap(true);

        hotelRoomTextArea = new JTextArea(hotelRoomService.findHotelRoomByHotelIdx(hotelIdx).toString());
        hotelRoomTextArea.setBounds(0, 360, 800, 200);
        hotelRoomTextArea.setEditable(true);
        hotelRoomTextArea.setLineWrap(true);

        btn_RoomDetail = new JButton("객실 상세보기");
        btn_RoomDetail.setBounds(500, 100, 100, 100);
        btn_RoomDetail.addActionListener(this);
        btn_RoomDetail.setActionCommand("roomDetail");

        hotelRoomTextArea = new JTextArea();
        hotelRoomTextArea.setBounds(500, 50, 100, 50);
        hotelRoomTextArea.setEditable(true);

        add(label);
        add(userLabel);
        add(btn_logout);
        add(hotelDetailTextArea);
        add(hotelRoomTextArea);
        add(btn_RoomDetail);

        add(panel);


        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("logout")) {
            log.info("로그아웃 버튼 클릭");
            userProvider.updateUser(null);
        }
        if (command.equals("roomDetail")) {
            log.info("객실 상세보기 버튼 클릭");
            if (hotelRoomTextArea.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "객실 번호를 입력해주세요.");
                return;
            }
            new HotelRoomDetailPage(Long.valueOf(hotelRoomTextArea.getText()));
        }
    }
}
