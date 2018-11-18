create database if not exists id7780538_centralserver;
use id7780538_centralserver;

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
    NIC varchar(10),
    name varchar(50),
    telephone int,
    address varchar(200),
    agent_id varchar(10),
    primary key (NIC),
    foreign key (agent_id) references bankingAgents(agent_id)
);

create table if not exists accounts(
    acountNumber varchar(10),
    cutomerNIC varchar(10),
    accountType ENUM('children', 'teen', 'adult', 'senior'),
    status varchar(5),
    currentBalance decimal(15,2),
    accountDetails varchar(200),
    branch_number varchar(10),
    primary key (acountNumber),
    foreign key (cutomerNIC) references customers(NIC),
    foreign key (branch_number) references branches(branch_number)
);

create table if not exists jointAccounts(
    acountNumber varchar(10),
    cutomerNIC varchar(10),
    status varchar(5),
    currentBalance decimal(15,2),
    accountDetails varchar(200),
    branch_number varchar(10),
    primary key (acountNumber),
    foreign key (cutomerNIC) references customers(NIC),
    foreign key (branch_number) references branches(branch_number)
);

create table if not exists jointAccountHolders(
    acountNumber varchar(10),
    cutomerNIC varchar(10),
    primary key (acountNumber, cutomerNIC),
    foreign key (acountNumber) references accounts(acountNumber),
    foreign key (cutomerNIC) references customers(NIC)
);

create table if not exists fixedAccounts(
    acountNumber varchar(10),
    cutomerNIC varchar(10),
    status varchar(5),
    duration int,
    currentBalance decimal(15,2),
    accountDetails varchar(200),
    branch_number varchar(10),
    primary key (acountNumber),
    foreign key (cutomerNIC) references customers(NIC),
    foreign key (duration) references fixedInterestRates(duration),
    foreign key (branch_number) references branches(branch_number)
);

create table if not exists transactions(
    transactionID varchar(5),
    acountNumber varchar(10),
    agent_id varchar(10),
    transactionType ENUM('withdraw', 'deposit'),
    transactionDate DATETIME,
    amount decimal(15,2),
    transactionDetails varchar(100),
    charges decimal(15,2),
    primary key (transactionID),
    foreign key (acountNumber) references accounts(acountNumber),
    foreign key (agent_id) references bankingAgents(agent_id)
);





