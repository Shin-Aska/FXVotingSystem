<?php
	$value = "";
	$id= 0;

	$array = array();
	$dbcon = mysqli_connect("localhost", "root", "");
	mysqli_select_db($dbcon, "votingsystem-um" );
	$stm = mysqli_prepare($dbcon, "SELECT id, value FROM ticket");
	mysqli_stmt_execute($stm);
	mysqli_stmt_bind_result($stm, $id, $value);

	while (mysqli_stmt_fetch($stm)) {

		array_push($array, array("id" => $id, "value" => $value));
	}

	mysqli_stmt_close($stm);
	echo json_encode($array);
?>