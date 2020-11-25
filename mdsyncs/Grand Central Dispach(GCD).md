## Grand Central Dispach(GCD)



### 什么是GCD？

**Grand Central Dispatch**(GCD)是异步执行任务的技术之一。一般将应用程序中记述的线程管理用的代码在系统级中实现。开发者只需要定义想执行的任务并追加到适当的 **Dispatch queue**中，GCD就能生成必要的线程并执行任务。由于线程管理是作为系统的一部分来实现的，由此可统一管理，也可执行任务，这样就比以前的线程更有效率。

也就是说，GCD用非常简洁的方式，实现了极为复杂繁琐的多线程编程。

以下就是在后台线程执行长时间处理，处理结束后，主线程使用该处理结果的代码。

```objc
    dispatch_queue_t queue = dispatch_queue_create("myQueue", DISPATCH_QUEUE_CONCURRENT);
    dispatch_async(queue, ^{
       /*
        长时间处理
        例如 数据库访问等
        */
        
        /*
         长时间处理结束,主线程使用该处理结果
         */
        dispatch_async(dispatch_get_main_queue(), ^{
            /**
             只有主线程可以执行的处理
             例如 UI界面刷新
             */
        });
    });
```



### 多线程编程

#### 什么是线程？

ObjC代码是如何在Mac 或 iPhone上执行的呢？

- ObjC源代码通过编译器转换为如下的CPU命令列(二进制代码)
- 汇集CPU命令列和数据，将其作为一个应用程序安装到Mac 或 iPhone上
- Mac,iPhone的操作系统 Mac OS 和 iOS根据用户的指示启动该应用程序后，首先便将包含在应用程序中的CPU指令列配置到内存中。CPU从应用程序指定的地址开始，一个一个地执行CPU命令列。
- 在ObjC的 if 语句 和 for语句等控制语句或函数调用的情况下，执行命令列的地址会远离当前的位置(位置迁移).但是,由于一个CPU一次只能执行一个命令，不能执行某处分开的并列的两个命令，因此 **通过CPU执行的CPU命令列就好比一条无分叉的大道，其执行不会出现分歧**。
- 这里说的，**1个CPU执行的CPU命令列为一条无分叉的路径，即为线程**
- 这种 **无分叉的路径不止1条，存在多条时即为 多线程**，在多线程中，1个CPU核执行多条不通路径上的不同命令。



一个CPU核一次只能够执行的CPU命令始终为1，那么如何在多条路径执行CPU命令列呢？

- OS X 和 iOS的核心 **XNU** 内核在发生操作系统时间时(如每隔一段时间，唤起系统调用等情况)会**切换执行路径**
- 执行中路径的状态，例如CPU的寄存器信息保存到各自路径专用的内存块中，从切换目标路径专用的内存块，复原CPU寄存器等信息，继续执行切换路径的CPU命令列。被称为 **上下文切换**
- 由于使用多线程的程序可以**在某个线程和其他线程之间反复多次进行上下文切换**，因此看上去就好像1个CPU核能够并行的执行多个线程一样
- 在具有多个核心的CPU的情况下，就是真正的提供了多个CPU核并行执行多个线程的技术。



多线程技术容易发生的问题？

- 多个线程更新相同资源会导致数据的不一致**(数据竞争)**。
- 停止等待事件的线程会导致多个线程相互持续等待**(死锁)**。
- 使用太多线程会**消耗大量内存**。

尽管有这些问题，仍然应当使用多线程编程，因为使用多线程编程课**保证程序的响应性能**。





### GCD的API

苹果官方对GCD的说明: **开发者要做的只是定义想执行的任务并追加到适当的Dispatch Queue中。**

Dispatch Queue是什么呢？是执行处理的等待队列。应用程序编程人员通过 dispatch_async 函数等API，在Block语法中将其追加到  Dispatch Queue中。Dispatch Queue按照追加的顺序(先进先出FIFO)执行处理。



#### Dispatch Queue的种类

另外在执行处理时存在两种 Dispatch Queue, 一种是等待执行中处理的 Serial Dispatch Queue, 一种是不等待现在执行处理中 Concurrent Dispatch Queue。

| Dispatch Queue的种类      | 说明                     |
| ------------------------- | ------------------------ |
| Serial Dispatch Queue     | 等待现在执行中处理结束   |
| Concurrent Dispatch Queue | 不等待现在执行中处理结束 |

