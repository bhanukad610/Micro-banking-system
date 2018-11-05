use centralServer;

INSERT INTO branches VALUES 
( '0001', 'Colombo'),
( '0002', 'Moratuwa'),
( '0003', 'Kurunegala'),
( '0004', 'Kandy'),
( '0005', 'Anuradhapura'),
( '0006', 'Galle'),
( '0007', 'Matara'),
( '0008', 'Gampaha');

INSERT INTO savingInterestRates VALUES 
( 'children', 12.00, null),
( 'teen', 11.00, 500.00),
( 'adult', 10.00, 1000.00),
( 'senior', 13.00, 1000.00),
( 'joint', 7.00, 5000.00);

INSERT INTO `fixedInterestRates` VALUES ('6', '13.00'),
('12', '14.00'),
('36', '15.00');

INSERT INTO bankingAgents VALUES 
('00001', '859632147H', '0778569321', 'Olliver Qeen', 'Star city, DC', 'Green arrow', '0001'),
('00002', '859654127H', '07185694321', 'Barry Allen', 'Central city, DC', 'Flash', '0002'),
('00003', '987454127H', '01125878763', 'Harry Potter', 'Ministry of magic, London', 'The boy who lived', '0003');


INSERT INTO customers VALUES ('962232531V', 'Bhanuka Dissanayake', '0713600468', 'Kurunegala', '00001'),
('987452121R', 'Ron Weasley', '0112475844', 'London', '00003'),
('854754559Q', 'Draco Malfoy', '0778459624', 'London', '00002');


INSERT INTO accounts VALUES ('160137', '962232531V','children','ok', 5000.00, 'good', '0001'),
('160456', '987452121R','adult', 'ok', 5000.00, 'good', '0002');

INSERT INTO jointAccounts VALUES ('160123', '962232531V', 'ok', 5000.00, 'good', '0001'),
('160712', '987452121R','ok', 4000.00, 'good', '0002');

INSERT INTO jointAccountHolders
VALUES ( '160137', '962232531V'),
( '160456', '987452121R');


INSERT INTO fixedAccounts VALUES ('160789', '962232531V', 'ok', 6, 5000.00, 'good', '0001'),
('160497', '854754559Q', 'ok', 12, 10000.00, 'good', '0002');










