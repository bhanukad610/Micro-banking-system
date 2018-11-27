<?php 
require_once 'connection.php';

$response = array();
$query = "select * from accounts";
$result = mysqli_query($connection, $query);

if(mysqli_num_rows($result) > 0){
    $accounts = array();
    while ($row = mysqli_fetch_assoc($result)){
        array_push($accounts, $row);
    }
    $response['accounts'] = $accounts;
}
else{
    $response['success'] = 0;
    $response['message'] = 'No data';
}

echo json_encode($response);

?>