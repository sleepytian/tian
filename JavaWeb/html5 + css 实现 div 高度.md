# html5 + css 实现 div 高度

在 html5 中, css 样式表不再支持直接设置 div 的 height 属性, 从而导致尽管设置了某个 div 的 height 属性为 某个值或者某个比例, 在实际效果中其仍然只有一行文字的高度.

本篇的目的在于解决这个问题, 方法很简单, 所以直接写出来.

## 解决方式

要想解决上述问题并且在 html5 中设置高度自适应, 可以直接设定 html 和 body 标签的 height 属性为 100%.

```css
html, body {
    height: 100%
}
```

在 css 样式表中添加如上设定后, 对 div 标签添加 height 属性并且使用百分数设置高度, 即可实现高度自适应.

```css
#div1 {
    height: 50%
}
```

---

<div style="font-family:JerBrainsMono; font-size:24px; text-align:center">~END~</div>