<?php 
require_once('koneksi.php');

$Email = $_POST["email"];

$query = "SELECT * FROM users WHERE email = '$Email'";
$obj_query = mysqli_query($koneksi, $query);
$data = mysqli_fetch_assoc($obj_query);

if($data){
    
    echo json_encode(
        array(
        	'status' => 'Berhasil Login',
            'response' => true,
            'payload' => array(
                "user_id" => $data["user_id"],
                "full_name" => $data["full_name"],
                "date_of_birth" => $data["date_of_birth"],
                "address" => $data["address"],
                "phone" => $data["phone"],
                "email" => $data["email"],
                
                
               
            )
        )
            );
   
} else {
    echo json_encode(
        array(
        	'status' => 'Login Gagal',
            'response' => false,
            'payload' => null
        )
        );
    
}
// mengatur tampilan json

header('Content-Type: application/json')




?>