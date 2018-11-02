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
    
    
    
    $query = "INSERT INTO USER (username, name, password,dob, country, contactNo, email, favoriteTeam)
VALUES ('$username', '$name', '$password','2003-01-09', 'Singapore', 98765432, 'robin@gmail.com', 'ManU')";


if (mysqli_query($connect, $query)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $query . "<br>" . mysqli_error($connect);
}

mysqli_close($connect);
    
    
}




?>