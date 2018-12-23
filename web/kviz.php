<!DOCTYPE html>
<html>
<head>
<title>kviz:</title>
</head>
<body>
<?php
if(!empty($_post)){
$ans1=$_post['ans1'];
$ans2=$_post['ans2'];
$ans3=$_post['ans3'];
$ans4=$_post['ans4'];
$ans5=$_post['ans5'];
$correct=0;
if($ans1==13){
$correct++;
echo"<p>helyes</p>";
}
else{
echo"<p>Rossz válasz</p>";
}
if($ans2==4){
$correct++;
echo"<p>helyes</p>";
}
else{
echo"<p>Rossz válasz</p>";
}
if($ans3==21){
$correct++;
echo"<p>helyes</p>";
}
else{
echo"<p>Rossz válasz</p>";
}
if($ans4==3){
$correct++;
echo"<p>helyes</p>";
}
else{
echo"<p>Rossz válasz</p>";
}
echo"<p>Helyes válaszok száma: $correct</p>";
else{
echo"<p>válaszolj minden kérdésre</p>";
}
?>
<form action="quiz.php" method="post">
<p>Mennyi 5+7?</p>
<input type="text">
<p>Mennyi 7-3? </p>
<input type="text">
<p>Mennyi 7*3?</p>
<input type="text">
<p>Mennyi 24/8?</p>
<input type="text">
<p>Click submit to see your results</p>
<input type="submit">
</form>
</body>
</html>