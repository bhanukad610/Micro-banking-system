create database if not exists mobileDB;
use mobileDB;

create table if not exists accounts(
    acountNumber varchar(10),
    cutomerNIC varchar(10),
    accountType ENUM('children', 'teen', 'adult', 'senior'),
    status varchar(5),
    currentBalance decimal(15,2),
    accountDetails varchar(200),
    branch_number varchar(10),
    primary key (acountNumber)
);

create table if not exists transactions(
    transactionID varchar(5),
    acountNumber varchar(10),
    transactionType ENUM('withdraw', 'deposit'),
    transactionDate DATETIME,
    amount decimal(15,2),
    transactionDetails varchar(100),
    charges decimal(15,2),
    primary key (transactionID),
    foreign key (acountNumber) references accounts(acountNumber)
);