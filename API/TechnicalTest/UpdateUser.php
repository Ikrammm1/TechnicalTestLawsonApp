<?php 
require_once('koneksi.php');

$UserId = $_POST['user_id'];
$FullName = $_POST['full_name'];
$Birth = $_POST['date_of_birth'];
$Address = $_POST['address'];
$Phone = $_POST['phone'];
$Email = $_POST['email'];



$query = "UPDATE users SET 
                            date_of_birth = '$Birth', 
                            full_name = '$FullName', 
                            address = '$Address', 
                            phone = '$Phone', 
                            email = '$Email',
                        WHERE user_id = '$UserId'";
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