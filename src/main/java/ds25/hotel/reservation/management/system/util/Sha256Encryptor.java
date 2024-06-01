package ds25.hotel.reservation.management.system.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA-256 알고리즘을 사용한 패스워드 인코더 구현체
 */
public class Sha256Encryptor implements PasswordEncryptor {

	@Override
	public String encrypt(String plainText) {
		try {
			// MessageDigest 인스턴스를 "SHA-256"으로 가져옵니다.
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// 평문(plainText)을 바이트 배열로 변환하여 해시 계산을 수행합니다.
			byte[] encodedHash = digest.digest(
					plainText.getBytes(StandardCharsets.UTF_8));

			// 계산된 해시 바이트 배열을 16진수 문자열로 변환합니다.
			return bytesToHex(encodedHash);
		} catch (NoSuchAlgorithmException e) {
			// "SHA-256" 알고리즘이 지원되지 않는 경우 예외 처리
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}

	@Override
	public boolean matches(String plainText, String encryptedText) {
		// 입력된 평문을 동일한 방식으로 암호화합니다.
		String hashedPlainText = encrypt(plainText);

		// 생성된 해시와 기존에 암호화된 텍스트를 비교합니다.
		return hashedPlainText.equals(encryptedText);
	}

	/**
	 * 바이트 배열을 16진수(Hex) 문자열로 변환하는 헬퍼 메소드
	 */
	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
