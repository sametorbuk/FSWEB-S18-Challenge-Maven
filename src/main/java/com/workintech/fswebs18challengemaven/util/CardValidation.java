package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardValidation {

    public static boolean cardIsValid(Card card){
        if((card.getType() == null && card.getValue() == null)
                || card.getId() == null || card.getColor()==null){
            return false;
        }
        return true;
    }


    public static boolean idIsValid(Long id){
        if(id <= 0 || id == null){
            return false;
        }
        return true;
    }
}
