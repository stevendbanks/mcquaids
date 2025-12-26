<!-- Customer Search Modal -->
			<div id="ModalCustomerSearch" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Search For Customer</h4>
							<button type="button" class="close" data-dismiss="modal">×</button>
						</div>
						<div class="modal-body">
							<!-- Error Message -->
							<div id="ModalerrorMessage" class="alert alert-danger"
								style="display: none;"></div>

							<form id="searchForm">
								<div class="form-row">
									<div class="form-group col-md-3">
										<label for="modalCustomerID">Customer ID</label> <input
											type="text" class="form-control" id="modalCustomerID">
									</div>
									<div class="form-group col-md-4">
										<label for="modalCustomerName">Customer Name</label> <input
											type="text" class="form-control" id="modalCustomerName">
									</div>
									<div class="form-group col-md-3">
										<label for="modalPhoneNumber">Phone Number</label> <input
											type="tel" class="form-control" id="modalPhoneNumber"
											placeholder="###-###-####"
											pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
											title="Format: 902-314-1232">
									</div>
									<div class="form-group col-md-3">
										<label for="modalEmail">Email</label> <input type="text"
											class="form-control" id="modalEmail">
									</div>
								</div>
								
							</form>
							<div class="modal-footer">
							    <button type="button" id="searchCustomerModalButton" class="btn btn-primary">Search</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
							<!--  Customer Search Results -->
							        <table class="table mt-4">
            <thead>
                <tr>
                    <th scope="col">Customer ID</th>
                    <th scope="col">Customer Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone Number</th>
                </tr>
            </thead>
            <tbody id=modalCustomerSearchResults>
                <!-- modalCustomerSearchResults will be inserted here -->
            </tbody>
        </table>
						</div>
					</div>
				</div>
			</div>
			
		<script type="text/javascript" src="/mcquaids/javascript/ModalWindowJS.js"></script>				
<!-- Customer Search Modal /  End-->