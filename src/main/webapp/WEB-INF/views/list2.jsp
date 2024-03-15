<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>전화번호부</h1>

	<h2>리스트</h2>

	<p>등록된 전화번호 리스트입니다.</p>
	
	<div id="list"> 
	</div>
	
	<!-- 
	<c:forEach items="${requestScope.person }" var="person">
	<table border="1">
		<tr>
			<th>이름(name)</th>
			<td>${person.name }</td>
		</tr>
		<tr>
			<th>핸드폰(hp)</th>
			<td>${person.hp }</td>
		</tr>
		<tr>
			<th>회사(company)</th>
			<td>${person.company }</td>
		</tr>
		<tr>
			<td><a
				href="/phonebook6/delete?no=${person.personId }">[삭제]</a>
			</td>
			<td><a
				href="/phonebook6/modifyform?no=${person.personId }">[수정]</a>
			</td>
		</tr>

	</table>
	<br>
	</c:forEach>
	 -->
	<a href="">추가번호 등록</a>
	

	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

	<script>
	document.addEventListener("DOMContentLoaded", function(){
		//리스트 뽑기
		getList();
		
		//삭제
		let list = document.querySelector("#list");
		list.addEventListener("click", function(){
			event.preventDefault();
			console.log(event.target.dataset.no);
			//데이터 셋 사용법이 중요
			
			if(event.target.textContent == "[삭제]"){
				let no = event.target.dataset.no;
				console.log(no);
				phoneDelete(no);
			}
		});
	});
	
	
	
	//함수
	function getList(){
		axios({
			method: 'get', // put, post, delete 
			url: '${pageContext.request.contextPath}/api/phonebooks',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			//params: phoneVo, //get방식 파라미터로 값이 전달
			//data: phoneVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
		})
		.then(function (response) {
			console.log(response); //수신데이타
			
			let p = response.data;
			
			for(let i = 0; i<p.length; i++){
				let phoneVo = p[i]
				console.log(phoneVo);
				render(phoneVo);
			}
		})
		.catch(function (error) {
			console.log(error);
		}); 
	};
	
	function render(phoneVo){
		//console.log(phoneVo);
		
		let personId = phoneVo.personId;
		let name = phoneVo.name;
		let hp = phoneVo.hp;
		let company = phoneVo.company
		
		let list = document.querySelector("#list");
		
		let str = "";
		str += "<table border='1'>";
		str += "<tr>";
		str += "	<th>이름(name)</th>";
		str += "	<td>"+name+"</td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<th>핸드폰(hp)</th>";
		str += "	<td>"+hp+"</td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<th>회사(company)</th>";
		str += "	<td>"+company+"</td>";
		str += "</tr>";
		str += "<tr>";
		str += "	<td><a href='/phonebook6/delete?no=${person.personId }' data-no="+personId+">[삭제]</a>";
		str += "	</td>";
		str += "	<td><a href='/phonebook6/modifyform?no=${person.personId }'>[수정]</a>";
		str += "	</td>";
		str += "</tr>";
		str += "</table>";
		
		
		
		list.insertAdjacentHTML("beforeend",str);
	};
	
	function phoneDelete(no){
		
		let phoneVo = {
				personId: no
		}
		
		console.log(phoneVo);
		
		axios({
			method: 'delete', // put, post, delete 
			url: '${pageContext.request.contextPath}/api/phonebooks',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			//params: phoneVo, //get방식 파라미터로 값이 전달
			data: phoneVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
		})
		.then(function (response) {
			console.log(response); //수신데이타
			console.log(response.data.personId);
			
			let no = response.data.personId; //->이숫자로 해당 리스트 화면에서 지우기
			
		})
		.catch(function (error) {
			console.log(error);
		}); 
	};
	
	
</script>


</body>
</html>