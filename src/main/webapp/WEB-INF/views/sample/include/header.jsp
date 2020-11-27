<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html><!-- 현재문서의 형태선언 -->
<html><!-- html문서는 태그열고 ~ 태그닫고 하는 영역확인 중요 -->
<head><!-- 문서내용과는 관계없는 문서정보=메타데이터(데이터의데이터)가 존재 -->
<meta charset="UTF-8"><!-- 문서의 언어 인코딩설정  지금은 유니코드로 됨 -->
<!-- 반응형을 작동하기 위해서 사용하는 메타태그 뷰포트는 모니터,모바일,프린터-->
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1">
<title>반응형홈페이지</title><!-- 문서의 제목을 브라우저상단왼쪽에 표시 -->
<!-- 외부 자바스크립트 불러오는 태그(아래) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script><!-- j쿼리코어 -->
<script src="/resources/sample/js/user.js"></script>
<!-- 외부 css파일 불러오는 태그(아래) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- 위 부트스트랩 코어입니다. -->

<link rel="stylesheet" href="/resources/sample/css/reset.css" type="text/css">
<link rel="stylesheet" href="/resources/sample/css/mobile.css" type="text/css">
<link rel="stylesheet" href="/resources/sample/css/tablet.css" type="text/css">
<link rel="stylesheet" href="/resources/sample/css/pc.css" type="text/css">
<!-- span태그 자리를 차지하지 않는 영역 = 한뼘 , px 픽셀 화면에 표시되는 크기의 단위 -->
<!-- :콜론(속성:값, Key:Value), ;세미콜론(문장의 끝) -->
<style>
/*@미디어쿼리사용-메타태그viewport값 필수*/
/*PC용 스타일 시작*/
/*미디어쿼리:미디어의 가로크기가 1132px 이상이면 아래스타일 적용*/
@media all and (min-width:1132px) {



}
/*PC용 스타일 끝*/
</style>
<script>
/* 자바스크립트 시작 */
$(document).ready(function($) {
    $(".carousel").carousel({
    interval:1500,//이미지가 좌우로 움직이는 대기시간 지정. 1초//
    pause:false
    	});
});

/* 자바스크립트 끝 */
</script>
</head>
<body><!-- 문서내용이 들어가는 영역 -->
<div id="wrap"><!-- 랩핑시킨 영역 -->
<header class="header">
<!-- 상단 로고와 햄버거 메뉴영역 시작 -->
<h1 class="logo">
<a href="/">LOGO</a><!-- a태그는 페이지이동역할, href헤르프 속성값에 URL을 입력해서 이동 -->
</h1><!-- 헤드라인 글자를 표시 h1~h6 -->
<div class="menu-toggle-btn">
<span></span>
<span></span>
<span></span>
</div>

<!-- html5 시만텍 웹 = 의미가 있는 태그를 사용하게 해서 ai가 인식하게 됩니다. nav태그 -->
<nav class="gnb"><!-- gnb:글로벌네비게이션:전체영역메뉴 Global Navigagion -->
<!-- 리스트를 모여주는 태그 ul(UnOrdered List *.~, *.~ ) , ol(Ordered List 1.~, 2.~ ) -->
<ul>
<li><a href="/">HOME</a></li>
<li><a href="/weare">WE ARE</a></li>
<li><a href="/work">WORK</a></li>
<li><a href="/blog">BLOG</a></li>
<li><a href="/contact">CONTACT US</a></li>
<li><a href="javascript:alert('관리자단 준비중 입니다')">AdminLTE</a></li>
</ul>
</nav>
<!-- 상단 로고와 햄버거 메뉴영역 끝 -->

</header>