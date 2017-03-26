# MyJava

### This is my Learn Java way

  * SpringMVC + Mybatis整合测试，从数据库中读取数据展示到前台页面，数据库采用MySQL，Dao层使用逆向工程生成
  * B2CProject电商项目实践
    * 一个类似于JD、TB的分布式架构设计，包括后台管理系统、前台系统、单点登陆系统、搜索系统、会员系统，架构属于分布式架构，前台系统和单点登陆系统采用的是集群方式部署，在后台采用Maven的多模块化的管理分层开发，系统内部之间通讯采用MQ方式，使用Active MQ实现，部署方面采用Nginx+tomcat模式，一部分做反向代理，一部分做图片上传服务器
  * JavaBase（java基础）
    * 算法初学
        * 排序
            * 冒泡排序
            * 选择排序
            * 字符串的反转
            * 插入排序
    * dataType数据类型
        * 数据的自动装箱
        * String深入理解
            * String类初始化后是不可变的
            * String不一定创建对象
            * 使用new String 一定会创建对象
            * String.intern()
    * 多线程
        * 进程：每个进程都有独立的代码和数据空间（进程上下文），进程间的切换会有较大的开销，一个进程包含1--n个线程。（进程是资源分配的最小单位）
        * 线程：同一类线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换开销小。（线程是cpu调度的最小单位）
        * 在java中要想实现多线程，有两种手段，一种是继续Thread类，另外一种是实现Runable接口.(其实准确来讲，应该有三种，还有一种是实现Callable接口，并与Future、线程池结合使用
        * 并行：多个cpu实例或者多台机器同时执行一段处理逻辑，是真正的同时。
        * 并发：通过cpu调度算法，让用户看上去同时执行，实际上从cpu操作层面不是真正的同时。并发往往在场景中有公用的资源，那么针对这个公用的资源往往产生瓶颈，我们会用TPS或者QPS来反应这个系统的处理能力。 
        * 线程安全：经常用来描绘一段代码。指在并发的情况之下，该代码经过多线程使用，线程的调度顺序不影响任何结果。这个时候使用多线程，我们只需要关注系统的内存，cpu是不是够用即可。
        * 同步：Java中的同步指的是通过人为的控制和调度，保证共享资源的多线程访问成为线程安全，来保证结果的准确。如上面的代码简单加入@synchronized关键字。在保证结果准确的同时，提高性能，才是优秀的程序。线程安全的优先级高于性能。
        * Thread和Runnable的区别
            * 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享。
            * 总结：
                * 实现Runnable接口比继承Thread类所具有的优势：
                1. 适合多个相同的程序代码的线程去处理同一个资源
                2. 可以避免java中的单继承的限制
                3. 增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
                4. 线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的类</br>
        **main方法其实也是一个线程。在java中所以的线程都是同时启动的，至于什么时候，哪个先执行，完全看谁先得到CPU的资源。**</br>
        在java中，每次程序运行至少启动2个线程。一个是main线程，一个是垃圾收集线程。因为每当使用java命令执行一个类的时候，实际上都会启动一个JVM，每一个JVM实习在就是在操作系统中启动了一个进程。
        * 常用函数
            * sleep(long millis):在指定的毫秒值内让当前正在执行的线程休眠（暂停执行）
            * join():等待t线程终止，join是Thread类的一个方法，启动线程后直接调用，即join()的作用是：“等待该线程终止”，这里需要理解的就是该线程是指的主线程等待子线程的终止。也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。
            
        
