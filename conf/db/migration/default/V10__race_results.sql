-- Classification Types

CREATE TABLE classification_types (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX classification_types_idx_name ON venue_types(name);

INSERT INTO classification_types(name, created_at, updated_at) VALUES ('LM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('M', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('MK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('I', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('II', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('III', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('I j.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('II j.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO classification_types(name, created_at, updated_at) VALUES ('III j.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Age Groups

INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-06', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-40', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-50', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-60', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-70', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('V-75', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-06', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-40', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-50', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-60', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-70', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO age_groups (name, created_at, updated_at) VALUES ('S-75', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Race Results

CREATE TABLE race_results (
  id SERIAL NOT NULL,
  race_id BIGINT NOT NULL REFERENCES races(id),
  race_distance_id BIGINT NOT NULL REFERENCES race_distances(id),
  time BIGINT,
  distance DECIMAL(10,3),
  points SMALLINT,
  rank SMALLINT,
  rank_text VARCHAR(255),
  age_group_id BIGINT REFERENCES age_groups(id),
  classification_type_id BIGINT REFERENCES classification_types(id),
  wind DECIMAL(3,3),
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_by INTEGER,
  PRIMARY KEY (id)
);

CREATE INDEX race_results_idx_race_id ON race_results(race_id);
CREATE INDEX race_results_idx_race_distance_id ON race_results(race_distance_id);
CREATE INDEX race_results_idx_age_group_id ON race_results(age_group_id);
CREATE INDEX race_results_idx_classification_type_id ON race_results(classification_type_id);

-- Table refactoring

alter table age_groups drop column created_at;
alter table classification_types drop column created_at;
alter table clubs drop column created_at;
alter table discipline_types drop column created_at;
alter table distance_types drop column created_at;
alter table persons drop column created_at;
alter table persons_clubs drop column created_at;
alter table persons_coaches drop column created_at;
alter table race_distances drop column created_at;
alter table race_results drop column created_at;
alter table races drop column created_at;
alter table venue_types drop column created_at;

alter table age_groups alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table classification_types alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table clubs alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table discipline_types alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table distance_types alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table persons alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table persons_clubs alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table persons_coaches alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table race_distances alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table race_results alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table races alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;
alter table venue_types alter column updated_at SET DEFAULT CURRENT_TIMESTAMP;

alter table age_groups alter column updated_at DROP NOT NULL;
alter table classification_types alter column updated_at DROP NOT NULL;
alter table clubs alter column updated_at DROP NOT NULL;
alter table discipline_types alter column updated_at DROP NOT NULL;
alter table distance_types alter column updated_at DROP NOT NULL;
alter table persons alter column updated_at DROP NOT NULL;
alter table persons_clubs alter column updated_at DROP NOT NULL;
alter table persons_coaches alter column updated_at DROP NOT NULL;
alter table race_distances alter column updated_at DROP NOT NULL;
alter table race_results alter column updated_at DROP NOT NULL;
alter table races alter column updated_at DROP NOT NULL;
alter table venue_types alter column updated_at DROP NOT NULL;



