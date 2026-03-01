<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Reservation Delivery Address Panel -->
<div class="card mb-3" id="reservation-delivery-address-card">

    <div class="card-header d-flex justify-content-between align-items-center">
        <span>Delivery Address</span>
<button type="button"
        class="btn btn-sm btn-outline-secondary delivery-toggle"
        data-target="#reservation-delivery-address-body">
    Toggle
</button>
    </div>

    <div class="card-body" id="reservation-delivery-address-body">

        <!-- Address Source -->
<div class="form-group">
    <label class="form-label d-block">Address Source</label>

    <div class="d-flex align-items-center">

        <!-- CUSTOMER = true -->
        <div class="form-check mr-4">
            <input type="radio"
                   class="form-check-input"
                   name="reservation.deliverySameAsCustomer"
                   id="useCustomerAddress"
                   value="true"
                   <s:if test="reservation.deliverySameAsCustomer == true">checked</s:if>>
            <label class="form-check-label" for="useCustomerAddress">
                Use Customer Address
            </label>
        </div>

        <!-- ALTERNATIVE = false -->
        <div class="form-check">
            <input type="radio"
                   class="form-check-input"
                   name="reservation.deliverySameAsCustomer"
                   id="useAlternativeAddress"
                   value="false"
                   <s:if test="reservation.deliverySameAsCustomer == false">checked</s:if>>
            <label class="form-check-label" for="useAlternativeAddress">
                Use Alternative Address
            </label>
        </div>

    </div>
</div>


        <!-- Delivery Address Fields -->
        <div id="delivery-address-fields">

            <div class="form-group">
                <label for="deliveryStreet">Street</label>
                <s:textfield id="deliveryStreet"
                             name="reservation.deliveryStreet"
                             cssClass="form-control"/>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="deliveryCity">City</label>
                    <s:textfield id="deliveryCity"
                                 name="reservation.deliveryCity"
                                 cssClass="form-control"/>
                </div>

                <div class="form-group col-md-4">
                    <label for="deliveryProvince">Province</label>
                    <s:textfield id="deliveryProvince"
                                 name="reservation.deliveryProvince"
                                 cssClass="form-control"/>
                </div>

                <div class="form-group col-md-4">
                    <label for="deliveryPostal">Postal Code</label>
                    <s:textfield id="deliveryPostal"
                                 name="reservation.deliveryPostalCode"
                                 cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="deliveryCountry">Country</label>
                <s:textfield id="deliveryCountry"
                             name="reservation.deliveryCountry"
                             cssClass="form-control"/>
            </div>

        </div>

<!--         <button id="save-delivery-address-btn" -->
<!--                 type="button" -->
<!--                 class="btn btn-primary btn-block mt-3"> -->
<!--             Save Delivery Address -->
<!--         </button> -->

    </div>
</div>
