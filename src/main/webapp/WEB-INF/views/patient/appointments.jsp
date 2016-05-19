<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="today" class="java.util.Date" />
<!DOCTYPE html>
<html>
<head>
<title>My Appointments</title>
<spring:url value="/" var="rootUrl"></spring:url>
<link rel="stylesheet" type="text/css"
	href="${rootUrl}resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">
</head>
<body>
	<div class="header">
		<h1>
			CS4 - <span>there is nothing at the top but lets do it</span>
		</h1>
		<ul>
			
			<li><a href="${rootUrl}p/">Home</a></li>
			<li><a href="${rootUrl}doctor/">Doctors</a></li>
			<li><a href="${rootUrl}p/profile/${patientId}">Profile</a></li>
			<li><a class="active"  href="${rootUrl}p/">Appointment</a></li>
			<li class="right"><a href="${rootUrl}logout.do">Logout</a></li>
			<li class="right">
				<form action="${rootUrl}search/" method="post"> <input name="query" type="text" class="search" placeholder="Search..."></input></form>
			</li>

		</ul>
	</div>
	<div class="content">
		<h1>DashBoard</h1>
		<!-- main content -->

		<div class="main patient">
			<!-- latest posts related to the user -->
			
			
			<c:if test="${!empty appointments}">
				<c:forEach items="${appointments}" var="appointment">
			
					<div class="lists">
						<p>
							
							<c:choose>
								<c:when test="${today gt appointment.dateTime}">
								<i class="fa fa-heartbeat redcolor" aria-hidden="true"></i>&nbsp;
								<a href="profile/${appointment.patient.id}">You</a> 
									had
								</c:when>
								<c:otherwise>
								<i class="fa fa-heartbeat greencolor" aria-hidden="true"></i> &nbsp;
								<a href="profile/${appointment.patient.id}">You</a>  
									have 
								</c:otherwise>
							</c:choose>
							a appointment with
							<a href="${rootUrl}doctor/profile/${appointment.doctor.id}">
								<i class="fa fa-stethoscope" aria-hidden="true"></i>
								${appointment.doctor.firstName}, ${appointment.doctor.lastName}
							</a> on <fmt:formatDate type="both" value="${appointment.dateTime}" />
							<a href="a/${appointment.id}" class="readmore" href="#">View Details</a>
						</p>
					</div>
					
				</c:forEach>
			</c:if>
			<!-- latest posts ends -->
		</div>

		<!-- side bar -->
		<div class="sidebar">
			<ol>
				<li><i class="fa fa-clock-o greencolor" aria-hidden="true"></i><a href="${rootUrl}p/">My Appointments</a></li>
				<li><i class="fa fa-user greencolor" aria-hidden="true"></i><a href="${rootUrl}p/profile/${patientId}">My Profile</a></li>
			</ol>
		</div>
	</div>

	<div class="footer">
		<p>All right reserved @2016</p>
		<p>Appointment reserved system</p>
	</div>
</body>
</html>