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

<style>
 a {text-decoration: none}
</style>


<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>Train Info</caption>
	<colgroup>
		<col width="3%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="16%"/>
		<col width="16%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
	</colgroup>
	<tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">번호</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">출발역  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">도착역  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">출발시각   </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">도착시각  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">소요시간  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">운임  </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">열차종류   </span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">열차번호   </span></b></font></p>
        </td>
        
    </tr>
    <c:choose>
    <c:when test="${empty infoList}">
	<tr>
        <td colspan="5">
            <p align="center"><b><span style="font-size:9pt;">등록된 게시물이 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
	<c:forEach items="${infoList}" var="st" varStatus="state"><!-- pageList.getContent() -->
		    <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${state.count}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.depplacename}</span></p>
		        </td>
		        <td bgcolor="">
					<p align="center"><span style="font-size:9pt;">
					${st.arrplacename}
					</span></p>
		        </td>
		        
		       
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.depplandtime}</span></p>
		        </td>
		         
		        
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.arrplandtime}</span></p>
		        </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.duration}</span></p>
		        </td>
		         <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            <fmt:formatNumber type="number" maxFractionDigits="3" value="${st.adultcharge}"/>원 </span></p>
		        </td>
		         <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.traingradename}</span></p>
		        </td>
		         <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${st.trainno}</span></p>
		        </td>
		    </tr>
    </c:forEach>
	</c:otherwise>
    </c:choose>
</table>

</body>
</html>