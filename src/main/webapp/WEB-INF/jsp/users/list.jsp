<%@ include file="/WEB-INF/header.jsp"%>

<%@ include file="/WEB-INF/menu.jsp"%>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">

	<input type="hidden" id="controller" value="users/" />

		<div class="row">
			<div class="col-sm-12">
				<section class="panel">
					<header class="panel-heading">
						<fmt:message key="system.title.users" />
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
						

						<div class="adv-table">
							<table class="display table table-bordered table-striped"
								id="dynamic-table">
								<thead>
									<tr>
										<th>ID</th>
										<th><fmt:message key="field.label.name" /></th>
										<th><fmt:message key="field.label.profile" /></th>
										<th><fmt:message key="field.label.approved" /></th>
										<th><fmt:message key="field.label.actions" /></th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="u" items="${userList}">
										<tr>
											<td><a href="#myModal" data-toggle="modal" data-update="${u.id}" class="update">${u.id }</a></td>
											<td><a href="#myModal" data-toggle="modal" data-update="${u.id}" class="update">${u.firstName } ${u.lastName } </a></td>
											<td><a href="#myModal" data-toggle="modal" data-update="${u.id}" class="update">${u.profile.label }</a></td>
											<td><a href="#myModal" data-toggle="modal" data-update="${u.id}" class="update">${u.approved }</a></td>
											<td>
												<a href="#" data-remove="${u.id}" class="remove" title="<fmt:message key="button.label.remove" />">
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
						<h4 class="modal-title">Adicionar Usuário</h4>
					</div>
					<div class="modal-body">

						


						<form class="form-horizontal" role="form" autocomplete="off"
							id="Form" enctype="multipart/form-data">
							
							<input type="hidden" class="form-control" id="user.id"
							name="user.id" >
							<input type="hidden" class="form-control" id="user.approved"
							name="user.approved" value="1">

							<div class="form-group">
								<label for="user.firstName" class="col-lg-2 col-sm-2 control-label"> Nome</label>
								<div class="col-lg-5">
									<input type="text" class="form-control" id="user.firstName"
										name="user.firstName" userholder="Primeiro Nome" required>
								</div>
							
								<div class="col-lg-5">
									<input type="text" class="form-control" id="user.lastName"
										name="user.lastName" userholder="Último Nome" required>
								</div>
							</div>
							<div class="form-group">
								<label for="user.address.phone"
									class="col-lg-2 col-sm-2 control-label">Telefone</label>
								<div class="col-lg-10">
									<input type="text" class="form-control telefone"
										id="user.address.phone" name="user.address.phone"
										userholder="Telefone" required>
								</div>
							</div>

							<div class="form-group">
								<label for="user.address.name"
									class="col-lg-2 col-sm-2 control-label">Endereço</label>
								<div class="col-lg-7">
									<input type="text" class="form-control nameAddress"
										id="user.address.name" name="user.address.name"
										userholder="Rua, Avenida, Travessa" required>
								</div>

								<label for="user.address.number"
									class="col-lg-1 col-sm-1 control-label">Num</label>
								<div class="col-lg-2">
									<input type="text" class="form-control numberAddress"
										id="user.address.number" name="user.address.number"
										userholder="">
								</div>
							</div>

							<div class="form-group">
								<label for="user.address.district"
									class="col-lg-2 col-sm-2 control-label">Bairro</label>
								<div class="col-lg-10">
									<input type="text" class="form-control districtAddress"
										id="user.address.district" name="user.address.district"
										userholder="Bairro" required>
								</div>
							</div>

							<div class="form-group">
								<label for="user.address.city"
									class="col-lg-2 col-sm-2 control-label">Cidade</label>
								<div class="col-lg-10">
									<select class="form-control input-sm m-bot16 cityAddress"
										id="user.address.city" name="user.address.city" required>
										<option>Niterói</option>
										<option>Rio de Janeiro</option>
										<option>São Gonçalo</option>
									</select>
								</div>
							</div>


							<div class="form-group">
								<label for="user.address.lat"
									class="col-lg-2 col-sm-2 control-label">Latitude</label>
								<div class="col-lg-4">
									<input type="text" class="form-control lat"
										id="user.address.lat" name="user.address.lat">
								</div>

								<label for="user.address.lng"
									class="col-lg-2 col-sm-2 control-label">Longitude</label>
								<div class="col-lg-4">
									<input type="text" class="form-control lng"
										id="user.address.lng" name="user.address.lng" >
								</div>
							</div>
							<a href="#" id="ShowLocationGoogleMaps">Setar Latitude</a>


							<div class="form-group">
								<label for="user.website"
									class="col-lg-2 col-sm-2 control-label">WebSite</label>
								<div class="col-lg-10">
									<input type="url" class="form-control" id="user.website"
										name="user.website" userholder="WebSite">
								</div>
							</div>
							<div class="form-group">
								<label for="user.fanpage"
									class="col-lg-2 col-sm-2 control-label">Fã-Page</label>
								<div class="col-lg-10">
									<input type="url" class="form-control" id="user.fanpage"
										name="user.fanpage" userholder="Fã-Page">
								</div>
							</div>

							<div class="form-group">
								<label for="user.fanpage"
									class="col-lg-2 col-sm-2 control-label">Descrição</label>
								<div class="col-lg-10">
									<textarea class="form-control" id="user.description"
										name="user.description" rows="6"></textarea>
								</div>
							</div>




							<div class="form-group">
								<label for="user.logo" class="col-lg-2 col-sm-2 control-label">Logo</label>
								<div class="col-lg-10">
									<input type="file" id="photo" name="photo">
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
<script src="${pageContext.request.contextPath}/static/js/scripts-users.js"></script>

<!--dynamic table initialization -->
<script	src="${pageContext.request.contextPath}/static/js/dynamic_table_init.js"></script>