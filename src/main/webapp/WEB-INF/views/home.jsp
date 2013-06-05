<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Image Upload Manager</title>
</head>

<body>
<!-- Begin home.jsp -->
<h2>Image Upload Manager</h2>

<form action="<c:url value="/" />" method="post" enctype="multipart/form-data" >
	<p>
		Filename: <input name="name" />
	</p>
	<p>
		Image: <input type="file" name="image" />
	</p>
	<p>
		<input type="submit" value="Upload" />
	</p>
</form>

<!-- End home.jsp -->
</body>

</html>