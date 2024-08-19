package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.global.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomTypeDto;
import ds25.hotel.reservation.management.system.pattern.proxy.ProxyImage;
import ds25.hotel.reservation.management.system.domain.user.presentation.MyPage;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.LoginPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Slf4j
public class HotelRoomDetailPage extends JFrame implements ActionListener {

    JTextArea textArea, information;
    JScrollPane scrollPane;
    HotelRoomTypeService roomService;

    ProxyImage imageIcon;
    JLabel imageLabel;
    int imageIndex = 0;
    JButton nextButton, prevButton, reserveButton, cancelButton;
    JPanel centerPanel, imageControlPanel, mainPanel, textPanel;
    Optional<HotelRoomTypeDto> room;

    public HotelRoomDetailPage(Long idx) {
        roomService = SpringBridge.getInstance().getBean(HotelRoomTypeService.class);
        room = roomService.findHotelRoomByIdx(idx);
        if (room.isEmpty()) {
            JOptionPane.showMessageDialog(null, "해당 호텔 방이 존재하지 않습니다.");
            return;
        }

        setTitle("호텔 방 상세 정보");

        mainPanel = new JPanel(new BorderLayout());


        centerPanel = new JPanel(new GridLayout(5, 5));
        textPanel = new JPanel(new GridLayout(-1, 1));


        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(roomService.findHotelRoomByIdx(idx).get().getDescription());

        imageIcon = new ProxyImage(room.get().getImages().get(0).getImage(),500,282);

        imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);


        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 300, 500, 500);

        imageControlPanel = new JPanel(new GridLayout(1, 2));


        nextButton = new JButton("다음");
        nextButton.setBounds(600, 10, 100, 30);
        nextButton.setActionCommand("nextImage");
        nextButton.addActionListener(this);

        prevButton = new JButton("이전");
        prevButton.setBounds(600, 110, 100, 30);
        prevButton.setActionCommand("prevImage");
        prevButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0));

        reserveButton = new JButton("예약");
        reserveButton.setBounds(600, 210, 100, 30);
        reserveButton.setActionCommand("reserve");
        reserveButton.addActionListener(this);

        cancelButton = new JButton("취소");
        cancelButton.setBounds(600, 310, 100, 30);
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);

        buttonPanel.add(nextButton);
        buttonPanel.add(prevButton);

        buttonPanel.add(reserveButton);
        buttonPanel.add(cancelButton);

        centerPanel.add(imageControlPanel);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        textPanel.add(scrollPane, BorderLayout.SOUTH);
        centerPanel.add(textPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(imageLabel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(new NorthPanel(room.get().getName()), BorderLayout.NORTH);
        LoginPanel loginPanel = new LoginPanel();
        loginPanel.btn_myPage.addActionListener(this);
        loginPanel.btn_logout.addActionListener(this);

        add(loginPanel, BorderLayout.SOUTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);


        setSize(600, 800);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "nextImage" -> {
                ++imageIndex;
                if (imageIndex >= room.get().getImages().size()) {
                    imageIndex = 0;
                }
                log.info("nextImage index {}", imageIndex);
                imageLabel.setIcon(new ProxyImage(room.get().getImages().get(imageIndex).getImage(),500,282));
            }
            case "prevImage" -> {
                --imageIndex;
                if (imageIndex < 0) {
                    imageIndex = room.get().getImages().size() - 1;
                }
                log.info("prevImage index {}", imageIndex);
                imageLabel.setIcon(new ProxyImage(room.get().getImages().get(imageIndex).getImage(),500,282));
            }
            case "reserve" -> {
                log.info("reserve");
                new HotelReservationPage(room.get().getIdx());
            }
            case "cancel" -> {
                log.info("cancel");
                new HotelDetailPage(room.get().getHotelIdx());
                dispose();
            }
            case "back" -> {
                log.info("back");
                new HotelDetailPage(room.get().getHotelIdx());
                dispose();
            }
            case "myPage" -> {
                log.info("my page");
                new MyPage();
            }
            default -> {
                log.info("default");
            }
        }

    }
}
