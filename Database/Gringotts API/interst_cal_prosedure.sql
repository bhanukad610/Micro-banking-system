DELIMITER $$
create procedure addFDInterestToFixedAccount()

	begin	
			DECLARE done INT DEFAULT FALSE;
			declare acc_numb varchar(12);
			declare days int(12) default 0;
			DECLARE cur1 CURSOR FOR select accountNumber from fixedAccounts;
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
			OPEN cur1;
			read_loop: LOOP
			  FETCH cur1 INTO acc_numb;
			  IF done THEN
			  LEAVE read_loop;
			  END IF;
					select (curdate()-opendate) as Di_days from fixedAccounts where accountNumber=acc_numb into days;
					if  days%5=0 then
						start transaction;				
						 insert into transactions 
						 values (null,acc_numb,null,'interests',CURDATE(),CURTIME(),(0.01 *
						(select currentBalance from fixedAccounts where accountNumber=acc_numb) *
						(SELECT rate from fixedInterestRates NATURAL JOIN fixedAccounts WHERE accountNumber=acc_numb))/12,'fixed add',0);				
						commit;
					END IF;
			  END LOOP ;
			CLOSE cur1 ;		
	END; $$
DELIMITER ;

DELIMITER $$
create procedure addFDInterestToSavingAccount()

	begin	
			DECLARE done INT DEFAULT FALSE;
			declare acc_numb varchar(12);
			declare days int(12) default 0;
			DECLARE cur1 CURSOR FOR select accountNumber from accounts;
			DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
			OPEN cur1;
			read_loop: LOOP
			  FETCH cur1 INTO acc_numb;
			  IF done THEN
			  LEAVE read_loop;
			  END IF;
					select (curdate()-opendate) as Di_days from accounts where accountNumber=acc_numb into days;
					if  days%5=0 then
						start transaction;				
						 insert into transactions 
						 values (null,acc_numb,null,'interests',CURDATE(),CURTIME(),(0.01 *
						(select currentBalance from accounts where accountNumber=acc_numb) *
						(SELECT interestRate from savingInterestRates NATURAL JOIN accounts WHERE accountNumber=acc_numb))/12,'saving add ',0);				
						commit;
					END IF;
			  END LOOP ;
			CLOSE cur1 ;		
	END; $$
DELIMITER ;
