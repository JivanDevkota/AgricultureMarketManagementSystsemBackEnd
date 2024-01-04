package com.nsu.agriculturemarketinfosys.service.admin.faq;

import com.nsu.agriculturemarketinfosys.dto.FAQDTO;
import com.nsu.agriculturemarketinfosys.entity.FAQ;
import com.nsu.agriculturemarketinfosys.entity.Product;
import com.nsu.agriculturemarketinfosys.repository.FAQRepository;
import com.nsu.agriculturemarketinfosys.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;


    public FAQDTO postFAQ(Long productId, FAQDTO faqdto){
        Optional<Product>optionalProduct=productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            FAQ faq=new FAQ();

            faq.setQuestion(faqdto.getQuestion());
            faq.setAnswer(faqdto.getAnswer());
            faq.setProduct(optionalProduct.get());
            return faqRepository.save(faq).getFAQDto();
        }
        return null;
    }
}