- Serial Dispatch Queue  **要等待**现在执行的处理结束，所以任务按顺序执行，同时执行的处理数只能有1个。
- Concurrent Dispatch Queue **不用等待**现在执行的处理结束，因此可以并行的执行多个处理，但并行处理的数量取决于当前系统的状态
  - 即iOS 和 OS X基于 Dispatch Queue中的处理数，CPU核数以及CPU负荷等当前系统的状态来决定 Concurrent Dispatch Queue中并行执行的处理数。
  - 所谓并行执行，就是使用多个线程同时执行多个处理。



#### dispatch_queue_create

使用dispatch_queue_create生成 Dispatch Queue.

```objc
dispatch_queue_t serialQueue = dispatch_queue_create("com.zxf.serialqueue", DISPATCH_QUEUE_SERIAL);
```



##### 使用Serial Dispatch Queue注意事项

- Serial Dispatch Queue同时只能执行1个追加处理。虽然 Dispatch Queue 受到系统资源的限制，但用 dispatch_queue_create函数课生成任意多个 Dispatch Queue
- 当生成多个 Serial Dispatch Queue时，各个Serial Dispatch Queue将并行执行。一旦生成 Serial Dispatch Queue并追加处理，系统对一个Serial Dispatch Queue就只生成并使用一个线程
- 如果生成2000个 Serial Dispatch Queue，那么就生成2000条线程。
- 向之前列举的多线程问题一样，如果过多使用多线程，就会消费大量内存，引起大量的上下文切换，大幅度降低系统的响应性能。
- 只是为了避免多线程编程问题 - 多个线程更新相同资源导致数据竞争时使用 Serail Dispatch Queue
- 但是Serial Dispatch Queue的生成个数应当仅限所必需的数量。虽然 Serial Dispatch Queue能比Concurrent Dispatch Queue能生成更多的线程，但绝不能大量生成 Serial Dispatch Queue
- 当想并行执行不发生数据竞争等问题的处理时，使用 Concurrent Dispatch Queue.而且 Concurent 来说，不管生成多少，由于 XNU内核只使用有效管理的线程，因此不会发生 Serial Dispatch Queue的那些问题。





#### Main Dispatch Queue/Global Dispatch Queue

第二种方法是获取系统标准提供的 Dispatch Queue.

**Main Dispatch Queue** : 是在主线程中执行的 Dispatch Queue。因为主线程只有1个，所以 Main Dispatch Queue 自然就是 Serial Dispatch Queue

- 追加到 Main Dispatch Queue的处理在主线程的RunLoop中执行。

**Global Dispatch Queue**是所有应用程序都能够使用呢的 Concurrent Dispatch Queue. 在 XNU内核用于 Global Dispatch Queue的线程并不能保证实时性，因此执行优先级只是大致的判断

| 名称                          | Dispatch Queue种类 | 说明             |
| ----------------------------- | ------------------ | ---------------- |
| Main Dispatch Queue           | Serial             | 主线程执行       |
| Global (High Priority)        | Concurrent         | 执行优先级:高    |
| Global (Default Priority)     | Concurrent         | 执行优先级: 默认 |
| Global (Low Priority)         | Concurrent         | 执行优先级: 低   |
| Global (Backgrounnd Priority) | Concurrent         | 执行优先级: 后台 |





#### dispach_set_target_queue

dispatch_queue_create函数生成的 Dispatch Queue不管是Serial Dispatch Queue还是 Concurrent Dispatch Queue，都使用与默认的 Global Dispatch Queue相同执行优先级的线程。

变更优先级要使用 dispatch_set_target 函数

```objc
dispatch_queue_t serialQueue = dispatch_queue_create("com.zxf.serialqueue", DISPATCH_QUEUE_SERIAL);

dispatch_queue_t globalBackgroundDispatchQueue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0);

dispatch_set_target_queue(serialQueue, globalBackgroundDispatchQueue);
```



在必须将不可必行执行的处理追加到某个Serial Dispatch Queue中时，如果使用 dispach_set_target_queue 函数将目标指定为某一个 Serial Dispatch Queue ，即可防止处理并行执行。



#### dispatch_after

想在指定时间后执行处理的情况，可使用 dispatch_after函数来实现.

```objc
dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(3 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        
});
```

**注意** : 

- dispatch_after 函数并不是在指定时间后执行处理，而只是在指定时间追加处理到 Dispatch Queue.
- 因为Main Dispatch Queue在主线程的RunLoop中执行，所以比如每个 1/60s执行的RunLoop中，Block最快在3s后执行，最慢在 3 + 1/60秒后执行，并且在Main Dispatch Queue 有大量追加或主线程本身有延迟时，这个时间会更长。



#### disptch_group

在追加到 dispatch_queue中的多个处理全部结束后想执行结束处理，这种情况经常出现。

