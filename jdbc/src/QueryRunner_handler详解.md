# apache dbUtils / QueryRunner 入门

QueryRunner 是 Apache 公司为了在总体上简化 jdbc 操作而编写的一个工具类, 其官方名字是 `Apache Commons DbUtils 1.7 API` QueryRunner 是其中一个常用的类.

## 1. 下载和安装

这里是 apache 提供的下载地址:

[DbUtils – JDBC Utility Component (apache.org)](https://commons.apache.org/proper/commons-dbutils/).

下载后将文件夹内的`commons-dbutils-1.7.jar`文件导入到工程文件中即可.

>   注意: dbUtils 是为了方便 jdbc 操作而创建的, 所以必须首先导入 jdbc connector.jar 以后才可以使用.

## 2. 基本操作

