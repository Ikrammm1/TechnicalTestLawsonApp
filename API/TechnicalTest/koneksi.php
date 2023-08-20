<?php 
$server = "localhost";
$username = "root";
$password = "";
$database = "db_lawson";
$koneksi = mysqli_connect($server, $username, $password, $database);

if(mysqli_connect_errno()){
    echo"gagal koneksi" .mysqli_connect_errno();
}



?>