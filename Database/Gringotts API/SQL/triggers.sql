use id7910250_centralserver;

/*trigger to check for savings account before creating fixed accounts*/
DELIMITER $$
-- before inserting new id
DROP TRIGGER IF EXISTS before_create_fixed$$
CREATE TRIGGER before_create_fixed
    BEFORE INSERT ON fixedAccounts FOR EACH ROW
    BEGIN
		declare account_no int(10) default 0;
		DECLARE msg varchar(255);
		SELECT accountNumber from accounts where accounts.accountNumber=new.accountNumber 
		into account_no;
        IF account_no = 0 then
			SET msg = 'You can not create a fix acccount with out saving account' ;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg; 
		 	DELETE from fixedAccounts where accountNumber= new.accountNumber;
			
        END IF;   
    END; $$
DELIMITER ;

/*trigger to update accounts table after transaction*/
DROP TRIGGER IF EXISTS update_accounts_after_transaction;
DELIMITER $$
CREATE TRIGGER update_accounts_after_transaction
AFTER INSERT ON transactions 
FOR EACH ROW 
begin
DECLARE current_balance decimal(15,2);
DECLARE temp_balance decimal(15,2);
DECLARE temp1 decimal(15,2);
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
	SET temp1 = current_balance - new.amount - new.charges;
    select minimumAmount into temp_balance from savingInterestRates where accountType=(select accountType from accounts where accountNumber = new.accountNumber);
    IF temp1 >= temp_balance then
    UPDATE accounts SET currentBalance = temp1 WHERE accountNumber = new.accountNumber;
    ELSE
    UPDATE accounts SET currentBalance = temp_balance WHERE accountNumber = new.accountNumber;
    END IF;
END IF;
end $$
DELIMITER ;