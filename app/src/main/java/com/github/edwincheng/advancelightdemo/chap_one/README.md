## Android 5.0特性
### 系统级别的新特性
1. 全新的Material Design风格页面
2. 支持多种设备
3. 全新的通知中心设计
4. 支持64位ART虚拟机
> 5.0从性能上的提升，放弃之前使用的Dalvik虚拟机，改用ART虚拟机，实现了跨平台编译，在ARM、X86、MIPs等无处不在。
5. OverView（多任务视窗、多任务栏）
6. 设备识别解锁（比如小米生态中手机识别手表，点击解锁）
7. OK google语音指令（类似苹果Siri）
8. Face Unlock面部解锁
### 开发级别
9. RecyclerView 替代了ListView跟GridView
> 高度解耦，提供不同的Layout Manager、ItemDecoration、IntemAnimatior. 缺点在于需要自己去设置分割线，点击事件也要自己设置。
  去掉recyclerView 头尾的阻尼动画：android:overScrollMode="never"
  > adapter 中cardView可以用来设置圆角以及阴影，使得item更加立体
    card_view:cardCornrtRadius='20dp' / card_view:cardElevation='20dp'

10. Notification 通知
> 三种通知：1. 普通Noyification  2. 折叠式 3.悬挂式
  代码如上面所示，需要到app应用中开启 通知权限，
  并且mainifest添加:     <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>

11. Toolbar 可写成自定义toolbar工具栏。

## Android 6.0特性

1. 运行时权限
> 运行是权限包含Normal Permissions 和Dangerous Permissions
2. Android Pay
3. 指纹支持
4. Doze电量管理
5. app links（关联app跳转，比如有个推特链接，点击链接可以自己跳转到推特apps）
6. now on tap
