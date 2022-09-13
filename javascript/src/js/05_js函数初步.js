/*
* 在 js 文件中的函数 不被调用不会自动执行.
* */
function getSum(a, b) {
    // a,b 都是局部变量 都是形参
    alert(a + b);

    // 返回函数值
    return a + b;
}

getSum(10,20);
