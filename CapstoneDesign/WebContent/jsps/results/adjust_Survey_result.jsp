<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>adjust_Survey_result.jsp</title>
</head>
<body>
	This is adjust_Survey_result.jsp
	<br /> --adjusted Survey data--
	<br /> primaryKey : ${primary_key}
	<br /> beaconNo : ${beacon_no}
	<br /> URL : ${url}
	<br /> Type : ${type}
	<br /> Writer : ${Writer}
	<br /> RewardType : ${RewardType}
	<br /> Reward : ${Reward}
	
	
	<form action="index.jsp" method="post" >
		<input type="submit" value="Ȩ���� �̵�" /> <br />
	</form>
</body>
</html>