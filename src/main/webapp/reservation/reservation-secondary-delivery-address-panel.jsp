<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Secondary Delivery Address Panel -->
<div class="card mb-3" id="reservation-secondary-delivery-address-card">

    <div class="card-header d-flex justify-content-between align-items-center">
        <span>Secondary Delivery Address (Optional)</span>
<button type="button"
        class="btn btn-sm btn-outline-secondary delivery-toggle"
        data-target="#reservation-secondary-delivery-address-body">
    Toggle
</button>
    </div>

    <div class="card-body" id="reservation-secondary-delivery-address-body">

        <!-- Secondary Delivery Address Fields -->
        <div id="secondary-delivery-address-fields">

            <div class="form-group">
                <label for="secondaryStreet">Street</label>
                <s:textfield id="secondaryStreet"
                             name="reservation.secondaryStreet"
                             cssClass="form-control"/>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="secondaryCity">City</label>
                    <s:textfield id="secondaryCity"
                                 name="reservation.secondaryCity"
                                 cssClass="form-control"/>
                </div>

                <div class="form-group col-md-4">
                    <label for="secondaryProvince">Province</label>
                    <s:textfield id="secondaryProvince"
                                 name="reservation.secondaryProvince"
                                 cssClass="form-control"/>
                </div>

                <div class="form-group col-md-4">
                    <label for="secondaryPostalCode">Postal Code</label>
                    <s:textfield id="secondaryPostalCode"
                                 name="reservation.secondaryPostalCode"
                                 cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="secondaryCountry">Country</label>
                <s:textfield id="secondaryCountry"
                             name="reservation.secondaryCountry"
                             cssClass="form-control"/>
            </div>

            <div class="form-group col-md-4 pl-0">
                <label for="secondaryDeliveryDate">Secondary Delivery Date</label>
                <s:textfield id="secondaryDeliveryDate"
                             name="reservation.secondaryDeliveryDate"
                             cssClass="form-control"
                             type="date"/>
            </div>

        </div>

<!--         <button id="save-secondary-delivery-address-btn" -->
<!--                 type="button" -->
<!--                 class="btn btn-primary btn-block mt-3"> -->
<!--             Save Secondary Delivery Address -->
<!--         </button> -->

    </div>
</div>