CREATE TABLE accounts(
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    authorities VARCHAR(32) NOT NULL
);

CREATE TABLE comment(
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    account_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    CONSTRAINT fk_comments_account
        FOREIGN KEY(account_id)
        REFERENCES account(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_comments_post
        FOREIGN KEY(post_id)
        REFERENCES posts(id)
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

CREATE TABLE social_group (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE,
    createdAt TIMESTAMP NOT NULL,
    accessibility VARCHAR(7) NOT NULL,
    account_id INTEGER NOT NULL,
    CONSTRAINT fk_posts_account
        FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON DELETE CASCADE,
    CONSTRAINT check_accessibility
        CHECK (accessibility LIKE 'OPEN' OR accessibility LIKE 'PRIVATE' OR accessibility LIKE 'HIDDEN')
);

CREATE TABLE social_group_request (
    account_id INTEGER NOT NULL,
    group_id INTEGER NOT NULL,
    PRIMARY KEY (account_id, group_id),
    CONSTRAINT fk_reqgro_account
        FOREIGN KEY (account_id)
        REFERENCES account(id),
    CONSTRAINT fk_reqgro_group
        FOREIGN KEY (group_id)
        REFERENCES social_group(id)
);

CREATE TABLE social_group_member (
    account_id INTEGER,
    group_id INTEGER,
    PRIMARY KEY (account_id, group_id),
    CONSTRAINT fk_accgro_account
        FOREIGN KEY (account_id)
        REFERENCES account(id),
    CONSTRAINT fk_accgro_group
        FOREIGN KEY (group_id)
        REFERENCES social_group(id)
);