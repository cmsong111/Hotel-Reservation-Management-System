package ds25.hotel.reservation.management.system.ui.admin;

import ds25.hotel.reservation.management.system.config.AppConfig;
import ds25.hotel.reservation.management.system.di.DiContext;
import ds25.hotel.reservation.management.system.domain.reservation.application.HotelReservationService;
import ds25.hotel.reservation.management.system.ui.widget.EastPanel;
import ds25.hotel.reservation.management.system.ui.widget.NorthPanel;
import ds25.hotel.reservation.management.system.ui.widget.SouthPanel;
import ds25.hotel.reservation.management.system.ui.widget.WestPanel;
import ds25.hotel.reservation.management.system.util.observer.Observable;
import ds25.hotel.reservation.management.system.util.observer.Observer;
import ds25.hotel.reservation.management.system.util.provider.HotelReservationProvider;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RoomReservationListPage extends JFrame implements ActionListener, ListSelectionListener, Observer { // ReservationPage 버튼 누르면 나오는 페이지로 생각중

	HotelReservationProvider hotelReservationProvider = AppConfig.getInstance().getHotelReservationProvider();


	Long hotelRoomIdx;
	private JList hotelList;
	private JScrollPane reservationListScrollPane;
	private JPanel reservationListPanel;
	private DefaultListModel reservationListModel;
	private HotelReservationService hotelReservationService;

	public RoomReservationListPage(Long hotelRoomIdx) {
		this.hotelRoomIdx = hotelRoomIdx;
		hotelReservationProvider.registerObserver(this);
		hotelReservationService = DiContext.getInstance().getComponent(HotelReservationService.class);

		reservationListModel = new DefaultListModel();
		hotelList = new JList(reservationListModel);
		hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hotelList.addListSelectionListener(this);

		reservationListScrollPane = new JScrollPane(hotelList);
		reservationListScrollPane.setPreferredSize(new Dimension(250, 80));

		hotelReservationService.getHotelReservationByHotelRoomIdx(hotelRoomIdx).forEach(hotelReservationDto -> {
			reservationListModel.addElement(hotelReservationDto);
		});

		setTitle("예약 목록");
		setSize(1000, 800);


		setLayout(new BorderLayout());

		add(reservationListScrollPane, BorderLayout.CENTER);
		add(new NorthPanel(hotelRoomIdx.toString() + "객실"), BorderLayout.NORTH);
		add(new WestPanel(), BorderLayout.WEST);
		add(new SouthPanel(), BorderLayout.SOUTH);
		add(new EastPanel(), BorderLayout.EAST);

		setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

	@Override
	public void update(Observable o, Object arg) {
		hotelReservationProvider.removeObserver(this);
		dispose();
		new RoomReservationListPage(hotelRoomIdx);
	}
}
