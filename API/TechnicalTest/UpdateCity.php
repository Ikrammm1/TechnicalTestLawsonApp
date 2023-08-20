<?php 
require_once('koneksi.php');

$CityId = $_POST['id'];
$CityName = $_POST['name'];
$Latitude = $_POST['latitude'];
$Longitude = $_POST['longitude'];

$query = "UPDATE city SET
                name = '$CityName',
                longitude = '$Longitude',
                latitude = '$Latitude'
            Where id = '$CityId'
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