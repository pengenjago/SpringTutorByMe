package com.maanz.tutor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maanz.tutor.model.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

}
