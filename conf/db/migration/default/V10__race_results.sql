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

INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60m', '60m', 0.06, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('100m', '100m', 0.10, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('200m', '200m', 0.20, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('300m', '300m', 0.30, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('400m', '400m', 0.40, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('500m', '500m', 0.50, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('600m', '600m', 0.60, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('800m', '800m', 0.80, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1000m', '1000m', 1.00, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1500m', '1500m', 1.50, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2000m', '2000m', 2.00, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3000m', '3000m', 3.00, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5000m', '5000m', 5.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10000m', '10000m', 10.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60m (106,7cm)', '60m (106,7cm)', 0.06, null, 6, true, 106.700);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('100m (84,0cm)', '100m (84,0cm)', 0.10, null, 6, true, 84.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('100m (76,2cm)', '100m (76,2cm)', 0.10, null, 6, true, 76.200);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('110m (106,7cm)', '110m (106,7cm)', 0.11, null, 6, true, 106.700);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('110m (100,0cm)', '110m (100,0cm)', 0.11, null, 6, true, 100.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('110m (91,4cm)', '110m (91,4cm)', 0.11, null, 6, true, 91.400);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('110m (84,0cm)', '110m (84,0cm)', 0.11, null, 6, true, 84.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('110m (76,2cm)', '110m (76,2cm)', 0.11, null, 6, true, 76.200);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('300m (76,2cm)', '300m (76,2cm)', 0.30, null, 6, true, 76.200);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('400m (91,4cm)', '400m (91,4cm)', 0.40, null, 6, true, 91.400);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('400m (76,2cm)', '400m (76,2cm)', null, null, 6, true, 76.200);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1500m hurdles', '1500m hurdles', 1.50, null, 6, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2000m hurdles', '2000m hurdles', 2.00, null, 6, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3000m hurdles', '3000m hurdles', 3.00, null, 6, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1km', '1km', 1.00, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.5km', '1.5km', 1.50, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2km', '2km', 2.00, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.5km', '2.5km', 2.50, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3km', '3km', 3.00, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.5km', '3.5km', 3.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4km', '4km', 4.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.5km', '4.5km', 4.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5km', '5km', 5.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.5km', '5.5km', 5.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6km', '6km', 6.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6.5km', '6.5km', 6.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7km', '7km', 7.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.5km', '7.5km', 7.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8km', '8km', 8.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.5km', '8.5km', 8.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9km', '9km', 9.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.5km', '9.5km', 9.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10km', '10km', 10.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.5km', '10.5km', 10.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('11km', '11km', 11.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('11.5km', '11.5km', 11.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('12km', '12km', 12.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('12.5km', '12.5km', 12.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('13km', '13km', 13.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('13.5km', '13.5km', 13.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('14km', '14km', 14.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('14.5km', '14.5km', 14.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('15km', '15km', 15.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('15.5km', '15.5km', 15.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('16km', '16km', 16.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('16.5km', '16.5km', 16.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('17km', '17km', 17.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('17.5km', '17.5km', 17.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('18km', '18km', 18.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('18.5km', '18.5km', 18.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('19km', '19km', 19.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('19.5km', '19.5km', 19.50, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('20km', '20km', 20.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('25km', '25km', 25.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('30km', '30km', 30.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('35km', '35km', 35.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('40km', '40km', 40.00, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('50km', '50km', 50.00, null, 4, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1000m soļošana', '1000m soļošana', 1.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2000m soļošana', '2000m soļošana', 2.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3000m soļošana', '3000m soļošana', 3.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5000m soļošana', '5000m soļošana', 5.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10km soļošana', '10km soļošana', 10.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('20km soļošana', '20km soļošana', 20.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('30km soļošana', '30km soļošana', 30.00, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('50km soļošana', '50km soļošana', null, null, 7, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4*100m stafete', '4*100m stafete', 0.40, null, 8, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4*200m stafete', '4*200m stafete', 0.80, null, 8, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4*400m stafete', '4*400m stafete', 1.60, null, 8, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('400+300+200+100m stafete', '400+300+200+100m stafete', 1.00, null, 8, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pusmaratons', 'pusmaratons 21,098km', 21.10, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('stundas skrējiens', 'stundas skrējiens', null, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('maratons', 'maratons 42,195km', null, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pusdiennakts skrējiens', 'pusdiennakts skrējiens', null, null, 4, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pusjūdze', 'pusjūdze', 0.80, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2 jūdzes', '2 jūdzes', 3.22, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pusstundas skrējiens', 'pusstundas skrējiens', null, null, 3, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1 jūdze', '1 jūdze', 1.61, null, 2, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('diennakts skrējiens', 'diennakts skrējiens', null, null, 4, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6 stundu skrējiens', '6 stundu skrejiens', null, null, 4, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('50m', '50m', 0.05, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('100km', '100km', 100.00, null, 4, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('astoņcīņa jauniešiem', 'astoņcīņa jauniešiem', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('astoņcīņa zēniem', 'astoņcīņa zēniem', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('augstlēkšana', 'augstlēkšana', null, null, 5, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('tāllēkšana', 'tāllēkšana', null, null, 5, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('trīssoļlēkšana', 'trīssoļlēkšana', null, null, 5, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('kārtslēkšana', 'kārtslēkšana', null, null, 5, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('disks (0,75kg)', 'disks (0,75kg)', null, 0.750, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('disks (1,5kg)', 'disks (1,5kg)', null, 1.500, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('disks (1,75kg)', 'disks (1,75kg)', null, 1.750, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('disks (1kg)', 'disks (1kg)', null, 1.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('disks (2kg)', 'disks (2kg)', null, 2.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('lode (2kg)', 'lode (2kg)', null, 2.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('lode (3kg)', 'lode (3kg)', null, 3.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('lode (4kg)', 'lode (4kg)', null, 4.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('lode (5kg)', 'lode (5kg)', null, 5.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('lode (6kg)', 'lode (6kg)', null, 6.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('lode (7,257kg)', 'lode (7,257kg)', null, 7.257, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('šķēps (400g)', 'šķēps (400g)', null, 0.400, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('šķēps (500g)', 'šķēps (500g)', null, 0.500, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('šķēps (600g)', 'šķēps (600g)', null, 0.600, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('šķēps (700g)', 'šķēps (700g)', null, 0.700, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('šķēps (800g)', 'šķēps (800g)', null, 0.800, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('veseris (3kg)', 'veseris (3kg)', null, 3.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('veseris (4kg)', 'veseris (4kg)', null, 4.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('veseris (5kg)', 'veseris (5kg)', null, 5.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('veseris (6kg)', 'veseris (6kg)', null, 6.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('veseris (7,257kg)', 'veseris (7,257kg)', null, 7.257, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pieccīņa', 'pieccīņa', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pieccīņa (u-16)', 'pieccīņa (u-16)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('pieccīņa (u-18)', 'pieccīņa (u-18)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('septiņcīņa', 'septiņcīņa', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('septiņcīņa (u-16)', 'septiņcīņa (u-16)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('septiņcīņa (u-18)', 'septiņcīņa (u-18)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('septiņcīņa (u-20,u-18)', 'septiņcīņa (u-20,u-18)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('desmitcīņa', 'desmitcīņa', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('desmitcīņa (u-18)', 'desmitcīņa (u-18)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('desmitcīņa (u-20)', 'desmitcīņa (u-20)', null, null, 10, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('80m (84,0cm)', '80m (84,0cm)', null, null, 6, true, 84.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('80m (76,2cm)', '80m (76,2cm)', null, null, 6, true, 76.200);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('400m (84,0cm)', '400m (84,0cm)', null, null, 6, true, 84.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('veseris (2kg)', 'veseris (2kg)', null, 2.000, 9, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60m (100,0cm)', '60m (100,0cm)', null, null, 6, true, 100.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60m (91,4cm)', '60m (91,4cm)', null, null, 6, true, 91.400);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60m (84,0cm)', '60m (84,0cm)', null, null, 6, true, 84.000);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60m (76,2cm)', '60m (76,2cm)', null, null, 6, true, 76.200);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('150m', '150m', 0.15, null, 1, true, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('.45km', '.45km', 0.45, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('.7km', '.7km', 0.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.02km', '1.02km', 1.02, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.07km', '1.07km', 1.07, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.1km', '1.1km', 1.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.2km', '1.2km', 1.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.3km', '1.3km', 1.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.4km', '1.4km', 1.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.53km', '1.53km', 1.53, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.6km', '1.6km', 1.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.7km', '1.7km', 1.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.8km', '1.8km', 1.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('1.9km', '1.9km', 1.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.04km', '2.04km', 2.04, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.1km', '2.1km', 2.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.2km', '2.2km', 2.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.25km', '2.25km', 2.25, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.3km', '2.3km', 2.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.4km', '2.4km', 2.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.6km', '2.6km', 2.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.68km', '2.68km', 2.68, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.7km', '2.7km', 2.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.8km', '2.8km', 2.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('2.9km', '2.9km', 2.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.06km', '3.06km', 3.06, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.1km', '3.1km', 3.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.2km', '3.2km', 3.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.21km', '3.21km', 3.21, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.22km', '3.22km', 3.22, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.3km', '3.3km', 3.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.4km', '3.4km', 3.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.6km', '3.6km', 3.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.7km', '3.7km', 3.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.8km', '3.8km', 3.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('3.9km', '3.9km', 3.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.1km', '4.1km', 4.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.2km', '4.2km', 4.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.3km', '4.3km', 4.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.4km', '4.4km', 4.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.52km', '4.52km', 4.52, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.59km', '4.59km', 4.59, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.6km', '4.6km', 4.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.7km', '4.7km', 4.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('4.8km', '4.8km', 4.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.1km', '5.1km', 5.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.2km', '5.2km', 5.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.25km', '5.25km', 5.25, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.3km', '5.3km', 5.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.35km', '5.35km', 5.35, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.36km', '5.36km', 5.36, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.4km', '5.4km', 5.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.6km', '5.6km', 5.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.7km', '5.7km', 5.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('5.8km', '5.8km', 5.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6.2km', '6.2km', 6.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6.3km', '6.3km', 6.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6.4km', '6.4km', 6.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6.6km', '6.6km', 6.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('6.8km', '6.8km', 6.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.1km', '7.1km', 7.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.2km', '7.2km', 7.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.34km', '7.34km', 7.34, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.4km', '7.4km', 7.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.6km', '7.6km', 7.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('7.8km', '7.8km', 7.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.04km', '8.04km', 8.04, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.1km', '8.1km', 8.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.2km', '8.2km', 8.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.4km', '8.4km', 8.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.45km', '8.45km', 8.45, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.6km', '8.6km', 8.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('8.8km', '8.8km', 8.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.2km', '9.2km', 9.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.4km', '9.4km', 9.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.6km', '9.6km', 9.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.7km', '9.7km', 9.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.8km', '9.8km', 9.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('9.9km', '9.9km', 9.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.2km', '10.2km', 10.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.3km', '10.3km', 10.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.4km', '10.4km', 10.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.54km', '10.54km', 10.54, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.55km', '10.55km', 10.55, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.7km', '10.7km', 10.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('10.8km', '10.8km', 10.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('11.2km', '11.2km', 11.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('11.3km', '11.3km', 11.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('11.4km', '11.4km', 11.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('11.7km', '11.7km', 11.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('12.6km', '12.6km', 12.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('12.8km', '12.8km', 12.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('13.2km', '13.2km', 13.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('13.6km', '13.6km', 13.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('13.7km', '13.7km', 13.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('13.9km', '13.9km', 13.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('14.1km', '14.1km', 14.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('14.8km', '14.8km', 14.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('15.4km', '15.4km', 15.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('16.1km', '16.1km', 16.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('16.14km', '16.14km', 16.14, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('16.2km', '16.2km', 16.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('17.3km', '17.3km', 17.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('20.3km', '20.3km', 20.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('21km', '21km', 21.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('22km', '22km', 22.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('23km', '23km', 23.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('23.4km', '23.4km', 23.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('24km', '24km', 24.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('26km', '26km', 26.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('28km', '28km', 28.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('33km', '33km', 33.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('33.9km', '33.9km', 33.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('34km', '34km', 34.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('34.2km', '34.2km', 34.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('36km', '36km', 36.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('40.08km', '40.08km', 40.08, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('42.2km', '42.2km', 42.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('43km', '43km', 43.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('52km', '52km', 52.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('53km', '53km', 53.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('53.7km', '53.7km', 53.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('54km', '54km', 54.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('54.5km', '54.5km', 54.50, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('55km', '55km', 55.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('60km', '60km', 60.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('63.3km', '63.3km', 63.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('73.33km', '73.33km', 73.33, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('78.5km', '78.5km', 78.50, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('80km', '80km', 80.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('82km', '82km', 82.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('84.4km', '84.4km', 84.40, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('86km', '86km', 86.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('101km', '101km', 101.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('105.5km', '105.5km', 105.50, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('107km', '107km', 107.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('110.2km', '110.2km', 110.20, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('117km', '117km', 117.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('119.1km', '119.1km', 119.10, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('126.6km', '126.6km', 126.60, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('131.3km', '131.3km', 131.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('147.7km', '147.7km', 147.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('167.7km', '167.7km', 167.70, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('168km', '168km', 168.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('168.8km', '168.8km', 168.80, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('189.9km', '189.9km', 189.90, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('211km', '211km', 211.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('245.3km', '245.3km', 245.30, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('295km', '295km', 295.00, null, null, false, null);
INSERT INTO distance_types (name, skriesim_name, distance, weight, discipline_type_id, is_standard, height) VALUES ('300km', '300km', 300.00, null, null, false, null);

ALTER TABLE race_distances ADD COLUMN venue_type_id BIGINT REFERENCES venue_types(id);
ALTER TABLE race_distances ADD COLUMN with_qualification BOOLEAN;
ALTER TABLE race_distances DROP COLUMN name;


