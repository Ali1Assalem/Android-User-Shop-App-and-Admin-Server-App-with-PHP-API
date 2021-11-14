<?php

class DB_Functions{
    private $conn;

    function __construct()
    {
        require_once 'db_connect.php';
        $db=new DB_Connect();
        $this->conn=$db->connect();
    }

    function __destruct()
    {

    }

    function checkExistsUser($email,$password)
    {
        $stmt=$this->conn->prepare("SELECT * FROM User WHERE Email=? AND Password=?");
        $stmt->bind_param("ss",$email,$password);
        $stmt->execute();
        $stmt->store_result();

        if($stmt->num_rows > 0)
        {
            $stmt->close();
            return true;
        }
        else
        {
            $stmt->close();
            return false;
        }
    }


    public function registerNewUser($email,$password,$name,$address)
    {
        $stmt=$this->conn->prepare("INSERT INTO User(Email,Password,Name,Address) VALUES(?,?,?,?)");
        $stmt->bind_param("ssss",$email,$password,$name,$address);
        $result=$stmt->execute();
        $stmt->close();

        if($result) 
        {
            $stmt=$this->conn->prepare("SELECT * FROM User WHERE Email =?");
            $stmt->bind_param("s",$email);
            $stmt->execute();
            $user=$stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        }
        else
        return false;
    }

    public function getUserInformation($email){
        $stmt=$this->conn->prepare("SELECT * FROM User WHERE Email =?");
            $stmt->bind_param("s",$email);
            if($stmt->execute())
            {
                $user=$stmt->get_result()->fetch_assoc();
                $stmt->close(); 
                return $user;
            }
            else
            return NULL;
}




public function getBanners(){
    $result=$this->conn->query("SELECT * FROM banner ORDER BY ID LIMIT 3");
    $banners=array();
    while($item=$result->fetch_assoc())
    $banners[]=$item;
    return $banners;
}

public function getMenu(){
    $result=$this->conn->query("SELECT * FROM menu");
    $menu=array();
    while($item=$result->fetch_assoc())
    $menu[]=$item;
    return $menu;
}


public function getRandom(){
    $result=$this->conn->query("SELECT * FROM `product` ORDER BY RAND() LIMIT 15");
    $menu=array();
    while($item=$result->fetch_assoc())
    $menu[]=$item;
    return $menu;
}


public function getCompany(){
    $result=$this->conn->query("SELECT * FROM company");
    $company=array();
    while($item=$result->fetch_assoc())
    $company[]=$item;
    return $company;
}


public function getProductByMenuID($menuId){
    $query="SELECT * FROM Product WHERE MenuId='".$menuId."'";
    
    $result=$this->conn->query($query);
    $drinks=array();
    while($item=$result->fetch_assoc())
    $drinks[]=$item;
    return $drinks;
} 


public function updateAvatar($email,$fileName){
    return $result=$this->conn->query("UPDATE user SET avatarUrl='$fileName' WHERE Email='$email'");
}



public function getAllDrinks(){
    $result=$this->conn->query("SELECT * FROM Product WHERE 1") or die($this->conn->error);
    
    $drinks=array();
    while($item=$result->fetch_assoc())
    $drinks[]=$item;
    
    return $drinks; 
    }



    
    public function insertNewOrder($orderPrice,$orderDetail,$orderComment,$orderAddress,$userEmail,$paymentMethod){
        $stmt=$this->conn->prepare("INSERT INTO `orderr`(`OrderDate`,`OrderStatus`, `OrderPrice`, `OrderDetail`, `OrderComment`, `OrderAddress`, `UserEmail`,`PaymentMethod`) VALUES (NOW(),0,?,?,?,?,?,?)")
        or die($this->conn->error);
        $stmt->bind_param("ssssss",$orderPrice,$orderDetail,$orderComment,$orderAddress,$userEmail,$paymentMethod);
        $result=$stmt->execute();
        $stmt->close();
    
        if($result){
        $stmt=$this->conn->prepare("SELECT * FROM `orderr` WHERE `UserEmail`=? ORDER BY `OrderId` DESC LIMIT 1")
        or die($this->conn->error);
        $stmt->bind_param("s",$userEmail);
        $stmt->execute();
        $order=$stmt->get_result()->fetch_assoc();
        $stmt->close();
        return $order;
        }
        else
        return false;
    }
    


