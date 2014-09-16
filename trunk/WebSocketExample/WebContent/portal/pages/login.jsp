<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/header"/>

<div id="login" style="width:500px;">
	
	<form name="login" action="login.do" method="post" onsubmit="return validateForm();" class="form-horizontal" role="form">
		<div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
		    <div class="col-sm-10">
		      <input type="text" name="name" class="form-control" id="input_name" placeholder="Please enter your name">
		    </div>
		</div>
  	
  		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-primary">Enter to Chat Room</button>
    		</div>
  		</div>
	</form>
	
</div>

<script type="text/javascript">
function validateForm()
{
	var lName = $("#input_name").val();
	if(lName == null || lName.trim().length ==0 )
	{
		$("#message").html('<div class="alert alert-danger" role="alert">Please provide your name!</div>');
		return false;
	}
	return true;
}
</script>
<jsp:include page="/footer"/>