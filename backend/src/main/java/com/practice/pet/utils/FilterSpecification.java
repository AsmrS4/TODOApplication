package com.practice.pet.utils;

import com.practice.pet.dto.FilterParams;
import com.practice.pet.entities.TodoEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;


@UtilityClass
public class FilterSpecification {
    public static Specification<TodoEntity> configureFilters(FilterParams params) {
        Specification<TodoEntity> specification = (root, query, cb) -> null;

        if(params.getStatus() != null)
            specification = specification.and(addStatusFiltration(params));

        if(params.getDateFrom() != null && params.getDateTo() !=null)
            specification = specification.and(addDateRangeFiltration(params));

        return specification;
    }

    private static Specification<TodoEntity> addStatusFiltration(FilterParams params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), params.getStatus());
    }

    private static Specification<TodoEntity> addDateRangeFiltration(FilterParams params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("createTime"), params.getDateFrom(), params.getDateTo());
    }
}
