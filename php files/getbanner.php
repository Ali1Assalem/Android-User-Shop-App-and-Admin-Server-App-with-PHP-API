<?php
require_once 'db_functions.php';
$db =new DB_Functions();


    
       $banners=$db->getBanners();
        echo json_encode($banners);

?>