- 只使用一个 Serial Dispatch Queue时，只要将想执行难的处理全部追加到该 Serail Dispatch Queue中并在最后追加结束处理，即可实现
- 但是使用 Concurrent 呢？
- Dispatch_group

```objc
- (void)group{
    dispatch_group_t group = dispatch_group_create();
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    
    dispatch_group_async(group, queue, ^{NSLog(@"blk0");});
    dispatch_group_async(group, queue, ^{NSLog(@"blk1");});
    dispatch_group_async(group, queue, ^{NSLog(@"blk2");});
    
    dispatch_group_notify(group, queue, ^{NSLog(@"done");});
}
```

- blk0, blk1, blk2 因为是异步添加到 global_queue中, 所以顺序不固定。
- 但时 done 一定是最后输出的。



另外，也可以使用 dispatch_group_wait 函数仅等待全部处理执行结束

```objc
- (void)group{
    dispatch_group_t group = dispatch_group_create();
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    
    dispatch_group_async(group, queue, ^{NSLog(@"blk0");});
    dispatch_group_async(group, queue, ^{NSLog(@"blk1");});
    dispatch_group_async(group, queue, ^{NSLog(@"blk2");});
    
    dispatch_group_wait(group, DISPATCH_TIME_FOREVER);
    NSLog(@"done");
}
```

- 一旦调用 dispatch_group_wait, 该函数就处于调用状态而不返回。即执行 dispatch_group_wait 的线程(当前线程)停止，在经过 dispatch_group_wait 函数中指定的时间或属于指定 group 的处理全部执行结束之前，执行该函数的线程停止。
- 在主线程的 RunLoop 的每次循环中，可检查是否结束，从而不耗费多余的等待时间，虽然这样也可以，但一般情况下，还是推荐使用 dispatch_group_notify 函数追加结束处理到 Main Dispatch Queue中。



#### dispatch_barrier_async

```objc
- (void)viewDidLoad{
    dispatch_queue_t queue = dispatch_queue_create("com.zxf.gcd.barrier", DISPATCH_QUEUE_CONCURRENT);
    /**读取*/
    dispatch_async(queue, ^{blk1_for_readinng});
    dispatch_async(queue, ^{blk2_for_readinng});
    dispatch_async(queue, ^{blk3_for_readinng});
    dispatch_async(queue, ^{blk4_for_readinng});
    dispatch_async(queue, ^{blk5_for_readinng});
    
    /**写入处理*/
    dispatch_barrier_async(queue, ^{blk_for_writing});
    
    /**读取*/
    dispatch_async(queue, ^{blk6_for_readinng});
    dispatch_async(queue, ^{blk7_for_readinng});
    dispatch_async(queue, ^{blk8_for_readinng});
    dispatch_async(queue, ^{blk9_for_readinng});
    dispatch_async(queue, ^{blk10_for_readinng});
}
```

- dispatch_barrier_async函数会等待追加到Concurrent Dispatch Queue上的并行执行的全部处理结束之后，再将指定的处理添加到 Concurrent Dispatch Queue中。
- 再有 dispatch_barrier_async 函数追加的处理执行完毕后，Concurrent Dispatch Queue才恢复为一般的动作，追加到Concurrent Dispatch Queue的处理又开始并行执行。
- 使用 Concurrent Dispatch Queue 和 dispatch_barrier_async 函数课实现高效率的数据库访问和文件访问。



#### dispatch_sync

dispatch_async函数的 async 意味着 **非同步**，就是将指定的 Block **非同步地** 追加到指定的 Dispatch Queue中。dispatch_async不做任何等待。

dispatch_sync函数意味着**同步**, 也就是将指定的Block **同步地** 追加到 Dispatch Queue中，在追加Block结束之前，dispatch_sync函数会一直等待。

- 如 dispatch_group_wait 函数说明所示，**等待**意味着**当前线程停止**

- 假设有这样一种情况 : 执行Main Dispatch Queue时，使用另外的线程 Global Dispatch Queue 进行处理，处理结束后立即使用所得到的结果，这种情况下就要使用 dispatch_sync函数

  ```objc
  dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
  dispatch_sync(queue, ^{
          /** 处理 */
  });
  ```

- 一旦调用 dispatch_sync函数，那么在指定的处理结束执行之前，该函数不会返回。dispatch_sync函数课简化源代码，也可说是简易版的 dispatch_group_wait 函数.



##### 死锁

当前串型队列 -> 同步添加任务 -> 死锁

一旦调用 dispatch_sync 函数，那么在指定的 处理执行结束之前，该函数不会返回。

```objc
dispatch_queue_t queue = dispatch_get_main_queue();
dispatch_sync(queue, ^{
    
});
```



