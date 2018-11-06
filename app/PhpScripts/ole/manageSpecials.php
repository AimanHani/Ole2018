<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    require 'connection.php';
    
    $method = $_POST["method"];
    if ($method == "get"){
        getSpecials();
    } else {
        insertSpecialsPrediction();
    }
    
}

function getSpecials(){
    global $connect;
    
    $logId = $_POST["logId"];
    
    $query = "select s.specialsId, s.description from specialsLog sl, specials s where sl.specialsId = s.specialsId and sl.logId = '$logId'";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    
    if ($number_of_rows >0){
        while ($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
    }
    
    header('Content-Type: application/json');
    echo json_encode(array("results"=>$temp_array));
    mysqli_close($connect);
}


function insertSpecialsPrediction(){
    
}