package com.nsu.agriculturemarketinfosys.service.admin.faq;

import com.nsu.agriculturemarketinfosys.dto.FAQDTO;

public interface FAQService {

    FAQDTO postFAQ(Long productId, FAQDTO faqdto);
}
