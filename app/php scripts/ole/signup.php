<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    require 'connection.php';
    signup();
}

function signup(){
    global $connect;
    
    $username = $_POST["username"];
    $name = $_POST["name"];
    $password = $_POST["password"];
    $dob  = $_POST["dob"];
    $country = $_POST["country"];
    $contactNo = $_POST["contactNo"];
    $email = $_POST["email"];
    $favoriteTeam = $_POST["favoriteTeam"];
    
    
    
    
    $query = "INSERT INTO USER (username, name, password,dob, country, contactNo, email, favoriteTeam)
VALUES ('$username', '$name', '$password','$dob', '$country', $contactNo, '$email', '$favoriteTeam')";


if (mysqli_query($connect, $query)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($connect);
    
    
}




?>