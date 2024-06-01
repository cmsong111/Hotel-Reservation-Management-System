package ds25.hotel.reservation.management.system.di;

import jakarta.inject.Named;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Named // JSR-330 표준(@Named)과 연결
public @interface Component {
	String value() default "";
}
