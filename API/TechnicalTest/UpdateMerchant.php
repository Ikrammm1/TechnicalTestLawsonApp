<?php 
require_once('koneksi.php');

$MerchantId = $_POST['id'];
$MerchantName = $_POST['merchant_name'];
$CityId = $_POST['city_id'];
$Address = $_POST['address'];
$Phone = $_POST['phone'];
$ExpiredDate = $_POST['expired_date'];

$query = "UPDATE merchant SET
                merchant_name = '$MerchantName',
                city_id = '$CityId',
                address =  '$Address',
                phone = '$Phone',
                expired_date = '$ExpiredDate'
            WHERE id = '$MerchantId'
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