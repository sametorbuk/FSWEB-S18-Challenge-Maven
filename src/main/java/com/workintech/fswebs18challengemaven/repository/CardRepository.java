package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardRepository {

    Card save(Card card);

    List<Card> findAll();

    List<Card> findByColor(String color);

    List<Card> findByValue(int value);

    List<Card> findByType(String type);

    Card update(Card card);

    Card remove(Long id);

}
