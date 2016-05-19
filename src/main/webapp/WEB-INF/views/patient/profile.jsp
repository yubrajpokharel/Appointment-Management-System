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
			<li><a class="active"  href="${rootUrl}p/profile/${patientId}">Profile</a></li>
			<li><a href="${rootUrl}p/">Appointment</a></li>
			<li class="right"><a href="${rootUrl}logout.do">Logout</a></li>
			<li class="right">
				<form action="${rootUrl}search/" method="post"> <input name="query" type="text" class="search" placeholder="Search..."></input></form>
			</li>

		</ul>
	</div>
	<div class="content">
		<h1>
			<i class="fa fa-user-plus greencolor" aria-hidden="true"></i>
			${profile.fname} ${profile.lastName}
		</h1>
		<!-- main content -->

		<div class="main">
			<!-- latest posts related to the user -->
			<div class="lists">
				<div class="imagesectionProfile">
					<img
						src="http://www.keenthemes.com/preview/metronic/theme/assets/pages/media/profile/profile_user.jpg"
						class="userpicBig" title="doctor" alt="doctor">
				</div>
				<div class="detailsection">
					<span class="name"><a href="${rootUrl}p/profile/${patientId}">${profile.firstName}
							${profile.lastName}</a></span><br> <span>Address
						:${profile.street}, ${profile.city}, ${profile.state} </span><br> <span>Contact
						: ${profile.contact}</span><br> <span>Email :
						${profile.email}</span>
					<hr>
					<div class="bio">
						<p>
							<i class="fa fa-clock-o greencolor" aria-hidden="true"></i>
								Total pending Appointment : ${future} 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<i class="fa fa-clock-o redcolor"
								aria-hidden="true"></i> Total past Appointments : ${past}
						</p>
						<hr>
						<p>	
							
							<c:if test="${!empty doctors }">
								<i class="fa fa-user-md greencolor" aria-hidden="true"></i> My Doctors
								<ol>
									<c:forEach items="${doctors}" var="doc" >
										<li>
											<a href="${rootUrl}doctor/profile/${doc.id}"><i class="fa fa-stethoscope greencolor" aria-hidden="true"></i> ${doc.firstName} </a>
										</li>
									</c:forEach>
								</ol>
							</c:if>
							
							
							
						</p>
					</div>
				</div>
			</div>


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