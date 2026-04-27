<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display:none;"></div>

    <!-- ========================= -->
    <!-- HEADER CARD (SOURCE-AWARE) -->
    <!-- ========================= -->
    <div class="card mb-4">
        <div class="card-body">

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h5 class="card-title mb-0">
                    Dispatch Plan
                    <span class="badge badge-info ml-2">
                        <s:property value="dispatchPlanStatusText"/>
                    </span>
                </h5>

                <!-- Back Button -->
                <c:choose>
                    <c:when test="${sourceType eq 'RESERVATION'}">
                        <a href="/mcquaids/reservation/edit.action?reservation.reservationID=${sourceId}"
                           class="btn btn-secondary">
                            Back to Reservation
                        </a>
                    </c:when>

                    <c:when test="${sourceType eq 'MOVEMENT'}">
                        <a href="/mcquaids/movement/viewMovementOrder.action?movementOrderId=${sourceId}"
                           class="btn btn-secondary">
                            Back to Movement Order
                        </a>
                    </c:when>

                    <c:otherwise>
                        <a href="/dispatch/list.action" class="btn btn-secondary">Back</a>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Source Summary -->
            <div class="row">

                <div class="col-sm-4">
                    <strong>Source:</strong><br/>
                    <c:choose>
                        <c:when test="${sourceType eq 'RESERVATION'}">
                            Reservation #${sourceId}
                        </c:when>
                        <c:when test="${sourceType eq 'MOVEMENT'}">
                            Movement Order #${sourceId}
                        </c:when>
                        <c:otherwise>
                            Unknown
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-sm-4">
                    <strong>Created:</strong><br/>
                    <s:property value="createdDateTime"/>
                </div>

                <div class="col-sm-4">
                    <strong>Last Updated:</strong><br/>
                    <s:date name="lastUpdatedDateTime" format="MMM dd, yyyy h:mm a"/>
                </div>

            </div>

        </div>
    </div>

    <!-- ========================= -->
    <!-- DISPATCH GROUPS -->
    <!-- ========================= -->
    <div class="card mb-4">
        <div class="card-body">

            <h5 class="card-title mb-3">Dispatch Actions</h5>

            <s:iterator value="dispatchGroups" var="group">

                <div class="card mb-4">
                    <div class="card-header bg-secondary text-white">
                        <strong><s:property value="#group.label"/></strong>
                    </div>

                    <div class="card-body p-0">

                        <table class="table table-striped mb-0">
                            <thead>
                            <tr>
                                <th style="width:40px;"></th>
                                <th>Equipment</th>
                                <th>Action</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Source</th>
                                <th>Scheduled Date</th>
                                <th>Calendar</th>
                            </tr>
                            </thead>

                            <tbody>

                            <s:iterator value="#group.actions" var="a">

                                <tr>

                                    <!-- Removed badge -->
                                    <td>
                                        <s:if test="(#a.removedFromReservation == true)">
                                            <span class="badge badge-danger ml-1">Removed</span>

                                            <span class="text-muted ml-1"
                                                  data-toggle="popover"
                                                  data-trigger="hover focus"
                                                  title="Removed From Source"
                                                  data-content="This item was removed from the source workflow but remains in the Dispatch Plan. Review and remove it if no longer needed.">
                                                <i class="fa fa-question-circle"></i>
                                            </span>
                                        </s:if>
                                    </td>

                                    <!-- Equipment -->
                                    <td>
                                        <s:property value="#a.equipmentNumber"/><br/>
                                        <small>
                                            <s:property value="#a.reservationLineItemDTO.equipmentTypeText"/>
                                            &nbsp;
                                            <s:property value="#a.reservationLineItemDTO.equipmentSubTypeText"/>
                                        </small>
                                    </td>

                                    <!-- Action -->
                                    <td>
                                        <s:property value="#a.actionType"/>
                                        (<s:property value="#a.status"/>)
                                    </td>

                                    <!-- From -->
                                    <td>
                                        <s:if test="(#a.fromYardId != null)">
                                            <s:property value="#a.fromLocationName"/>
                                        </s:if>
                                        <s:if test="(#a.fromYardId == null)">
                                            <s:property value="#a.fromAddress.street"/>,
                                            <s:property value="#a.fromAddress.city"/>
                                        </s:if>
                                    </td>

                                    <!-- To -->
                                    <td>
                                        <s:if test="(#a.toYardId != null)">
                                            <s:property value="#a.toLocationName"/>
                                        </s:if>
                                        <s:if test="(#a.toYardId == null)">
                                            <s:property value="#a.toAddress.street"/>,
                                            <s:property value="#a.toAddress.city"/>
                                        </s:if>
                                    </td>

                                    <!-- Source Type -->
                                    <td>
                                        <s:property value="#a.sourceType"/>
                                    </td>

                                    <!-- Date -->
                                    <td>
                                        <s:date name="#a.scheduledDateTime" format="MMM dd, yyyy h:mm a"/>
                                        
                                    </td>

                                    <!-- Calendar -->
                                    <td>

                                        <!-- Case 1: Not removed, no event -->
                                        <s:if test="((#a.removedFromReservation == false) && (#a.googleEventId == null))">
                                            <a href="javascript:void(0);"
                                               class="btn btn-sm btn-outline-primary push-to-calendar"
                                               data-dispatch-action-id="<s:property value='#a.dispatchActionId'/>">
                                                Push
                                            </a>
                                        </s:if>

                                        <!-- Case 2: Not removed, event exists -->
                                        <s:if test="((#a.removedFromReservation == false) && (#a.googleEventId != null))">
                                            <a href="https://calendar.google.com/calendar/u/0/r/eventedit/<s:property value='#a.universalEidUrl'/>"
                                               target="_blank"
                                               class="btn btn-sm btn-outline-success">
                                                View
                                            </a>
                                        </s:if>

                                        <!-- Case 3: Removed, event exists -->
                                        <s:if test="((#a.removedFromReservation == true) && (#a.googleEventId != null))">
                                            <a href="javascript:void(0);"
                                               class="btn btn-sm btn-outline-danger remove-from-calendar"
                                               data-dispatch-action-id="<s:property value='#a.dispatchActionId'/>">
                                                Remove
                                            </a>
                                        </s:if>

                                    </td>

                                </tr>

                            </s:iterator>

                            </tbody>
                        </table>

                    </div>
                </div>

            </s:iterator>

        </div>
    </div>

</div>

<!-- Calendar JS -->
<script type="module">
import { initDispatchCalendarButtons } from '/mcquaids/javascript/common/dispatch-calendar.js';

document.addEventListener('DOMContentLoaded', () => {
    initDispatchCalendarButtons();
});
</script>

<!-- Bootstrap 4 Popovers -->
<script>
$(function () {
    $('[data-toggle="popover"]').popover({
        container: 'body',
        trigger: 'hover focus'
    });
});
</script>
