<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Base URL (ensure this matches your deployment path) -->
    <base href="http://newschasers.ca/">

    <title><tiles:insertAttribute name="title" ignore="true" /></title>

    <!-- ========================= -->
    <!--          CSS              -->
    <!-- ========================= -->

    <!-- Bootstrap 4.6.2 CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- Font Awesome Icons -->
    <link rel="stylesheet"
          href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">

    <!-- DataTables CSS (if used) -->
    <link rel="stylesheet" href="/mcquaids/css/datatables.min.css">

    <!-- Your custom site CSS -->
    <link rel="stylesheet" href="/mcquaids/css/mcquaids.css">

    <!-- ========================= -->
    <!--       JS LIBRARIES        -->
    <!-- ========================= -->

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Popper.js (Bootstrap tooltips/popovers) -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>

    <!-- Bootstrap 4.6.2 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>

    <!-- DataTables JS -->
    <script src="/mcquaids/javascript/datatables.min.js"></script>

    <!-- ========================= -->
    <!--        SITE JS            -->
    <!-- ========================= -->

    <script src="/mcquaids/javascript/navbar.js"></script>
    <script src="/mcquaids/javascript/FormValidations.js"></script>
    <script src="/mcquaids/javascript/mcquaids.js"></script>

    <!-- Shared workflow logic (selectors, navigation, context) -->
    <script src="/mcquaids/javascript/common/workflow.js"></script>

    <!-- Page-specific JS (optional) -->
    <!-- <script src="/mcquaids/javascript/reservation/deliveryAddress.js"></script> -->
</head>
<body class="bg-light">
	<tiles:insertAttribute name="navigation" />
	<tiles:insertAttribute   name="body" ignore="true"/>
	<tiles:insertAttribute name="footer" />

</body>
</html>