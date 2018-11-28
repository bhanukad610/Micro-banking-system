<?php 
require_once 'connection.php';
$agent_id = $_POST["agent_id"];
$response = array();
$query = "select * from account_details_for_agents where agent_id = '$agent_id'";
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