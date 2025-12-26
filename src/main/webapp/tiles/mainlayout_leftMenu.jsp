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


<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">

<!-- DataTables CSS and JS Files  -->
<!-- <link rel="stylesheet" type="text/css" href="/mcquaids/css/datatables.min.css" /> -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

	<script type="text/javascript" src="/mcquaids/css/mcquaids.css"></script>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>

<script type="text/javascript" src="/mcquaids/css/datatables.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="/mcquaids/javascript/navbar.js"></script>
	<script type="text/javascript" src="/mcquaids/javascript/FormValidations.js"></script>
	<script type="text/javascript" src="/mcquaids/css/mcquaids.js"></script>
		


<!-- Custom styles for this template -->
<link href="/mcquaids/css/mcquaids.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <!-- Insert the vertical menu here -->
                <tiles:insertAttribute name="menu" />
            </div>
            <div class="col-sm-10">
                <!-- Your existing body content -->
                <tiles:insertAttribute name="navigation" />
                <tiles:insertAttribute name="body" ignore="true"/>
            </div>
        </div>
    </div>
                    <tiles:insertAttribute name="footer" />
</body>
</html>