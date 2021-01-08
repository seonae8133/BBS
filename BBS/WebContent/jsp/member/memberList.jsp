<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.pagination {
  display: inline-block;
}

.pagination li {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
  border: 1px solid #4CAF50;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
</head>
<body>
<jsp:include page="../menu/mainMenu.jsp" />
<hr>
<div align="center">
	<div><h1>회원목록 정보</h1></div>
	<form name="searchfrm" >
		<input type="p" name ="p">
		이름 : <input name="name">
		권한 : <input name="author">
		<button >검색</button>
	</form>
	<div>
		<table border="1">
			<tr>
				<th width="100">아이디</th>
				<th width="100">이    름</th>
				<th width="200">주    소</th>
				<th width="150">전화번호</th>
				<th width="150">가입일자</th>
				<th width="100">권     한</th>
				
			</tr>
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.id}</td>
					<td>${member.name}</td>
					<td>${member.address}</td>
					<td>${member.tel}</td>
					<td>${member.enterdate}</td>
					<td>${member.author}</td>
			
				</tr>
			</c:forEach>
		</table>
		<script>
			function goPage(p) {
				//location.href="memberList.do?p="+p;
				searchfrm.p.value=p;
				searchfrm.submit();
			}
		</script>
		<my:paging paging="${paging}" jsfunc="goPage"/>
		
	</div>
</div>
</body>
</html>