<?php
require_once 'db_functions.php';
$db =new DB_Functions();

$response=array();
if(isset($_POST['email']) && 
isset($_POST['password']) && 
isset($_POST['name']) &&
isset($_POST['address']))
{
    $email=$_POST['email'] ;
    $password=$_POST['password'];
    $name=$_POST['name']; 
    $address=$_POST['address'];

    if($db->checkExistsUser($email,$password)){
        $response["error_msg"]="user already exists with ".$email;
        echo json_encode($response);
    }
    else
    {
       $user=$db->registerNewUser($email,$password,$name,$address);
       if($user) 
       {
           $response["email"]=$user["Email"];
           $response["password"]=$user["Password"];
           $response["name"]=$user["Name"];
           $response["address"]=$user["Address"];
           echo json_encode($response);
       }
       else
       {
           $response["error_msg"]="Unknown error occured in registeration!";
           echo json_encode($response);
       }
    }
}
else
{
    $response["error_msg"]="Required Parameter (email,password,name,address) is missing!";
    echo json_encode($response);
}


?>