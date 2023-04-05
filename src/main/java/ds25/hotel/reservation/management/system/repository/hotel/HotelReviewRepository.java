package ds25.hotel.reservation.management.system.repository.hotel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ds25.hotel.reservation.management.system.entity.hotel.HotelReview;
import ds25.hotel.reservation.management.system.entity.hotel.HotelRoom;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;

@Slf4j
public class HotelReviewRepository {
    Gson gson = new Gson();
    ArrayList<HotelReview> hotelReviews = new ArrayList<>();
    int idx = 0;

    public HotelReviewRepository() {
        loadFromJson();
    }

    /**
     * Json에서 호텔 정보 불러오기
     *
     * @author 김남주
     */
    public void loadFromJson(){
        Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("db/hotelReview.json"), StandardCharsets.UTF_8);
        hotelReviews = gson.fromJson(reader, new TypeToken<ArrayList<HotelReview>>() {
        }.getType());
        idx = hotelReviews.get(hotelReviews.size() - 1).getIdx();
        log.info("호텔 리뷰 데이터가 \"{}\"에서 불러와졌습니다", getClass().getClassLoader().getResource("db/hotelReview.json").getPath());
        log.info("호텔 리뷰 데이터 {}개를 불러왔습니다.", hotelReviews.size());
    }

    public void saveToJson() throws IOException {
        FileWriter file = new FileWriter(getClass().getClassLoader().getResource("db/hotelReview.json").getPath());
        hotelReviews.sort(Comparator.comparing(HotelReview::getIdx));
        gson.toJson(hotelReviews, file);
        file.flush();
        file.close();
        log.info("Hotel 리뷰 데이터가 \"{}\"에 저장되었습니다", getClass().getClassLoader().getResource("db/hotelReview.json").getPath());
        loadFromJson();
    }


}
