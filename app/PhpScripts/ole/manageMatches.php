<?php
if ($_SERVER["REQUEST_METHOD"] == "GET"){
    require 'connection.php';
    getRecentMatches();
} else {
    require 'connection.php';
    insertMatchesLog();
}

function getRecentMatches(){
    global $connect;
    
    $query = "select m.matchId, m.date, m.time, (SELECT t.teamName FROM team t where t.teamId = m.team1 ) AS team1, (SELECT t.teamName FROM team t where t.teamId = m.team2 ) AS team2 from `match` m  where m.date > DATE(NOW()) LIMIT 2";
    
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

function insertMatchesLog(){
    global $connect;
    
     $logId = $_POST["logId"];
     $matchId = $_POST["matchId"];
     $team1Prediction = $_POST["team1Prediction"];
     $team2Prediction = $_POST["team2Prediction"];
     $doubleIt = $_POST["doubleItValues"];
     
     $double = 0;
     if ($doubleIt == "true"){
         $double = 1;
     }
    
    $query = "insert into matcheslog (logId, matchId, doubleIt, team1_prediction, team2_prediction) VALUES ('$logId', '$matchId', '$double', '$team1Prediction', '$team2Prediction')";
    
    if (mysqli_query($connect, $query)) {
        echo "successful";
    } else {
        //echo "Error: " . $query . "<br>" . mysqli_error($connect);
        updateMatchesLog();s
    }

    mysqli_close($connect);
    

}

function updateMatchesLog(){
    global $connect;
    
     $logId = $_POST["logId"];
     $matchId = $_POST["matchId"];
     $team1Prediction = $_POST["team1Prediction"];
     $team2Prediction = $_POST["team2Prediction"];
     $doubleIt = $_POST["doubleItValues"];
     
     $double = 0;
     if ($doubleIt == "true"){
         $double = 1;
     }
    $query = "UPDATE matcheslog set doubleIt = '$double', team1_prediction= '$team1Prediction', team2_prediction = '$team2Prediction' where logid = '$logId' and matchId='$matchId'";
    
    if (mysqli_query($connect, $query)) {
        echo "successful";
    } else {
        echo "Error: " . $query . "<br>" . mysqli_error($connect);
    }

    mysqli_close($connect);
    

}