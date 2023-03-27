const codeHost = 'https://raw.githubusercontent.com/AkagiYui/NCWUJava/master/KenkoWeb/'
const host = 'https://ghproxy.net/https://raw.githubusercontent.com/AkagiYui/NCWUJava/master/KenkoWeb/'
const repo = 'https://github.com/AkagiYui/NCWUJava'

const link = document.createElement("link");
link.setAttribute("rel", "stylesheet");
link.setAttribute("href", "//unpkg.com/@highlightjs/cdn-assets@11.7.0/styles/default.min.css");
document.head.appendChild(link);

const script = document.createElement("script");
script.setAttribute("src", "//unpkg.com/@highlightjs/cdn-assets@11.7.0/highlight.min.js");
document.head.appendChild(script);

const repoLink = document.createElement("a");
repoLink.setAttribute("href", repo);
repoLink.setAttribute("target", "_blank");
repoLink.setAttribute("style", "margin-right: 10px");
repoLink.innerHTML = "源码仓库🔗";

function showCode(path, language) {
    if (language === undefined) {
        language = "java";
    }

    const pre = document.createElement("pre");

    const linkTo = document.createElement("a");
    linkTo.setAttribute("href", codeHost + path);
    linkTo.setAttribute("target", "_blank");
    linkTo.innerHTML = "本页源码🔗：";
    pre.appendChild(repoLink);
    pre.appendChild(linkTo);

    const code = document.createElement("code");
    pre.appendChild(code);
    pre.setAttribute("id", "thisCode");
    code.setAttribute("class", "language-" + language);
    document.body.appendChild(pre);

    script.onload = function() {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', host + path, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                code.innerHTML = xhr.responseText.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                hljs.highlightElement(code);
            } else {
                code.innerHTML = "加载失败";
            }
        };
        xhr.send();
    };
}
