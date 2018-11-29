use id7780538_centralserver;

/*procedure to get account wise transaction details*/
DROP PROCEDURE IF EXISTS get_transaction_details_account_wise;
DELIMITER $$
CREATE PROCEDURE get_transaction_details_account_wise(IN account_number varchar(10))
BEGIN
  select agent_id,transactionType,date,time,amount,details,charges
    from transactions
   where accountNumber = account_number;
END $$
DELIMITER ;

/*procedure to get agent wise transaction details*/
DROP PROCEDURE IF EXISTS get_transaction_details_agent_wise;
DELIMITER $$
CREATE PROCEDURE get_transaction_details_agent_wise(IN agentId varchar(10))
BEGIN
  select accountNumber,transactionType,date,time,amount,details,charges
    from transactions
   where agent_id = agentId;
END $$
DELIMITER ;