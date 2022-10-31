package com.trkdmrl.readingisgood.service;

import com.trkdmrl.readingisgood.model.Book;

public interface BookService {

    long save(Book book);

    long updateStock(int newStockNumber, long id);
}
