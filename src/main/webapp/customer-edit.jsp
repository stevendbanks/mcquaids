 <%@ taglib prefix="s" uri="/struts-tags"%>
 <div class="container" style="margin-bottom: 20px; max-width: 720px;">

    <div id="errorMessage" class="alert alert-danger" style="display: none;"></div>

    <s:form action="save" namespace="/customer" method="post" theme="bootstrap">

        <s:hidden name="saveActionType" />
        <s:hidden name="returnParams" id="returnParams" />

        <div class="card mb-4">
            <div class="card-body">

                <!-- Title -->
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="card-title mb-0">
                        <s:property value="title"/>
                    </h5>
                </div>

                <!-- Identity -->
                <h6 class="text-muted mb-3">Identity</h6>

                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <s:textfield id="firstName" name="customer.firstName"
                                         cssClass="form-control" theme="simple"/>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <s:textfield id="lastName" name="customer.lastName"
                                         cssClass="form-control" theme="simple"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="userID">User ID</label>
                    <s:textfield id="userID" name="customer.userID"
                                 cssClass="form-control" theme="simple"/>
                </div>

                <!-- Contact -->
                <h6 class="text-muted mt-4 mb-3">Contact</h6>

                <div class="row">
                    <!-- Phone + Call -->
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <div class="input-group">
                                <s:textfield id="phone" name="customer.phone"
                                             cssClass="form-control" theme="simple"/>

                                <div class="input-group-append">
                                    <a href="tel:<s:property value='customer.phone'/>"
                                       class="btn btn-outline-primary">
                                        Call
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Email + Email button -->
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-group">
                                <s:textfield id="email" name="customer.email"
                                             cssClass="form-control" theme="simple"/>

                                <div class="input-group-append">
                                    <a href="mailto:<s:property value='customer.email'/>"
                                       class="btn btn-outline-secondary">
                                        Email
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Address -->
                <h6 class="text-muted mt-4 mb-3">Address</h6>

                <div class="form-group">
                    <label for="street">Street</label>
                    <s:textfield id="street" name="customer.street"
                                 cssClass="form-control" theme="simple"/>
                </div>

                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="city">City</label>
                            <s:textfield id="city" name="customer.city"
                                         cssClass="form-control" theme="simple"/>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="provinceState">Province/State</label>
                            <s:select id="provinceState"
                                      name="customer.province"
                                      list="codeValues.provinces"
                                      listKey="key"
                                      listValue="value"
                                      cssClass="form-control"
                                      headerKey=""
                                      headerValue="Select a Province"
                                      theme="simple"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="country">Country</label>
                    <s:textfield id="country" name="customer.country"
                                 cssClass="form-control" theme="simple"/>
                </div>

            </div>
        </div>

        <!-- Buttons -->
<div class="form-group no-print">
    <div class="d-flex justify-content-between mt-4">

        <s:submit value="Save" cssClass="btn btn-primary"/>

        <div>

            <!-- Only show if returnParams exists -->
            <s:if test="%{returnParams != null && returnParams.trim() != ''}">
                <button type="button"
                        class="btn btn-outline-primary mr-2"
                        onclick="navigateToReservation()">
                    Return to Reservation
                </button>
            </s:if>

            <button type="button"
                    class="btn btn-secondary"
                    onclick="navigateToCustomerSearch()">
                Return to Search
            </button>

        </div>

    </div>
</div>

    </s:form>

</div>

