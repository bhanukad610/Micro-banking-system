<?php 
    require_once 'connection.php';
    $agent_id = $_GET["agent_id"];
	$query= "call get_transaction_details_agent_wise('$agent_id')";
	$result_set=mysqli_query($connection,$query);
	
	if ($result_set){
	
		while($record = mysqli_fetch_assoc($result_set)){
			echo "<pre>";
		print_r($record);
		echo "<pre>";
		}
		
	}else{
		echo "Query unsuccessful";
	}
?>