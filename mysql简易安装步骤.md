# mysql 简洁式安装步骤

## 1. 设置全局变量

解压mysql压缩包到指定位置, 然后配置全局变量, 在 path 中添加全局变量, 值为 mysql 根目录下 bin 目录路径, 比如:

```
D:\code_space\environments\mysql-8.0.30\bin
```

然后保存即可

## 2. 配置 my.ini 文件

在解压的 mysql 根目录中新建文件并命名为`my.ini`, 内容如下

```ini
[mysqld]
# 设置3306端口
port=3306
# 设置mysql的安装目录
basedir=E:\\kits\mysql-8.0.28
# 设置mysql数据库的数据的存放目录
datadir=E:\\kits\mysql-8.0.28\data
```

注意: 

1. 两个目录路径中, 盘符后的符号是两个`\`, 命名不规范会导致后面的配置过程中出现错误
2. 第二个目录中的 data 现在是不存在的, 但是不要手动创建, 示例的 data 的路径是在 mysql 的根目录下的(也是建议的位置), 为了卸载的时候方便操作, 所以直接卸载根目录下

## 3. 初始化mysql

管理员身份打开 cmd, 依次输入以下两个指令:

```mysql
# 安装 mysql
mysqld install
# 初始化 mysql 数据文件
mysqld --initialize-insecure -user=mysql
```

## 4. 重启 mysql 并且修改密码

首先重启 mysql, 以下两个指令在 具有管理员身份的 cmd 下进行(前面步骤的 cmd 关掉重启)

```shell
# 启动
net start mysql
# 关闭
net stop mysql
```

利用 root 用户登录

```shell
mysql -u root -p
```

接下来会询问一些信息, 全部回车即可

登录后修改密码 (这里以 root 作为密码 演示)

```sql
#修改新密码 ALTER USER 'root'@'localhost' IDENTIFIED BY '新密码';
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
#新密码登录,注意没有空格
mysql -u root -proot
```
