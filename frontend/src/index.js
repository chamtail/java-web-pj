import React from 'react';
import ReactDOM from 'react-dom';
import Application from './component/application';
import Axios from 'axios';

var redirecting = false;

Axios.interceptors.response.use((response) => {
    var data = response.data;
    if(data instanceof Object) {
        if((data.success == false) && (data.errorCode == 30200)) {
            if(!redirecting) {
                location.href = data.errorMsg;
                redirecting = true;
            }
        }
    }
 	return response;
}, (error) => {
    console.log('error', error);
	var config = error.config;
	window._error = error;
	var dom = document.createElement("div");
	dom.className = "error-message";
	dom.innerText += "[网络请求失败]\n";
	dom.innerText += `方法: ${config.method}\n`;
	dom.innerText += `路径: ${config.url}\n`;
	dom.innerText += `头部: ${JSON.stringify(config.headers)}\n`;
	dom.innerText += `正文: ${JSON.stringify(config.data)}\n`;
	dom.innerText += `${error.message}\n\n`;
	dom.innerText += "请刷新您的浏览器";
	document.body.appendChild(dom);
 	return Promise.reject(error);
});

ReactDOM.render(
    <Application />,
	document.getElementById('react-root')
);

