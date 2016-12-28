/**
 *  CoolPlugin.js 
 * @type {*|Object}
 */
var exec = require('cordova/exec');

function CoolPlugin() {
    console.log("CoolPlugin.js: is created");
}

CoolPlugin.prototype.showToast = function(aString){
    // 第一个参数是success回调, error回调, 插件名, action名, 数组参数
    exec(function(result){}, function(result){}, "CoolPlugin","toast" , [aString]);
}
CoolPlugin.prototype.print = function(aString){
    // 第一个参数是success回调, error回调, 插件名, action名, 数组参数
    exec(function(result){}, function(result){}, "CoolPlugin", "print", [aString]);
}

var coolPlugin = new CoolPlugin();
module.exports = coolPlugin;

