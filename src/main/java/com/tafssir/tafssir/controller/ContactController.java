package com.tafssir.tafssir.controller;
import com.tafssir.tafssir.model.Contact;
import com.tafssir.tafssir.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactRepo contactFormRepository;

    @PostMapping
        public @ResponseBody String registerUser(@RequestParam String name

                , @RequestParam String email
                , @RequestParam String message) {
            Contact n = new Contact(name, email, message);
            n.setName(name);
            n.setEmail(email);
            n.setMessage(message);
            contactFormRepository.save(n);
            return "saved";
    }
}
