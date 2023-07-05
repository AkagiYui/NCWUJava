<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <script type="text/javascript"  src="https://lf26-cdn-tos.bytecdntp.com/cdn/expire-1-M/jquery/1.4.4/jquery.min.js"></script>
    <script type="text/javascript">
        var  ws;
        var url="ws://localhost:8080/chatSocket?username=${sessionScope.username}";

        window.onload=connect;
        function connect(){
            if ('WebSocket' in window) {
                ws = new WebSocket(url);
            } else if ('MozWebSocket' in window) {
                ws = new MozWebSocket(url);
            } else {
                alert('WebSocket is not supported by this browser.');
                return;
            }
            ws.onmessage=function(event){
                eval("var result="+event.data);

                if(result.alert!=undefined){
                    $("#content").append(result.alert+"<br/>");
                }

                if(result.names!=undefined){
                    $("#userList").html("");
                    $(result.names).each(function(){
                        $("#userList").append(this+"<br/>");
                    });
                }

                if(result.from!=undefined){
                    $("#content").append(result.from+" "+result.date+
                        " 说：<br/>"+result.sendMsg+"<br/>");
                }

            };
        }

        function  send(){
            var value= $("#msg").val();
            ws.send(value);
        }


    </script>
</head>
<body>

<h3>欢迎 ${sessionScope.username } 使用本系统！！</h3>

<div  id="content"  style="
		border: 1px solid black; width: 400px; height: 300px;
		float: left;
	"  ></div>
<div  id="userList"  style="
		border: 1px solid black; width: 100px; height: 300px;
		float:left;
	"  ></div>

<div  style="clear: both;" >
    <input id="msg"  /><button  onclick="send();"  >send</button>
</div>


</body>
</html>
