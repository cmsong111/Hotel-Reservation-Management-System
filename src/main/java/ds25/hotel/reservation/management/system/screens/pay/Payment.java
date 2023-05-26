package ds25.hotel.reservation.management.system.screens.pay;

import ds25.hotel.reservation.management.system.configuration.Singleton;
import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReservationDto;
import ds25.hotel.reservation.management.system.pattern.command.HotelReservationCommand;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelReservationService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class Payment extends JFrame implements ActionListener {

    HotelReservationService hotelReservationService;
    HotelReservationDto hotelReservationDto;
    JTextField tx_totalAmount;


    public Payment(HotelReservationDto hotelReservationDto) {
        hotelReservationService = SpringBridge.getInstance().getBean(HotelReservationService.class);
        this.hotelReservationDto = hotelReservationDto;

        if (hotelReservationDto.getIdx() == 0 || hotelReservationDto.getPayedMoney() != 0) {
            JOptionPane.showMessageDialog(null, "주문이 올바르지 않습니다.");
            return;
        }


        setLayout(new BorderLayout());


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(7, 2, 30, 30));

        centerPanel.add(new JLabel("카드번호"));
        JTextField tx_cardNum = new JTextField();
        centerPanel.add(tx_cardNum);

        centerPanel.add(new JLabel("유효기간"));
        JTextField tx_validDate = new JTextField();
        centerPanel.add(tx_validDate);

        centerPanel.add(new JLabel("비밀번호"));
        JTextField tx_password = new JTextField();
        centerPanel.add(tx_password);

        centerPanel.add(new JLabel("할부개월"));
        JTextField tx_installment = new JTextField();
        centerPanel.add(tx_installment);

        centerPanel.add(new JLabel("할인코드"));
        JTextField tx_discountCode = new JTextField();
        centerPanel.add(tx_discountCode);

        centerPanel.add(new JLabel("할인금액"));
        JTextField tx_discountAmount = new JTextField();
        centerPanel.add(tx_discountAmount);

        centerPanel.add(new JLabel("총 결제금액"));
        tx_totalAmount = new JTextField((int) hotelReservationDto.getTotalPrice());
        centerPanel.add(tx_totalAmount);

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton btn_pay = new JButton("결제하기");
        btn_pay.addActionListener(this);
        btn_pay.setActionCommand("pay");
        JButton btn_cancel = new JButton("취소하기");
        btn_cancel.addActionListener(this);
        btn_cancel.setActionCommand("cancel");

        southPanel.add(btn_pay);
        southPanel.add(btn_cancel);
        add(southPanel, BorderLayout.SOUTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("pay")) {
            //결제하기
            JOptionPane.showConfirmDialog(null, "결제가 완료되었습니다.", "결제완료", JOptionPane.DEFAULT_OPTION);
            log.info("결제 완료");
            HotelReservationCommand hotelReservationCommand = new HotelReservationCommand(hotelReservationDto);
            hotelReservationCommand.pay(Integer.parseInt(tx_totalAmount.getText()));


            Singleton.getInstance().getHotelReservationProvider().notifyObservers();

            dispose();
        } else if (command.equals("cancel")) {
            dispose();
        }
    }
}
