<?php
session_start();

?>
<div id="header"><?php include_once 'view/header.php'; ?></div>
	<div class="col-md-3"></div>
	<div class="col-md-6 well">
		<h3 class="text-primary"><?php print($sitename) ?></h3>
		<hr style="border-top:1px dotted #ccc;"/>
                <h1>
                    <?php
                    if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] == true) {
                        echo "Üdvözöljük: " . $_SESSION['username'] . "!";
                        echo '<br><a href="logout.php">Kijelentkezés</a>';
                    } 
                    else {
                        echo "Kérem <html><a href='login.php'>jelentkezzen be</a></html> ,hogy megtekinthesse az oldal tartalmát!";
                    }
                    ?>
                </h1>
	</div>
</body>
</html>
