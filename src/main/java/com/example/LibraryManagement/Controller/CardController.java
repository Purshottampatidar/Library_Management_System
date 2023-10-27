package com.example.LibraryManagement.Controller;

import com.example.LibraryManagement.Entities.LibraryCard;
import com.example.LibraryManagement.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/generatePlainCard")
    public ResponseEntity generateCare(){
        LibraryCard newPlainCard=cardService.generateCard();
        String response="the new card is generated and having a card number "+newPlainCard.getCardNo();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/associateCardWithStudent")
    public ResponseEntity associateCardWithStudent(@RequestParam("studentId")Integer studentId, @RequestParam("cardId") Integer cardId){
        String response =cardService.associateCardWithStudent(studentId,cardId);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
