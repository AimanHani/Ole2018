<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    require 'connection.php';
    login();
}

function login(){
    global $connect;
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    
    $query = "select * from user where username = '$username' and password ='$password';";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    
    header('Content-Type: application/json');
    
    if ($number_of_rows >0){
        while ($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
        
        echo json_encode(array("user"=>$temp_array));
    } else {
        echo "error";
        
    }
    mysqli_close($connect);
    
}




?>