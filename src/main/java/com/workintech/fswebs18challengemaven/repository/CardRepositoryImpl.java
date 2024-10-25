package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class CardRepositoryImpl implements CardRepository {

    private EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c" , Card.class);
        return query.getResultList();
    }


    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color" , Card.class);
        query.setParameter("color" , color);

        List<Card> results = query.getResultList();
        if (results.isEmpty()) {
            throw new CardException("Color not found: " + color , HttpStatus.NOT_FOUND);
        }
        return query.getResultList();
    }



    @Override
    public List<Card> findByValue(int value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value" , Card.class);
        query.setParameter("value" , value);
        return  query.getResultList();
    }


    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type" , Card.class);
        query.setParameter("type" , type);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
      entityManager.merge(card);
      return card;
    }



    @Transactional
    @Override
    public Card remove(Long id) {
       Card card = entityManager.find(Card.class ,id);
       entityManager.remove(card);
       return card;
    }
}
