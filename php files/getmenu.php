<?php
require_once 'db_functions.php';
$db =new DB_Functions();


    
       $menus=$db->getMenu();
        echo json_encode($menus);

?>