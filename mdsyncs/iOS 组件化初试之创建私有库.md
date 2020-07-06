---
title: "iOS组件化初试之创建私有库"
date: 2020-05-07T20:45:03+08:00
draft: true
tags: ["iOS"]
category: "iOS"
---

## iOS组件化初试之创建私有库

iOS组件的实现基本是基于cocoapods，如何利用cocoapod创建我们自己的私有库，是实现组件话的第一步，下边步骤中，我们将利用码云的仓库创建私有库来呈现私有库的创建过程。



此文使用 **码云** 做私有库存储，也可以使用公司 **自己服务器** 存储私有库，步骤是一样的。



### 一，创建私有库的索引库

我们需要创建两个库，一个索引库用于存放spec文件的地方，用于索引倒代码的位置。 一个私有库，指的是我们真正放置组件代码的地方。

**索引库** 就好比指针，私有库就好比对象，指针中存放了对象的地址，通过地址就可以找到对象。

![image-20200507142501063](https://tva1.sinaimg.cn/large/007S8ZIlly1gejv9usg6qj30jg0m5wgr.jpg)



点击创建后，创建的仓库如下图：

![image-20200507142543551](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvamwexlj30os0dign4.jpg)



### 二，将创建好的索引库添加到本地cocoapods仓库

- cocoapods本地仓库路径

  ```
  $ ~/.cocoapods/repos
  ```

- 在本地添加一个自己的远程索引库

  ```
  pod repo add CGRepo https://gitee.com/zhang_xiong__fei/CGRepo.git
  ```

  上边的地址是刚刚我们在码云上创建的索引库的地址。



添加完成后，在repos文件夹中，多了一个CGRepo文件夹，如图:

![屏幕快照 2020-05-07 下午2.29.07](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvepxtd1j30cb04caa7.jpg)

我们把自己创建的索引库放置在cocoapods管理的仓库下，默认情况下只有一个master, 我们常用的SDWebImage,AFNetworking等的本地索引就放在这个仓库中。 

现在通过码云创建的自己的仓库，所以需要自己创建一个索引仓库， 该仓库只存放各组件的索引文件，不存放代码。 



### 三，创建模板

在桌面创建一个CGTool文件夹，并在终端中cd到此文件夹目录下,

![image-20200507143319031](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvihox6xj307m074juf.jpg)

![image-20200507143344884](https://tva1.sinaimg.cn/large/007S8ZIlly1gejviylv5ij30cb02dgll.jpg)

执行创建模板的命令

```
pod lib create CGTool
```

通过以上命令，会自动创建一个模板工程,里边包含我们需要的索引文件, 格式为 spec, 接下来通过该模板制作组件。 

![屏幕快照 2020-05-07 下午2.36.19](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvmso3wjj30hp0e73zz.jpg)



### 四，将组件放入制定目录

我们将组件放入 CGTool/Pods/Development Pods/CGTool文件夹下，如图

![WeChatc4220f8e61f73fa11cc7b202647a190f](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvqh9rgij30e20daabo.jpg)

![image-20200507144048171](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvqa5alqj30rs05k3z6.jpg)



### 五，将制作好的组件上传至码云

上边说过，我们需要创建两个仓库索引库和私有库，私有库用来存放我们制作的组件，接下来我们就要创建私有库，并把制作的组件上传至私有库中。

![image-20200507144439825](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvuazxqtj30hq0ikdhh.jpg)



执行下边命令，上传模板文件

```
$ cd /Users/liuxiaoyong/Desktop/CGTool/CGTool
$ git remote add origin https://gitee.com/zhang_xiong__fei/CGTool.git
$ git push -u origin master
```

上边几条命令，我们只上传了模板文件，接下来，我们创建自己添加的组件代码

```
$ git add .
$ git commit -m "组件"
$ git push -u origin master
```

![image-20200507144957805](https://tva1.sinaimg.cn/large/007S8ZIlly1gejvztdw1nj30o80e940b.jpg)

此时查看仓库，模板以及我们添加的Tool组件都已经上传。



### 六，更改模板文件相关信息

我们需要修改三个地方， s.summary指的是组件描述。 s.homepage 和 s.source。 如图：

![image-20200507145527408](https://tva1.sinaimg.cn/large/007S8ZIlly1gejw5j1zw8j30vl0fg11k.jpg)



### 七，验证spec文件

执行如下命令：

```
$ pod lib lint --private
```

顺利的话，会得到验证通过的信息。 如下图：

![屏幕快照 2020-05-07 下午2.57.46](https://tva1.sinaimg.cn/large/007S8ZIlly1gejw8nd9ccj30kn04hgmd.jpg)



### 八，给版本打一个tag

此处的 版本号 0.1.0要与 spec文件的保持一致。 一定要与Xcode中看到的s.version号一致。

```
$ git tag 0.1.0
$ git push --tags

```



### 九，建立关联

执行如下命令：

```
pod repo push CGRepo CGTool.podspec
```

![image-20200507150319436](https://tva1.sinaimg.cn/large/007S8ZIlly1gejwdq1325j30kl0a0dh4.jpg)

通过这一步，我们同步好了远程和本地索引库，到此私有库制作完毕。



### 十，使用私有库

接下来我们新建一个项目，''私有库测试'' 。 来使用我们的私有库。

- 新建工程 ''私有库测试“
- 到这个工程目录下
- 执行pod init命令
- 编辑podfile , 如下图

当使用私有库时，需要饮用我们之前第一步创建的索引仓库地址。 如下图：

![image-20200507150722505](https://tva1.sinaimg.cn/large/007S8ZIlly1gejwhyiydnj30mg0d9tak.jpg)

- 执行 pod install命令

执行完毕后，我们看到 Pods文件夹下，多出来了我们自己创建的组件。 如图：

![image-20200513105612677](https://tva1.sinaimg.cn/large/007S8ZIlly1ger4fd24jzj307d08n3yo.jpg)

至此，私有库创建完成～



### 十一，版本更新

- 需要更新版本时，首先需要将老版本从仓库中下载下来， 如图

- 打开老版本项目， 在Tools文件夹中进行更改，添加/删除/修改文件操作。

- 比如，需要再添加一种分类的代码，把需要添加的代码复制进Tools文件夹。

- 新版本打下tag  (eg git tab 0.1.2)

- 将tag push到远程 (eg git push --tags)

- 同步需要修改 .podspec文件中 s.version中的版本号，如图

  ![image-20200507172210031](https://tva1.sinaimg.cn/large/007S8ZIlly1gek0e73efij30lv06975q.jpg)

- 再次执行建立关联命令 (pod repo push CGRepo CGTool.podspec)

- 将变动push到远程

- 然后在我们需要更新版本项目的podfile文件中，将版本号改为当前版本号。 重新执行pod install即可。



### 十二，repo管理多个版本库

重复执行 **三 到 九**步骤即可。 

![屏幕快照 2020-05-13 上午10.54.54](https://tva1.sinaimg.cn/large/007S8ZIlly1geqmxh624qj307r09m41o.jpg)



### 十二，repo管理多个版本库

重复执行 **三 到 九**步骤即可。 

![屏幕快照 2020-05-13 上午10.54.54](https://tva1.sinaimg.cn/large/007S8ZIlly1geqmxh624qj307r09m41o.jpg)



### 十三，私有库有依赖库

依然是重复 **三 到 九**步骤即可，不同之处, 需要以下几点

- 编辑  **.podspec** 文件

- 指定依赖库, 如笔者依赖 SVProgressHUD

  ```ruby
  s.dependency 'SVProgressHUD', '~>2.2.5'
  ```

- 修改版本 s.version

- 在第七步验证spec时，需要指定私有库的源 ，和cocoapods的源. (笔者直接使用清华的镜像源，速度更快)

  ```ruby
  $ pod lib lint --private --sources='https://gitee.com/zhang_xiong__fei/ZXFRepo,https://mirrors.tuna.tsinghua.edu.cn/git/CocoaPods/Specs.git'
  ```

- 第九步建立关联时，也需要指定源

  ```ruby
  $ pod repo push ZXFRepo ZXFTool.podspec --sources='https://gitee.com/zhang_xiong__fei/ZXFRepo,https://mirrors.tuna.tsinghua.edu.cn/git/CocoaPods/Specs.git'
  ```

- 在使用私有库时，在项目的podfile中，依然需要指定源

  ```ruby
  source 'https://mirrors.tuna.tsinghua.edu.cn/git/CocoaPods/Specs.git'
  source 'https://gitee.com/zhang_xiong__fei/ZXFRepo'
  
  target '测试' do
    # Comment the next line if you don't want to use dynamic frameworks
    use_frameworks!
  
    pod 'ZXFTool', '~> 0.1.1'
  
  end
  ```

  
  
  ### 十四：忽略警告
  
  在验证 podspec 和 建立关联时，如果是警告而非错误导致的验证不通过，则可以加上 --allow-warnings解决. 例如：
  
  ```ruby
  $ pod lib lint --private --sources='https://gitee.com/zhang_xiong__fei/ZXFTools.git,https://mirrors.tuna.tsinghua.edu.cn/git/CocoaPods/Specs.git' --allow-warnings
  ```
  
  