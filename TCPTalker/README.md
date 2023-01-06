```plantuml
@startuml
actor TcpClient
actor TcpServer
TcpServer -> TcpServer: 创建ServerSocket并绑定到一个端口
TcpServer -> TcpServer: 监听传入的连接
TcpClient -> TcpClient: 创建Socket并连接到ServerSocket
TcpClient -> TcpServer: 发送请求到ServerSocket
TcpServer -> TcpClient: 接受传入的连接
TcpClient -> TcpClient: 创建输入和输出流
TcpServer -> TcpServer: 创建输入和输出流
TcpClient -> TcpServer: 通过流读写数据
TcpServer -> TcpClient: 通过流读写数据
TcpClient -> TcpServer: 发送了end
TcpServer -> TcpClient: 关闭流和Socket
TcpClient -> TcpServer: 关闭流和Socket
@enduml
```

```
[Shape]<>--[Circle]
[Shape]<>--[Rectangle]
[Shape]<>--[Triangle]
[Shape]<|--[UnsupportedShapeException]
[Shape]
:+draw():void
:+erase():void
[ShapeFactory]
:+createShape(shapeType: String): Shape
[Circle]
:+draw():void
:+erase():void
[Rectangle]
:+draw():void
:+erase():void
[Triangle]
:+draw():void
:+erase():void
```
