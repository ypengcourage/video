<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录界面</title>
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
  </head>
  <body>
	   
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>添加主讲人-主讲人管理</h1>
		    </div>
		    
		    <form class="form-horizontal" action="${pageContext.request.contextPath }/speaker/addSpeaker.action" method="post">
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">名字</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputEmail3" placeholder="主讲人名字" name="speakerName">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">职位</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputPassword3" placeholder="主讲人职位"  name="speakerJob">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">头像图片</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="inputPassword3" placeholder="主讲人头像地址、网络地址" name="speakerHeadUrl">
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">主讲人简介</label>
			    <div class="col-sm-10">
			      <textarea class="form-control" id="inputPassword3" name="speakerDescr"></textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-3">
			      <button type="submit" class="btn btn-primary">提交</button>
			      <a type="button" class="btn btn-default" href="javascript:history.go(-1)">返回列表</a>
			    </div>
			  </div>
			</form>
		

		</div>
  </body>
</html>