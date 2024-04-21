Feature: Bookstore Website Functionality

    Scenario: Search for a Book
    Given User is on the bookstore website homepage
    When User searches for a book with title "The Great Gatsby"
    Then Search results should display the book with title "The Great Gatsby"
    
    Scenario: Add a Book to Cart
    Given User is on the bookstore website homepage
    When User searches for a book with title "The Great Gatsby"
    When User navigates to the details page of a book
    And User adds the book to the shopping cart
    Then The book should be successfully added to the shopping cart

