DROP TABLE countries;

CREATE TABLE countries(
    id SERIAL NOT NULL,
    code CHAR(2) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE UNIQUE INDEX countries_idx_code ON countries(code);

INSERT INTO COUNTRIES (CODE,NAME) VALUES('AD', 'Andorra');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AE', 'United Arab Emirates');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AF', 'Afghanistan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AG', 'Antigua and Barbuda');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AI', 'Anguilla');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AL', 'Albania');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AM', 'Armenia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AN', 'Netherlands Antilles');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AO', 'Angola');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AQ', 'Antarctica');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AR', 'Argentina');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AS', 'American Samoa');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AT', 'Austria');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AU', 'Australia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AW', 'Aruba');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AX', 'Åland Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('AZ', 'Azerbaijan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BA', 'Bosnia and Herzegovina');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BB', 'Barbados');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BD', 'Bangladesh');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BE', 'Belgium');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BF', 'Burkina Faso');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BG', 'Bulgaria');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BH', 'Bahrain');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BI', 'Burundi');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BJ', 'Benin');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BL', 'Saint Barthélemy');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BM', 'Bermuda');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BN', 'Brunei');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BO', 'Bolivia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BQ', 'British Antarctic Territory');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BR', 'Brazil');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BS', 'Bahamas');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BT', 'Bhutan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BV', 'Bouvet Island');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BW', 'Botswana');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BY', 'Belarus');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('BZ', 'Belize');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CA', 'Canada');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CC', 'Cocos [Keeling] Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CD', 'Congo - Kinshasa');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CF', 'Central African Republic');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CG', 'Congo - Brazzaville');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CH', 'Switzerland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CI', 'Côte d’Ivoire');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CK', 'Cook Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CL', 'Chile');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CM', 'Cameroon');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CN', 'China');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CO', 'Colombia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CR', 'Costa Rica');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CS', 'Serbia and Montenegro');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CT', 'Canton and Enderbury Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CU', 'Cuba');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CV', 'Cape Verde');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CX', 'Christmas Island');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CY', 'Cyprus');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('CZ', 'Czech Republic');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DD', 'East Germany');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DE', 'Germany');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DJ', 'Djibouti');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DK', 'Denmark');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DM', 'Dominica');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DO', 'Dominican Republic');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('DZ', 'Algeria');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('EC', 'Ecuador');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('EE', 'Estonia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('EG', 'Egypt');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('EH', 'Western Sahara');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ER', 'Eritrea');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ES', 'Spain');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ET', 'Ethiopia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FI', 'Finland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FJ', 'Fiji');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FK', 'Falkland Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FM', 'Micronesia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FO', 'Faroe Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FQ', 'French Southern and Antarctic Territories');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FR', 'France');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('FX', 'Metropolitan France');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GA', 'Gabon');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GB', 'United Kingdom');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GD', 'Grenada');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GE', 'Georgia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GF', 'French Guiana');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GG', 'Guernsey');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GH', 'Ghana');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GI', 'Gibraltar');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GL', 'Greenland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GM', 'Gambia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GN', 'Guinea');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GP', 'Guadeloupe');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GQ', 'Equatorial Guinea');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GR', 'Greece');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GS', 'South Georgia and the South Sandwich Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GT', 'Guatemala');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GU', 'Guam');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GW', 'Guinea-Bissau');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('GY', 'Guyana');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('HK', 'Hong Kong SAR China');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('HM', 'Heard Island and McDonald Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('HN', 'Honduras');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('HR', 'Croatia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('HT', 'Haiti');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('HU', 'Hungary');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ID', 'Indonesia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IE', 'Ireland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IL', 'Israel');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IM', 'Isle of Man');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IN', 'India');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IO', 'British Indian Ocean Territory');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IQ', 'Iraq');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IR', 'Iran');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IS', 'Iceland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('IT', 'Italy');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('JE', 'Jersey');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('JM', 'Jamaica');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('JO', 'Jordan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('JP', 'Japan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('JT', 'Johnston Island');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KE', 'Kenya');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KG', 'Kyrgyzstan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KH', 'Cambodia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KI', 'Kiribati');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KM', 'Comoros');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KN', 'Saint Kitts and Nevis');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KP', 'North Korea');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KR', 'South Korea');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KW', 'Kuwait');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KY', 'Cayman Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('KZ', 'Kazakhstan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LA', 'Laos');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LB', 'Lebanon');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LC', 'Saint Lucia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LI', 'Liechtenstein');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LK', 'Sri Lanka');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LR', 'Liberia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LS', 'Lesotho');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LT', 'Lithuania');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LU', 'Luxembourg');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LV', 'Latvia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('LY', 'Libya');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MA', 'Morocco');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MC', 'Monaco');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MD', 'Moldova');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ME', 'Montenegro');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MF', 'Saint Martin');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MG', 'Madagascar');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MH', 'Marshall Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MI', 'Midway Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MK', 'Macedonia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ML', 'Mali');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MM', 'Myanmar [Burma]');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MN', 'Mongolia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MO', 'Macau SAR China');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MP', 'Northern Mariana Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MQ', 'Martinique');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MR', 'Mauritania');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MS', 'Montserrat');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MT', 'Malta');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MU', 'Mauritius');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MV', 'Maldives');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MW', 'Malawi');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MX', 'Mexico');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MY', 'Malaysia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('MZ', 'Mozambique');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NA', 'Namibia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NC', 'New Caledonia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NE', 'Niger');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NF', 'Norfolk Island');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NG', 'Nigeria');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NI', 'Nicaragua');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NL', 'Netherlands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NO', 'Norway');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NP', 'Nepal');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NQ', 'Dronning Maud Land');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NR', 'Nauru');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NT', 'Neutral Zone');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NU', 'Niue');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('NZ', 'New Zealand');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('OM', 'Oman');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PA', 'Panama');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PC', 'Pacific Islands Trust Territory');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PE', 'Peru');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PF', 'French Polynesia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PG', 'Papua New Guinea');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PH', 'Philippines');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PK', 'Pakistan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PL', 'Poland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PM', 'Saint Pierre and Miquelon');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PN', 'Pitcairn Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PR', 'Puerto Rico');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PS', 'Palestinian Territories');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PT', 'Portugal');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PU', 'U.S. Miscellaneous Pacific Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PW', 'Palau');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PY', 'Paraguay');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('PZ', 'Panama Canal Zone');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('QA', 'Qatar');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('QO', 'Outlying Oceania');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('QU', 'European Union');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('RE', 'Réunion');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('RO', 'Romania');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('RS', 'Serbia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('RU', 'Russia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('RW', 'Rwanda');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SA', 'Saudi Arabia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SB', 'Solomon Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SC', 'Seychelles');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SD', 'Sudan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SE', 'Sweden');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SG', 'Singapore');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SH', 'Saint Helena');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SI', 'Slovenia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SJ', 'Svalbard and Jan Mayen');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SK', 'Slovakia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SL', 'Sierra Leone');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SM', 'San Marino');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SN', 'Senegal');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SO', 'Somalia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SR', 'Suriname');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ST', 'São Tomé and Príncipe');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SU', 'Union of Soviet Socialist Republics');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SV', 'El Salvador');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SY', 'Syria');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('SZ', 'Swaziland');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TC', 'Turks and Caicos Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TD', 'Chad');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TF', 'French Southern Territories');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TG', 'Togo');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TH', 'Thailand');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TJ', 'Tajikistan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TK', 'Tokelau');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TL', 'Timor-Leste');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TM', 'Turkmenistan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TN', 'Tunisia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TO', 'Tonga');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TR', 'Turkey');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TT', 'Trinidad and Tobago');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TV', 'Tuvalu');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TW', 'Taiwan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('TZ', 'Tanzania');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('UA', 'Ukraine');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('UG', 'Uganda');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('UM', 'U.S. Minor Outlying Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('US', 'United States');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('UY', 'Uruguay');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('UZ', 'Uzbekistan');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VA', 'Vatican City');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VC', 'Saint Vincent and the Grenadines');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VD', 'North Vietnam');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VE', 'Venezuela');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VG', 'British Virgin Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VI', 'U.S. Virgin Islands');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VN', 'Vietnam');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('VU', 'Vanuatu');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('WF', 'Wallis and Futuna');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('WK', 'Wake Island');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('WS', 'Samoa');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('YD', 'People''s Democratic Republic of Yemen');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('YE', 'Yemen');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('YT', 'Mayotte');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ZA', 'South Africa');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ZM', 'Zambia');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ZW', 'Zimbabwe');
INSERT INTO COUNTRIES (CODE,NAME) VALUES('ZZ', 'Unknown or Invalid Region');


CREATE TABLE persons (
    id SERIAL NOT NULL,
    given_name VARCHAR(200) NOT NULL,
    family_name VARCHAR(200) NOT NULL,
    date_of_birth DATE,
    year_of_birth SMALLINT,
    sex CHAR(1) NOT NULL,
    country_id INTEGER NOT NULL REFERENCES countries(id),
    skriesim_id INTEGER,
    sportlat_id INTEGER,
    noskrien_id INTEGER,
    is_coach BOOLEAN,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by INTEGER,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX persons_idx_skriesim_id ON persons(skriesim_id);
CREATE UNIQUE INDEX persons_idx_sportlat_id ON persons(sportlat_id);
CREATE UNIQUE INDEX persons_idx_noskrien_id ON persons(noskrien_id);
CREATE INDEX persons_idx_country_id ON persons(country_id);
CREATE INDEX persons_idx_sex ON persons(sex);
CREATE INDEX persons_idx_is_coach ON persons(is_coach);
CREATE INDEX persons_idx_given_name ON persons(given_name);
CREATE INDEX persons_idx_family_name ON persons(family_name);