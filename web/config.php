<?php
//SQLite Kapcsolat letrehozása
	$conn = new PDO('sqlite:db/db_member.sqlite3');
	
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	/*
	//erre akkor van szükség h nem létezik az adatbázis fájl
	$query = "CREATE TABLE IF NOT EXISTS user(mem_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT, password TEXT, email TEXT, vezeteknev TEXT, keresztnev TEXT)";
	*/
	$conn->exec($query);
        
//Alap konfigurációs adatok
        $baseurl="http://kviz.ml";
        $sitename="KVÍZ Teszt";
        $sitetitle="RFT 'KVÍZ Project'";
?>
