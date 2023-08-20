<?php 
require_once('koneksi.php');

$FullName = $_POST['full_name'];
$Birth = $_POST['date_of_birth'];
$Address = $_POST['address'];
$Phone = $_POST['phone'];
$Email = $_POST['email'];



$query = "INSERT INTO users (
                            date_of_birth, 
                            full_name, 
                            address, 
                            phone, 
                            email,
                            active) 
                    VALUES (
                            '$Birth', 
                            '$FullName', 
                            '$Address',
                            '$Phone',
                            '$Email',
                            'active')";
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