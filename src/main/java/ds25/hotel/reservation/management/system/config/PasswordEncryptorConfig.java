package ds25.hotel.reservation.management.system.config;

import ds25.hotel.reservation.management.system.di.Bean;
import ds25.hotel.reservation.management.system.di.Configuration;
import ds25.hotel.reservation.management.system.util.PasswordEncryptor;
import ds25.hotel.reservation.management.system.util.Sha256Encryptor;

@Configuration
public class PasswordEncryptorConfig {

	@Bean
	public PasswordEncryptor passwordEncryptor() {
		return new Sha256Encryptor();
	}
}
