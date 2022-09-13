# Druid 数据库连接池技术的基本使用

Druid 是属于由阿里创造的开源数据库连接池, 目前来说属于是最好的 jdbc 数据库连接池之一.

## 0. 导包

使用 Druid 之前需要先导包到项目中.

由于 Druid 在 github 开源, 所以你可以在 github 下载到 druid 的 jar 包:

[alibaba/druid](https://github.com/alibaba/druid) 

## 1. 组成/原理

Druid 的使用类似于 dbcp 数据库连接池.

在使用中, 你会用到以下类:

- 配置文件
  
  这是一个文件名为`druid.properties`的 properties 文件, 一般保存在 src 目录下, 用于保存关于 druid 连接池的基本信息

- DruidDataSourceFactory --> 德鲁伊数据源工厂
  
  数据源工厂主要用于根据 properties 提供的信息创建数据源对象.

- DruidDataSource --> 德鲁伊数据源
  
  用于获取数据库连接的对象

## 2. 创建

虽然没有必要但还是先提醒一次, 下面的内容要想进行, 请**先导包**.

### a. 创建 properties 配置文件

在 src 目录下创建德鲁伊的配置文件 文件名为: `druid.properties`.

该配置文件中应该至少包含以下内容:

```
driverClassName --> 驱动类路径
url                --> 数据库 url, 比如: jdbc:mysql://localhost:3306/test
username        --> 数据库用户名
password        --> 密码
```

还有一些常用配置, 已经贴到了文章末尾

### b. 创建数据源对象

首先要利用流对象获取 properties 配置文件中保存的信息:

```java
InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
Properties properties = new Properties();
properties.load(inputStream);
```

是利用数据工厂的静态函数创建数据源:

```java
DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
```

## 3. 使用

数据库连接池的目的就是优化数据库连接的获取功能, 所以这里只给出简单的利用数据源对象获取数据库连接的方式:

```java
Connection connection = dataSource.getConnection();    // 利用 getConnection() 函数获取数据库连接
```

## 4. 常用的 Druid 数据库连接池的参数

下面是一些常用的 Druid 数据库连接池参数, 可以直接写在配置文件中用于创建数据库连接池, 也可以创建以后, 利用 getter 和 setter 进行设置.

| **配置**                        | **缺省** | **说明**                                                                                                                             |
| ----------------------------- | ------ | ---------------------------------------------------------------------------------------------------------------------------------- |
| name                          |        | 配置这个属性的意义在于，如果存在多个数据源，监控的时候可以通过名字来区分开来。   如果没有配置，将会生成一个名字，格式是：”DataSource-” +   System.identityHashCode(this)                      |
| url                           |        | 连接数据库的url，不同数据库不一样。例如：mysql :   jdbc:mysql://10.20.153.104:3306/druid2      oracle :   jdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
| username                      |        | 连接数据库的用户名                                                                                                                          |
| password                      |        | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。详细看这里：<https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter>           |
| driverClassName               |        | 根据url自动识别   这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相应的driverClassName(建议配置下)                                                      |
| initialSize                   | 0      | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时                                                                                 |
| maxActive                     | 8      | 最大连接池数量                                                                                                                            |
| maxIdle                       | 8      | 已经不再使用，配置了也没效果                                                                                                                     |
| minIdle                       |        | 最小连接池数量                                                                                                                            |
| maxWait                       |        | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。                                              |
| poolPreparedStatements        | false  | 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。                                                     |
| maxOpenPreparedStatements     | -1     | 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100                 |
| validationQuery               |        | 用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。                                     |
| testOnBorrow                  | true   | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。                                                                                        |
| testOnReturn                  | false  | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能                                                                                         |
| testWhileIdle                 | false  | 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。                                 |
| timeBetweenEvictionRunsMillis |        | 有两个含义： 1)Destroy线程会检测连接的间隔时间2)testWhileIdle的判断依据，详细看testWhileIdle属性的说明                                                             |
| numTestsPerEvictionRun        |        | 不再使用，一个DruidDataSource只支持一个EvictionRun                                                                                             |
| minEvictableIdleTimeMillis    |        |                                                                                                                                    |
| connectionInitSqls            |        | 物理连接初始化的时候执行的sql                                                                                                                   |
| exceptionSorter               |        | 根据dbType自动识别   当数据库抛出一些不可恢复的异常时，抛弃连接                                                                                               |
| filters                       |        | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：   监控统计用的filter:stat日志用的filter:log4j防御sql注入的filter:wall                                              |
| proxyFilters                  |        | 类型是List，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系                                                                                   |
