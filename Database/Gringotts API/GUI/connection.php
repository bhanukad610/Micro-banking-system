<?php

 define ('DB_HOST', 'localhost');
 define ('DB_USER', 'id7910250_micro'); //admin
 define ('DB_PASSWORD' , 'micro');
 define ('DB_NAME' , 'id7910250_centralserver');

 $connection = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
 if(mysqli_connect_errno()){
 die('Database connection failed' . mysqli_connect_errno());
 }
 else{
     echo "Connection successful";
 }
?>