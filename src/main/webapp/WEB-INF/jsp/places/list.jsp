<%@ include file="/WEB-INF/header.jsp"%>

<%@ include file="/WEB-INF/menu.jsp"%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">

	<input type="hidden" id="controller" value="places/" />

		<div class="row">
			<div class="col-sm-12">
				<section class="panel">
					<header class="panel-heading">
						<fmt:message key="system.title.places" />
						<span class="tools pull-right"> <a href="javascript:;"
							class="fa fa-chevron-down"></a> <a href="javascript:;"
							class="fa fa-cog"></a> <a href="javascript:;" class="fa fa-times"></a>
						</span>
					</header>
					<div class="panel-body">

						<a href="#myModal" data-toggle="modal" title="<fmt:message
								key="button.label.add" />"><i class="fa fa-plus"></i>
								<fmt:message
								key="button.label.add" />
								</a> 
								| 
						<a href="#" id="saveJSON"
							data-value="places" data-toggle="modal" title="<fmt:message
								key="button.label.saveJSON" />">
							<i class="fa fa-archive"></i>
							<fmt:message
								key="button.label.saveJSON" />
							</a>

						<div class="adv-table">
							<table class="display table table-bordered table-striped"
								id="dynamic-table">
								<thead>
									<tr>
										<th>ID</th>
										<th><fmt:message key="field.label.name" /></th>
										<th><fmt:message key="field.label.city" /></th>
										<th><fmt:message key="field.label.approved" /></th>
										<th><fmt:message key="field.label.actions" /></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${placeList}">
										<tr>
											<td><a href="#myModal" data-toggle="modal" data-update="${p.id}" class="update">${p.id }</a></td>
											<td><a href="#myModal" data-toggle="modal" data-update="${p.id}" class="update">${p.name }</a></td>
											<td><a href="#myModal" data-toggle="modal" data-update="${p.id}" class="update">${p.address.city }</a></td>
											<td><a href="#myModal" data-toggle="modal" data-update="${p.id}" class="update">${p.approved }</a></td>
											<td>
												<a href="#" data-remove="${p.id}" class="remove" title="<fmt:message key="button.label.remove" />">
													<i class="fa fa-trash-o"></i>
												</a>
												
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</section>
			</div>
		</div>
		<!-- page end-->

		<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"
			tabindex="-1" id="myModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button aria-hidden="true" data-dismiss="modal" class="close"
							type="button">×</button>
						<h4 class="modal-title">Adicionar estabelecimento</h4>
					</div>
					<div class="modal-body">

						


						<form class="form-horizontal" role="form" autocomplete="off"
							id="Form" enctype="multipart/form-data">
							
							<input type="hidden" class="form-control" id="place.id"
							name="place.id" >
							<input type="hidden" class="form-control" id="place.approved"
							name="place.approved" value="1">

							<div class="form-group">
								<label for="place.name" class="col-lg-2 col-sm-2 control-label">Nome</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="place.name"
										name="place.name" placeholder="Nome" required>
								</div>
							</div>

							<div class="form-group">
								<label for="place.address.phone"
									class="col-lg-2 col-sm-2 control-label">Telefone</label>
								<div class="col-lg-10">
									<input type="text" class="form-control telefone"
										id="place.address.phone" name="place.address.phone"
										placeholder="Telefone" required>
								</div>
							</div>

							<div class="form-group">
								<label for="place.address.name"
									class="col-lg-2 col-sm-2 control-label">Endereço</label>
								<div class="col-lg-7">
									<input type="text" class="form-control nameAddress"
										id="place.address.name" name="place.address.name"
										placeholder="Rua, Avenida, Travessa" required>
								</div>

								<label for="place.address.number"
									class="col-lg-1 col-sm-1 control-label">Num</label>
								<div class="col-lg-2">
									<input type="text" class="form-control numberAddress"
										id="place.address.number" name="place.address.number"
										placeholder="">
								</div>
							</div>

							<div class="form-group">
								<label for="place.address.district"
									class="col-lg-2 col-sm-2 control-label">Bairro</label>
								<div class="col-lg-10">
									<input type="text" class="form-control districtAddress"
										id="place.address.district" name="place.address.district"
										placeholder="Bairro" required>
								</div>
							</div>

							<div class="form-group">
								<label for="place.address.city"
									class="col-lg-2 col-sm-2 control-label">Cidade</label>
								<div class="col-lg-10">
									<select class="form-control input-sm m-bot16 cityAddress"
										id="place.address.city" name="place.address.city" required>
										<option>Niterói</option>
										<option>Rio de Janeiro</option>
										<option>São Gonçalo</option>
									</select>
								</div>
							</div>


							<div class="form-group">
								<label for="place.address.lat"
									class="col-lg-2 col-sm-2 control-label">Latitude</label>
								<div class="col-lg-4">
									<input type="text" class="form-control lat"
										id="place.address.lat" name="place.address.lat">
								</div>

								<label for="place.address.lng"
									class="col-lg-2 col-sm-2 control-label">Longitude</label>
								<div class="col-lg-4">
									<input type="text" class="form-control lng"
										id="place.address.lng" name="place.address.lng" >
								</div>
							</div>
							<a href="#" id="ShowLocationGoogleMaps">Setar Latitude</a>


							<div class="form-group">
								<label for="place.website"
									class="col-lg-2 col-sm-2 control-label">WebSite</label>
								<div class="col-lg-10">
									<input type="url" class="form-control" id="place.website"
										name="place.website" placeholder="WebSite">
								</div>
							</div>
							<div class="form-group">
								<label for="place.fanpage"
									class="col-lg-2 col-sm-2 control-label">Fã-Page</label>
								<div class="col-lg-10">
									<input type="url" class="form-control" id="place.fanpage"
										name="place.fanpage" placeholder="Fã-Page">
								</div>
							</div>

							<div class="form-group">
								<label for="place.fanpage"
									class="col-lg-2 col-sm-2 control-label">Descrição</label>
								<div class="col-lg-10">
									<textarea class="form-control" id="place.description"
										name="place.description" rows="6"></textarea>
								</div>
							</div>




							<!-- div class="form-group">
								<label for="place.logo" class="col-lg-2 col-sm-2 control-label">Logo</label>
								<div class="col-lg-10">
									<input type="file" id="photo" name="photo">
								</div>
							</div-->
							
							
							 <div class="form-group last">
                                <label class="col-lg-2 col-sm-2 control-label" >Logo</label>
                                <div class="col-md-10">
                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
				
										<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt="" />                                            

                                        </div>
                                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                        <div>
                                                   <span class="btn btn-white btn-file">
                                                   <span class="fileupload-new"><i class="fa fa-paper-clip"></i> Selecione a imagem</span>
                                                   <span class="fileupload-exists"><i class="fa fa-undo"></i> Alterar</span>
                                                   <input type="file" class="default" id="photo" name="photo"/>
                                                   </span>                                          
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>


							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<button type="submit" class="btn btn-default">Salvar</button>
								</div>
							</div>
						</form>

					</div>

				</div>
			</div>
		</div>




	</section>
</section>
<!--main content end-->



</section>


<%@ include file="/WEB-INF/scripts.jsp"%>


<script	src="${pageContext.request.contextPath}/static/js/dynamic_table_init.js"></script>
<script src="${pageContext.request.contextPath}/static/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/static/js/scripts-places.js"></script>

<!--dynamic table initialization -->
<script	src="${pageContext.request.contextPath}/static/js/dynamic_table_init.js"></script>