<?php
require_once 'db_functions.php';
$db =new DB_Functions();


if(isset($_POST['orderId']) && 
isset($_POST['userEmail']))
{
    $orderId=$_POST['orderId'] ;
    $userEmail=$_POST['userEmail'];

    $result=false;

    $result=$db->cancelOrder($orderId,$userEmail);

    if($result)
    echo json_encode("Order has been cancelled");

    else
    echo json_encode("Error while write to database");
}
else
{
    echo json_encode("Required parameter (orderId,userEmail) is missing!");
}


?>