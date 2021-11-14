<?php
require_once '../../db_functions.php';
$db =new DB_Functions();


if(isset($_POST['status']))
{
   
    $status=$_POST['status'] ;


    
       $orders=$db->getOrderServerByStatus($status);
     
           echo json_encode($orders);
       
      
    
} 
else
{
    $response="Required Parameter (status) is missing!";
    echo json_encode($response);
}


?>