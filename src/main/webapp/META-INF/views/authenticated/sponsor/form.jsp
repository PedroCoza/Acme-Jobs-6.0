<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.sponsor.form.label.organisation" path="organisation" />

	<acme:form-submit test="${command != 'create' && isCard}" method="get" code="authenticated.sponsor.submit.card" action="/sponsor/credit-card/show?id=${cardId}"/>
	
	<acme:form-submit test="${command != 'create' && !isCard }" method="get" code="authenticated.sponsor.create.card" action="/sponsor/credit-card/create"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.label.create"
		action="/authenticated/sponsor/create" />
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.form.label.update"
		action="/authenticated/sponsor/update" />


	<acme:form-return code="authenticated.sponsor.form.button.return" />
</acme:form>
