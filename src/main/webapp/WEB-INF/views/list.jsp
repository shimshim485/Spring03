<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<caption>게시물 리스트</caption>
<tr>
	<td colspan="5">현재페이지:${pg} / 전체페이지수:${pageCount}</td>
</tr>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>
<c:forEach items="${list}" var="dto">
<tr>
	<td>${dto.no}</td>
	<td><a href="detail?no=${dto.no}&pg=${pg}">${dto.title}</a></td>
	<td>${dto.name}</td>
	<td><fmt:formatDate value="${dto.regdate}" type="date" /></td>
	<td>${dto.readcount}</td>
</tr>
</c:forEach>
<tr>
	<td colspan="5">
		<c:if test="${startPage != 1}">
			[<a href="list?pg=${startPage - 1}">이전블럭</a>]
		</c:if>
		
		<c:forEach begin="${startPage}" end="${endPage}" var="p">
			<c:if test="${p == pg}">${p}</c:if>
			<c:if test="${p != pg}"><a href="list?pg=${p}">${p}</a></c:if>
		</c:forEach>
		
		<c:if test="${endPage != pageCount}">
			[<a href="list?pg=${endPage + 1}">다음블럭</a>]
		</c:if>
	</td>
</tr>
</table><br/>
<a href="insert">글쓰기</a>
</body>
</html>