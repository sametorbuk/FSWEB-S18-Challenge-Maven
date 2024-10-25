package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.repository.CardRepositoryImpl;
import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CardController {

    CardRepositoryImpl cardRepositoryImp;


    @Autowired
    public CardController(CardRepositoryImpl cardRepositoryImp) {
        this.cardRepositoryImp = cardRepositoryImp;
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getCards(){
        return ResponseEntity.ok(cardRepositoryImp.findAll());
    }

    @GetMapping("/cards/byColor/{color}")
    public ResponseEntity<List<Card>> findByColor(@PathVariable String color){
        return ResponseEntity.ok(cardRepositoryImp.findByColor(color));
    }

    @PostMapping("/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Card> saveCard(@RequestBody Card card){
        if(CardValidation.cardIsValid(card)){
           return ResponseEntity.ok(cardRepositoryImp.save(card));
        }

        throw new CardException("The data of card is not valid" , HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/card/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable long id){
        if(CardValidation.idIsValid(id)){
            return  ResponseEntity.ok(cardRepositoryImp.remove(id));
        }else{
            throw  new CardException("Please enter a valid id" , HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/cards/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id , @RequestBody Card card){
        if(!CardValidation.idIsValid(id)){
            throw new CardException("Please enter valid id" , HttpStatus.BAD_REQUEST);
        }
        if (CardValidation.cardIsValid(card)){
            return ResponseEntity.ok(cardRepositoryImp.update(card));
        }else{
            throw new CardException("Please enter valid card data" , HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/cards/byValue/{value}")
    public ResponseEntity<List<Card>> getByValue(@PathVariable int value){
        return ResponseEntity.ok(cardRepositoryImp.findByValue(value));
    }


    @GetMapping("/cards/byType/{type}")
    public ResponseEntity<List<Card>> getByType(@RequestBody String type ){
        return ResponseEntity.ok(cardRepositoryImp.findByType(type));
    }



}
