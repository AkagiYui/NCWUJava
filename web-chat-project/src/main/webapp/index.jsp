<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<style>
    #pop{
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.1);
        display: none;
    }

    #context{
        position: relative;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        width: 800px;
        height: 500px;
        border: 1px solid red;
        background-color: floralwhite;
    }

    #context p:nth-last-child(1) :nth-child(1) {
       float: right;
    }
</style>
<body>


<form action="/method1" method="get">
    <p>
        <input type="text" name="username">
    </p>
    <p>
        <input type="submit" value="get请求">
        <input type="submit" value="post请求" formmethod="post">
        <input type="submit" value="get请求22" formaction="/method2">
        <input type="submit" value="get请求" formaction="/method2" formmethod="post">
    </p>

</form>

<%--自己写一个弹出框--%>
<div>
    <label><a href="#" onclick="onPop()">添加</a></label>
</div>

<%--定义的一个弹出框--%>
<div id="pop">
   <%--弹出框内容--%>
    <div id="context">
        <p>
            <label>账号:</label><input type="text" name="username">
        </p>
        <p>
            <label>密码:</label><input type="text" name="password">
        </p>
        <p>
            <button onclick="onYes()">确定:</button>
            <button onclick="onCancel()">取消</button>
        </p>
    </div>
</div>
</body>
<script>
    //弹出事件
   function onPop(){
      //通过js的dom事件获取标签元素对象
       let popDiv = document.getElementById("pop");
       //获取style属性做样式排版
       popDiv.style.display = 'block';
   }

   //确定提交
    function onYes(){
      //取消弹出框
        document.getElementById("pop").style.display='none';
      //调回调函数
      getFlushData(function (data,index){
          console.log(data+"---------"+data.username)
          console.log(index+"===============")
      })
    }

    //回调函数
    function getFlushData(rallbock){
         let obj = {"username":'哈哈',age:23}
         rallbock(obj,1);
    }

    //取消弹出框
    function onCancel(){
        document.getElementById("pop").style.display='none';
    }
</script>
</html>
