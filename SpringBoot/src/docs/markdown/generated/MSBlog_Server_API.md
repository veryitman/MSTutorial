# MSBlog Server API


<a name="overview"></a>
## Overview
API for MSBlog


### Version information
*Version* : 1.0.0


### Contact information
*Contact* : itman  
*Contact Email* : veryitman@126.com


### License information
*License* : Apache 2.0  
*License URL* : http://www.apache.org/licenses/LICENSE-2.0.html  
*Terms of service* : null


### URI scheme
*Host* : localhost:8080  
*BasePath* : /


### Tags

* ms-test-redirect-controller : MS Test Redirect Controller
* 用户模块 : MS Signin Controller




<a name="paths"></a>
## Paths

<a name="siginusingget"></a>
### 用户名登录
```
GET /signin/name
```


#### Description
用户名登录


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**username**  <br>*required*|用户名|string|
|**Query**|**userpwd**  <br>*required*|密码|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[MSResponse](#msresponse)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `\*/*`


#### Tags

* 用户模块


<a name="signupusingpost"></a>
### 用户名注册
```
POST /signup/name
```


#### Description
用户名和密码注册


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**username**  <br>*required*|注册的用户名|string|
|**Query**|**userpwd**  <br>*required*|注册的密码|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[MSResponse](#msresponse)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `\*/*`


#### Tags

* 用户模块


<a name="redirectusingget"></a>
### redirect
```
GET /testredirect/access/web
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `\*/*`


#### Tags

* ms-test-redirect-controller


<a name="redirect2usingget"></a>
### redirect2
```
GET /testredirect/access/web2
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `\*/*`


#### Tags

* ms-test-redirect-controller


<a name="realusingget"></a>
### real
```
GET /testredirect/index/realweb
```


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `\*/*`


#### Tags

* ms-test-redirect-controller




<a name="definitions"></a>
## Definitions

<a name="msresponse"></a>
### MSResponse

|Name|Schema|
|---|---|
|**code**  <br>*optional*|integer (int32)|
|**msg**  <br>*optional*|string|
|**results**  <br>*optional*|object|





