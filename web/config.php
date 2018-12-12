<?php
//SQLite Kapcsolat letrehozása
	$conn = new PDO('sqlite:db/db_member.sqlite3');
	
	$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	
	$query = "CREATE TABLE IF NOT EXISTS member(mem_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT, password TEXT, email TEXT, vezeteknev TEXT, keresztnev TEXT)";
	
	$conn->exec($query);
        
//Alap konfigurációs adatok
        $baseurl="http://kviz.ml";
        $sitename="KVÍZ Teszt";
        $sitetitle="RFT 'KVÍZ Project'";
?>