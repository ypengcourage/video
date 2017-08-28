<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://zhiyou100.com/common/" prefix="bobo"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>视频列表</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
    <link  href="${pageContext.request.contextPath }/static/css/jquery-confirm.css" rel="stylesheet" >
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-confirm.js"></script>
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
 	var count=0;
 	$(function(){
 		$(".bobo01").click(function(){
		 	var aa = this.name;
		 	$.confirm({
		 		title: '警告',
		 	    content: '你确定要删除此条记录吗?',
		 	    buttons: {
		 	    	确定删除 : function () {
		 	    		$.get(
		 	    			"${pageContext.request.contextPath }/video/deleteVideo.action",
		 	    			"id="+aa,
		 	    			function (){
		 	    				location.reload();
		 	    			}
		 	    		)
		 	        },
		 	       	 取消  : function () {
		 	            return;
		 	        }
		 	    }
		 	});
 			/* if(confirm("确定要删除记录吗?")){
 				var aa = this.name;
 				location.href="${pageContext.request.contextPath }/video/deleteVideo.action?id="+aa;
 			} */
 		});
 		$("input[name=bobo02]").click(function(){
 			var cc = this.checked;
 			$(":checkbox").each(function(a,b){
 				b.checked=cc;
 			});
 			if(cc){
 				count=$(":checkbox").length-1;
 				$(".badge").text(count);
 			}else{
 				count=0;
 				$(".badge").text(count);
 			}
 		});
 		$("input[name=xuanze]").click(function(){
 			if(this.checked){
 				count++;
 			}else{
 				count--;
 			}
 			$(".badge").text(count);
 			if(count == $(":checkbox").length-1){
 				
 			}
 		});
 		$("#bobo03").click(function(){
 			if(count!=0){
 				$.confirm({
 			 		title: '多条记录删除警告!',
 			 	    content: '你确定要删除此些记录吗?',
 			 	    buttons: {
 			 	    	确定删除 : function () {
 			 	    		$("#bobo04").submit();
 			 	        },
 			 	       	 取消  : function () {
 			 	            return;
 			 	        }
 			 	    }
 			 	});
	 			/* if(confirm("确定要删除这些记录吗?")){
	 				$("#bobo04").submit();
	 			} */
 			}else{
 				$.alert({
 				    title: '没选中',
 				    content: '删个屁!!!',
 				});
 			}
 		});
 	});
 	/* function bobo(xx){
 		var arr = document.getElementsByName("xuanze");
 		for(var i = 0;i < arr.length;i++){
 				arr[i].checked = xx;
 		}
 		if(xx){
 			count=arr.length;
 		}else {
 			count=0;
		}
 		document.getElementsByTagName("span")[0].innerHTML=count;
 	}
	function delete1(){
	   var orConfirm = confirm("确定要删除此条记录吗?");
	   return (orConfirm); 
	   //第一个参数表示提示框的标题,第二个参数表示框中文本框的内容,两个都可以省略
	   //返回值表示提示框中的输入框内容,只有点击确定以后才会进行返回
	}
	function deleteAll(){
		var orConfirm = confirm("确定要删除这些记录吗?");
		   if(orConfirm == true){
			   document.getElementsByTagName("form")[1].submit();
		   }
	}
	function selectBox(the){
		//alert(count);
		if(the.checked==true){
			count++;
		}else{
			count--;
		}
		document.getElementsByTagName("span")[0].innerHTML=count;
	} */
	</script>
  </head>
  <body>
	   
	    
	    <div class="container theme-showcase" role="main">
	    	
		    <div class="jumbotron">
		        <h1>视频列表-视频管理</h1>
		    </div>
		    
		    <a class="btn btn-primary" href="${pageContext.request.contextPath }/video/addVideo.action">添加视频</a>
		    <a class="btn btn-primary" id="bobo03"><font color="white">批量删除</font>&nbsp;&nbsp;<span class="badge">0</span></a>
		    <div style="float: right;">
	          <form class="navbar-form navbar-right"  action="${pageContext.request.contextPath }/video/videoList.action">
	            <div class="form-group">
	              <input type="text" placeholder="视频标题" class="form-control" name="videoTitle" value="${speakervo.videoTitle }">
	            </div>
	            <div class="form-group">
	            	<select class="form-control" name="videoSpeaker">
	            	  <option  value="" >请选择主选人</option>
					  <c:forEach items="${listSpeaker }" var="speaker">
					  <option value="${speaker.id}" ${ speaker.id==speakervo.videoSpeaker?"selected":"" }>${speaker.speakerName}</option>
					  </c:forEach>
					</select>
	            </div>
	            <div class="form-group">
	            	<select class="form-control" name="videoCourse">
	            	  <option value="">请选择课程</option>
					  <c:forEach items="${listCourse }" var="course">
					  <option value="${course.id}" ${ course.id==speakervo.videoCourse?"selected":""}>${course.courseName}</option>
					  </c:forEach> 
					</select>
	            </div>
	            <button type="submit" class="btn btn-primary">查询</button>
	          </form>
        	</div>
		    
			
			<div class="bs-example" data-example-id="hoverable-table">
			    <form action="${pageContext.request.contextPath }/video/deleteAllVideo.action" id="bobo04">
			    <table class="table table-hover">
			      <thead>
			        <tr>
			          <th class="col-md-0"><input type="checkbox" name="bobo02"></th>
			          <th class="col-md-0">序号</th>
			          <th class="col-md-2">名称</th>
			          <th class="col-md-9">介绍</th>
			          <th class="col-md-0">讲师</th>
			          <th class="col-md-2">课程</th>
			          <th class="col-md-0">时长(秒)</th>
			          <th class="col-md-0">播放次数</th>
			          <th class="col-md-0">编辑</th>
			          <th class="col-md-0">删除</th>
			        </tr>
			      </thead>
			      <tbody>
			        <c:forEach items="${page.rows }" var="video" varStatus="status">
				        <tr>
				          <th scope="row"><input type="checkbox" name="xuanze" value="${video.id}"></th>
				          <td>${status.count+(page.page-1)*5}</td>
				          <td>${video.videoTitle }</td>
				          <td>${video.videoDescr }</td>
				          <td>${video.speakerName }</td>
				          <td>${video.courseName }</td>
				          <td>${video.videoLength }</td>
				          <td>${video.videoPlayTimes }</td>
				          <td><a class="glyphicon glyphicon-edit" href="${pageContext.request.contextPath }/video/updateVideo.action?id=${video.id }"></a></td>
				         <%--  <td><a class="glyphicon glyphicon-trash" id="bobo01" href="${pageContext.request.contextPath }/video/deleteVideo.action?id=${video.id }" onclick="return delete1()"></a></td> --%>
				          <td><a class="glyphicon glyphicon-trash bobo01" name="${video.id }"></a></td>
				        </tr>
			        </c:forEach>
			      </tbody>
			    </table>
			    </form>
			    <bobo:page url="${pageContext.request.contextPath }/video/videoList.action"></bobo:page>
			</div>
			
		</div>
  </body>
</html>