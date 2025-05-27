package com.example.booklist.repository;


import com.example.booklist.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query (value = "SELECT DISTINCT genre From books", nativeQuery = true)
//    List<String> findAllGenres();
//
//    @Query(value = "SELECT * FROM books WHERE genre IN (:genres)", nativeQuery = true)
//    List<Book> findBooksByGenres(List<String> genres);
}
