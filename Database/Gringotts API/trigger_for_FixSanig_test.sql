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