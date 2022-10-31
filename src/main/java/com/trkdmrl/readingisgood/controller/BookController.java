package com.trkdmrl.readingisgood.controller;

import com.trkdmrl.readingisgood.utils.common.ResponseMessage;
import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addCustomer(@RequestBody Book book) {
        long result = bookService.save(book);
        ResponseMessage responseMessage = new ResponseMessage("Saved",result);
        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/updatestock")
    public ResponseEntity<ResponseMessage> updateStock(@RequestParam int number, @RequestParam long id) {

        long result = bookService.updateStock(number, id);
        ResponseMessage responseMessage = new ResponseMessage("Updated", result);
        return ResponseEntity.ok(responseMessage);
    }
}
