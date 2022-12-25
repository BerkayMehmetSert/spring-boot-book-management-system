package com.bms.bookmanagementsystem.helper;

public class LogMessage {
    public static class General {
        public static final String DATA_SUCCESSFULLY_SAVED = "Data successfully saved";
        public static final String DATA_SUCCESSFULLY_UPDATED = "Data successfully updated";
        public static final String DATA_SUCCESSFULLY_DELETED = "Data successfully deleted";
        public static final String DATA_SUCCESSFULLY_RETRIEVED = "Data successfully retrieved";
    }

    public static class Address{
        public static final String ADDRESS_NOT_FOUND = "Address not found";
    }

    public static class Author {
        public static final String AUTHOR_NOT_FOUND = "Author not found";
        public static final String AUTHOR_ALREADY_EXISTS = "Author already exists";
    }

    public static class Book {
        public static final String BOOK_NOT_FOUND = "Book not found";
        public static final String BOOK_ALREADY_EXISTS = "Book already exists";
        public static final String BOOK_NOT_ACTIVE = "Book not active";
    }

    public static class Category {
        public static final String CATEGORY_NOT_FOUND = "Category not found";
        public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
        public static final String CATEGORY_NOT_ACTIVE = "Category not active";
    }

    public static class Publisher {
        public static final String PUBLISHER_NOT_FOUND = "Publisher not found";
        public static final String PUBLISHER_ALREADY_EXISTS = "Publisher already exists";
    }
}
