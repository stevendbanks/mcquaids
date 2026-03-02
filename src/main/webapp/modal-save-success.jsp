<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
(function() {
    var cid = '<s:property value="customer.userID"/>';
    var cname = '<s:property value="customer.firstName"/> <s:property value="customer.lastName"/>';
    var retryCount = 0;

    function attemptSelection() {
        if (typeof window.selectCustomerForReservation === "function") {
            console.info("Function found! Executing selection for:", cname);
            window.selectCustomerForReservation(cid, cname);
        } else if (retryCount < 10) {
            // If not found, wait 50ms and try again (up to 500ms total)
            retryCount++;
            setTimeout(attemptSelection, 50);
        } else {
            console.error("Failed to find selectCustomerForReservation after retries.");
            alert("Customer saved: " + cname + ". Please close the modal to see changes.");
        }
    }

    attemptSelection();
})();
</script>