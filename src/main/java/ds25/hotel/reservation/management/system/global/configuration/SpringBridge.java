package ds25.hotel.reservation.management.system.global.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Deprecated(forRemoval = true)
public class SpringBridge {
    private static final SpringBridge INSTANCE = new SpringBridge();
    private final ApplicationContext context = new AnnotationConfigApplicationContext();


    public static SpringBridge getInstance() {
        return INSTANCE;
    }

    public <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
