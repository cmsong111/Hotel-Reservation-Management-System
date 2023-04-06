package ds25.hotel.reservation.management.system.configuration;

import ds25.hotel.reservation.management.system.service.user.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBridge {
    private static final SpringBridge INSTANCE = new SpringBridge();
    private final ApplicationContext context;

    private SpringBridge() {
        context = new AnnotationConfigApplicationContext(JpaConfig.class);
    }

    public static SpringBridge getInstance() {
        return INSTANCE;
    }

    public <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
