<?php
require_once '../../db_functions.php';
$db =new DB_Functions();

if(isset($_POST['name'])&& isset($_POST['imgPath']))
{
    $name=$_POST['name'];
    $imgPath=$_POST['imgPath'];

    $result=$db->insertNewCategory($name,$imgPath);
    if($result)
    echo json_encode("Add Category success !");
    else
    echo json_encode("Error while write to database");
}
else{
    echo(json_encode("Required parameters (name , imgPath) is missing !"));
}
?> 