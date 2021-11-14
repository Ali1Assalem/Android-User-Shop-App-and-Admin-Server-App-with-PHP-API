<?php

require_once '../../db_functions.php';
$db =new DB_Functions();


if(isset($_POST['status'])&&
isset($_POST['email'])&&
isset($_POST['order_id']))
{
   
    $status=$_POST['status'] ;
    $email=$_POST['email'] ;
    $staorder_id=$_POST['order_id'] ;


    
       $result=$db->updateOrderStatus($email,$staorder_id,$status);
     
           echo json_encode($result);
       
      
    
} 
else
{
    $response="Required Parameter (status , email ,order id) is missing!";
    echo json_encode($response);
}


?>