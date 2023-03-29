// 获取当前文件，如果不是根或者index.jsp，就在body最前面添加一个跳转首页的按钮

const currentPath = window.location.pathname;
const appName = currentPath.split("/")[1];

if (currentPath !== "/" + appName + "/" && currentPath !== "/" + appName + "/index.jsp") {
    const homeButton = document.createElement("button");
    homeButton.innerHTML = "返回首页";
    homeButton.onclick = function () {
        window.location.href = "/" + appName + "/index.jsp";
    };
    document.body.insertBefore(document.createElement("br"), document.body.firstChild);
    document.body.insertBefore(homeButton, document.body.firstChild);
}
