<?php
require_once '../config/connection.php';
$query = "select accountNumber, accountType, currentBalance from accounts";
$results = mysqli_query($connection, $query);




if($results){
    while ($record = mysqli_fetch_assoc($results)){
        $accountNumber = $record['accountNumber'];
        $accountType = $record['accountType'];
        $currentBalance = $record['currentBalance'];
        echo $accountType;
        $query_rate =  "SELECT * FROM `savingInterestRates` where accountType = '".$accountType."'";
        $rate = mysqli_query($connection, $query_rate);
        if($rate){
           while ($record = mysqli_fetch_assoc($rate)){
        
            echo "<pre>";
            print_r($record);
            echo "</pre>";
    }

}
    }

}

?>