package vn.thai.example.querydsl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;
import vn.thai.example.querydsl.entity.User;
import vn.thai.example.querydsl.repository.UserRepository;
import vn.thai.example.querydsl.specification.EqualSpecification;
import vn.thai.example.querydsl.specification.GreaterThanSpecification;
import vn.thai.example.querydsl.specification.InSpecification;
import vn.thai.example.querydsl.specification.IsAgeOverUserSpecification;

@SpringBootApplication
public class Main implements CommandLineRunner {

  @Autowired UserRepository userRepository;

  public static void main(String[] args) {
    new SpringApplication(Main.class).run(args);
  }

  @Override
  public void run(String... args) {

    User u1 = new User();
    u1.setEmail("thai@gmail.com");

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, -19);
    Date nineteenYearAgo = calendar.getTime();

    u1.setBirthDay(nineteenYearAgo);
    userRepository.saveAndFlush(u1);

    User u2 = new User();
    u2.setEmail("thai@gmail.com");

    calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, -17);
    Date seventeenYearAgo = calendar.getTime();

    u2.setBirthDay(seventeenYearAgo);

    userRepository.saveAndFlush(u2);

    User u3 = new User();
    u3.setEmail("thai3@gmail.com");
    calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, -17);
    u3.setBirthDay(calendar.getTime());
    userRepository.saveAndFlush(u3);

    List<User> userList =
        userRepository.findAll(
            Specification.where(new EqualSpecification<User, String>("email", "thai@gmail.com"))
                .and(new IsAgeOverUserSpecification(18)));

    List<User> userList1 =
        userRepository.findAll(
            Specification.where(new EqualSpecification<User, String>("email", "thai@gmail.com"))
                .and(new GreaterThanSpecification<User, Integer>("age", 17)));

    List<User> userList3 =
        userRepository.findAll(
            Specification.where(new EqualSpecification<User, String>("email", "thai@gmail.com")));

    List<String> emails = new ArrayList<>();
    emails.add("thai@gmail.com");
    emails.add("thai3@gmail.com");
    List<User> userList4 =
        userRepository.findAll(Specification.where(new InSpecification<>("email", emails)));

    System.out.println(userList);
    System.out.println(userList1);
    System.out.println(userList3);
    System.out.println(userList4);
  }
}
