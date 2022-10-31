package com.trkdmrl.readingisgood.repository;

import com.trkdmrl.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderedDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Page<Order> findByCustomerId(long customerId, Pageable pageable);
}
