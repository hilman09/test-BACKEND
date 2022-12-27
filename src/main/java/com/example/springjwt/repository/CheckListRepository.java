package com.example.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjwt.entities.CheckList;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    Optional<CheckList> findByName(String name);
}
