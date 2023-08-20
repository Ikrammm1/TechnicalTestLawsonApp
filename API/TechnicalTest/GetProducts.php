<?php 
require_once('koneksi.php');

$query = mysqli_query($koneksi, "SELECT * 
                                FROM products 
                                INNER JOIN merchant on merchant.id = products.merchant_id
                                WHERE merchant.id = products.merchant_id");
//kriteria
$result = array(); 
while($data  = mysqli_fetch_array($query)) {
   
array_push($result, array(
    'product_id'        =>$data['product_id'],
    'productname'       =>$data['name'],
    'price'             =>$data['price'],
    'merchant_name'     =>$data['merchant_name'],
    'merchant_id'       =>$data['id'],

    
    

 ));

}echo json_encode ( array('Products' =>$result));
header('Content-Type: application/json')
?>