<?php
require_once 'db_functions.php';
$db =new DB_Functions();

$response=array();
if(isset($_POST['orderDetail']) && 
isset($_POST['email']) && 
isset($_POST['address']) &&
isset($_POST['comment']) &&
isset($_POST['price'])&&
isset($_POST['paymentMethod']))
{
    $email=$_POST['email'] ;
    $orderDetail=$_POST['orderDetail'];
    $address=$_POST['address']; 
    $comment=$_POST['comment'];
    $price=$_POST['price'];
    $paymentMethod=$_POST['paymentMethod'];


    $result=$db->insertNewOrder($price,$orderDetail,$comment,$address,$email,$paymentMethod);

    echo json_encode($result);
}
else
{
    echo json_encode("Required parameter (email,detail,address,comment,price,paymentMethod) is missing!");
}


?>