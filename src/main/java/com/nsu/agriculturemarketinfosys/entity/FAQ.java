package com.nsu.agriculturemarketinfosys.entity;

import com.nsu.agriculturemarketinfosys.dto.FAQDTO;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public FAQDTO getFAQDto(){
        FAQDTO faqdto=new FAQDTO();
        faqdto.setId(id);
        faqdto.setQuestion(question);
        faqdto.setAnswer(question);
        faqdto.setProductId(product.getId());

        return faqdto;
    }
}
