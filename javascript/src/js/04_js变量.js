/*
* js 变量规则
*
* java 是一种强类型语言 --> 存在编译阶段, 一个变量从创建到结束为止, 都是同一个数据类型, 不会改变
*
* js 是弱类型编程语言
*
* 对于 数据类型: byte short int long float double boolean char
* 占用空间大小依次是: 1 2 4 8 4 8 1 2
*
* */

// 使用 `var 变量名` 创建一个变量
var var_name;

var tmp;

// js 可以随意赋值, 变量的数据类型随赋值 的类型 改变.
tmp = 123;
tmp = "hello";

// 注意: undefined 是一个值, 当变量没有被赋值值, 该变量值为 undefined
alert("var_name = " + var_name);

// 一个变量没有声明就进行调用, 会导致发生错误, 这个报错可以在 浏览器的控制面板查看 --> 这是语法错误!!
// alert(age);


var a,b,c = 300;
alert(a);
alert(b);
alert(c);

