
/*
* 需求1: 在鼠标悬浮在水果名字上方时改变背景颜色
*
* 1. 创建修改背景颜色的函数
* 2. 对目标添加 onmouseover 属性
*
* */
// 定义函数: 当鼠标悬浮时 显示背景颜色.
function showBGColor () {
    // 当前发生的事件 叫做 event
    // alert(Event.target);
    alert(Event.target.tagName);
}