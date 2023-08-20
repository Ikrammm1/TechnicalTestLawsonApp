<?php 
require_once('koneksi.php');

$OrderId = $_POST['order_id'];
$ProductId = $_POST['product_id'];
$Quantity = $_POST['quantity'];
$UserId = $_POST['user_id'];


$query = "UPDATE order_items SET
                quantity = '$Quantity',
                product_id = '$ProductId',
                user_id = '$UserId'
            WHERE order_id = '$OrderId'";
$obj_query = mysqli_query($koneksi, $query);

 if ($obj_query) {
        
    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";
            
 }else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";  
 }
  echo json_encode($data);
 
// mengatur tampilan json

header('Content-Type: application/json')

?>