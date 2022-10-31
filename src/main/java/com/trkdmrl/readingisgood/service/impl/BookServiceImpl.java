package com.trkdmrl.readingisgood.service.impl;

import com.trkdmrl.readingisgood.exception.GenericException;
import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.repository.BookRepository;
import com.trkdmrl.readingisgood.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public long save(Book book) {
        Book savedBook = bookRepository.save(book);
        if(savedBook == null) {
            throw new GenericException("Unssuccessful save");

        }
        return savedBook.getId();
    }

    @Override
    public long updateStock(int newStockNumber, long id) {
       Optional<Book> inDb = bookRepository.findById(id);
       if(inDb == null || !inDb.isPresent()) {
           throw new GenericException("Book not found");
       }
       inDb.get().setStockNumber(newStockNumber);
       Book inDbBook = bookRepository.save(inDb.get());
       return inDbBook.getId();
    }
}
