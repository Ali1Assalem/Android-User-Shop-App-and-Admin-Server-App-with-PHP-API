<?php
require_once 'db_functions.php';
$db =new DB_Functions();


    
       $companies=$db->getCompany();
        echo json_encode($companies);

?>