package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "card" , schema = "public")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Color color;


    public void setType(Type type) {
       if(type == null){
           this.type=type;
       }

       if(type == Type.JOKER){
           this.value=null;
           this.color=null;
       }

       if(type != null){
           this.value=null;
           this.type=type;
       }
    }


    public void setValue(Integer value) {
        if(value == null){
          this.value=value;
        }
       if(value != null){
           type=null;
           this.value=value;
       }
    }
}
