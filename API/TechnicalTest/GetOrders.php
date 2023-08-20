<?php 
require_once('koneksi.php');

$query = mysqli_query($koneksi, 
"SELECT order_items.order_id,
    order_items.quantity,
    order_items.product_id,
    products.name,
    products.price,
    merchant.merchant_name,
    users.full_name,
    order_items.user_id,
    (products.price * order_items.quantity)AS total,
    master_status.name AS status,
    order_items.date
FROM order_status
INNER JOIN order_items ON order_items.order_id = order_status.order_id
INNER JOIN products ON products.product_id = order_items.product_id
INNER JOIN merchant ON merchant.id = products.merchant_id
INNER JOIN users ON users.user_id = order_items.user_id
INNER JOIN master_status ON master_status.id = order_status.status_id
WHERE order_status.order_id = order_items.order_id
AND products.product_id = order_items.product_id
AND users.user_id = order_items.user_id
AND master_status.id = order_status.status_id

");
//kriteria
$result = array(); 
while($data  = mysqli_fetch_array($query)) {
   
array_push($result, array(
    'order_id'         =>$data['order_id'],
    'quantity'         =>$data['quantity'],
    'product_id'       =>$data['product_id'],
    'productname'      =>$data['name'],
    'price'            =>$data['price'],
    'merchant_name'    =>$data['merchant_name'],
    'full_name'        =>$data['full_name'],
    'user_id'          =>$data['user_id'],
    'status'           =>$data['status'],
    'total'            =>$data['total'],
    'date'             =>$data['date'],
    

 ));

}echo json_encode ( array('Orders' =>$result));
header('Content-Type: application/json')
?>