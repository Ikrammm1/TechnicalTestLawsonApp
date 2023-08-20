<?php

require_once('koneksi.php');

$OrderId = $_POST['order_id'];


$query = "DELETE FROM order_items where order_id = '$OrderId'";
$sql = mysqli_query($koneksi, $query);

if($sql){
    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";
} else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
echo json_encode($data);

?>