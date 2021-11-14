<?php
require_once 'db_functions.php';
$db =new DB_Functions();

$response=array();
if(isset($_POST['email']))
{
    $email=$_POST['email'] ;
    

    
       $user=$db->getUserInformation($email);
       if($user) 
       {
           $response["email"]=$user["Email"];
           $response["password"]=$user["Password"];
           $response["name"]=$user["Name"];
           $response["address"]=$user["Address"];
           $response["avatarUrl"]=$user["avatarUrl"];
          

           echo json_encode($response);
       }
       else
       {
           $response["error_msg"]="User does not exists!";
           echo json_encode($response);
       }
    
}
else
{
    $response["error_msg"]="Required Parameter (email) is missing!";
    echo json_encode($response);
}


?>