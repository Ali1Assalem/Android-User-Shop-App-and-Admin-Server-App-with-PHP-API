<?php
require_once 'db_functions.php';
$db =new DB_Functions();

$response=array();
if(isset($_POST['email'])&&
isset($_POST['password']))
{
    $email=$_POST['email'];
    $password=$_POST['password'];
    if($db->checkExistsUser($email,$password))
    {
      $response["exists"]=TRUE;
      echo json_encode($response);  
    }
    else
    {
        $response["exists"]=FALSE;
        echo json_encode($response);
    }
}
else
{
    $response["error_msg"]="Required Parameter (email) is missing!";
    echo json_encode($response);
}


?>