<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://zhiyou100.com/common/" prefix="goubo" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
 	<style>
	#bobo1{
	position: relative;
	margin:200px auto;
	background: white;
	width: 350px;
	height: 240px;
	border-radius:15px;
	} 
 	</style>
 	<script type="text/javascript">
		function delete1(){
	   var orConfirm =	confirm("确定要删除此条记录吗?");
	   return (orConfirm); 
	   //第一个参数表示提示框的标题,第二个参数表示框中文本框的内容,两个都可以省略
	   //返回值表示提示框中的输入框内容,只有点击确定以后才会进行返回
	}
</script>
  </head>
  <body>
	   
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>主选人列表-主选人管理</h1>
		    </div>
		    
		    <a type="button" class="btn btn-primary" href="${pageContext.request.contextPath }/speaker/addSpeaker.action">添加主选人</a>
		    <div style="float: right;">
	          <form class="navbar-form navbar-right">
	            <div class="form-group">
	              	名称<input type="text" placeholder="主选人名称" class="form-control" name="speakerName">
	            </div>
	            <div class="form-group">
	            	主讲人职位<input type="text" placeholder="主讲人职位" class="form-control" name="speakerJob">
	            </div>
	            <button type="submit" class="btn btn-primary">查询</button>
	          </form>
        	</div>
		    
			
			<div class="bs-example" data-example-id="hoverable-table">
			    <table class="table table-hover">
			      <thead>
			        <tr>
			          <th class="col-md-0">序号</th>
			          <th class="col-md-1">名称</th>
			          <th class="col-md-2">职位</th>
			          <th class="col-md-9">介绍</th>
			          <th class="col-md-0">编辑</th>
			          <th class="col-md-0">删除</th>
			        </tr>
			      </thead>
			      <tbody>
			      <c:forEach items="${page.rows}" var="speaker" varStatus="status">
			        <tr>
			          <th scope="row">${status.count+(page.page-1)*5}</th>
			          <td>${speaker.speakerName }</td>
			          <td>${speaker.speakerJob }</td>
			          <td>${speaker.speakerDescr }</td>
			          <td><a class="glyphicon glyphicon-edit" href="${pageContext.request.contextPath }/speaker/updateSpeaker.action?id=${speaker.id}"></a></td>
			          <td><a class="glyphicon glyphicon-trash" href="${pageContext.request.contextPath }/speaker/deleteSpeaker.action?id=${speaker.id}" onclick="return delete1()"></a></td>
			        </tr>
			      </c:forEach>
			      </tbody>
			    </table>
			    <goubo:page url="${pageContext.request.contextPath }/speaker/speakerList.action"></goubo:page>
			</div>
		</div>
  </body>
</html>