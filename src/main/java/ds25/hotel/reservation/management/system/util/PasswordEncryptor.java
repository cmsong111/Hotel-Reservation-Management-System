package ds25.hotel.reservation.management.system.util;

/**
 * 패스워드 인코더
 */
public interface PasswordEncryptor {
	String encrypt(String plainText);

	boolean matches(String plainText, String encryptedText);
}
