package com.chequer.ax5.api.demo.entity.grid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridRepository extends JpaRepository<AX5Grid, Long> {
}