CREATE TABLE cars
(
    id    serial PRIMARY KEY,
    name      VARCHAR(30) UNIQUE NOT NULL,
    brand     VARCHAR(30) NOT NULL,
    carbody   VARCHAR(30) NOT NULL,
    petrol100 DOUBLE NOT NULL
);

insert into CARS values (0, 'Камри', 'Тойота', 'седан', 13.5);
insert into CARS values (1, 'Королла', 'Тойота', 'хэтчбек', 8.3);
insert into CARS values (2, 'Тиида', 'Ниссан', 'седан', 10.0);
insert into CARS values (3, 'Ларгус', 'Лада', 'универсал', 9.8);
insert into CARS values (4, '525', 'БМВ', 'седан', 15.5);

CREATE TABLE carsPrice
(
    car_id serial PRIMARY KEY,
    prise  INTEGER NOT NULL
);

insert into CARSPRICE values (0,2500000);
insert into CARSPRICE values (1,1500000);
insert into CARSPRICE values (2,1800000);
insert into CARSPRICE values (3,1000000);
insert into CARSPRICE values (4,4500000);

CREATE TABLE dictRusToEng
(
    nameRus VARCHAR(30) UNIQUE NOT NULL,
    nameEng VARCHAR(30) UNIQUE NOT NULL,
);

insert into DictRusToEng values ("Тойота", "Toyota");
insert into DictRusToEng values ("Ниссан", "Nissan");
insert into DictRusToEng values ("БМВ", "BMW");
insert into DictRusToEng values ("Лада", "LADA");
insert into DictRusToEng values ("седан", "sedan");
insert into DictRusToEng values ("хэтчбек", "hatchback");
insert into DictRusToEng values ("универсал", "universal");
insert into DictRusToEng values ("Камри", "Camry");
insert into DictRusToEng values ("Королла", "Corolla");
insert into DictRusToEng values ("Ларгус", "Largus");
insert into DictRusToEng values ("Тиида", "Tiida");
insert into DictRusToEng values ("525", "525");