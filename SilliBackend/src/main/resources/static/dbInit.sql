CREATE TABLE accounts(
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    authorities VARCHAR(32) NOT NULL
);

CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    account_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    CONSTRAINT fk_comments_account
        FOREIGN KEY(account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_comments_post
        FOREIGN KEY(post_id)
        REFERENCES posts(id),
        ON DELETE CASCADE
);

CREATE TABLE post(
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    account_id INTEGER NOT NULL,
    CONSTRAINT fk_posts_account
        FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON DELETE CASCADE
);

CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE,
    createdAt TIMESTAMP NOT NULL,
    account_id INTEGER NOT NULL,
    CONSTRAINT fk_posts_account
        FOREIGN KEY (account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE
);

CREATE TABLE accounts_groups (
    account_id INTEGER,
    group_id INTEGER,
    PRIMARY KEY (account_id, group_id),
    CONSTRAINT fk_accgro_account
        FOREIGN KEY (account_id)
        REFERENCES accounts(id),
    CONSTRAINT fk_accgro_group
        FOREIGN KEY (group_id)
        REFERENCES groups(id)
);