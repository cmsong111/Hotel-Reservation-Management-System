package ds25.hotel.reservation.management.system.domain.review;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	long hotelId;

	@Column(nullable = false)
	long authorId;

	@Column(nullable = false)
	long reservationId;

	@Column(nullable = false)
	int rating;

	@Column(columnDefinition = "TEXT")
	String content;

	@Column(columnDefinition = "TEXT")
	String reply;

	@CollectionTable(
			name = "hotel_review_images",
			joinColumns = @JoinColumn(name = "id")
	)
	@OrderColumn(name = "image_order")
	private List<String> images;

	@Column(updatable = false)
	@CreationTimestamp
	Timestamp createdAt = Timestamp.from(Instant.now());
	@UpdateTimestamp
	Timestamp updatedAt = Timestamp.from(Instant.now());

	/**
	 * 빌더 객체를 생성하는 정적 팩토리 메소드입니다.
	 * HotelReview.builder() 형태로 호출할 수 있습니다.
	 */
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private long hotelId;
		private long authorId;
		private long reservationId;
		private int rating;
		private String content;
		private String reply;
		private List<String> images;
		private Timestamp createdAt;
		private Timestamp updatedAt;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder hotelId(long hotelId) {
			this.hotelId = hotelId;
			return this;
		}

		public Builder authorId(long authorId) {
			this.authorId = authorId;
			return this;
		}

		public Builder reservationId(long reservationId) {
			this.reservationId = reservationId;
			return this;
		}

		public Builder rating(int rating) {
			this.rating = rating;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Builder reply(String reply) {
			this.reply = reply;
			return this;
		}

		public Builder images(List<String> images) {
			this.images = images;
			return this;
		}

		public Builder createdAt(Timestamp createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder updatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public Review build() {
			return new Review(id, hotelId, authorId, reservationId, rating, content, reply, images, createdAt, updatedAt);
		}
	}
}
