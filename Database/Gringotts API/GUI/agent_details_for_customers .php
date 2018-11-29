<?php 
	require_once 'connection.php';
	$query= "SELECT * FROM `agent_details_for_customers`";
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
