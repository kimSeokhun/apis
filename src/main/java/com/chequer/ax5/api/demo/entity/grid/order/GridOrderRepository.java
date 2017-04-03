package com.chequer.ax5.api.demo.entity.grid.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridOrderRepository extends JpaRepository<AX5GridOrder, Long> {
}