package com.example.ecommerce.specification;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderDetail;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderSpecification implements Specification<Order> {
    private SearchCriteria criteria;


    public OrderSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperation()) {
            case EQUALS:
                return criteriaBuilder.equal(
                        root.get(criteria.getKey()),
                        criteria.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()),
                        String.valueOf(criteria.getValue()));
            case GREATER_THAN_OR_EQUALS:
                if (root.get(criteria.getKey()).getJavaType() == LocalDateTime.class) {
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), (LocalDateTime) criteria.getValue());
                } else {
                    return criteriaBuilder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString());
                }
            case LESS_THAN:
                return criteriaBuilder.lessThan(
                        root.get(criteria.getKey()),
                        String.valueOf(criteria.getValue()));
            case LESS_THAN_OR_EQUALS:
                if (root.get(criteria.getKey()).getJavaType() == LocalDateTime.class) {
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), (LocalDateTime) criteria.getValue());
                } else {
                    return criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString());
                }
            case PRODUCT_JOIN_PRODUCT_NAME_LIKE:
                Join<OrderDetail, Product> orderDetailProductJoins = root.join("orderDetails").join("product");
                Predicate predicate2 = criteriaBuilder.or(
                        // ho???c t??m trong b???ng product b???n ghi c?? name gi???ng v???i gi?? tr???
                        criteriaBuilder.like(orderDetailProductJoins.get("name"), "%" + criteria.getValue() + "%")
                );
                return predicate2;
            case USER_JOIN_LIKE :
                Join<User, Order> orderUserJoin = root.join("user");
                return criteriaBuilder.like(orderUserJoin.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        return null;
    }
}
