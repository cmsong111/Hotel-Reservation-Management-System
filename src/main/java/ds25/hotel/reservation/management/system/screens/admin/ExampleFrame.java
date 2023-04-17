package ds25.hotel.reservation.management.system.screens.admin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExampleFrame extends JFrame { // 방 10개 띄워놓고 객실현황 확인하는 틀(?)

    JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    JList<String> listBox;
    DefaultListModel<String> listModel;

    public ExampleFrame() {
        super("Example Frame");

        // 버튼 생성
        button1 = new JButton("방 1");
        button2 = new JButton("방 2");
        button3 = new JButton("방 3");
        button4 = new JButton("방 4");
        button5 = new JButton("방 5");
        button6 = new JButton("방 6");
        button7 = new JButton("방 7");
        button8 = new JButton("방 8");
        button9 = new JButton("방 9");
        button10 = new JButton("방 10");

        // 리스트 박스 생성
        listModel = new DefaultListModel<>();
        listBox = new JList<>(listModel);

        // 리스트 박스에 데이터 추가
        ArrayList<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        items.add("Item 5");
        items.add("Item 6");
        items.add("Item 7");
        items.add("Item 8");
        for (String item : items) {
            listModel.addElement(item);
        }

        setLayout(new GridLayout(6, 4,6,6));
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        add(button8);
        add(button9);
        add(button10);
        add(new JScrollPane(listBox));

        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ExampleFrame();
    }
}