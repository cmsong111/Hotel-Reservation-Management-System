package ds25.hotel.reservation.management.system.service.hotel;

import ds25.hotel.reservation.management.system.entity.hotel.HotelImage;
import ds25.hotel.reservation.management.system.repository.hotel.HotelImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HotelImageService {

    private final HotelImageRepository hotelImageRepository;
    @Autowired
    public HotelImageService(HotelImageRepository hotelImageRepository) {
        this.hotelImageRepository = hotelImageRepository;
    }

    public HotelImage findByIdx(Long idx) {
        return hotelImageRepository.findById(idx).orElse(null);
    }

    public void initHotelImageData(List<HotelImage> hotelImageList) {
        log.info("initHotelImageData");

        hotelImageRepository.saveAll(hotelImageList);

        for (HotelImage hotelImage : hotelImageRepository.findAll()) {
            log.info("HotelImage : {}", hotelImage);
        }

    }
}
