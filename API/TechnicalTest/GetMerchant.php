<?php 
require_once('koneksi.php');

$query = mysqli_query($koneksi, "SELECT merchant.id,
                                    merchant.merchant_name,
                                    merchant.address,
                                    merchant.phone,
                                    merchant.expired_date,
                                    city.name,
                                    merchant.city_id
                                FROM merchant 
                                INNER JOIN city on city.id = merchant.city_id
                                WHERE merchant.city_id = city.id");

$result = array(); 
while($data  = mysqli_fetch_array($query)) {
   
array_push($result, array(
    'id'                =>$data['id'],
    'merchant_name'     =>$data['merchant_name'],
    'address'           =>$data['address'],
    'phone'             =>$data['phone'],
    'expired_date'      =>$data['expired_date'],
    'cityname'          =>$data['name'],
    'city_id'           =>$data['city_id'],
    
    

 ));

}echo json_encode ( array('Merchant' =>$result));
header('Content-Type: application/json')
?>