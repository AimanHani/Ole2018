<?php
if ($_SERVER["REQUEST_METHOD"] == "GET"){
    require 'connection.php';
    getRecentMatches();
} else {
    require 'connection.php';
    
    $method = $_POST["method"];
    
    if ($method == "getOneMatch") {
        getOneMatch();
    } else {
        updateMatchesLog();
    }
}

function getRecentMatches(){
    global $connect;
    
    $query = "select * from `match` where date > DATE(NOW()) LIMIT 3";
    
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

function getOneMatch(){
    global $connect;
    
    $matchId = $_POST["matchId"];
    
    $query = "select m.matchId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2 from `match` m where m.matchId = '$matchId'";
    
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