    public function getOrderByStatus($userEmail,$status){
    $query="SELECT * FROM `orderr` WHERE `OrderStatus` = '" . $status . "' AND `UserEmail` = '" .$userEmail."' ";
    $result=$this->conn->query($query) or die($this->conn->error);

    $orders=array();
    while($order=$result->fetch_assoc())
    $orders[]=$order;

    return $orders;

    }


    public function cancelOrder($orderId,$userEmail)
{
    $stmt=$this->conn->prepare("UPDATE `orderr` SET `OrderStatus`=-1 WHERE `OrderId`=? AND `UserEmail`=?") or die ($this->conn->error);
    $stmt->bind_param("ss",$orderId,$userEmail);
    $result= $stmt->execute() or die($stmt->error);

    return $result;
}



public function insertNewCategory($name,$img_path){
    $stmt=$this->conn->prepare("INSERT INTO `menu` (`Name`,`Link`) VALUES (?,?)")
    or die($this->conn->error);
    $stmt->bind_param("ss",$name,$img_path);
    $result=$stmt->execute();
    $stmt->close();
    
    if($result)
    return true;
    else
    return false;
    }
    


    public function updateCategory($id,$name,$img_path){
        $stmt=$this->conn->prepare("UPDATE `menu` SET `Link`=?,`Name`=? WHERE `ID`=?");
        $stmt->bind_param("sss",$img_path,$name,$id);
        $result=$stmt->execute();
        return $result;
    
    }
    
    
    public function deleteCategory($id){
        $stmt=$this->conn->prepare("DELETE FROM `menu` WHERE `ID`=?");
        $stmt->bind_param("s",$id);
        $result=$stmt->execute();
        return $result;
    
    }



    public function insertNewDrink($name,$imgPath,$price,$menuId)
    {
    $stmt=$this->conn->prepare("INSERT INTO `product` (`Name`,`Link`,`Price`,`MenuId`) VALUES (?,?,?,?)");
    $stmt->bind_param("ssss",$name,$imgPath,$price,$menuId);
    $result=$stmt->execute();
    return $result;

    if($result)
    return true;
    else
    return false;
    }

    

    public function updateProduct($id,$name,$img_path,$price,$menuId){
        $stmt=$this->conn->prepare("UPDATE `product` SET `Link`=?,`Name`=?,`Price`=?,`MenuId`=? WHERE `ID`=?");
        $stmt->bind_param("sssss",$img_path,$name,$price,$menuId,$id);
        $result=$stmt->execute();
        return $result;
    
    }
    
    
    
    public function deleteProduct($id){
        $stmt=$this->conn->prepare("DELETE FROM `product` WHERE `ID`=?");
        $stmt->bind_param("s",$id);
        $result=$stmt->execute();
        return $result;
    
    }

    

    public function getOrderServerByStatus($status)
    {
        $query="SELECT * FROM `orderr` WHERE `OrderStatus` = '" . $status . "' ";
        $result=$this->conn->query($query) or die($this->conn->error);
    
        $orders=array();
        while($order=$result->fetch_assoc())
        $orders[]=$order;
    
        return $orders;
    
    }



    public function updateOrderStatus($email,$orderId,$status)
{
    $stmt=$this->conn->prepare("UPDATE `orderr` SET `OrderStatus` = ? WHERE `UserEmail`= ? AND `OrderId`=?")
    or die($this->conn->error);
    $stmt->bind_param("sss",$status,$email,$orderId);
    $result=$stmt->execute();
    return $result;
}


public function insertToken($email,$token,$isServerToken){
    $stmt=$this->conn->prepare("INSERT INTO token(email,token,isServerToken) VALUES (?,?,?) ON DUPLICATE KEY UPDATE token=?,isServerToken=?")
    or die($this->conn->error);

    $stmt->bind_param("sssss",$email,$token,$isServerToken,$token,$isServerToken);
    $result=$stmt->execute();
    $stmt->close();

    if($result)
    {
        $stmt=$this->conn->prepare("SELECT * FROM token where email=?");
        $stmt->bind_param("s",$email);
        $stmt->execute();
        $user=$stmt->get_result()->fetch_assoc();
        $stmt->close();
        return $user;


    }
    else{
        return false;
    }
}



public function getToken($email,$isServerToken)
{
    $stmt=$this->conn->prepare("SELECT * FROM `token` WHERE email=? AND isServerToken=?")
    or die($this->conn->error);
    $stmt->bind_param("ss",$email,$isServerToken);
    $result=$stmt->execute();
    $token=$stmt->get_result()->fetch_assoc();
    $stmt->close(); 
    return $token;
}


}
?>
