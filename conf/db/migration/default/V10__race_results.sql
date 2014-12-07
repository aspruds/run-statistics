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

-- Changes in Distance Types

alter table distance_types alter column weight type numeric(10,3);
alter table distance_types add column height numeric(10,3);
alter table distance_types drop column venue_type_id;

INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('60m', '60m', null, 0.06, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('100m', '100m', null, 0.10, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('200m', '200m', null, 0.20, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('300m', '300m', null, 0.30, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('400m', '400m', null, 0.40, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('500m', '500m', null, 0.50, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('600m', '600m', null, 0.60, null, 1, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('800m', '800m', null, 0.80, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1000m', '1000m', null, 1.00, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1500m', '1500m', null, 1.50, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('2000m', '2000m', null, 2.00, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('3000m', '3000m', null, 3.00, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('5000m', '5000m', null, 5.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('10000m', '10000m', null, 10.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('half-mile', 'half-mile', null, 0.80, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1mile', '1mile', null, 1.61, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('2mile', '2mile', null, 3.22, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('60m (106,7cm)', '60m (106,7cm)', 106.700, 0.06, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('100m (84,0cm)', '100m (84,0cm)', 84.000, 0.10, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('100m (76,2cm)', '100m (76,2cm)', 76.200, 0.10, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('110m (106,7cm)', '110m (106,7cm)', 106.700, 0.11, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('110m (100,0cm)', '110m (100,0cm)', 100.000, 0.11, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('110m (91,4cm)', '110m (91,4cm)', 91.400, 0.11, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('110m (84,0cm)', '110m (84,0cm)', 84.000, 0.11, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('110m (76,2cm)', '110m (76,2cm)', 76.200, 0.11, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('300m (76,2cm)', '300m (76,2cm)', 76.200, 0.30, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('400m (91,4cm)', '400m (91,4cm)', 91.400, 0.40, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('400m (76,2cm)', '400m (76,2cm)', 76.200, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1500m hurdles', '1500m hurdles', null, 1.50, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('2000m hurdles', '2000m hurdles', null, 2.00, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('3000m hurdles', '3000m hurdles', null, 3.00, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1km', '1km', null, 1.00, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1.5km', '1.5km', null, 1.50, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('2km', '2km', null, 2.00, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('2.5km', '2.5km', null, 2.50, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('3km', '3km', null, 3.00, null, 2, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('3.5km', '3.5km', null, 3.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('4km', '4km', null, 4.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('4.5km', '4.5km', null, 4.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('5km', '5km', null, 5.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('5.5km', '5.5km', null, 5.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('6km', '6km', null, 6.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('6.5km', '6.5km', null, 6.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('7km', '7km', null, 7.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('7.5km', '7.5km', null, 7.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('8km', '8km', null, 8.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('8.5km', '8.5km', null, 8.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('9km', '9km', null, 9.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('9.5km', '9.5km', null, 9.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('10km', '10km', null, 10.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('10.5km', '10.5km', null, 10.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('11km', '11km', null, 11.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('11.5km', '11.5km', null, 11.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('12km', '12km', null, 12.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('12.5km', '12.5km', null, 12.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('13km', '13km', null, 13.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('13.5km', '13.5km', null, 13.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('14km', '14km', null, 14.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('14.5km', '14.5km', null, 14.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('15km', '15km', null, 15.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('15.5km', '15.5km', null, 15.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('16km', '16km', null, 16.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('16.5km', '16.5km', null, 16.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('17km', '17km', null, 17.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('17.5km', '17.5km', null, 17.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('18km', '18km', null, 18.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('18.5km', '18.5km', null, 18.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('19km', '19km', null, 19.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('19.5km', '19.5km', null, 19.50, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('20km', '20km', null, 20.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('25km', '25km', null, 25.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('30km', '30km', null, 30.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('35km', '35km', null, 35.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('40km', '40km', null, 40.00, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('50km', '50km', null, 50.00, null, 4, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('pusmaratons', 'pusmaratons', null, 21.10, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('marathon', 'marathon', null, null, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('halfhour', 'halfhour', null, null, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('hour', 'hour', null, null, null, 3, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('6hour', '6hour', null, null, null, 4, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('halfday', 'halfday', null, null, null, 4, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('day', 'day', null, null, null, 4, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('50m', '50m', null, 0.05, null, null, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('100km', '100km', null, 100.00, null, null, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('150m', '150m', null, 0.15, null, null, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('1000m soļošana', '1000m soļošana', null, 1.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('2000m soļošana', '2000m soļošana', null, 2.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('3000m soļošana', '3000m soļošana', null, 3.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('5000m soļošana', '5000m soļošana', null, 5.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('10km soļošana', '10km soļošana', null, 10.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('20km soļošana', '20km soļošana', null, 20.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('30km soļošana', '30km soļošana', null, 30.00, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('50km soļošana', '50km soļošana', null, null, null, 7, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('4*100m stafete', '4*100m stafete', null, 0.40, null, 8, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('4*200m stafete', '4*200m stafete', null, 0.80, null, 8, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('4*400m stafete', '4*400m stafete', null, 1.60, null, 8, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('400+300+200+100m stafete', '400+300+200+100m stafete', null, 1.00, null, 8, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('astoņcīņa jauniešiem', 'astoņcīņa jauniešiem', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('astoņcīņa zēniem', 'astoņcīņa zēniem', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('augstlēkšana', 'augstlēkšana', null, null, null, 5, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('tāllēkšana', 'tāllēkšana', null, null, null, 5, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('trīssoļlēkšana', 'trīssoļlēkšana', null, null, null, 5, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('kārtslēkšana', 'kārtslēkšana', null, null, null, 5, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('disks (0,75kg)', 'disks (0,75kg)', null, null, 0.750, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('disks (1,5kg)', 'disks (1,5kg)', null, null, 1.500, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('disks (1,75kg)', 'disks (1,75kg)', null, null, 1.750, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('disks (1kg)', 'disks (1kg)', null, null, 1.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('disks (2kg)', 'disks (2kg)', null, null, 2.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('lode (2kg)', 'lode (2kg)', null, null, 2.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('lode (3kg)', 'lode (3kg)', null, null, 3.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('lode (4kg)', 'lode (4kg)', null, null, 4.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('lode (5kg)', 'lode (5kg)', null, null, 5.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('lode (6kg)', 'lode (6kg)', null, null, 6.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('lode (7,257kg)', 'lode (7,257kg)', null, null, 7.257, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('šķēps (400g)', 'šķēps (400g)', null, null, 0.400, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('šķēps (500g)', 'šķēps (500g)', null, null, 0.500, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('šķēps (600g)', 'šķēps (600g)', null, null, 0.600, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('šķēps (700g)', 'šķēps (700g)', null, null, 0.700, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('šķēps (800g)', 'šķēps (800g)', null, null, 0.800, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('veseris (3kg)', 'veseris (3kg)', null, null, 3.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('veseris (4kg)', 'veseris (4kg)', null, null, 4.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('veseris (5kg)', 'veseris (5kg)', null, null, 5.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('veseris (6kg)', 'veseris (6kg)', null, null, 6.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('veseris (7,257kg)', 'veseris (7,257kg)', null, null, 7.257, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('pieccīņa', 'pieccīņa', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('pieccīņa (u-16)', 'pieccīņa (u-16)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('pieccīņa (u-18)', 'pieccīņa (u-18)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('septiņcīņa', 'septiņcīņa', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('septiņcīņa (u-16)', 'septiņcīņa (u-16)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('septiņcīņa (u-18)', 'septiņcīņa (u-18)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('septiņcīņa (u-20,u-18)', 'septiņcīņa (u-20,u-18)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('desmitcīņa', 'desmitcīņa', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('desmitcīņa (u-18)', 'desmitcīņa (u-18)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('desmitcīņa (u-20)', 'desmitcīņa (u-20)', null, null, null, 10, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('80m (84,0cm)', '80m (84,0cm)', 84.000, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('80m (76,2cm)', '80m (76,2cm)', 76.200, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('400m (84,0cm)', '400m (84,0cm)', 84.000, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('veseris (2kg)', 'veseris (2kg)', null, null, 2.000, 9, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('60m (100,0cm)', '60m (100,0cm)', 100.000, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('60m (91,4cm)', '60m (91,4cm)', 91.400, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('60m (84,0cm)', '60m (84,0cm)', 84.000, null, null, 6, true);
INSERT INTO distance_types (name, skriesim_name, height, distance, weight, discipline_type_id, is_standard) VALUES ('60m (76,2cm)', '60m (76,2cm)', 76.200, null, null, 6, true);


ALTER TABLE race_distances ADD COLUMN venue_type_id BIGINT REFERENCES venue_types(id);
ALTER TABLE race_distances ADD COLUMN with_qualification BOOLEAN;


