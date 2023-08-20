<?php 
require_once('koneksi.php');

$MerchantName = $_POST['merchant_name'];
$CityId = $_POST['city_id'];
$Address = $_POST['address'];
$Phone = $_POST['phone'];
$ExpiredDate = $_POST['expired_date'];

$query = "INSERT INTO merchant(
                merchant_name,
                city_id,
                address,
                phone,
                expired_date)
            VALUE(
            '$MerchantName',
            '$CityId',
            '$Address',
            '$Phone',
            '$ExpiredDate'
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