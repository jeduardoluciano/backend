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
										<th><fmt:message key="field.label.login" /></th>
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
											<td><a href="#myModal" data-toggle="modal" data-update="${u.id}" class="update">${u.login }</a></td>
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
										name="user.firstName" placeholder="Primeiro Nome" required>
								</div>
							
								<div class="col-lg-5">
									<input type="text" class="form-control" id="user.lastName"
										name="user.lastName" placeholder="Último Nome" required>
								</div>
							</div>
							
							
							<div class="form-group">
									<label for="user.profile"
										class="col-lg-2 col-sm-2 control-label"><fmt:message
											key="field.label.profile" /></label>
									<div class="col-lg-4">
										<select name="user.profile" id="user.profile" class="form-control input-sm m-bot16" required>
											<option value=""><fmt:message
													key="system.label.selected" />
												<fmt:message key="field.label.profile" /></option>
											<c:forEach items="${profiles}" var="profile">											
													<option value="${profile}">${profile.label}</option>												
											</c:forEach>
										</select>
									</div>
									
									
									<label for="user.profile"
										class="col-lg-2 col-sm-2 control-label"><fmt:message
											key="field.label.gender" /></label>
									<div class="col-lg-4">
										<select name="user.gender" id="user.gender" class="form-control input-sm m-bot16" required>
											<option value=""><fmt:message
													key="system.label.selected" />
												<fmt:message key="field.label.gender" /></option>
											<c:forEach items="${genders}" var="gender">											
													<option value="${gender}">${gender.label}</option>												
											</c:forEach>
										</select>
									</div>
									
									
								</div>
							
							
							<div class="form-group">
								<label for="user.login"
									class="col-lg-2 col-sm-2 control-label">Login</label>
								<div class="col-lg-10">
									<input type="email" class="form-control"
										id="user.login" name="user.login"
										placeholder="Login" required>
								</div>
							</div>
							
							
							<div class="form-group password">
								<label for="user.password"
									class="col-lg-2 col-sm-2 control-label">Senha</label>
								<div class="col-lg-10">
									<input type="password" class="form-control"
										id="user.password" name="user.password"
										placeholder="Senha" required>
								</div>
							</div>
							
							
								
							



							
							
							<div class="form-group last">
                                <label class="col-lg-2 col-sm-2 control-label" >Foto</label>
                                <div class="col-md-10">
                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                            
                                            <img 
                                            <c:choose>
                                            	<c:when  test="${user.photo != null }">
                                            	src="${user.photo}" 
                                            	</c:when>
                                            	<c:otherwise>
                                            	src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" 
                                            	</c:otherwise>
                                            </c:choose>
                                            	alt="" />
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
<script src="${pageContext.request.contextPath}/static/js/scripts-users.js"></script>

<!--dynamic table initialization -->
<script	src="${pageContext.request.contextPath}/static/js/dynamic_table_init.js"></script>