<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="errorMessage" class="alert" style="display:none;"></div>

<div class="container-fluid mt-3">

    <jsp:include page="viewMovementOrder.jsp" />

    <div class="card mt-3">
        <div class="card-header bg-warning">
            Swap Link
        </div>
        <div class="card-body">

            <c:choose>
                <c:when test="${swapLink != null}">
                    <p><strong>Line A:</strong> ${swapLink.lineAId}</p>
                    <p><strong>Line B:</strong> ${swapLink.lineBId}</p>
                </c:when>

                <c:otherwise>
                    <div class="form-row">
                        <div class="col">
                            <input type="text"
                                   class="form-control"
                                   placeholder="Line A ID"
                                   data-swap-line-a />
                        </div>
                        <div class="col">
                            <input type="text"
                                   class="form-control"
                                   placeholder="Line B ID"
                                   data-swap-line-b />
                        </div>
                        <div class="col">
                            <button class="btn btn-primary"
                                    data-swap-link-button
                                    data-order-id="${movementOrderHeader.movementOrderId}">
                                Create Swap Link
                            </button>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>

</div>

<script type="module" src="/javascript/pages/movement-order.js"></script>
<script type="module" src="/javascript/pages/movement-order-swap.js"></script>

<script>
    if (window.initMovementOrderSwap) initMovementOrderSwap();
</script>
