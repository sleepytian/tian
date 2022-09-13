function getSum (a, b) {
    return(a + b);
}

// 如果传入的参数小于参数列表数量, 那么会默认传参给第一个参数
var tmp = getSum("name");
//alert(tmp);// nameundefined

tmp = getSum();
//alert(tmp); // NaN 表示 not a number, 即 不是一个数字

// 如果传入三个参数 那么只传入前两个实参, 后面的实参全部无视
var tmp = getSum(1,2,3);
//alert(tmp);

function test1() {
    alert("没有参数的test函数");
}

function test1(username) {
    alert("有参数的 test 函数");
}