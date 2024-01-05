package com.example.demo.repositoryTest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void addUser() {

        //Before

        List<User> userList = userRepository.findAll();
        Assertions.assertEquals(2, userList.size());

        //Given

        User user = new User("ULIM", "12345678");
        userRepository.save(user);

        //After
        List<User> userListAfter = userRepository.findAll();
        Assertions.assertEquals(3, userListAfter.size());

    }
    @Test
    public void getByUsername(){
        User user = userRepository.findByUsername("CEITI").get();
        Assertions.assertEquals(2, user.getId());
        Assertions.assertEquals("ceiti2020", user.getPassword());
    }
    @Test
    public void updateUserById(){
        //Before
        User userBefore = userRepository.findById(2).get();
        Assertions.assertEquals("CEITI", userBefore.getUsername());
        Assertions.assertEquals("ceiti2020", userBefore.getPassword());

        //Given
        User user = new User(2,"CEITI", "00000000");
        userRepository.save(user);

        //After
        User userAfter = userRepository.findById(2).get();
        Assertions.assertEquals("CEITI", userAfter.getUsername());
        Assertions.assertEquals("00000000", userAfter.getPassword());

    }
}
