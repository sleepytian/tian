# css 实现 table 边框合并效果

## 1. 引
在过去的 html 中,
设置边框合并可以使用 table 标签的 cellspacing 属性实现,
但是在 该属性随着 cellspacing 属性一同被废弃后, 
我们需要一个新的方式实现 table 单元格边框合并功能

## 2. 解决方案
新的解决方案比较简单,
设置 table 标签的 css 样式为:
```css
table {
    border-collapse: collapse;
}
```
即可实现修改.

**注意:**

想要显示单元格边框, 建议对 tr, th, td 同时使用 `border` 属性实现.
```css
th, tr, td {
    border: black 1px solid;
    color: black;
    text-align: center;
}
```

