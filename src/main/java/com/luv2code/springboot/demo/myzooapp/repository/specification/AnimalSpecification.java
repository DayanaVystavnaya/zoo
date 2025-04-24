package com.luv2code.springboot.demo.myzooapp.repository.specification;


import com.luv2code.springboot.demo.myzooapp.model.Animal;
import org.springframework.data.jpa.domain.Specification;

public class AnimalSpecification {

    // Метод для создания спецификации с фильтрами по виду и минимальному возрасту
    public static Specification<Animal> withFilters(String species, Integer minAge) {
        return (root, query, builder) -> {
            // Создание фильтра по виду (если указан)
            if (species != null && !species.isEmpty()) {
                return builder.and(
                        builder.equal(root.get("species"), species),
                        builder.greaterThanOrEqualTo(root.get("age"), minAge != null ? minAge : 0)
                );
            }

            // Если вид не указан, но минимальный возраст указан
            if (minAge != null) {
                return builder.greaterThanOrEqualTo(root.get("age"), minAge);
            }

            // Если нет фильтров, возвращаем null (без фильтрации)
            return builder.conjunction();
        };
    }
}
