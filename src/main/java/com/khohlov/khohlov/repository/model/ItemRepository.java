package com.khohlov.khohlov.repository.model;

import com.khohlov.khohlov.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
