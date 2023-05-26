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

    private JLabel reviewText;

    private IteratorImpl iterator;
    private JButton prevButton;
    private JButton nextButton;

    public HotelReviewPage(Long hotelIdx) {
        AggregateImpl replyList = hotelReviewService.getHotelReviewListByHotelIdx(hotelIdx);

        iterator = replyList.iterator();


        setTitle("DS25 호텔 예약 관리 시스템");

        setLayout(new BorderLayout());
        pack();

        log.info("replyList size : {}", replyList.getLength());

        Panel centerPanel = new Panel(new BorderLayout());

        reviewText = new JLabel(makeHtmlText((HotelReviewDto) replyList.getObjectAt(0)));


        centerPanel.add(new JLabel("리뷰"), BorderLayout.NORTH);

        centerPanel.add(reviewText, BorderLayout.CENTER);


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
            reviewText.setText(makeHtmlText(nextReview));

        } else {
            JOptionPane.showMessageDialog(null, "더 이상 리뷰가 없습니다.");
            nextButton.setEnabled(false);
        }


        // 좌우 여백 설정
        add(new NorthPanel(), BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(new WestPanel(), BorderLayout.WEST);
        add(new EastPanel(), BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("prev")) {
            log.info("prev button clicked");
            if (iterator.hasPrevious()) {
                HotelReviewDto prevReview = (HotelReviewDto) iterator.previous();
                reviewText.setText(makeHtmlText(prevReview));
                nextButton.setEnabled(true);  // 이전 버튼을 누르면 다음 버튼 활성화
            } else {
                prevButton.setEnabled(false);  // 이전 버튼을 누르면 비활성화
                JOptionPane.showMessageDialog(null, "더 이상 리뷰가 없습니다.");
            }

        } else if (e.getActionCommand().equals("next")) {
            log.info("next button clicked");
            if (iterator.hasNext()) {
                HotelReviewDto nextReview = (HotelReviewDto) iterator.next();
                reviewText.setText(makeHtmlText(nextReview));
                prevButton.setEnabled(true);  // 다음 버튼을 누르면 이전 버튼 활성화
            } else {
                nextButton.setEnabled(false);  // 다음 버튼을 누르면 비활성화
                JOptionPane.showMessageDialog(null, "더 이상 리뷰가 없습니다.");
            }
        }
    }

    private String makeHtmlText(HotelReviewDto review) {
        String htmlContent = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; line-height: 1.5; }" +
                // ... HTML 스타일 코드 추가 ...
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>"+  hotelReviewService.getHotelName(review.getHotelIdx()) +"</h1>" +
                "<div class=\"review-container\">" +
                "<div class=\"review-header\">" +
                "<span class=\"user-id\"> 작성자:" + review.getUserId() + "</span><br>" +
                "<span class=\"rating\">Rating: " + review.getRating() + "</span>" +
                "</div>" +
                "<div class=\"review-content\">" +
                "<h3>" + review.getContent() + "</h3ㄴ>" +
                "</div>" +
                // ... 필요한 필드들을 추가로 채워넣음 ...
                "</div>" +
                "</body>" +
                "</html>";

        return htmlContent;
    }

}
