<?php
if ($_SERVER["REQUEST_METHOD"] == "GET"){
    require 'connection.php';
    retrieveUsers();
}

function retrievePublicLeague(){
    global $connect;
    
        $query = "SELECT count(username), l.leagueId from log l inner join publicleague p where l.leagueId = p.leagueId";
        
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