package vn.thai.example.querydsl.specification;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import vn.thai.example.querydsl.entity.User;

public class IsAgeOverUserSpecification implements Specification<User> {

    private int age;


    public IsAgeOverUserSpecification(int age) {
        this.age = age;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
        CriteriaBuilder builder) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 0 - this.age);
        Date eighteenYearAgo = calendar.getTime();

        return builder.lessThanOrEqualTo(root.get("birthDay"), eighteenYearAgo);
    }
}
