package vn.thai.example.querydsl.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import vn.thai.example.querydsl.entity.User;

public class EmailUserSpecification implements Specification<User> {

    private String email;

    public EmailUserSpecification(String email) {
        this.email = email;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
        CriteriaBuilder builder) {
        return builder.equal(root.get("email"), this.email);
    }
}
