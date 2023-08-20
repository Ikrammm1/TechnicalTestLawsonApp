<?php 
require_once('koneksi.php');
require_once('SimpleXLSXGen.php');

$query = mysqli_query($koneksi, "SELECT order_items.order_id,
    order_items.quantity,
    order_items.product_id,
    products.name AS Product,
    merchant.merchant_name,
    users.full_name AS Users,
    (products.price * order_items.quantity)AS Total,
    master_status.name AS status,
    order_items.date,
    DATE_FORMAT(order_items.date, '%c-%Y') AS MonthYear,
    city.name AS City
FROM order_status
INNER JOIN order_items ON order_items.order_id = order_status.order_id
INNER JOIN products ON products.product_id = order_items.product_id
INNER JOIN merchant ON merchant.id = products.merchant_id
INNER JOIN users ON users.user_id = order_items.user_id
INNER JOIN master_status ON master_status.id = order_status.status_id
INNER JOIN city on city.id = merchant.city_id
WHERE order_status.order_id = order_items.order_id
AND products.product_id = order_items.product_id
AND users.user_id = order_items.user_id
AND master_status.id = order_status.status_id
ORDER BY (products.price * order_items.quantity) DESC");

$rows = $query->fetch_all(MYSQLI_ASSOC);
$result = [];

foreach($rows as $row) {
    if (count($result) == 0) {
        array_push($result, [
            "Users" => $row["Users"],
            "result" => [$row]
        ]);
    } else {
        foreach($result as $idx => $item) {
            if ($item["Users"] == $row["Users"]) {
                array_push($result[$idx]["result"], $row);
            } else {
                array_push($result, [
                    "Users" => $row["Users"],
                    "result" => [$row]
                ]);
            }
        } 
    }
}

$result = array_map(
    function ($item) {
        $items = $item["result"];

        $rowsArr = array_map(
            function ($row) {
                $keys = array_keys($row);
                return array_map(
                    function($key) use ($row) {
                        return $row[$key]; 
                    },
                    $keys
                );
            },
            $items,
        );

        array_unshift($rowsArr, array_keys($items[0]));

        return [
            "Users" => $item["Users"],
            "rows" => $rowsArr
        ];
    },
    $result
);

$xlsx = new Shuchkin\SimpleXLSXGen();
foreach($result as $item){
    $xlsx->addSheet($item['rows'], $item['Users']);
}
$xlsx->downloadAs('ReportByUsers.xlsx');
exit();

// if($rows){
//         $data["message"] = "data saved successfully";
//         $data["status"] = "Ok";
//     } else {
//         $data["message"] = "data not saved successfully";
//         $data["status"] = "error";   
//     }


// echo json_encode($result);

?>