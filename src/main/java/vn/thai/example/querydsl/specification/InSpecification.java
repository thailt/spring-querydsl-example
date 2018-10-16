package vn.thai.example.querydsl.specification;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class InSpecification<T, Y extends Comparable<? super Y>> implements Specification<T> {

  private final String key;
  private final List<Y> values;

  public InSpecification(String key, List<Y> values) {
    this.key = key;
    this.values = values;
  }

  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return root.get(this.key).in(this.values);
  }
}
