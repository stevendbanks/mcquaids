<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="container">

    <!-- Workflow + Page JS -->
    <script src="/mcquaids/javascript/common/workflow.js"></script>
	<script type="module" src="/mcquaids/javascript/workflows/reservation-delivery-address-workflow.js"></script>
    <script type="module" src="/mcquaids/javascript/pages/reservation-edit.js"></script>

    <!-- Error Message -->
    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>
    <!-- Read caller + reservationId -->
		<c:set var="caller" value="${param.caller}" />
		<c:set var="reservationId" value="${param['reservation.reservationID']}" />

    <!-- Main Save Reservation form -->
    <s:form action="save" method="post" namespace="/reservation" theme="bootstrap">

        <!-- Hidden fields -->
        <s:hidden name="actionType"/>
        <s:hidden id="reservationID" name="reservation.reservationID"/>
        <s:hidden id="reservationStatusCode" name="reservation.reservationStatusCode"/>
        <s:hidden id="customerID" name="reservation.customerID"/>

        <!-- Reservation Header -->
        <div class="card mb-4">
            <div class="card-body">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="card-title mb-0">
                        <s:property value="pageTitle"/>
                        <span class="badge badge-info ml-2">
                            <s:property value="reservation.reservationStatusText"/>
                        </span>
                    </h5>

                    <!-- Action Dropdown -->
                    <div class="btn-group">
                        <button type="button"
                                class="btn btn-outline-dark dropdown-toggle"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                            Actions
                        </button>

                        <div class="dropdown-menu dropdown-menu-right">
                            <button class="dropdown-item" type="button" onclick="uploadSignedLeasePDF()">
                                Upload signed lease
                            </button>
                            <button class="dropdown-item" type="button" onclick="cancelReservation()">
                                Cancel Reservation
                            </button>
                            <button class="dropdown-item" type="button" onclick="duplicateReservation()">
                                Duplicate Reservation
                            </button>
                            <button class="dropdown-item" type="button" onclick="printReservation()">
                                Print
                            </button>
						    <button class="dropdown-item" type="button" onclick="createDispatchPlan()">
						        Dispatch Plan
						    </button>
                            
                        </div>
                    </div>
                </div>

                <!-- Reservation Fields -->
                <div class="row">

                    <!-- Customer -->
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="customerID">Customer</label>

                            <div class="input-group">
                                <s:textfield
                                        id="fullName"
                                        name="reservation.customer.fullName"
                                        cssClass="form-control"
                                        disabled="true"
                                        theme="simple"
                                />

                                <div class="input-group-append no-print">
                                    <button type="button"
                                            class="btn btn-outline-secondary"
                                            onclick="openCustomerLookup()">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Required Date -->
                    <div class="col-sm-3">
                        <div class="form-group">
                            <label for="reservationStartDate">Required Date</label>
                            <s:textfield id="reservationStartDate"
                                         type="datetime-local"
                                         name="reservation.startDate"
                                         cssClass="form-control"
                                         format="yyyy-MM-dd"/>
                        </div>
                    </div>

                    <!-- Return Date -->
                    <div class="col-sm-3">
                        <div class="form-group">
                            <label for="reservationEndDate">Return Date</label>
                            <s:textfield id="reservationEndDate"
                                         type="datetime-local"
                                         name="reservation.endDate"
                                         cssClass="form-control"
                                         format="yyyy-MM-dd"/>
                        </div>
                    </div>

                </div>

                <!-- Notes -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="instructions">Reservation Notes</label>
                            <s:textarea id="instructions"
                                        name="reservation.instructions"
                                        cssClass="form-control auto-expand"
                                        oninput="autoExpand(this)"/>
                        </div>
                    </div>
                </div>
                
<!-- Additional Contact (Optional) -->
<div class="row mt-3">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="additionalPersonName">Additional Person Name</label>
            <s:textfield id="additionalPersonName"
                         name="reservation.additionalPersonName"
                         cssClass="form-control"/>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="form-group">
            <label for="additionalPersonPhone">Additional Person Phone</label>
            <s:textfield id="additionalPersonPhone"
                         name="reservation.additionalPersonPhone"
                         cssClass="form-control"/>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="form-group">
            <label for="additionalPersonEmail">Additional Person Email</label>
            <s:textfield id="additionalPersonEmail"
                         name="reservation.additionalPersonEmail"
                         cssClass="form-control"/>
        </div>
    </div>
</div>                

            </div>
        </div>
        
<c:if test="${not empty reservation.leaseDocumentPath}">
    <div class="card mt-3 mb-4">
        <div class="card-header">
            Signed Lease
        </div>

        <div class="card-body">

            <div class="row align-items-center">

                <!-- Download Button -->
                <div class="col-md-4 mb-2">
					<a href="/uploads/leases/${reservation.leaseDocumentPath}"
					   class="btn btn-outline-primary">
					    Download Signed Lease (PDF)
					</a>
                </div>

                <!-- Signed On -->
                <div class="col-md-4 mb-2">
                    <strong>Signed On:</strong>
                    <fmt:formatDate value="${reservation.leaseSignedDate}" pattern="yyyy-MM-dd HH:mm" />
                </div>

                <!-- Signed By -->
                <div class="col-md-4 mb-2">
                    <strong>Signed By:</strong>
                    ${reservation.leaseSignedBy}
                </div>

            </div>

        </div>
    </div>
