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
			
			<li><a href="${rootUrl}doctor/">Home</a></li>
			<li><a href="${rootUrl}doctor/">Doctors</a></li>
			<li><a href="${rootUrl}doctor/profile/${patientId}">Profile</a></li>
			<li><a class="active"  href="${rootUrl}doctor/profile/${docId}/appointment">Appointment</a></li>
			<li class="right"><a href="${rootUrl}logout.do">Logout</a></li>
			<li class="right">
				<form action="${rootUrl}search/" method="post"> <input name="query" type="text" class="search" placeholder="Search..."></input></form>
			</li>

		</ul>
	</div>
	<div class="content">
		<h1>Search Results</h1>

		<div class="main">
			<!-- latest posts related to the user -->
						<c:if test="${!empty listAppointments}">
				<c:forEach items="${listAppointments}" var="appointment">
			
					<div class="lists">
						<p>
							
							<c:choose>
								<c:when test="${today gt appointment.dateTime}">
								<i class="fa fa-heartbeat redcolor" aria-hidden="true"></i>&nbsp;
								<a href="${rootUrl}doctor/profile/${docId}">You</a> 
									had
								</c:when>
								<c:otherwise>
								<i class="fa fa-stethoscope greencolor" aria-hidden="true"></i> &nbsp;
								<a href="${rootUrl}doctor/profile/${docId}">You</a> 
									have 
								</c:otherwise>
							</c:choose>
							a appointment with
							<a href="${rootUrl}p/profile/${appointment.patient.id}">
								<i class="fa fa-heartbeat" aria-hidden="true"></i>
								${appointment.patient.firstName}, ${appointment.patient.lastName}
							</a> on <fmt:formatDate pattern="yyyy-MMM-dd hh:MM" type="both" value="${appointment.dateTime}" />
							<a href="${rootUrl}doctor/profile/${docId}/appointment/${appointment.id}" class="readmore">View Details</a>
						</p>
					</div>
					
				</c:forEach>
			</c:if>
			<!-- latest posts ends -->
		</div>
		<!-- side bar -->
		<div class="sidebar">
			<ol>
				<li><i class="fa fa-clock-o greencolor" aria-hidden="true"></i><a href="${rootUrl}doctor/profile/${docId}/appointment">My Appointments</a></li>
				<li><i class="fa fa-user greencolor" aria-hidden="true"></i><a href="${rootUrl}doctor/profile/${docId}">My Profile</a></li>
			</ol>
		</div>
	</div>

	<!-- header footer starts -->
	<div class="footer">
		<p>All right reserved @2016</p>
		<p>Appointment reserved system</p>
	</div>
</body>
</html>