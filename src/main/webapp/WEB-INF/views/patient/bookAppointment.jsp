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
	<link rel="stylesheet" type="text/css" href="${rootUrl }resources/build/jquery.datetimepicker.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
	<script src="${rootUrl }resources/build/jquery.datetimepicker.full.min.js"></script>
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
		<h1>Register Appointment</h1>
		<!-- main content -->

		<div class="main patient">
			<!-- appointment book  -->
			<form action="../appointment.do" method="post">
				<div class="aapointmentBox">
				<div>
					<div class="appP">
						<label>Please select Date and Time : </label><br>
						<input name="dateTime" id="datetimepicker3" type="text" style="display: none;" />
					</div>
					<div class="appP">
						<span class="name"><a href="../../doctor/profile/${doctor.id }"><i class="fa fa-stethoscope greencolor" aria-hidden="true"></i> ${doctor.firstName}
							${doctor.lastName}</a></span><br> <span>Address
						:${doctor.street}, ${doctor.city}, ${doctor.state} </span><br> <span>Contact
						: ${doctor.contact}</span><br> <span>Email :
						${doctor.email}</span>
					<hr>
					</div>
				</div>
				<br>
				<div>
					<div>
						<label>Write some messages : </label><br>
						<textarea name="desc" rows="10" class="message"></textarea>
					</div>
				</div>
				<p align="center">
					<input class="hidden" value="${doctor.id}" name="docid"/>
					<input name="desc" class="aptbutton" type="submit"  value="Send Request" />
				</p>
				</div>
			<!-- appointment book  -->
			</form>
		</div>

		<!-- side bar -->
		<div class="sidebar">
			<ol>
				<li><i class="fa fa-clock-o greencolor" aria-hidden="true"></i><a
					href="../">My Appointments</a></li>
				<li><i class="fa fa-user greencolor" aria-hidden="true"></i><a
					href="../profile/${appointment.patient.id}">My Profile</a></li>
			</ol>
		</div>
	</div>

	<div class="footer">
		<p>All right reserved @2016</p>
		<p>Appointment reserved system</p>
	</div>
</body>

<script type="text/javascript">// <![CDATA[

	jQuery(function() {

		jQuery('#datetimepicker3').datetimepicker({
			inline : true,
			weeks : true
		});

	});
</script>

</html>