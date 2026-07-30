package io.herald.springboot.Controller;

import io.herald.springboot.Model.ImageTable;
import io.herald.springboot.Repository.ImageRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.util.Base64;

@Controller
public class GalleryController {

    @Autowired
    private ImageRepository imgRepo;


    @GetMapping("/gallery")
    public String galleryGet(HttpServletRequest request){

        HttpSession session = request.getSession();;
        if(session.getAttribute("username")==null){
            return "loginPage";
        }
        return "galleryPage";
    }

    @PostMapping("/galleryPost")
    public String galleryPost(@RequestParam("imgFile") MultipartFile imgFile){
        try{
            byte[] imgBytes = imgFile.getBytes();
            String imgString = Base64.getEncoder().encodeToString(imgBytes);
//            System.out.println('*');
//            System.out.println('*');
//            System.out.println('*');
//            System.out.println(imgString);
//            System.out.println('*');
//            System.out.println('*');
//            System.out.println('*');

            ImageTable img = new ImageTable();
            img.setImage(imgString);
            imgRepo.save(img);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "galleryPage";
    }
}
