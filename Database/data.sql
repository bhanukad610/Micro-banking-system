use centralServer;

INSERT INTO branches ( branch_number, city)
VALUES ( '0001', 'Colombo');
INSERT INTO branches ( branch_number, city)
VALUES ( '0002', 'Moratuwa');
INSERT INTO branches ( branch_number, city)
VALUES ( '0003', 'Kurunegala');

INSERT INTO savingInterestRates ( accountType, interestRate, minimumAmount)
VALUES ( 'children', 12.00, null);
INSERT INTO savingInterestRates ( accountType, interestRate, minimumAmount)
VALUES ( 'teen', 11.00, 500.00);
INSERT INTO savingInterestRates ( accountType, interestRate, minimumAmount)
VALUES ( 'adult', 10.00, 1000.00);
INSERT INTO savingInterestRates ( accountType, interestRate, minimumAmount)
VALUES ( 'senior', 13.00, 1000.00);
INSERT INTO savingInterestRates ( accountType, interestRate, minimumAmount)
VALUES ( 'joint', 7.00, 5000.00);

INSERT INTO `fixedInterestRates` (duration, rate) VALUES ('6', '13.00');
INSERT INTO `fixedInterestRates` (duration, rate) VALUES ('12', '14.00');
INSERT INTO `fixedInterestRates` (duration, rate) VALUES ('36', '15.00');

INSERT INTO bankingAgents (agent_id, NIC, telephone, name, address, agent_details, branch_number) VALUES ('00001', '859632147H', '0778569321', 'Olliver Qeen', 'Star city, DC', 'Green arrow', '0001');
INSERT INTO bankingAgents (agent_id, NIC, telephone, name, address, agent_details, branch_number) VALUES ('00002', '859654127H', '07185694321', 'Barry Allen', 'Central city, DC', 'Flash', '0002');
INSERT INTO bankingAgents (agent_id, NIC, telephone, name, address, agent_details, branch_number) VALUES ('00003', '987454127H', '01125878763', 'Harry Potter', 'Ministry of magic, London', 'The boy who lived', '0003');


INSERT INTO customers (NIC, name, telephone, address, agent_id) VALUES ('962232531V', 'Bhanuka Dissanayake', '0713600468', 'Kurunegala', '00001');
INSERT INTO customers (NIC, name, telephone, address, agent_id) VALUES ('987452121R', 'Ron Weasley', '0112475844', 'London', '00003');
INSERT INTO customers (NIC, name, telephone, address, agent_id) VALUES ('854754559Q', 'Draco Malfoy', '0778459624', 'London', '00002');


INSERT INTO accounts (acountNumber,cutomerNIC, accountType, status, currentBalance, accountDetails, branch_number) VALUES ('160137', '962232531V','children','ok', 5000.00, 'good', '0001');
INSERT INTO accounts (acountNumber,cutomerNIC, accountType,status, currentBalance, accountDetails, branch_number) VALUES ('160456', '987452121R','adult', 'ok', 5000.00, 'good', '0002');

INSERT INTO jointAccounts (acountNumber,cutomerNIC, status, currentBalance, accountDetails, branch_number) VALUES ('160123', '962232531V', 'ok', 5000.00, 'good', '0001');
INSERT INTO jointAccounts (acountNumber,cutomerNIC, status, currentBalance, accountDetails, branch_number) VALUES ('160712', '987452121R','ok', 4000.00, 'good', '0002');

INSERT INTO jointAccountHolders ( acountNumber, cutomerNIC)
VALUES ( '160137', '962232531V');
INSERT INTO jointAccountHolders ( acountNumber, cutomerNIC)
VALUES ( '160456', '987452121R');


INSERT INTO fixedAccounts (acountNumber,cutomerNIC, status, duration, currentBalance, accountDetails, branch_number) VALUES ('160789', '962232531V', 'ok', 6, 5000.00, 'good', '0001');
INSERT INTO fixedAccounts (acountNumber,cutomerNIC, status, duration, currentBalance, accountDetails, branch_number) VALUES ('160497', '854754559Q', 'ok', 12, 10000.00, 'good', '0002');










