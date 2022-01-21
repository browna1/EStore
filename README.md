# EStore

### 1 系统开发及运行环境

基于springboot框架开发的电脑商城系统

1.操作系统：Windows 10

2.Java开发包：JDK 8

3.项目管理工具：Maven 3.8.4

4.项目开发工具：IntelliJ IDEA 2021.2 x64

5.数据库：MySQL8.0-winx64

6.浏览器：Google Chrome

7.服务器架构：Spring Boot 2.6.2 + MyBatis 2.2.1+ AJAX

### 2 项目分析

1.在开发某个项目之前，应先分析这个项目中可能涉及哪些种类的数据。本项目中涉及的数据：用户、商品、商品类别、收藏、订单、购物车、收货地址。

2.关于数据，还应该要确定这些数据的开发顺序。设计开发顺序的原则是：先开发基础、简单或熟悉的数据。以上需要处理的数据的开发流程是：用户-收货地址-商品类别-商品-收藏-购物车-订单。

3.在开发每种数据的相关功能时，先分析该数据涉及哪些功能。在本项目中以用户数据为例，需要开发的功能有：登录、注册、修改密码、修改资料、上传头像。

4.然后，在确定这些功能的开发顺序。原则上，应先做基础功能，并遵循增查删改的顺序来开发。则用户相关功能的开发顺序应该是：注册-登录-修改密码-修改个人资料-上传头像。

5.在实际开发中，应先创建该项目的数据库，当每次处理一种新的数据时，应先创建该数据在数据库中的数据表，然后在项目中创建该数据表对应的实体类。

6.在开发某个具体的功能时，应遵循开发顺序：持久层-业务层-控制器-前端页面。