package io.herald.springboot.RController;

import io.herald.springboot.Model.UserTable;
import io.herald.springboot.Repository.Image2Repository;
import io.herald.springboot.Repository.ImageRepository;
import io.herald.springboot.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RControllerClass {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private Image2Repository image2Repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!!";
    }

    @GetMapping("/getAllUsers")
    public List<UserTable> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody UserTable user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Saved Successfully!!";
    }

    @GetMapping("/getOne/{id}")
    public UserTable getOne(@PathVariable int id){
        return userRepository.findById(id).get();
    }

    @GetMapping("/getID/{id}")
    public ResponseEntity<?>  getID(@PathVariable int id){
        if(userRepository.findById(id).isPresent()){
            UserTable u = userRepository.findById(id).get();
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID Not Found");



    }
}
