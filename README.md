# spring-batch
spring-batch学习Demo

> SpringBoot的启动类必须放在顶层包下，这样才能扫描注解
>
> @EnableBatchProcessing 支持Batch，Application类上加注解，其他地方就不必加该注解

## 一、核心API

- JobInstance：该领域概念和Job的关系与Java中实例和类的关系一样。Job定义了一个工作流，JobInstance就是该工作流程的一个具体实例。一个Job可以有多个JobInstance。
- JobParameter：是一组可以贯穿整个Job的运行时配置参数。不同的配置将产生不同的JobInstance。如果使用相同的JobParameter运行同一个Job，那么这次运行会重用上次创建的JobInstance。
- JobExecution：表示JobInstance的一次运行。JobInstance运行时可能成功或失败。每次JobInstance的运行都会产生一个JobExecution。
- StepExecution：表示Step的一次运行。Step是Job的一部分，因此一个StepExecution会关联到一个JobExecution。另外该对象还会存储很多与该次Step运行相关的所有数据，因此该对象会有很多属性，并且需要持久化来支持一些SpringBatch的特性。
- ExecutionContext：是一个数据的容器，由Batch框架控制，框架对该容器持久化，开发人员可以使用该容器保存一些数据，以支持在整个Job或整个Step中共享这些数据。

## 二、 Job的创建和使用

`JobDemo.java`

> Job：作业，是Batch操作的基础单元。每个Job由一个或多个Step

##  三、Flow的创建和使用

`FlowDemo.java`

> 1. Flow是多个Step的集合
> 2. 可以被多个Job使用
> 3. 使用FlowBuilder来创建

## 四、split实现并发执行

`SplitDemo.java`

>  实现任务中多个step或多个flow并发执行
>
> 1. 创建若干个step
> 2. 创建两个flow
> 3. 创建一个任务包含以上两个flow，并让这两个flow并发执行

## 五、决策器的使用

`DeciderDemo.java`

> 接口：JobExecutionDecider

## 六、Job的嵌套

`ChildJob1.java` `ChildJob2.java`  `ParentJob.java`

> 一个Job可以嵌套在另一个Job中，被嵌套的Job成为子Job，外部的Job称为父Job。子Job不能单独执行，需要由父Job来启动。
>
> 案例：创建一个子Job，再创建一个父Job

**需要在配置文件（application.properties）文件中指明启动的Job：** `spring.batch.job.names=parentDemoJob`

