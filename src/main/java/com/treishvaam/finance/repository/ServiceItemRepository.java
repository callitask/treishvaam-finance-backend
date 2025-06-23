package com.treishvaam.finance.repository;

import com.treishvaam.finance.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {}