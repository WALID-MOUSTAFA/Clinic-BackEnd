<%@include file="../layout/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="login-card">
			<div class="col s12 m8 ">
				<div class="card">
					<form:form method="POST" modelAttribute="loginForm">

						<div class="input-field">
							<i class="material-icons prefix">account_circle</i>
							<label for="username">username</label>
							<form:input path="username" name="username" type="text" required="true" class="validate" />
							<form:errors path="username" cssClass="validate-error" element="div" />
	
						</div>
						
						
						<div class="input-field">
							<i class="material-icons prefix">lock</i>
							<label for="password">password</label>
							<form:input path="password" name="password" type="password" required="true" class="validate" />
							<form:errors path="password" cssClass="validate-error" element="div" />
	
						</div>

						
						
						
						<button class="btn waves-effect waves-light" type="submit" name="action">Submit
							<i class="material-icons right">send</i>
						</button>

					</form:form>
				</div>
			</div>
		</div>
		
	</div>
</div>

<%@include file="../layout/footer.jsp" %>
