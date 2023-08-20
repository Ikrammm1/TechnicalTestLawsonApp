<?php 
require_once('koneksi.php');

$ProductId = $_POST['product_id'];
$Quantity = $_POST['quantity'];
$UserId = $_POST['user_id'];


$query = "INSERT INTO order_items(
                quantity,
                product_id,
                user_id)
            VALUE(
            '$Quantity',
            '$ProductId',
            '$UserId'
            )";
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