DELIMITER $$
-- before inserting new id
DROP TRIGGER IF EXISTS before_Enter_duration$$
CREATE TRIGGER before_Enter_duration
    BEFORE INSERT ON fixedAccounts FOR EACH ROW
    BEGIN
		declare account_no int(10) default 0;
		DECLARE msg varchar(255);
		SELECT duration from fixedInterestRates where fixedInterestRates.duration=new.duration 
		into account_no;
        IF account_no = 0 then
			SET msg = 'You can not add a duration where fixedInterestRates table have not' ;
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = msg; 
		 	DELETE from fixedAccounts where accountNumber= new.accountNumber;
			
        END IF;   
    END; $$

DELIMITER ;