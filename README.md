# 工程简介
基于SpringBoot的商城后台系统
|        技术选型        |  版本   |
| :--------------------: | :-----: |
|       SpringBoot       |  2.3.7  |
|  MySQL-connector-java  | 8.0.22  |
|      Mybatis-Plus      |  3.4.2  |
| Mybatis-Plus-Generator |  3.5.1  |
|        Swagger         |  3.0.0  |
|         lombok         | 1.18.16 |
|         log4j2         | 2.13.3  |
|       validation       |  2.6.7  |
|         Redis          |  2.3.6  |


# 参考链接

1. [SpringBoot电商项目](https://blog.csdn.net/csucsgoat/category_11604468.html?spm=1001.2014.3001.5515)
2. [Mybatis-Plus官方文档](https://baomidou.com/)
3. [SpringBoot整合Mybatis-Plus](https://www.cnblogs.com/liuyj-top/p/12976396.html)
4. [SpringBoot整合Swagger3](https://segmentfault.com/a/1190000037455077)
5. [SpringBoot使用DevTools热部署](https://blog.csdn.net/pan_junbiao/article/details/105840785)
6. [SpringBoot整合Validation](https://blog.csdn.net/weixin_42236404/article/details/105653432)
7. [SpringBoot整合Redis](https://wenku.baidu.com/view/aff2cfcbfbc75fbfc77da26925c52cc58ad69045.html)

   

# 每日计划

1. 2022-4-22
   - 设计数据库表项
   - 新建项目并初始化git
   - 整合Mybatis-Plus和Swagger3
   - 整合log4j2保存请求日志
2. 2022-4-23
   - 添加统一返回API类
3. 2022-4-24
   - 添加统一异常处理返回
4. 2022-4-25
   - 完成用户模块
   - 登录注册、登出、修改个人信息
5. 2022-4-26
   - 限制修改个人信息的范围
   - 添加修改密码
   - 商品分类实体类重构
   - 添加商品分类
6. 2022-4-27
   - 添加实体校验 整合Validation
   - 修改商品分类（判重抛出错误信息）
7. 2022-4-28
   - ---------------------摸鱼一天-----------------------
8. 2022-4-29
   - 添加统一管理员身份拦截器
   - 删除商品分类
   - 获取商品一级分类
   - 获取商品子分类
   - 整合 Redis 缓存商品分类
9. 2022-4-30
   - 优化Redis缓存，添加和更新时更新缓存，删除时删除缓存，查询时写入缓存
10. 2022-5-1
    - 添加商品接口开发
    - 修改商品信息接口开发
11. 2022-5-2
    - 删除商品接口开发
12. 2022-5-3
    - 批量上下架商品接口开发
    - 配置Mybatis-Plus分页
    - 后台分页商品按条件查询接口开发（价格排序、库存排序、模糊字搜索、上下架状态选择）
    - 后台商品详情接口开发
    - 前台分页商品按条件查询接口开发（价格排序、库存排序、模糊字搜索）>>>>>（默认选择已上架商品、库存大于0的商品）
    - 前台商品详情接口开发（隐去库存量，商品上架时间、商品修改时间）>>>>>（若商品未上架，返回商品id不存在）
13. 2022-5-4
    - 添加用户过滤器类
    - 用户查询购物车接口开发
    - 用户新增或更新购物车项接口开发
    - 用户删除购物车项接口开发
    - 购物车是否选中接口开发
    - 购物车全选全不选接口开发
14. 2022-5-5
    - ---------------------摸鱼一天-----------------------
15. 2022-5-6
    - 优化购物车模块代码
16. 2022-5-7
