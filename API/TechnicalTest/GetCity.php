<?php 
require_once('koneksi.php');

$query = mysqli_query($koneksi, "SELECT * FROM city");
//kriteria
$result = array(); 
while($data  = mysqli_fetch_array($query)) {
   
array_push($result, array(
    'id'            =>$data['id'],
    'name'          =>$data['name'],
    'latitude'      =>$data['latitude'],
    'longitude'     =>$data['longitude']
    

 ));

}echo json_encode ( array('City' =>$result));
header('Content-Type: application/json')
?>