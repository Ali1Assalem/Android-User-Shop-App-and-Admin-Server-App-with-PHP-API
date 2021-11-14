<?php
require_once 'db_functions.php';
$db =new DB_Functions();


    
       $menus=$db->getRandom();
        echo json_encode($menus);

?>