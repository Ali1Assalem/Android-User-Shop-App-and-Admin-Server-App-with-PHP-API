<?php
require_once 'db_functions.php';
$db =new DB_Functions();

$response=array();
if(isset($_POST['email'])&&
isset($_POST['token'])&&
isset($_POST['isServerToken']))
{
    $email=$_POST['email'] ;
    $token=$_POST['token'] ;
    $isServerToken=$_POST['isServerToken'] ;

    
       $user=$db->insertToken($email,$token,$isServerToken);
     
       if($user)
           echo json_encode("Token update success");
       else
           echo json_encode("Token update failed");
       
      
    
}
else
{
    $response["error_msg"]="";
    echo json_encode("Required Parameter (email , token , isServerToken) is missing!");
}


?>