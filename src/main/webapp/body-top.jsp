<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container" style="margin-bottom: 20px;">
	<h1 class="mt-4 mb-4">
		<s:property value="title" />
	</h1>

	<s:if test="hasActionErrors()">
		<div id="errorMessage" class="alert alert-danger">
			<s:actionerror />
		</div>
	</s:if>
