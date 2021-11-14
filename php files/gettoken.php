<?php
require_once 'db_functions.php';
$db =new DB_Functions();


if(isset($_POST['email']) && isset($_POST['isServerToken']))
{
    $email=$_POST['email'] ;
    $isServerToken=$_POST['isServerToken'] ;


    
       $token=$db->getToken($email,$isServerToken);
     
           echo json_encode($token);
       
      
    
} 
else
{
    $response="Required Parameter (email,isServerToken) is missing!";
    echo json_encode($response);
}


?>