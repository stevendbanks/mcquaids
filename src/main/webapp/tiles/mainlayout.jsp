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

<!-- Font Awesome (icons) -->
<link rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">

<!-- Bootstrap 4.6.2 CSS -->
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- Your custom site CSS -->
<link rel="stylesheet" href="/mcquaids/css/mcquaids.css">

<!-- jQuery (Bootstrap 4 requires it) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Popper.js (required for dropdowns, tooltips, popovers) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>

<!-- Bootstrap 4.6.2 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>

<!-- DataTables (if you use it) -->
<script src="/mcquaids/css/datatables.min.js"></script>

<!-- Your site-wide JS -->
<script src="/mcquaids/javascript/navbar.js"></script>
<script src="/mcquaids/javascript/FormValidations.js"></script>

</head>
<body class="bg-light">
	<tiles:insertAttribute name="navigation" />
	<tiles:insertAttribute   name="body" ignore="true"/>
	<tiles:insertAttribute name="footer" />

</body>
</html>