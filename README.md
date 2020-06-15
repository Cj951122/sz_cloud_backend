

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| sz-api | api接口 | 无 | 接口 |
| sz-kernel | 核心包 | 无 | 用于jar包 版本的全局管理，所有项目的父类 |
| sz-common | 常量定义包 | 无 | 自定义注解，自定义异常枚举类，models | 
| sz-core | 常量定义包 | 无 | 全链路追踪，全局异常处理，apollo,swagger集成的集中实现，以及一些公共操作utils  | 
| sz-discover-service | 注册发现中心 | 无 | 基于eurake 实现 | 
| sz-gateway | 网关 | 无 | 基于zuul 实现 | 

---

## 注意事项

> * 开发环境为jdk 1.8
> * maven推荐使用阿里云镜像，拉取jar包保证成功

---

## 构建项目
（待完善，打算用python 脚本）

---

## 常见操作

####异常处理
``
throw new BusinessException(BizExceptionEnum.BAD_LICENCE_TYPE);
根据业务需要定义特定的异常枚举
``
####获取登陆用户
```
UserDetailsUtil.getCurrentUser();
注意，由于才有的jwt 实现，相关信息有可能是脏数据
例如登陆后，用户修改信息，此种情况该方法获取的就滞后了
但 用户id是不会变
```
####返回格式
> * web 返回前端统一用WebApiResult，服务调用统一用 ServiceApiResult

####全链路追踪
> * 采用在request header 里放trace_id,client_id,并在日志固定两个槽位打印



##待完善
> * 增加message-bus模块，对消息发送（短信，推送，入消息队列，发送邮件）提供统一的语义抽象