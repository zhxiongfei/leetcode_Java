ios基于码云创建私有库



s

![image-20200513105612677](https://tva1.sinaimg.cn/large/007S8ZIlly1geqmyfe3zzj307d08nq61.jpg)

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

