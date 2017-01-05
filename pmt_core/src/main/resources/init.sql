DROP FUNCTION IF EXISTS create_comments();
DROP TABLE IF EXISTS Task_comments;
DROP TABLE IF EXISTS Project_comments;
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Project_tasks;
DROP TABLE IF EXISTS Task_type;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Priority_level;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Status;
DROP SEQUENCE IF EXISTS all_id_seq;

CREATE SEQUENCE all_id_seq NO MAXVALUE;

CREATE TABLE Projects (
  id                  INTEGER DEFAULT nextval('all_id_seq'),
  title               VARCHAR(500) NOT NULL,
  description         TEXT,
  start_project_date  TIMESTAMPTZ  NOT NULL,
  ending_project_date TIMESTAMPTZ  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title)
);

CREATE TABLE Role (
  id           INTEGER DEFAULT nextval('all_id_seq'),
  access_level INTEGER,
  title        VARCHAR(100),
  PRIMARY KEY (id),
  CHECK (access_level >= 0),
  UNIQUE (access_level)
);

CREATE TABLE Users (
  id        INTEGER DEFAULT nextval('all_id_seq'),
  firstname VARCHAR(150) NOT NULL,
  lastname  VARCHAR(150) NOT NULL,
  role_id   INTEGER,
  image     BYTEA   DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES Role (id)
);

CREATE TABLE Comments (
  id      INTEGER DEFAULT nextval('all_id_seq'),
  message TEXT NOT NULL,
  user_id INTEGER,
  date    TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES Users (id)
);

CREATE TABLE Project_comments (
  project_id INTEGER,
  comment_id INTEGER,
  FOREIGN KEY (project_id) REFERENCES Projects (id),
  FOREIGN KEY (comment_id) REFERENCES Comments (id) ON DELETE CASCADE
);

CREATE TABLE Task_type (
  id          INTEGER DEFAULT nextval('all_id_seq'),
  title       VARCHAR(150) NOT NULL,
  description TEXT         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title),
  CHECK (length(description) >= 10),
  CHECK (length(title) >= 5)
);

CREATE TABLE Status (
  id          INTEGER DEFAULT nextval('all_id_seq'),
  title       VARCHAR(250) NOT NULL,
  description TEXT         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title),
  CHECK (length(description) > 10)
);

CREATE TABLE Priority_level (
  id    INTEGER DEFAULT nextval('all_id_seq'),
  title VARCHAR(250) NOT NULL,
  level INTEGER      NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title, level),
  CHECK (level >= 0)
);

CREATE TABLE Task (
  id           INTEGER DEFAULT nextval('all_id_seq'),
  title        VARCHAR(1000) NOT NULL,
  description  TEXT          NOT NULL,
  created_date TIMESTAMP     NOT NULL,
  start_date   TIMESTAMP     NOT NULL,
  ending_date  TIMESTAMP     NOT NULL,
  status_id    INTEGER,
  priority_id  INTEGER,
  task_type_id INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (status_id) REFERENCES Status (id),
  FOREIGN KEY (priority_id) REFERENCES Priority_level (id),
  FOREIGN KEY (task_type_id) REFERENCES Task_type (id),
  UNIQUE (title),
  CHECK (created_date <= Task.start_date),
  CHECK (ending_date > Task.start_date)
);

CREATE TABLE Project_tasks (
  project_id INTEGER,
  task_id    INTEGER,
  FOREIGN KEY (project_id) REFERENCES Projects (id),
  FOREIGN KEY (task_id) REFERENCES Task (id)
);

CREATE TABLE Task_comments (
  task_id    INTEGER,
  comment_id INTEGER,
  FOREIGN KEY (task_id) REFERENCES Task (id),
  FOREIGN KEY (comment_id) REFERENCES Comments (id)
);

-- INSERT TEST DATA INTO DATABASE BLOCK --
INSERT INTO Role (access_level, title) VALUES (0, 'God');
INSERT INTO Role (access_level, title) VALUES (1, 'Admin');
INSERT INTO Role (access_level, title) VALUES (2, 'Moderator');
INSERT INTO Role (access_level, title) VALUES (8, 'User');
INSERT INTO Role (access_level, title) VALUES (9, 'Guest');

INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Kirill', 'Ogoltsov', (SELECT R.id
                         FROM Role R
                         WHERE R.access_level = 0)
);
INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Aizada', 'Zeken', (SELECT R.id
                      FROM Role R
                      WHERE R.access_level = 1)
);
INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Evgeniy', 'Frolov', (SELECT R.id
                        FROM Role R
                        WHERE R.access_level = 1)
);
INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Ildar', 'Salihov', (SELECT R.id
                       FROM Role R
                       WHERE R.access_level = 2)
);
INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Zarina', 'Zhienbaeva', (SELECT R.id
                           FROM Role R
                           WHERE R.access_level = 2)
);
INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Ilya', 'Bondarenko', (SELECT R.id
                         FROM Role R
                         WHERE R.access_level = 8)
);
INSERT INTO Users (firstname, lastname, role_id) VALUES (
  'Xeniya', 'Petrenko', (SELECT R.id
                         FROM Role R
                         WHERE R.access_level = 9)
);

