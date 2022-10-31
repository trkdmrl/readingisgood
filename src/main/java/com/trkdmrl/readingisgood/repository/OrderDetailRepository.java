package com.trkdmrl.readingisgood.repository;

import com.trkdmrl.readingisgood.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrder_Id(long id);
}
