<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Action Completed</title>

    <!-- Bootstrap 4.6.2 -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body class="bg-light">

<div class="container py-5">

    <div class="alert alert-success shadow-sm text-center">
        <h3 class="mb-3">Action Completed</h3>

        <p class="lead">
            Action <strong><s:property value="actionId"/></strong> has been recorded.
        </p>

        <p class="text-muted">
            You may close this page.
        </p>
    </div>

</div>

<!-- Bootstrap 4 JS bundle -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>