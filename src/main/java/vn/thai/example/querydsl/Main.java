package vn.thai.example.querydsl;


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
import vn.thai.example.querydsl.specification.EmailUserSpecification;
import vn.thai.example.querydsl.specification.IsAgeOverUserSpecification;

@SpringBootApplication
public class Main implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

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

        List<User> userList =
            userRepository
                .findAll(Specification.where(new EmailUserSpecification("thai@gmail.com"))
                    .and(new IsAgeOverUserSpecification(18)));

        List<User> userList1 =
            userRepository
                .findAll(Specification.where(new EmailUserSpecification("thai@gmail.com"))
                    .and(new IsAgeOverUserSpecification(17)));

        System.out.println(userList);
        System.out.println(userList1);
    }
}
