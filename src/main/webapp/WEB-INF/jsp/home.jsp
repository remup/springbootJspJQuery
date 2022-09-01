<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Sample</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script> 

<script type="text/javascript"> 

jQuery(document).ready(function(){ 
	
  	 $("#button_1").click(function(e) {
    	e.preventDefault();
    	$.ajax({
     	 type: "GET",
     	 url: "/getMedicines",
	
      	dataType: "json",
	
      success: function(result) {
    	  var header = $("#table thead");
    	  header.append("<tr><th>rxcui</th><th>name</th></tr>")
        	$("#table").attr('border', '1');
		var table = $("#table tbody");
    	$.each(result, function(idx, elem){
        table.append("<tr>+<td>"+elem.rxcui+"</td>+<td>"+elem.name+"</td>+</tr>");
	
	
	
    });
      },
      error: function(xhr,result) {
        alert('error');
      }
    });
  });
});
</script>
</head>
<body>

	<h1>Welcome to HomePage</h1>
	<button id="button_1" value="val_1" name="but1" >GET DATA</button>
	<table  id="table">
  <thead>
  </thead>
  <tbody>
    </tbody>
</table>


</body>
</html>