</c:if>

        
        
        
        

		<!-- Delivery Address Panel -->
		<jsp:include page="/reservation/reservation-delivery-address-panel.jsp" />

		<!-- Delivery Address Panel -->
		<jsp:include page="/reservation/reservation-secondary-delivery-address-panel.jsp" />


        <!-- Requested Equipment list -->
        <div class="card mb-4">
            <div class="card-body">

                <h5 class="card-title">Reserved Equipment</h5>

                <table id="equipmentTable" class="table mt-4">
                    <thead>
                    <tr>
                        <th scope="col">Equipment Number</th>
                        <th scope="col">Equipment Type</th>
                        <th scope="col">SubType</th>
                        <th scope="col">Notes</th>

                        <th scope="col" class="text-right">
                            <div class="btn-group">
                                <button type="button"
                                        class="btn btn-sm btn-outline-primary"
                                        onclick="expandAllDetails()">
                                    Expand All
                                </button>
                            </div>
                        </th>

                        <th scope="col" class="text-right">
                            <div class="btn-group">
                                <button type="button"
                                        class="btn btn-sm btn-outline-secondary"
                                        onclick="collapseAllDetails()">
                                    Collapse All
                                </button>
                            </div>
                        </th>
                    </tr>
                    </thead>

                    <tbody id="reservationLineItemsTable">

                    <s:iterator value="reservationLineItemsDTO" var="item">

                        <!-- Main row -->
                        <tr class="line-item"
                            data-id="<s:property value='#item.reservationLineItemID'/>"
                            data-type="<s:property value='#item.equipmentTypeText'/>"
                            data-props='<s:property value="#item.equipmentPropertiesAsJson" escapeHtml="false" />'>

                            <td><s:property value="#item.equipmentNumber"/></td>
                            <td><s:property value="#item.equipmentTypeText"/></td>
                            <td><s:property value="#item.equipmentSubTypeText"/></td>
                            <td><s:property value="#item.lineItemNotes"/></td>

                            <!-- Details button -->
                            <td class="text-right">
                                <button type="button"
                                        class="btn btn-sm btn-secondary toggle-properties">
                                    Details
                                </button>
                            </td>

                            <!-- Actions dropdown -->
                            <td class="text-right">
                                <div class="btn-group">
                                    <button type="button"
                                            class="btn btn-sm btn-outline-dark dropdown-toggle"
                                            data-toggle="dropdown"
                                            aria-haspopup="true"
                                            aria-expanded="false">
                                        Actions
                                    </button>

                                    <div class="dropdown-menu dropdown-menu-right">
                                        <button class="dropdown-item"
                                                type="button"
                                                onclick="removeLineItem('<s:property value="#item.reservationLineItemID"/>')">
                                            Remove
                                        </button>

                                        <button class="dropdown-item"
                                                type="button"
                                                onclick="substituteLineItem('<s:property value="#item.reservationLineItemID"/>')">
                                            Substitute
                                        </button>

                                        <button class="dropdown-item"
                                                type="button"
                                                onclick="markReturned('<s:property value="#item.reservationLineItemID"/>')">
                                            Mark Returned
                                        </button>
                                    </div>
                                </div>
                            </td>

                        </tr>

                        <!-- Hidden expandable row -->
                        <tr id="props-<s:property value='#item.reservationLineItemID'/>"
                            class="properties-panel"
                            style="display:none;">
                            <td colspan="5"></td>
                        </tr>

                    </s:iterator>

                    </tbody>
                </table>

            </div>
        </div>
        
        

        <!-- Save Reservation + Search Equipment buttons -->
        <div class="form-group no-print">
            <div class="d-flex justify-content-between mt-4">

                <s:submit
                        value="Save Reservation"
                        cssClass="btn btn-primary btn-lg"/>

                <button type="button"
                        class="btn btn-outline-primary btn-lg"
                        onclick="navigateToEquipmentSearch()">
                    <i class="fa fa-search"></i> Search Equipment
                </button>

            </div>
        </div>
        
        <!-- Add Equipment Section (original placement) -->
        <jsp:include page="AddEquipmentSection.jsp"/>        




    </s:form>
</div>

<div class="modal fade" id="customerModal" tabindex="-1" role="dialog" aria-labelledby="customerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="customerModalLabel">Customer Search</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="customerModalBody">
                <div class="text-center">
                    <i class="fa fa-spinner fa-spin fa-3x"></i>
                    <p>Loading Customer Search...</p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<!-- Upload Signed Lease Modal -->
<div class="modal fade" id="uploadSignedLeaseModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title">Upload Signed Lease PDF</h5>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <div class="modal-body">

        <input type="file" id="signedLeaseFile" accept="application/pdf" class="form-control">

        <!-- Required for PHP upload progress -->
        <input type="hidden" name="PHP_SESSION_UPLOAD_PROGRESS" id="PHP_SESSION_UPLOAD_PROGRESS">

        <div class="mt-3" style="width: 100%; background: #ddd; height: 20px;">
          <div id="leaseUploadProgressBar" style="width: 0%; height: 20px; background: #4caf50;"></div>
        </div>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="submitSignedLeasePDF()">Upload</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
      </div>

    </div>
  </div>
</div>


