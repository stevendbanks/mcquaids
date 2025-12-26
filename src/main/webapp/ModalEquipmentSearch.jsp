<!-- EquipmentSearch Search Modal -->
<div id="modalEquipmentSearchDialog" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Search Equipment</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
			</div>
			<div class="modal-body">
				<!-- Error Message -->
				<div id="modalErrorMessage" class="alert alert-danger"
					style="display: none;"></div>

				<form id="searchEquipmentForm">
					<div class="form-row">
						<div class="input-group">
							<input type="text" class="form-control" id="modalEquipmentNumber"
								placeholder="Equipment Number"
								onchange="resetOtherFields('modalEquipmentNumber')">
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="button">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>

					</div>
					

				</form>
				<div class="modal-footer">
					<button type="button" id="modalSearchEquipmentButton"
						class="btn btn-primary">Search</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
				<!--  Customer Search Results -->
				<table class="table mt-4">
					<thead>
						<tr>
                    <th scope="col">Equipment Number</th>
                    <th scope="col">Equipment Type</th>
                    <th scope="col">Type</th>
                    <th scope="col">Special Notes</th>
						</tr>
					</thead>
					<tbody id=modalEquipmentSearchResults>
						<!-- modalEquipmentSearchResults will be inserted here -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="/mcquaids/javascript/ModalWindowJS.js"></script>
<!-- EquipmentSearch Search Modal /  End-->