INSERT INTO Task_type (title, description) VALUES (
  'Developing', 'Some description'
);
INSERT INTO task_type (title, description) VALUES (
  'Testing', 'Some description'
);
INSERT INTO Task_type (title, description) VALUES (
  'Checking', 'Some description'
);
INSERT INTO Task_type (title, description) VALUES (
  'UI.....', 'Some description'
);
INSERT INTO Task_type (title, description) VALUES (
  'Holy....', 'Some description'
);

INSERT INTO Status (title, description) VALUES (
  'TODO', 'Some description'
);
INSERT INTO Status (title, description) VALUES (
  'in Progress', 'Some description'
);
INSERT INTO Status (title, description) VALUES (
  'Testing', 'Some description'
);
INSERT INTO Status (title, description) VALUES (
  'Done', 'Some description'
);

INSERT INTO Priority_level (title, level) VALUES (
  'Hard', 0
);
INSERT INTO Priority_level (title, level) VALUES (
  'Normal', 1
);
INSERT INTO Priority_level (title, level) VALUES (
  'Soft', 2
);
INSERT INTO Priority_level (title, level) VALUES (
  'Relax', 3
);

CREATE OR REPLACE FUNCTION create_comments()
  RETURNS VOID AS $$
DECLARE
  counter INTEGER := 0;
BEGIN
  LOOP
    INSERT INTO Comments (message, user_id, date) VALUES (
      (
        SELECT CONCAT(
            'Some comment message from ',
            (
              SELECT U.firstname
              FROM Users U
              OFFSET floor(random() * 2)
              LIMIT 1
            )
        )
      ),
      (
        SELECT U.id
        FROM Users U
        OFFSET floor(random() * 2)
        LIMIT 1
      ),
      (
        SELECT TIMESTAMP '2016-01-01 00:00:01' +
               random() * (TIMESTAMP '2016-12-31 23:59:00' -
                           TIMESTAMP '2016-01-01 00:00:01')
      )
    );

    counter :=counter + 1;
    EXIT WHEN counter = 15;
  END LOOP;
END;
$$ LANGUAGE plpgsql;

SELECT create_comments();

INSERT INTO Projects (title, description, start_project_date, ending_project_date) VALUES (
  'First project', 'It is your first  project, enjoy:))',
  (SELECT TIMESTAMP '2016-01-01 00:00:01' +
          random() * (TIMESTAMP '2016-12-31 23:59:00' -
                      TIMESTAMP '2016-01-01 00:00:01')),
  (SELECT TIMESTAMP '2016-06-01 00:00:01' +
          random() * (TIMESTAMP '2016-12-31 23:59:00' -
                      TIMESTAMP '2016-01-01 00:00:01'))
);
-- BUG, LOOK PMT-9
-- CREATE OR REPLACE FUNCTION create_tasks()
--   RETURNS VOID AS $$
-- DECLARE
--   counter INTEGER := 0;
-- BEGIN
--   LOOP
--     INSERT INTO Task (title, description, created_date, start_date, ending_date, status_id, priority_id, task_type_id)
--     VALUES (
--       (concat('Some task title', counter)),
--       (concat('Some task description', counter)),
--       (
--         SELECT TIMESTAMP '2016-01-01 00:00:01' +
--                random() * (TIMESTAMP '2016-12-31 23:59:00' -
--                            TIMESTAMP '2016-10-01 00:00:01')
--       ),
--       (
--         SELECT TIMESTAMP '2016-06-03 00:00:01' +
--                random() * (TIMESTAMP '2016-12-31 23:59:00' -
--                            TIMESTAMP '2016-06-01 00:00:01')
--       ),
--       (SELECT TIMESTAMP '2016-06-01 00:00:01' +
--               random() * (TIMESTAMP '2016-12-31 23:59:00' -
--                           TIMESTAMP '2016-01-01 00:00:01')),
--       (
--         SELECT S.id
--         FROM Status S
--         OFFSET floor(random() * 2)
--         LIMIT 1
--       ),
--       (
--         SELECT P.id
--         FROM Priority_level P
--         OFFSET floor(random() * 2)
--         LIMIT 1
--       ),
--       (
--         SELECT TP.id
--         FROM Task_type TP
--         ORDER BY random()
--         LIMIT 1
--       )
--     );
--     EXIT WHEN counter = 25;
--   END LOOP;
-- END;
-- $$ LANGUAGE plpgsql;

-- SELECT create_tasks();

-- MAKE FEW TIMES: BEGIN
INSERT INTO Project_comments (project_id, comment_id) VALUES
  (
    (
      SELECT P.id
      FROM Projects P
      OFFSET floor(random() * 2)
      LIMIT 1
    ),
    (
      SELECT C.id
      FROM Comments C
      OFFSET floor(random() * 10)
      LIMIT 1
    )
  );

INSERT INTO Task_comments (task_id, comment_id) VALUES
  (
    (
      SELECT T.id
      FROM Task T
      OFFSET floor(random() * 2)
      LIMIT 1
    ),
    (
      SELECT C.id
      FROM Comments C
      OFFSET floor(random() * 10)
      LIMIT 1
    )
  );
-- END



