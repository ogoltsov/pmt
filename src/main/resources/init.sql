DROP TABLE IF EXISTS Project_comments;
DROP TABLE IF EXISTS Task_type;
DROP TABLE IF EXISTS Project_tasks;
DROP TABLE IF EXISTS Task_comments;
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Priority_level;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Status;

CREATE TABLE Projects (
  id                  SERIAL8,
  title               VARCHAR(500) NOT NULL,
  description         TEXT,
  start_project_date  TIMESTAMPTZ  NOT NULL,
  ending_project_date TIMESTAMPTZ  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title)
);

CREATE TABLE Role (
  id           SERIAL8,
  access_level INTEGER,
  title        VARCHAR(100),
  PRIMARY KEY (id),
  CHECK (access_level >= 0),
  UNIQUE (access_level)
);

CREATE TABLE Users (
  id        SERIAL8,
  firstname VARCHAR(150) NOT NULL,
  lastname  VARCHAR(150) NOT NULL,
  role_id   INTEGER,
  image     BYTEA DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES Role (id)
);

CREATE TABLE Comments (
  id      SERIAL8,
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
  id          SERIAL8,
  title       VARCHAR(150) NOT NULL,
  description TEXT         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title),
  CHECK (length(description) > 10),
  CHECK (length(title) > 10)
);

CREATE TABLE Status (
  id          SERIAL8,
  title       VARCHAR(250) NOT NULL,
  description TEXT         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title),
  CHECK (length(description) > 10)
);

CREATE TABLE Priority_level (
  id    SERIAL8,
  title VARCHAR(250) NOT NULL,
  level INTEGER      NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title, level),
  CHECK (level >= 0)
);

CREATE TABLE Task (
  id           SERIAL8,
  title        VARCHAR(1000) NOT NULL,
  description  TEXT          NOT NULL,
  created_date TIMESTAMP     NOT NULL,
  start_date   TIMESTAMP     NOT NULL,
  ending_date  TIMESTAMP     NOT NULL,
  status_id    INTEGER,
  priority_id  INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (status_id) REFERENCES Status (id),
  FOREIGN KEY (priority_id) REFERENCES Priority_level (id),
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