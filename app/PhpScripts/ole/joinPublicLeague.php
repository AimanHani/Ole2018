<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    require 'connection.php';
    $insertLog = insertToLog();
    
    if ($insertLog == "successful"){
        $logId = selectLog();
        insertToMatchLog($logId);
        insertSpecialsLog($logId);
        echo "successful";
    } else {
        echo "error";
    }
}

function insertToLog(){
    global $connect;
    
    $username = $_POST["username"];
    $leagueId = $_POST["leagueId"];
    
    $query = "INSERT INTO log (username, leagueId) VALUES ('$username', '$leagueId')";
 

    if (mysqli_query($connect, $query)) {
        return "successful";
    } else {
        return "error";
    }

}

function selectLog(){
    global $connect;
    
    $username = $_POST["username"];
    $leagueId = $_POST["leagueId"];
     $query = "SELECT logId from log where username = '$username' and leagueId = '$leagueId'";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    $logId = "";
    
    if ($number_of_rows >0){
        $row = mysqli_fetch_assoc($result);
        
        $logId = $row["logId"];
    }
    
    return $logId;
}

function insertToMatchLog($logId){
    global $connect;
    
    $query = "INSERT INTO matchlog (logId, points) VALUES ('$logId', 0)";
    $msg = "";
        
    if (mysqli_query($connect, $query)) {
        $msg = "successful";
    } else {
        $msg = "error";
    }
    return $msg ;
}

function insertSpecialsLog($logId){
    
    global $connect;
    $query = "SELECT specialsId from specialslog where logid = 1";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    if ($number_of_rows >0){
        while ($row = mysqli_fetch_assoc($result)) {
            $specialsId = $row["specialsId"];
            $query2 = "INSERT INTO specialsLog (logid, specialsId) VALUES ('$logId', '$specialsId')";
            mysqli_query($connect, $query2);
        }
    }
    mysqli_close($connect);
}



?>