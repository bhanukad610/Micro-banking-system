create database if not exists id7910250_centralserver;
use id7910250_centralserver;

create table if not exists branches(
    branch_number varchar(10),
    city varchar(20),
    primary key (branch_number)
);

create table if not exists savingInterestRates(
    accountType ENUM('children', 'teen', 'adult', 'senior', 'joint'),
    interestRate decimal(5,2),
    minimumAmount decimal(15,2),
    primary key (accountType)
);

create table if not exists fixedInterestRates(
    duration int,
    rate float(5,2),
    primary key (duration)
);

create table if not exists bankingAgents(
    agent_id varchar(5),
    NIC varchar(10),
    telephone int,
    name varchar(50),
    address varchar(200),
    agent_details varchar(100),
    branch_number varchar(10),
    primary key (agent_id),
    foreign key (branch_number) references branches(branch_number)
);

create table if not exists customers(
    customerNIC varchar(10),
    name varchar(50),
    telephone int,
    address varchar(200),
    agent_id varchar(10),
    primary key (customerNIC),
    foreign key (agent_id) references bankingAgents(agent_id)
);

create table if not exists accounts(
    accountNumber varchar(10),
    accountType ENUM('children', 'teen', 'adult', 'senior','joint'),
    status varchar(5),
    currentBalance decimal(15,2),
    accountDetails varchar(200),
    branch_number varchar(10),
    primary key (accountNumber),
    foreign key (branch_number) references branches(branch_number)
);

create table if not exists AccountHolders(
    accountNumber varchar(10),
    customerNIC varchar(10),
    primary key (accountNumber, customerNIC),
    foreign key (accountNumber) references accounts(accountNumber),
    foreign key (customerNIC) references customers(customerNIC)
);

create table if not exists fixedAccounts(
    accountNumber varchar(10),
    customerNIC varchar(10),
    status varchar(5),
    duration int,
    currentBalance decimal(15,2),
    accountDetails varchar(200),
    branch_number varchar(10),
    primary key (accountNumber),
    foreign key (customerNIC) references customers(customerNIC),
    foreign key (duration) references fixedInterestRates(duration),
    foreign key (branch_number) references branches(branch_number)
);

create table if not exists transactions(
    transactionID int AUTO_INCREMENT,
    accountNumber varchar(10),
    agent_id varchar(10),
    transactionType ENUM('withdraw', 'deposit', 'interest'),
    date varchar(20),
    time varchar(10),
    amount decimal(15,2),
    details varchar(100),
    charges decimal(15,2),
    primary key (transactionID),
    foreign key (accountNumber) references accounts(accountNumber),
    foreign key (agent_id) references bankingAgents(agent_id)
);