```objc
dispatch_async(queue, ^{
    dispatch_sync(queue, ^{
		/** 处理 */
		});
});
```



```objc
dispatch_queue_t queue = dispatch_queue_create("com.zxf.serial", DISPATCH_QUEUE_SERIAL);
dispatch_async(queue, ^{
    dispatch_sync(queue, ^{
        /** 处理 */
    });
});
```



#### dispatch_apply

dispatch_apply函数按指定的次数将Block追加到指定的 Dispatch queue中，并**等待全部处理执行结束**。

```objc
- (void)dispatch_apply{
    NSArray *arr = @[@1,@2,@3,@4,@5,@6,@7,@8];
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    /**
      在 Global Dispach Queue中非同步执行
     */
    dispatch_async(queue, ^{
        /**
         Global Dispatch Queue
         等待 dispatch_apply 函数中全部处理执行结束
         */
        dispatch_apply(arr.count, queue, ^(size_t index) {
           /**
            并列处理包含在 NSArray 对象的全部对象
            */
            NSLog(@"%zu:%@",index, arr[index]);
        });
    });
    
    /**
     dispatch_apply 函数中处理全部执行结束
     */
    /**
     在Main Dispach Queue中非同步执行
     */
    dispatch_async(dispatch_get_main_queue(), ^{
        /**
         在Main Dispach Queue中执行处理
         用户界面刷新等
         */
        NSLog(@"done");
    });
}
```



#### dispatch_suspend / dispatch_resume

当追加大量处理到 dispatch_queue 时，在追加处理的过程中，有时希望不执行已追加的处理，这种情况下，只要挂起 Dispatch Queue即可，当可还行时再恢复。

```objc
dispatch_suspend(queue);	// 挂起
dispatch_resume(queue);		// 恢复
```



#### Dispatch Semaphore

当并行执行的处理更新数据时，会产生数据不一致的情况，有时应用程序还会异常结束。

- Semaphore 是持有计数的信号，该计数是多线程编程中的计数类型信号。
- 所谓信号量，类似过马路时常用的手旗。 可以通过举起手旗，不可通过时放下手旗。而在Dispatch Semaphore 中，使用计数来实现该功能。 计数为 0 等待，计数为 1 或 大于1时，减去1 而不等待。
- dispatch_semaphore_wait 函数等待 semaphore 的计数值大于或等于1.
  - 当计数值 >= 1, 或者在待机中计数值 >= 1时，对该计数值减去1，并从 dispatch_semaphore_wait 中返回. 
- dispatch_semaphore_signal 可以将计数值 + 1，如果有 dispatch_sesmaphore_wait 函数等待 dispatch semaphore 的计数值增加的线程 ，就由最先等待的线程执行.

```objc
- (void)semaphore{
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    /**
     生成 dispatch semaphore
     dispatch semaphore 的计数初始值设定为 1
     保证可访问 NSMutableArray类对象的线程同时只有1个
     */
    dispatch_semaphore_t semaphore = dispatch_semaphore_create(1);
    NSMutableArray *arr = [[NSMutableArray alloc] init];
    for (int i = 0; i < 10000; i ++) {
        dispatch_async(queue, ^{
            /**
             等待 dispatch semaphore
             一直等待, 直到Dispatch Semaphore 的计数值达到 >= 1
             */
            /**
             由于 Dispatch Semaphore 的计数值达到 >= 1
             所以将 Dispatch Semaphore 的计数值减去 1
             dispatch_semaphore_wait 函数返回
             
             即将执行到此时的
             dispatch semaphore的计数值恒为 1
             因此可以安全的进行更新
             */
            dispatch_semaphore_wait(semaphore, DISPATCH_TIME_FOREVER);
            
            [arr addObject:[NSNumber numberWithInt:i]];
            
            /**
             排他控制处理结束
             所以通过 dispatch_semaphore_signal 函数
             将 Dispatch semaphore 的计数值 +1
             
             如果有通过 dispatch_semaphore_wait函数
             等待 dispatch semaphore的计数值增加的线程
             就由最先等待的线程执行
             */
            dispatch_semaphore_signal(semaphore);
        });
    }
}
```



#### dispatch_once

dispatch_once函数是保证在应用程序执行中只执行一次指定处理的API， 单例

通过 dispatch_once 函数，该源代码即使在多线程环境下执行，也能保证百分百安全

```objc
- (void)once{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        /**
         初始化
         */
    });
}
```



#### dispatch_IO

用于读取大文件，将文件分成合适的大小并使用 Global Dispatch Queue并列读取，会比一般的读取速度快不少。

能实现这一功能的就是 DispatchI/O 和 Dispatch Data.