package ds25.hotel.reservation.management.system.screens.admin;


import ds25.hotel.reservation.management.system.global.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelDto;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.SouthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagePage extends JFrame implements ActionListener {

    HotelDto hotelDto;
    HotelService hotelService;
    TextArea textArea;
    JButton btn_load, btn_update, btn_delete, btn_back;

    /**
     * 기본 생성자
     *
     * @param idx 호텔 인덱스 번호
     */
    public HotelManagePage(Long idx) {
        hotelService = SpringBridge.getInstance().getBean(HotelService.class);
        hotelDto = hotelService.findHotel(idx).get();

        setTitle("호텔 관리");
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        textArea = new TextArea();
        textArea.setText(hotelDto.toString());
        mainPanel.add(textArea);

        JPanel btn_panel = new JPanel(new GridLayout(1, 4, 10, 10));
        btn_load = new JButton("호텔 정보 불러오기");
        btn_load.setActionCommand("load");
        btn_load.addActionListener(this);

        btn_update = new JButton("호텔 정보 수정");
        btn_update.setActionCommand("update");
        btn_update.addActionListener(this);

        btn_delete = new JButton("호텔 정보 삭제");
        btn_delete.setActionCommand("delete");
        btn_delete.addActionListener(this);

        btn_back = new JButton("뒤로 가기");
        btn_back.setActionCommand("back");
        btn_back.addActionListener(this);

        btn_panel.add(btn_load);
        btn_panel.add(btn_update);
        btn_panel.add(btn_delete);
        btn_panel.add(btn_back);

        mainPanel.add(btn_panel);


        add(mainPanel, BorderLayout.CENTER);
        add(new NorthPanel(), BorderLayout.NORTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(new SouthPanel(), BorderLayout.SOUTH);


        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "load":
                hotelDto = hotelService.findHotel(hotelDto.getIdx()).get();
                textArea.setText(hotelDto.toString());
                break;
            case "update":
                JOptionPane.showMessageDialog(null, "수정되었습니다.");
                hotelDto = hotelService.findHotel(hotelDto.getIdx()).get();
                textArea.setText(hotelDto.toString());
                dispose();
                break;
            case "delete":
                hotelService.deleteHotel(hotelDto.getIdx());
                JOptionPane.showMessageDialog(null, "삭제되었습니다.");
                new HotelManagePage(hotelDto.getIdx());
                dispose();
                break;
            case "back":
                new AdminHotelSelectPage();
                dispose();
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) {
        new HotelManagePage(1L);
    }
}
