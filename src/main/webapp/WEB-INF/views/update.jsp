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
<form method="post">
<table>
	<caption>게시물 수정하기</caption>
<tr>
   <th>글번호</th>
   <td>${boardDTO.no}<input type="hidden" name="no" value="${boardDTO.no}" readonly="readonly"> </td>
</tr>
<tr>
   <th>제목</th>
   <td><input type="text" name="title" autofocus="autofocus" 
      required="required" value="${boardDTO.title}"/></td>
</tr>
<tr>
   <th>이름</th>
   <td>${boardDTO.name}<input type="hidden" name="name" value="${boardDTO.name} readonly="readonly"/></td>
</tr>
<tr>
   <th>내용</th>
   <td><textarea name="content" rows="5" cols="40" 
      required="required">${boardDTO.content}</textarea></td>
</tr>
<tr>
   <th>비밀번호</th>
   <td><input type="password" name="password" required="required" value=""/>
</tr>
<tr>
   <td colspan="2" align="center"><input type="submit" value="완료" /></td>
</tr>
</table>
</form>

</body>
</html>