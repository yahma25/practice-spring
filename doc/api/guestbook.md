# 방명록 API

<br>

## 방명록 리스트 가져오기
URL: /guestbook/list
method: GET

<br>

## 방명록 등록하기
URL: /guestbook/register
method: POST
redirect: /guestbook/list

<br>

## 방명록 수정하기
URL: /guestbook/modify
method: POST
redirect: /guestbook/read

<br>

## 방명록 삭제하기
URL: /guestbook/remove
method: POST
redirect: /guestbook/list

<br>

## 방명록 조회하기
URL: /guestbook/read
method: GET