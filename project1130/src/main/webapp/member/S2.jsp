<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp"%>

<style>
table {
	width: 500px;
}
</style>

<Script>
	function fn_function1() {
		// alert("확인");
		f1.C_NO.value = f1.C_NAME.value;
	}

	// 회원번호가 2000 번 이상이면 50% 할인된 수강료가 자동으로 계산.
	function fn_function2() {
		// 수강료 
		price = f1.CLASS_NAME.value * 1000;
		if (f1.C_NO.value >= 20000) {
			price = price * 0.5
		}
		f1.TUITION.value = price;
	}

	function ck1() {
		if (f1.REGIST_MONTH.value == "") {
			alert("수강월을 입력해주세요");
			f1.REGIST_MONTH.focus();
			return false;
		} else if (f1.ADDRESS.value == "") {
			alert("강의장소를 입력해주세요");
			f1.ADDRESS.focus();
			return false;
		}
	}
</Script>

<section>
	<br>
	<div align="center">
		<h2>수강신청</h2>
		<br>
		<form name="f1" action="ProjectController" onSubmit="return ck1()">
			<input type=hidden name="sw" value="INSERT">
			<table border=1>
				<tr>
					<td>수강월</td>
					<td><input type=text name="REGIST_MONTH"> 예) 2022년03월
						-> 202203</td>
				</tr>
				<tr>
					<td>회원명</td>
					<td><select name="C_NAME" onChange="fn_function1()">
							<option value="">회원명</option>
							<option value="10001">홍길동</option>
							<option value="10002">장발장</option>
							<option value="10003">임꺽정</option>
							<option value="20001">성춘향</option>
							<option value="20002">이몽룡</option>
					</select></td>
				</tr>
				<tr>
					<td>회원번호</td>
					<td><input type=text name="C_NO" readonly> 예) 10001</td>
				</tr>
				<tr>
					<td>강의장소</td>
					<td><input type=text name="ADDRESS"></td>
				</tr>
				<tr>
					<td>강의명</td>
					<td><select name="CLASS_NAME" onChange="fn_function2()">
							<option value="">강의신청</option>
							<option value="100">초급반</option>
							<option value="200">중급반</option>
							<option value="300">고급반</option>
							<option value="400">심화반</option>
					</select></td>
				</tr>
				<tr>
					<td>수강료</td>
					<td><input type=text name="TUITION" readonly> 원</td>
				</tr>
				<tr>
					<td colspan=2 align="center"><input type=submit value="수강신청">
						&emsp; <input type=reset value="다시쓰기"></td>
				</tr>
			</table>
		</form>
	</div>

	<br>
</section>

<%@ include file="/include/bottom.jsp"%>