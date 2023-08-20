<?php 
require_once('koneksi.php');

$ProductName = $_POST['name'];
$MerchantId = $_POST['merchant_id'];
$Price = $_POST['price'];

$query = "INSERT INTO products(
                name,
                merchant_id,
                price)
            VALUE(
            '$ProductName',
            '$MerchantId',
            '$Price'
            
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