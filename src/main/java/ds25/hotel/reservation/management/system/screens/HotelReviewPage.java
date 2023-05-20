package ds25.hotel.reservation.management.system.screens;

import ds25.hotel.reservation.management.system.configuration.SpringBridge;
import ds25.hotel.reservation.management.system.dto.hotel.HotelReviewDto;
import ds25.hotel.reservation.management.system.screens.widget.EastPanel;
import ds25.hotel.reservation.management.system.screens.widget.NorthPanel;
import ds25.hotel.reservation.management.system.screens.widget.WestPanel;
import ds25.hotel.reservation.management.system.service.hotel.HotelReviewService;
import ds25.hotel.reservation.management.system.util.AggregateImpl;
import ds25.hotel.reservation.management.system.util.IteratorImpl;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class HotelReviewPage extends JFrame implements ActionListener {

    HotelReviewService hotelReviewService = SpringBridge.getInstance().getBean(HotelReviewService.class);

    TextArea textArea = new TextArea();

    private IteratorImpl iterator;
    private JButton prevButton;
    private JButton nextButton;

    public HotelReviewPage(Long hotelIdx) {
        AggregateImpl replyList = hotelReviewService.getHotelReviewListByHotelIdx(hotelIdx);

        iterator = replyList.iterator();


        setTitle("DS25 호텔 예약 관리 시스템");

        setLayout(new BorderLayout());
        pack();

        // textArea.setText(((HotelReview) replyList.getObjectAt(0)).toString());

        log.info("replyList size : {}", replyList.getLength());

        textArea.setText(((HotelReviewDto) replyList.getObjectAt(0)).toString());


        // 바텀 컨트룰 패널
        // 이전, 다음 버튼이 있고, replyList.hasNext(), replyList.hasPrevious()를 통해
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        prevButton = new JButton("이전");
        prevButton.addActionListener(this);
        prevButton.setActionCommand("prev");
        prevButton.setEnabled(false);
        nextButton = new JButton("다음");
        nextButton.addActionListener(this);
        nextButton.setActionCommand("next");
        bottomPanel.add(prevButton);
        bottomPanel.add(nextButton);


        if (iterator.hasNext()) {
            HotelReviewDto nextReview = (HotelReviewDto) iterator.next();
            textArea.setText(nextReview.toString());
        } else {
            JOptionPane.showMessageDialog(null, "더 이상 리뷰가 없습니다.");
            nextButton.setEnabled(false);
        }


        // 좌우 여백 설정
        add(new NorthPanel(), BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(textArea, BorderLayout.CENTER);

        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("prev")) {
            log.info("prev button clicked");
            if (iterator.hasPrevious()) {
                HotelReviewDto prevReview = (HotelReviewDto) iterator.previous();
                textArea.setText(prevReview.toString());
                nextButton.setEnabled(true);  // 이전 버튼을 누르면 다음 버튼 활성화
            } else {
                prevButton.setEnabled(false);  // 이전 버튼을 누르면 비활성화
                JOptionPane.showMessageDialog(null, "더 이상 리뷰가 없습니다.");
            }

        } else if (e.getActionCommand().equals("next")) {
            log.info("next button clicked");
            if (iterator.hasNext()) {
                HotelReviewDto nextReview = (HotelReviewDto) iterator.next();
                textArea.setText(nextReview.toString());
                prevButton.setEnabled(true);  // 다음 버튼을 누르면 이전 버튼 활성화
            } else {
                nextButton.setEnabled(false);  // 다음 버튼을 누르면 비활성화
                JOptionPane.showMessageDialog(null, "더 이상 리뷰가 없습니다.");
            }
        }
    }
}
