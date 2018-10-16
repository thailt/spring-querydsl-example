package vn.thai.example.querydsl.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class GreaterThanSpecification<T, Y extends Comparable<? super Y>> implements Specification<T> {

  private final String key;
  private final Y value;

  public GreaterThanSpecification(String key, Y value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.greaterThan(root.get(this.key), this.value);
  }
}
