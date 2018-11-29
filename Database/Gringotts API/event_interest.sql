SET GLOBAL event_scheduler = ON;

DELIMITER $$

CREATE EVENT interest
    ON SCHEDULE EVERY 1 MINUTE

    DO 
		begin
			call addFDInterestToFixedAccount();
			call addFDInterestToSavingAccount();
		end; $$
DELIMITER ;