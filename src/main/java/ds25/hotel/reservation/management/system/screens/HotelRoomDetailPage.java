package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelRoomDto;
import ds25.hotel.reservation.management.system.service.hotel.HotelRoomService;
import ds25.hotel.reservation.management.system.util.ImageLoader;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Slf4j
public class HotelRoomDetailPage extends JFrame implements ActionListener {

    JTextArea textArea;
    JScrollPane scrollPane;
    HotelRoomService roomService;
    Panel panel;

    ImageIcon imageIcon;
    JLabel imageLabel;
    int imageIndex = 0;
    JButton nextButton, prevButton;
    Optional<HotelRoomDto> room;

    public HotelRoomDetailPage(Long idx) {
        roomService = SpringBridge.getInstance().getBean(HotelRoomService.class);
        room = roomService.findHotelRoomByIdx(idx);
        if (room.isEmpty()) {
            JOptionPane.showMessageDialog(null, "해당 호텔 방이 존재하지 않습니다.");
            return;
        }

        setTitle("호텔 방 상세 정보");

        panel = new Panel();

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(roomService.findHotelRoomByIdx(idx).toString());

        imageIcon = ImageLoader.getImage(room.get().getImages().get(0).getImage());

        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(10, 10, 500, 500);


        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 400, 500, 500);

        nextButton = new JButton("다음");
        nextButton.setBounds(600, 10, 100, 30);
        nextButton.setActionCommand("nextImage");
        nextButton.addActionListener(this);

        prevButton = new JButton("이전");
        prevButton.setBounds(600, 110, 100, 30);
        prevButton.setActionCommand("prevImage");
        prevButton.addActionListener(this);

        add(imageLabel);
        add(scrollPane);
        add(nextButton);
        add(prevButton);
        add(panel);

        setBounds(100, 100, 800, 900);
        setLayout(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("nextImage")) {
            log.info("nextImage idx : {}" , ++imageIndex);

            if (imageIndex >= room.get().getImages().size()) {
                imageIndex = 0;
            }
            imageLabel.setIcon(ImageLoader.getImage(room.get().getImages().get(imageIndex).getImage()));
        } else if (command.equals("prevImage")) {
            log.info("prevImage idx : {}" , --imageIndex);
            if (imageIndex < 0) {
                imageIndex = room.get().getImages().size() - 1;
            }
            imageLabel.setIcon(ImageLoader.getImage(room.get().getImages().get(imageIndex).getImage()));
        }

    }
}
