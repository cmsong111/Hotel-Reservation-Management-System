package ds25.hotel.reservation.management.system.di;

import ds25.hotel.reservation.management.system.HotelReservationManagementSystem;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

public class DiContext {

	private static volatile DiContext instance;
	private final Map<Class<?>, Object> componentMap = new HashMap<>();

	public static DiContext getInstance() {
		if (instance == null) {
			synchronized (DiContext.class) {
				if (instance == null) {
					instance = new DiContext();
				}
			}
		}
		return instance;
	}

	private DiContext() {
		Reflections reflections = new Reflections(HotelReservationManagementSystem.class.getPackageName());
		try {
			// 1. 필요한 핵심 의존성(EntityManagerFactory)을 먼저 등록
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2-db-unit");
			componentMap.put(EntityManagerFactory.class, emf);

			ValidatorFactory validatorFactory  = Validation.buildDefaultValidatorFactory();
			componentMap.put(ValidatorFactory.class, validatorFactory);


			// 2. @Component 어노테이션이 달린 클래스들을 찾아 등록
			Set<Class<?>> componentTypes = reflections.getTypesAnnotatedWith(Component.class);

			for (Class<?> componentType : componentTypes) {
				// 3. 어노테이션이나 인터페이스는 인스턴스화하지 않음
				if (componentType.isAnnotation() || componentType.isInterface()) {
					continue;
				}

				// 이미 등록된 객체는 스킵
				if (!componentMap.containsKey(componentType)) {
					getComponent(componentType); // 재귀 호출을 통해 의존성 주입
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("DiContext initialization failed", e);
		}
	}


	public <T> T getComponent(Class<T> componentType) {
		// 3. 캐시된 인스턴스가 있는지 확인
		if (componentMap.containsKey(componentType)) {
			return (T) componentMap.get(componentType);
		}

		// 4. 인스턴스 생성 및 의존성 주입
		try {
			// @Inject 어노테이션이 있는 생성자를 찾음
			Constructor<?> constructor = null;
			for (Constructor<?> c : componentType.getConstructors()) {
				if (c.isAnnotationPresent(Inject.class)) {
					constructor = c;
					break;
				}
			}
			if (constructor == null) {
				// @Inject가 없는 기본 생성자를 찾음
				constructor = componentType.getConstructor();
			}

			// 생성자 파라미터들의 의존성을 재귀적으로 가져와서 주입
			Object[] dependencies = new Object[constructor.getParameterCount()];
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				dependencies[i] = getComponent(parameterTypes[i]);
			}

			T instance = (T) constructor.newInstance(dependencies);
			componentMap.put(componentType, instance);
			return instance;
		} catch (Exception e) {
			throw new RuntimeException("Failed to create component: " + componentType.getName(), e);
		}
	}

	// 애플리케이션 종료 시 EMF를 닫는 메서드 추가
	public void close() {
		EntityManagerFactory emf = (EntityManagerFactory) componentMap.get(EntityManagerFactory.class);
		if (emf != null && emf.isOpen()) {
			emf.close();
		}

		ValidatorFactory validatorFactory = (ValidatorFactory) componentMap.get(ValidatorFactory.class);
		if (validatorFactory != null) {
			validatorFactory.close();
		}
	}
}
