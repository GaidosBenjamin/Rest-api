CREATE TABLE users (
    user_id INTEGER PRIMARY KEY NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    user_age VARCHAR(255)
                   ) ;

CREATE TABLE posts (
    post_id INTEGER PRIMARY KEY NOT NULL,
    post_description VARCHAR(255),
    user_id INTEGER NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE address (
    address_id INTEGER PRIMARY KEY NOT NULL,
    address VARCHAR(255),
    user_id INTEGER NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE groups (
    group_id INTEGER PRIMARY KEY NOT NULL,
    group_name VARCHAR(255)
)

CREATE TABLE groups_users (
    user_id INTEGER NOT NULL,
    group_id INTEGER NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES groups (group_id)
);

