<?php
	session_start();
	require_once 'config.php';
	
	if(ISSET($_POST['login'])){
		$username = $_POST['username'];
		$password = $_POST['password'];
		
		$query = "SELECT COUNT(*) as count FROM `user` WHERE `username` = :username AND `password` = :password";
		$stmt = $conn->prepare($query);
		$stmt->bindParam(':username', $username);
		$stmt->bindParam(':password', $password);
		$stmt->execute();
		$row = $stmt->fetch();
		
		$count = $row['count'];
		
		if($count > 0){
                        session_start();
                        $_SESSION['loggedin'] = true;
                        $_SESSION['username'] = $username;
			header('location:index.php');
		}else{
			$_SESSION['error'] = "Hibás felhasználónév vagy jelszó!";
			header('location:login.php');
		}
	}
?>
