<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<link rel="stylesheet"
		href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script type="text/javascript"
		src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<script type="text/javascript" src="jquery.ui.datepicker-ko.js"></script>
	<script type="text/javascript">
		var year, month, day;
		$(document).ready(function() {
			$("#date_text").datepicker({
				onSelect : function(dateText, inst) {
					var dateArr = dateText.split("/");
					month = dateArr[0];
					day = dateArr[1];
					year = dateArr[2];
				}
			});
			//showOn: "button";
		});
	</script>
	<script type="text/javascript">
		var year, month, day;
		$(document).ready(function() {
			$("#date_text2").datepicker({
				onSelect : function(dateText.inst) {
					var dateArr = dateText.split("/");
					month = dateArr[0];
					day = dateArr[1];
					year = dateArr[2];
				}
			});
			//showOn: "button";
		});	
	</script>
	<p>������ �߰�</p>
	
	<form action="Add_Poster_DB" method="post"
		enctype="multipart/form-data">
		Beacon��ȣ: <select name="Beacon_no">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select> <br /> 
		�̹������� ���� <input type="file" name="designated_file" /> <br />
		URL����(http://�����ϰ� �Է�) <input type="text" name="URL" /> <br /> 
		��¥ <input type="text" id="date_text" size="6" maxlength="10" name="dates">
		<input type="hidden" name="Type" value="Poster" /> <br /> 
		<input type="submit" value="������ �߰� ����" /> <br />
	</form>	<br />
	
	---------------------------------------------

	<p>�����߰�<p>
	
	<form action="Add_Survey_DB" method="post">
		Beacon��ȣ: <select name="Beacon_no">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
		</select> <br /> 
		����DOCS���� URL�Է� <input type="text" name="URL" /> <br /> 
		�ۼ��� �Է� <input type="text" name="Writer" /> <br /> 
		����Ÿ�� �Է� <input type="text" name="RewardType" /> <br /> 
		���� �Է� <input type="text" name="Reward" /><br /> 
		��¥  <input type="text" id="date_text2" size="6" maxlength="10" name="dates"> 
		<input type="hidden" name="Type" value="Survey" /> 
		<input type="submit" value="���� �߰� ����" />
		<br />
	</form>

	<br />
	<form action="index.jsp" method="post">
		<input type="submit" value="Ȩ���� �̵�" /> <br />
	</form>

</body>
</html>