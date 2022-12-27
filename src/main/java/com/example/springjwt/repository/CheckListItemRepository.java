package com.example.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjwt.entities.CheckListItem;

public interface CheckListItemRepository extends JpaRepository<CheckListItem, Long> {
    Optional<CheckListItem> findByName(String msg);
    
}
