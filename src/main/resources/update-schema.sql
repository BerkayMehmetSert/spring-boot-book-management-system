CREATE TABLE address
(
    id           VARCHAR(255) NOT NULL,
    street       VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    zip_code     VARCHAR(255),
    is_active    BOOLEAN,
    publisher_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE author
(
    id         VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    is_active  BOOLEAN,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_author PRIMARY KEY (id)
);

CREATE TABLE book
(
    id           VARCHAR(255) NOT NULL,
    name         VARCHAR(255),
    isbn         VARCHAR(255),
    description  VARCHAR(255),
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    deleted_at   TIMESTAMP WITHOUT TIME ZONE,
    is_active    BOOLEAN,
    author_id    VARCHAR(255) NOT NULL,
    publisher_id VARCHAR(255) NOT NULL,
    category_id  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_book PRIMARY KEY (id)
);

CREATE TABLE category
(
    id         VARCHAR(255) NOT NULL,
    name       VARCHAR(255),
    is_active  BOOLEAN,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE publisher
(
    id         VARCHAR(255) NOT NULL,
    name       VARCHAR(255),
    website    VARCHAR(255),
    is_active  BOOLEAN,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT "pk_publısher" PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_PUBLISHER_ID FOREIGN KEY (publisher_id) REFERENCES publisher (id);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_AUTHOR_ID FOREIGN KEY (author_id) REFERENCES author (id);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_PUBLISHER_ID FOREIGN KEY (publisher_id) REFERENCES publisher (id);