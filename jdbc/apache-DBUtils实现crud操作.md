# DBUtils 实现数据库 crud 操作

dbUtils 是一个由 apache 推出的 jdbc 系统, 这个系统的目的在于简易化 jdbc 的数据库操作.

dbUtils 已经在 jar 包中包含了常用的 crud 基本操作, 本篇的目的即是对 dbUtils 做一个简单介绍, 使得读者可以快速入门 dbUtils 并且掌握一些核心和基本的操作.

# dbUtils 的基本使用

dbUtils 主要使用在 dao 系统中, 尤其是 BaseDao 抽象类. 而其他的可能会用到的, 比如 jdbc_utils *(用于管理 jdbc 资源, 主要是 获取数据库连接和关闭相关资源(比如 connection, Statement, ResultSet))* 

## 1. 下载 dbUtils

dbUtils 是以 jar 包的形式实现的, 所以使用前需要首先下载并且导入 dbUtils 的 jar 包到项目的库中.

这里是 jar 包的官方[下载地址](https://commons.apache.org/proper/commons-dbutils/).

至于导包的方式, 这里就不作赘述了.

## 2. dbUtils 实现 jdbc_Utils 静态类

JdbcUtils 是一个静态类, 用于管理 jdbc 资源, 主要是 获取数据库连接和关闭相关资源(比如 connection, Statement, ResultSet).

实际上 jdbc_utils 基本没有依赖

## 3. dbUtils 实现 BaseDAO 抽象类



## 4. dbUtils 实现对具体 mysql 数据库的 jdbc 操作

