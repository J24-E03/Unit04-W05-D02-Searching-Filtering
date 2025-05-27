# Lab: Add Dynamic Search and Filtering to Your Book App Using Specifications

## Learning Objectives

By the end of this lab, you should be able to:

- Implement the Specification pattern to build dynamic, type-safe queries
- Create flexible filtering for Book entities based on multiple optional criteria
- Combine Specifications using logical operators (`and()`, `or()`)
- Refactor controllers to support query parameters for filtering
- (Optional) Add a search form using Thymeleaf to submit filtering parameters

---

## Part 1: Create a Specification for the `Book` Entity

1. Create a new class called `BookSpecification` in a `specifications` package.
2. Implement the following static methods that return `Specification<Book>`:
   - Filter by title (case-insensitive)
   - Filter by release year greater than or equal to a given year
   - Filter by publisher name
   - Filter by author name

---

## Part 2: Update the Repository

1. Modify your `BookRepository` to extend `JpaSpecificationExecutor<Book>` so it can execute specification queries.

---

## Part 3: Create a Filter Method in the Service

1. In the `BookService` class, add a method that takes in optional parameters (e.g., title, releaseYear, publisherId, authorId).
2. Combine the specifications conditionally based on which parameters are provided.
3. Return a filtered list of books based on the combined specification.

---

## Part 4: Add Filter Support in the Controller

1. Add a new `@GetMapping` method in your `BookController` to handle `/books/search`.
2. Accept optional `@RequestParam` inputs for each filter parameter.
3. Use the service method to fetch filtered books.
4. Return the results in the existing book list view.

