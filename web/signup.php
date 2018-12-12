<!DOCTYPE html>
<?php session_start()?>
<div id="header"><?php include_once 'view/header.php'; ?></div>
	<div class="col-md-3"></div>
	<div class="col-md-6 well">
		<h3 class="text-primary"><?php print($sitename) ?></h3>
		<hr style="border-top:1px dotted #ccc;"/>
		<a href="login.php">Már regisztrált? Bejelentkezés...</a>
		<br style="clear:both;"/><br />
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form method="POST" action="save_member.php">	
				<div class="alert alert-info">Regisztráció</div>
				<div class="form-group">
					<label>Felhasználónév</label>
					<input type="text" name="username" class="form-control" required="required"/>
				</div>
				<div class="form-group">
					<label>Jelszó</label>
					<input type="password" name="password" class="form-control" required="required"/>
				</div>
                                <div class="form-group">
					<label>E-Mail cím</label>
                                        <input type="email" name="email" class="form-control" required="required"/>
				</div>
				<?php
					if(ISSET($_SESSION['success'])){
				?>
				<div class="alert alert-success"><?php echo $_SESSION['success']?></div>
				<?php
					session_unset($_SESSION['success']);
					}
				?>
				<button class="btn btn-primary btn-block" name="register"><span class="glyphicon glyphicon-save"></span> Folytatás</button>
			</form>	
		</div>
	</div>
</body>
</html>