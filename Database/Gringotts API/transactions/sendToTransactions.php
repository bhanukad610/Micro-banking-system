<?php
require_once 'connection.php';
$account_number = $_POST["account_number"];
$agent_id = $_POST["agent_id"];
$transactionType = $_POST["transactionType"];
$date = $_POST["date"];
$time = $_POST["time"];
$amount = $_POST["amount"];
$details = $_POST["details"];
$charges = $_POST["charges"];

$query = "INSERT INTO `transactions` (`transactionID`, `accountNumber`, `agent_id`, `transactionType`, `date`, `time`, `amount`, `details`, `charges`) VALUES (NULL,'$account_number', '$agent_id', '$transactionType', '$date', '$time', '$amount', '$details', '$charges')";
$result = mysqli_query($connection, $query);

if($result){
echo "record added";
}
else{
echo "error";
}
?>