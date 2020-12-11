package com.barto.ERPbackend.repository;

import com.barto.ERPbackend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
