<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script  src="http://code.jquery.com/jquery-1.10.2.js" ></script>
<script>

    $.noConflict();
    var  J = jQuery;

	J(document).ready(function(){
          J('*').css('color','red');

});
</script></head>
<body>
$.noConflict() 메서드를 이용하여 더 이상 <br />
$식별자를 더 이상 사용할 수 없습니다.<br />
</body>
</html>
