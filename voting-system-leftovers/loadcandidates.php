<?php
	$name = "";
	$count= 0;

	$array = array();
	$dbcon = mysqli_connect("localhost", "root", "");
	mysqli_select_db($dbcon, "votingsystem-um" );
	$stm = mysqli_prepare($dbcon, "SELECT name, votecount FROM candidate");
	mysqli_stmt_execute($stm);
	mysqli_stmt_bind_result($stm, $name, $count);

	$intval = 10;
	while (mysqli_stmt_fetch($stm)) {

		array_push($array, array("x" => $intval, "y" => $count, "label" => $name));
		$intval += 10;
	}

	mysqli_stmt_close($stm);
	echo json_encode($array);
?>