<!DOCTYPE html>
<?php session_start()?>
<div id="header"><?php include_once 'view/header.php'; ?></div>
	<div class="col-md-3"></div>
	<div class="col-md-6 well">
		<h3 class="text-primary"><?php print($sitetitle) ?></h3>
		<hr style="border-top:1px dotted #ccc;"/>
		<a href="signup.php">Felhasználói fiók regisztrálása</a>
		<br style="clear:both;"/><br />
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form method="POST" action="login_query.php">	
				<div class="alert alert-info">Bejelentkezés</div>
				<div class="form-group">
					<label>Felhasználónév</label>
					<input type="text" name="username" class="form-control" required="required"/>
				</div>
				<div class="form-group">
					<label>Jelszó</label>
					<input type="password" name="password" class="form-control" required="required"/>
				</div>
				<?php
					if(ISSET($_SESSION['error'])){
				?>
					<div class="alert alert-danger"><?php echo $_SESSION['error']?></div>
				<?php
					session_unset($_SESSION['error']);
					}
				?>
				<button class="btn btn-primary btn-block" name="login"><span class="glyphicon glyphicon-log-in"></span> Mehet</button>
			</form>	
		</div>
	</div>
</body>
</html>