# Pagination
* 많은 데이터 list가 select되었을 때 전체 list를 한 화면에 보여주면 보는 데 상당히 애로사항이 있다
* 한 화면에 일정 list의 분량만 보옂고
* list 하단에 page navigation을 표현하여
* pageNum을 클릭하면 이후 list를 조회하여 보여주는 방식

## pagination 설계 시 요구사항
* 한 화면에 몇 개의 list를 보여줄 것인가 : 보통 10개 정도의 list
* Pagination의 개수는 몇 개를 할 것인가 : 5개 Or 10개 정도의 list 표현
* 처음으로 가기(1 page) : 1 page 보기
* 끝으로 가기(Last page) : 제일 마지막 list 중에서 보기
* 앞으로 가기, 뒤로 가기 : 현재 보고있는 page에서 앞, 뒤로 가기

* 보고있는 화면에서 page navigation 번호를 클릭했을 때 Controller에 전달하는 값  
pageNum만 전달하기, 검색어와 함께 전달하기, 검색어 정렬기준과 함께 전달하기

### 이 프로젝트에서 Pagination 구현하기
* SQL의 SELECT는 표준 SQL SELECT만 사용하기
* Java 코드에서 Pagination 구현하기
* 1.8 미만에서 사용하는 코드 ... 1.8 이상에서 구현하는 코드
* 1.8 이상의 코드 : Lambda, Stream(List 데이터에 대한)