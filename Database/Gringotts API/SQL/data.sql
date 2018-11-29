use id7910250_centralserver;

INSERT INTO branches ( branch_number, city)
VALUES ( '0001', 'Colombo');
INSERT INTO branches ( branch_number, city)
VALUES ( '0002', 'Moratuwa');
INSERT INTO branches ( branch_number, city)
VALUES ( '0003', 'Kurunegala');

INSERT INTO savingInterestRates VALUES ( 'children', 12.00, 0.00);
INSERT INTO savingInterestRates VALUES ( 'teen', 11.00, 500.00);
INSERT INTO savingInterestRates VALUES ( 'adult', 10.00, 1000.00);
INSERT INTO savingInterestRates VALUES ( 'senior', 13.00, 1000.00);
INSERT INTO savingInterestRates VALUES ( 'joint', 7.00, 5000.00);

INSERT INTO fixedInterestRates VALUES ('6', '13.00');
INSERT INTO fixedInterestRates VALUES ('12', '14.00');
INSERT INTO fixedInterestRates VALUES ('36', '15.00');

INSERT INTO bankingAgents VALUES ('00001', '859632147H', '0778569321', 'Olliver Qeen', 'Star city, DC', 'Green arrow', '0001');
INSERT INTO bankingAgents VALUES ('00002', '859654127H', '07185694321', 'Barry Allen', 'Central city, DC', 'Flash', '0002');
INSERT INTO bankingAgents VALUES ('00003', '987454127H', '01125878763', 'Harry Potter', 'Ministry of magic, London', 'The boy who lived', '0003');


INSERT INTO customers VALUES ('962232531V', 'Bhanuka Dissanayake', '0713600468', 'Kurunegala', '00001');
INSERT INTO customers VALUES ('961921759V', 'Lahiru Gunathilake', '0778964125', 'Kurunegala', '00001');
INSERT INTO customers VALUES ('987452121R', 'Ron Weasley', '0112475844', 'London', '00003');
INSERT INTO customers VALUES ('854754559Q', 'Draco Malfoy', '0778459624', 'London', '00002');
INSERT INTO customers VALUES ('456978235W', 'Ray Palmer', '0714575874', 'London', '00003');


INSERT INTO accounts VALUES ('160137','teen','ok','2018-11-06', 5000.00, 'good', '0001');
INSERT INTO accounts VALUES ('160182','teen','ok','2018-11-06', 3000.00, 'ghost', '0001');
INSERT INTO accounts VALUES ('160456','adult', 'ok','2018-11-06', 5000.00, 'good', '0002');
INSERT INTO accounts VALUES ('160101','joint', 'ok','2018-11-06', 5000.00, 'good', '0001');

INSERT INTO AccountHolders VALUES ( '160137', '962232531V'),( '160456', '987452121R'),
( '160101', '854754559Q'), ( '160101', '456978235W'),( '160182', '961921759V');


INSERT INTO fixedAccounts VALUES ('160789','160137', '962232531V', 'ok','2018-11-06', 6, 5000.00, 'good', '0001');
INSERT INTO fixedAccounts VALUES ('160497','160101', '854754559Q', 'ok','2018-11-06', 12, 10000.00, 'good', '0002');

