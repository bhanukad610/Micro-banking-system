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

/*trigger to update accounts table after transaction*/
DROP TRIGGER IF EXISTS update_accounts_after_transaction;
DELIMITER //
CREATE TRIGGER update_accounts_after_transaction
AFTER INSERT ON transactions 
FOR EACH ROW 
begin
DECLARE current_balance decimal(15,2);
select currentBalance
  into current_balance
  from accounts
 where accountNumber = new.accountNumber;

IF new.transactionType = 'deposit' then
UPDATE accounts SET currentBalance = currentBalance + new.amount - new.charges WHERE accountNumber = new.accountNumber;
END IF;

IF new.transactionType = 'interest' then
UPDATE accounts SET currentBalance = currentBalance + new.amount - new.charges WHERE accountNumber = new.accountNumber;
END IF;

IF new.transactionType = 'withdraw' then
current_balance = current_balance - new.amount - new.charges
    if (current_balance >= (select minimumAmount
      from savingInterestRates
     where accountType = (select accountType
       from accounts
      where accountNumber = new.accountNumber))) then 
      UPDATE accounts SET currentBalance = current_balance WHERE accountNumber = new.accountNumber;
END IF;
    end if;
END IF;
end;//
DELIMITER ;


/*create function interestDeposit(interestRate decimal(2,2),currentBalance decimal(15,2)) returns decimal(15,2)
begin
declare interestAmount;
interestAmount=(currentBalance*interestRate)
currentBalance=(currentBalance+interestAmount)
return currentbalance
end

create function Deposit(currentBalance decimal(15,2),amount decimal(15,2))
currentBalance=currentBalance+amount
return currentBalance
end

create function Withdraw(currentBalance decimal(15,2),amount decimal(15,2),minimumAmount decimal(15,2))
declare withdrawAmount decimal(15,2);
if minimumAmount<= (currentBalance-amount) then
	currentBalance=currentBalance-amount
	withdrawAmount=amount
	return currentBalance
	end if
else if minimumAmount< currentBalance
	withdrawAmount=currentBalnce-minimumAmount
	currentBalance=minimumAmount
else
	withdrawAmount=0
return currentbalance,withdrawAmount*/


