<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <script src="/mcquaids/javascript/common/workflow.js"></script>
    <script type="module" src="/mcquaids/javascript/workflows/reservation-delivery-address-workflow.js"></script>
    <script type="module" src="/mcquaids/javascript/pages/reservation-edit.js"></script>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

    <!-- Read caller + reservationId -->
    <c:set var="caller" value="${param.caller}" />
    <c:set var="reservationId" value="${param['reservation.reservationID']}" />

    <!-- Header Card -->
    <div class="card mb-4">
        <div class="card-body">

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h5 class="card-title mb-0">
                    Dispatch Plan for Reservation #
                    <s:property value="reservation.reservationID"/>
                    <span class="badge badge-info ml-2">
                        <s:property value="reservation.reservationStatusText"/>
                    </span>
                </h5>
            </div>

            <!-- Reservation Summary -->
            <div class="row">

                <!-- CUSTOMER -->
                <div class="col-sm-4">
                    <div class="form-group">
                        <label>Customer</label>
                        <s:textfield
                                name="reservation.customer.fullName"
                                cssClass="form-control"
                                disabled="true"
                                theme="simple"/>
                    </div>
                </div>

                <!-- START DATE -->
                <div class="col-sm-3">
                    <div class="form-group">
                        <label>Required Date</label>
<s:textfield type="datetime-local"
             name="reservation.startDate"
             cssClass="form-control"
             disabled="true"
             theme="simple"/>
                    </div>
                </div>

                <!-- END DATE -->
                <div class="col-sm-3">
                    <div class="form-group">
                        <label>Return Date</label>
                        <s:textfield type="datetime-local"
                                     name="reservation.endDate"
                                     cssClass="form-control"
                                     disabled="true"
                                     theme="simple"/>
                    </div>
                </div>

            </div>

            <!-- NOTES -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Reservation Notes</label>
                        <s:textarea name="reservation.instructions"
                                    cssClass="form-control"
                                    disabled="true"
                                    theme="simple"/>
                    </div>
                </div>
            </div>

        </div>
    </div>

<c:if test="${reservation.endDate == null}">
    <div class="alert alert-warning mb-3">
        No return date specified - equipment will remain on site until a pickup is scheduled.
    </div>
</c:if>


    <!-- Dispatch Groups -->
    <div class="card">
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
                                <th></th>
                                <th>Equipment</th>
                                <th>Action</th>
                                <th>From</th>
                                <th>To</th>
                                <th>Source</th>
                                <th>Date</th>
                                <th>Push</th>
                            </tr>
                            </thead>

                            <tbody>
                            <s:iterator value="#group.actions" var="a">
                                <tr>

									<td>
<s:if test="#a.removedFromReservation">
    <span class="badge bg-danger ms-2">
        Removed
    </span>

    <span class="ms-1 text-muted"
          data-bs-toggle="popover"
          data-bs-trigger="hover focus"
          title="Removed From Reservation"
          data-bs-content="This equipment was removed from the reservation, but it is still part of the Dispatch Plan and may still be on Google Calendar. Review and remove it if it is no longer needed.">
        <i class="bi bi-question-circle"></i>
    </span>
</s:if>
									</td>
					<td>
					    <s:property value="#a.equipmentNumber"/><br/>
					    <s:property value="#a.reservationLineItemDTO.equipmentTypeText"/>
					    &nbsp;
					    <s:property value="#a.reservationLineItemDTO.equipmentSubTypeText"/>
					</td>                           

                                    <td><s:property value="#a.actionType"/></td>
<td>
    <s:if test="#a.fromYardId != null">
        <s:property value="#a.fromLocationName"/>
    </s:if>
    <s:else>
        <s:property value="#a.fromAddress.street"/>,
        <s:property value="#a.fromAddress.city"/>
    </s:else>
</td>

<td>
    <s:if test="#a.toYardId != null">
        <s:property value="#a.toLocationName"/>
    </s:if>
    <s:else>
        <s:property value="#a.toAddress.street"/>,
        <s:property value="#a.toAddress.city"/>
    </s:else>
</td>
                                    <td><s:property value="#a.sourceType"/></td>
                                    <td><s:date name="#a.scheduledDateTime" format="MMM dd, yyyy"/></td>
<td>
<td>
    <!-- Case 1: Not removed, no event yet -->
    <s:if test="!#a.removedFromReservation && #a.googleEventId == null">
        <a href="javascript:void(0);"
           class="btn btn-sm btn-outline-primary push-to-calendar"
           data-dispatch-action-id="<s:property value='#a.dispatchActionId'/>">
            Push to Calendar
        </a>
    </s:if>

    <!-- Case 2: Not removed, event exists -->
    <s:if test="!#a.removedFromReservation && #a.googleEventId != null">
        <a href="https://calendar.google.com/calendar/u/0/r/eventedit/<s:property value='#a.universalEidUrl'/>"
           target="_blank"
           class="btn btn-sm btn-outline-success">
            View Calendar
        </a>
    </s:if>

    <!-- Case 3: Removed from reservation, event exists -->
    <s:if test="#a.removedFromReservation && #a.googleEventId != null">
        <a href="javascript:void(0);"
           class="btn btn-sm btn-outline-danger remove-from-calendar"
           data-dispatch-action-id="<s:property value='#a.dispatchActionId'/>">
            Remove from Calendar
        </a>
    </s:if>
</td>
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


<script type="module"> 
import { initDispatchCalendarButtons } from '/mcquaids/javascript/common/dispatch-calendar.js';

document.addEventListener('DOMContentLoaded', () => {
    initDispatchCalendarButtons();
});
</script>

<script>
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('[data-bs-toggle="popover"]').forEach(function (el) {
        new bootstrap.Popover(el, { 
            container: 'body',
            trigger: 'hover focus'
        });
    });
});
</script>