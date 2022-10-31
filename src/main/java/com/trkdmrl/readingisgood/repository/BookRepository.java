package com.trkdmrl.readingisgood.repository;

import com.trkdmrl.readingisgood.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
