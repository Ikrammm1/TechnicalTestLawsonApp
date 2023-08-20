<?php

require_once('koneksi.php');

$ProductId = $_POST['product_id'];


$query = "DELETE FROM products where product_id = '$ProductId'";
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