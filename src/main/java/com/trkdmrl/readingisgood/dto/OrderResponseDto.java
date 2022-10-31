package com.trkdmrl.readingisgood.dto;

import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.OrderDetail;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
        private long id;

        private CustomerDto customer;

        private BigDecimal price;

        private LocalDateTime orderedDate;

        private List<BookDto> books;
}
