<?php
require_once 'db_functions.php';
$db =new DB_Functions();


if(isset($_POST['userEmail']) && isset($_POST['status']))
{
    $userEmail=$_POST['userEmail'] ;
    $status=$_POST['status'] ;


    
       $orders=$db->getOrderByStatus($userEmail,$status);
     
           echo json_encode($orders);
       
      
    
} 
else
{
    $response="Required Parameter (userEmail,status) is missing!";
    echo json_encode($response);
}


?>