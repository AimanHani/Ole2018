<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
    require 'connection.php';
    
    getUserPrediction();
    // $method = $_POST["method"];
    // if ($method == "getUser"){
        // getUser();
    // } else {
        // getUserPrediction();
    // }
    
}

function getUser(){
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


function getUserPrediction(){
    global $connect;
     $username = $_POST["username"];
    
    $query = "SELECT log.logId, log.username, log.leagueId, l.leagueName, ml.team1_prediction, ml.team2_prediction, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2 FROM log, matcheslog ml, league l, `match` as m WHERE log.logId = ml.logId and l.leagueId = log.leagueId and m.matchId = ml.matchId and log.username = '$username' limit 1";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    $results = "error";
    if ($number_of_rows >0){
        while ($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
        $results = json_encode(array("results"=>$temp_array));
    }
    
    header('Content-Type: application/json');
    echo $results  ;
    mysqli_close($connect);
    
}