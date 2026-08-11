package io.herald.springboot.Controller;

import io.herald.springboot.Model.UserTable;
import io.herald.springboot.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.List;

//Controller handles http request like get, post,.... etc
@Controller

public class TotalController {

    //Autowired helps in dependency injection, provides all the required
    // functions and API's to a class/interface object no new keyword is required
    @Autowired
    private UserRepository uRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String firstPage(){
        return "index"; //returns index.html page
    }

    @GetMapping("/signup")
    public String signupGet(){
        return "signupPage";
    }

    @GetMapping("/login")
    public String loginGet(){
        return "loginPage";
    }

    @PostMapping("/loginPost")
    public String loginPost(HttpServletRequest request, Model m){
        String username, password;

        username=request.getParameter("username");
        password=request.getParameter("password");
//        static login
//        if(username.equals("admin") && password.equals("admin")){
//            return "homePage";
//        }

        //Repository Login
//        if(uRepo.existsByUsernameAndPassword(username,hashPassword)){
//            List<UserTable> userList = uRepo.findAll();
//            m.addAttribute("userList",userList);
        List<UserTable> users = uRepo.findByUsername(username);
        if(users.stream().anyMatch(user -> passwordEncoder.matches(password, user.getPassword()))){

            HttpSession session = request.getSession();
            //session resolves around the http requests. we are trying to
            //get a running session with the above code

            session.setAttribute("username", username);
            //after a successful signin, a username is provided a session acc to their username

            List<UserTable> totalUsers = uRepo.findAll();
            m.addAttribute("totalUsers", totalUsers);
            return "homePage";
        }

        //message lai Model ko attribute vaninxa
        m.addAttribute("LoginError","Username/Password Incorrect");

//        System.out.println(username);
//        System.out.println(password);
        return "loginPage";
    }

    @PostMapping("/signup")
    public String signupPost(HttpServletRequest request, Model m){

        String username,password,email;
        username=request.getParameter("username");
        password=request.getParameter("password");
        email = request.getParameter("email");

        if (uRepo.findByUsername(username).size() > 0) {
            m.addAttribute("LoginError", "Username already exists");
            return "signupPage";
        }

        //md5- DigestUtils
//        String hashPassword= DigestUtils.md5DigestAsHex(password.getBytes());
        String hashPassword= passwordEncoder.encode(password);

        UserTable ut = new UserTable();
        ut.setUsername(username);
        ut.setPassword(hashPassword);
        uRepo.save(ut);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Signup  Successfully");
        message.setText("Welcome "+username+"!");

//        mailSender.send(message);

        m.addAttribute("signupSuccess", "Successfully signed up! Please login...");
        return "loginPage";
    }

    @GetMapping("/home")
    public String homePage(Model m){
        m.addAttribute("totalUsers", uRepo.findAll());
        return "homePage";
    }

    //Model attribute is only for the upcoming page
    //Session attribute is for the whole session
}


