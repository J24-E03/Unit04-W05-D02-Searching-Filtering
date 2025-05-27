package com.example.booklist.controller;

import com.example.booklist.model.Book;
import com.example.booklist.service.AuthorService;
import com.example.booklist.service.BookService;
import com.example.booklist.service.GenreService;
import com.example.booklist.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.mutation.spi.BindingGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books/books-list";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("index", -1);
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.findAll());
        return "books/book-form";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, @RequestParam List<Long> authorIds, @RequestParam List<Long> publisherIds, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("index", -1);
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            model.addAttribute("publishers", publisherService.findAll());
            return "books/book-form";
        }
        bookService.addNewBook(book, authorIds, publisherIds);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("publishers", publisherService.findAll());

        return "books/book-form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, @RequestParam List<Long> authorIds, @RequestParam List<Long> publisherIds, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.findAllAuthors());
            model.addAttribute("genres", genreService.getAllGenres());
            model.addAttribute("publishers", publisherService.findAll());
            return "books/book-form";
        }
      bookService.updateBook(book,authorIds,publisherIds);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/filter/form")
    public String filterBooksByGenres(Model model) {
        //model.addAttribute("genres", bookService.findAllGenres());
        return "books/filter-form";
    }

    @GetMapping("/filter")
    public String filterBooksByGenres(@RequestParam List<String> genre, Model model) {
        //model.addAttribute("books", bookService.findBooksByGenres(genre));
       // model.addAttribute("selectedGenre", genre);
        return "books/filtered-books";
    }

}
