import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isIn;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.thai.example.querydsl.entity.User;
import vn.thai.example.querydsl.repository.SearchCriteria;
import vn.thai.example.querydsl.repository.UserRepository;
import vn.thai.example.querydsl.repository.UserSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {PersistenceJPAConfig.class})
@Transactional
@Commit
public class JPASpecificationsTest {

    @Autowired
    private UserRepository repository;

    private User userJohn;
    private User userTom;

    @Before
    public void init() {
        userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setEmail("john@doe.com");
        userJohn.setAge(22);
        repository.save(userJohn);

        userTom = new User();
        userTom.setFirstName("Tom");
        userTom.setLastName("Doe");
        userTom.setEmail("tom@doe.com");
        userTom.setAge(26);
        repository.save(userTom);
    }


    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec =
            new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results = repository.findAll(spec);

        assertThat(userJohn, isIn(results));
        assertThat(userTom, isIn(results));
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
            new UserSpecification(new SearchCriteria("firstName", ":", "john"));
        UserSpecification spec2 =
            new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results = repository.findAll(Specification.where(spec1).and(spec2));

        assertThat(userJohn, isIn(results));
    }
}