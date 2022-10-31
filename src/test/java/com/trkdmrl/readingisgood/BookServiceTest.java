package com.trkdmrl.readingisgood;

import com.trkdmrl.readingisgood.dto.BookDto;
import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.repository.BookRepository;
import com.trkdmrl.readingisgood.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.github.jsonzou.jmockdata.JMockData;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {


    private BookServiceImpl service;

    @Mock
    private BookRepository repository;


    @Before
    public void setup() {
        service = new BookServiceImpl(repository);
    }

    @Test
    public void model() {
        Book bean = new Book();
        bean.equals(null);

        BookDto dto = new BookDto();
        dto.equals(null);

        bean.equals(new Book());
        dto.equals(new BookDto());

        bean.toString();
        dto.toString();
    }

    @Test
    public void save() {
        Book bean = createBook();
        when(repository.save(any())).thenReturn(bean);
        long id = service.save(createBook());
        assertThat(id).isNotNull();
    }

    @Test
    public void updateStock() {
        Book bean = createBook();
        when(repository.save(any())).thenReturn(bean);
        createBook().setStockNumber(1);
        long id = service.save(createBook());
        assertThat(id).isNotNull();
    }

    private static Book createBook() {
        Book book = new Book();
        book.setId(1);
        book.setStockNumber(2);
        BigDecimal price = new BigDecimal("14.50");
        book.setPrice(price);
        return book;
    }

    private static BookDto createUserDTO() {
        BookDto dto = new BookDto();
        return dto;
    }
}
