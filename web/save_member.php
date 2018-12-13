<?php
	session_start();
	require_once 'config.php';
	
	if(ISSET($_POST['register'])){
		$username = $_POST['username'];
		$password = $_POST['password'];
                $email = $_POST['email'];
		
		$query = "INSERT INTO `User` (username, password, email) VALUES(:username, :password, :email)";
		$stmt = $conn->prepare($query);
		$stmt->bindParam(':username', $username);
		$stmt->bindParam(':password', $password);
                $stmt->bindParam(':email', $email);
		
		if($stmt->execute()){
			$_SESSION['success'] = "Felhasználói fiók sikeresen létrehozva";
			header('location: login.php');
		}

	}
?>
