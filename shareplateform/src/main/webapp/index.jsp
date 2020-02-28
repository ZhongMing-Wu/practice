<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/9/16
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btn").click(function () {
                //alert("hello btn");
                $.ajax({
                    url:"",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"name":"admin"}',
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                            alert(data);
                    }
                });
            });
        });
    </script>

    <script type="text/javascript">
        $(function () {
            $("#user_import").click(function () {
                $("button").attr("disabled","disabled");
                var formData=new FormData();
                formData.append("uploadUserFile",$("#uploadUserFile")[0].files[0]);
                $.ajax({
                    url:"userInformationUpload",
                    type:'post',
                    data:formData,
                    contentType:false,
                    processData:false,
                    cache:false,
                    success:function (data) {
                        alert(data);
                    }
                });
            });
        });
    </script>


</head>
<body>
    <button id="btn">导出会员请求信息</button>
    <form action="account/upload" method="post" enctype="multipart/form-data">
        选择文件： <input type="file" name="uploadFile"> </br>
        <input type="submit" value="上传">
    </form>

    <h3>图片上传</h3>
    <form action="uploadPicture" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="imageFile" /></br>
        输入文件类型：<input type="text" name="picType">
        <input type="submit" value="上传"/>
    </form>

    <h1>会员文件导入页面</h1>
    <form action="" method="post" enctype="multipart/form-data">
        <input type="file" name="uploadUserFile" class="required" id="uploadUserFile"><br>
        <button id="user_import" onclick="return false"> 导入 </button> <br><br>
    </form>


    <form action="downUserExcel" method="post">
        导出会员请求信息表：<input type="submit" value="导出"/>
    </form>
   <form action="downMemberExcel" method="post">
       导出加盟商信息：<input type="submit" value="导出"/>
   </form>

    <form action="download/TemplateFile" method="get">
        输入文件名：<input type="text" name="filename"></br>
        <input type="submit" value="文件模板下载">
    </form>

    <form action="export/File" method="get">
        输入文件名：<input type="text" name="filename"></br>
        输入加盟商编号：<input type="text" name="memberNo"></br>
        <input type="submit" value="文件下载">
    </form>


    <form action="downloadPicture" method="get">
        输入加盟商编号：<input type="text" name="memberNo"></br>
        输入文件类型：<input type="text" name="picType">
        <input type="submit" value="文件下载">
    </form>


    <br/>
</body>
</html>

