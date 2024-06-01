package ds25.hotel.reservation.management.system.util.attributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds25.hotel.reservation.management.system.domain.hotel.HotelAmenity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Collections;
import java.util.Set;

@Converter
public class HotelAmenitySetConverter implements AttributeConverter<Set<HotelAmenity>, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Set<HotelAmenity> -> JSON String
	 * Entity의 데이터를 DB에 저장할 때 호출됩니다.
	 */
	@Override
	public String convertToDatabaseColumn(Set<HotelAmenity> attribute) {
		if (attribute == null || attribute.isEmpty()) {
			return null; // 또는 "[]"
		}
		try {
			// Set을 JSON 배열 형태의 문자열로 변환
			// 예: {"WIFI", "POOL"} -> "[\"WIFI\", \"POOL\"]"
			return objectMapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			// 실제 운영 환경에서는 로깅을 하거나, 더 구체적인 예외 처리가 필요합니다.
			throw new IllegalArgumentException("JSON writing error", e);
		}
	}

	/**
	 * JSON String -> Set<HotelAmenity>
	 * DB의 데이터를 Entity로 변환할 때 호출됩니다.
	 */
	@Override
	public Set<HotelAmenity> convertToEntityAttribute(String dbData) {
		if (dbData == null || dbData.isBlank()) {
			return Collections.emptySet();
		}
		try {
			// JSON 배열 형태의 문자열을 Set<HotelAmenity>으로 변환
			// 예: "[\"WIFI\", \"POOL\"]" -> {"WIFI", "POOL"}
			return objectMapper.readValue(dbData, new TypeReference<Set<HotelAmenity>>() {
			});
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("JSON reading error", e);
		}
	}
}
