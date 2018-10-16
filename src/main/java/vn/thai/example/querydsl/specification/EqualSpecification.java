package vn.thai.example.querydsl.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class EqualSpecification<T, Y extends Comparable<? super Y>> implements Specification<T> {

  private final Y value;
  private final String key;

  public EqualSpecification(String key, Y value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.equal(root.get(this.key), this.value);
  }
}
