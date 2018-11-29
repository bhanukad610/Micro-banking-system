use id7910250_centralserver;

/*view for interests from transactions*/
create view if not exists interests as
select accountNumber, date, time, amount
from transactions
where transactionType = 'interest';

/*view for accounts details with customer details*/
create view if not exists accounts_with_customer_details as 
select * 
from (accounts natural join AccountHolders) natural join customers;

/*view for fixed accounts details with customer details*/
create view if not exists fixed_accounts_with_customer_details as 
select * 
from fixedAccounts natural join customers;

/*agent details for customers*/
create view if not exists agent_details_for_customers as 
select name, telephone, address, branch_number
from bankingAgents;

/*for sending details to the app*/
create view if not exists account_details_for_agents as 
SELECT accountNumber,accountType,status,currentBalance,accountDetails,agent_id from accounts NATURAL join AccountHolders NATURAL join customers;
