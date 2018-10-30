<?php
if ($_SERVER["REQUEST_METHOD"] == "GET"){
    require 'connection.php';
    retrievePublicLeague();
}

function retrievePublicLeague(){
    global $connect;
    
    $query = "SELECT l.leagueId, prize, tournamentId, pointsAllocated, leagueName 
                FROM publicleague pl, league l 
                where l.leagueId = pl.leagueId";
    
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




?>