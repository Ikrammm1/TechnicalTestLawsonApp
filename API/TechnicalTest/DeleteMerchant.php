<?php

require_once('koneksi.php');

$id = $_POST['id'];


$query = "DELETE FROM merchant where id = '$id'";
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