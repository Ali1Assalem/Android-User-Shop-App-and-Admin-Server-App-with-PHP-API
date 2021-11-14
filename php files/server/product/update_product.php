<?php
require_once '../../db_functions.php';
$db =new DB_Functions();

if(isset($_POST['id']))
{
    $id=$_POST['id'];
    $name=$_POST['name'];
    $imgPath=$_POST['imgPath'];
    $price=$_POST['price'];
    $menuId=$_POST['menuId'];

    $result=$db->updateProduct ($id,$name,$imgPath,$price,$menuId);
    if($result)
    echo json_encode("Update Product success !");
    else
    echo json_encode("Error while write to database");
}
else{
    echo(json_encode("Required parameters (id , name , imgPath , price , menueId) is missing !"));
}
?> 