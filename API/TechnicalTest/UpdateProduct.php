<?php 
require_once('koneksi.php');

$ProductId = $_POST['product_id'];
$ProductName = $_POST['name'];
$MerchantId = $_POST['merchant_id'];
$Price = $_POST['price'];

$query = "UPDATE products SET 
                name = '$ProductName',
                merchant_id = '$MerchantId',
                price = '$Price'
            WHERE product_id = '$ProductId'
           ";
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