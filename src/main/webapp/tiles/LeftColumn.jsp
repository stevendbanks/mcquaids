<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<base href="http://localhost:8080/" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>

</head>
<body>
Hello  <tiles:insertAttribute name="title" ignore="true" />

</body>
</html>
