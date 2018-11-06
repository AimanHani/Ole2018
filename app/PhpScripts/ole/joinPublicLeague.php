<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    require 'connection.php';
    $insertLog = insertToLog();
    //echo $insertLog;
    if ($insertLog == "successful"){
        $logId = selectLog();
        insertSpecialsLog($logId);
        echo $logId;
    } else {
        echo "error";
    }
}

function insertToLog(){
    global $connect;
    
    $username = $_POST["username"];
    $leagueId = $_POST["leagueId"];
    
    $query = "INSERT INTO log (username, leagueId, points) VALUES ('$username', '$leagueId', 0)";
 

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
    $query = "SELECT specialsId from specialslog where prediction = -1";
    
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