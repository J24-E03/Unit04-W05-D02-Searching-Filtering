package com.dci.full_mvc.specification;

import com.dci.full_mvc.model.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {


/*
   root.get("title")          // accesses the title field
   root.get("releaseYear")    // accesses the releaseYear field
   root.get("language")       // accesses the language field
   root.get("director").get("firstName") // if Movie has a Director field (OneToOne or ManyToOne)


    query.distinct(true); // eliminate duplicate results
    query.orderBy(cb.asc(root.get("title"))); // sort by title ASC


    cb.equal(root.get("language"), "English")        // WHERE language = 'English'
    cb.like(root.get("title"), "%dark%")             // WHERE title LIKE '%dark%'
    cb.greaterThan(root.get("rating"), 7.5)          // WHERE rating > 7.5
    cb.and(predicate1, predicate2)                   // WHERE condition1 AND condition2
    cb.or(predicate1, predicate2)                    // WHERE condition1 OR condition2
    cb.isNull(root.get("releaseDate"))               // WHERE releaseDate IS NULL



* */

//    each method represents a single query we want to perform
//    speicfication methods always return specifciation
    public static Specification<Movie> titleEquals(String title){
        return (root,query, cb)->{
            if(title == null || title.trim().isEmpty()) return null;
            return cb.equal(root.get("title"), title);
        };
    }


    public static Specification<Movie> releaseYearGreaterThan(Integer year){
        return (root, query, criteriaBuilder) -> {
            if(year == null) return null;
            return criteriaBuilder.greaterThan(root.get("releaseYear"),year);
        };
    }
}
