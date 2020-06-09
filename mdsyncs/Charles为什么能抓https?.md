---
title: "Charles为什么能抓https?"
date: 2020-06-09T10:36:04+08:00
draft: true
---

# 经历

曾经去某公司ms, 公司名称就不透露了， 面试官问到，**Charles为什么能抓https?** 当时没回答上来。一年半以后，终于把有关https **加密/数字签名/证书,** 以及**Charles抓https原理**等整理了一遍。

# 姐妹篇

[简单了解对称和非对称加密](https://zhangxiongfeiv.github.io/post/简单了解对称和非对称加密)

[单向散列函数](https://zhangxiongfeiv.github.io/post/单向散列函数)

[数字签名](https://zhangxiongfeiv.github.io/post/数字签名)

[CA数字证书](https://zhangxiongfeiv.github.io/post/ca数字证书)

# 安全的https

我们都知道，https通过一系列的 **加密，单项散列函数, 数字签名，数字证书**等操作来避免以下不安全的情况

 (消息发送者以下简称 **sender**，消息接收者一下简称 **receiver**)

## 明文传输被窃听

- SSL，TSL中的混合密码加密
  - 对称密码加密(DES,3DES,AES等)无法解决**密钥配送**问题
  - 公钥密码加密(公钥加密，私钥解密) 解决了**密钥配送**问题，但是效率低
  - 所以采用 **混合密码加密**
    - **receiver**生成一对 **公钥，私钥**
    - **recerver** 将生成的**公钥**发送给**sender**，私钥自己保存
    - **sender**随机生成 **会话密钥**(对称加密的密钥)
    - **sender**使用**receiver**的公钥，加密随机密钥
    - **sender**使用 **公钥加密过的随机密钥** **对称机密** **要发送的消息**
    - **sender**把**加密后的随机密钥** 和 **对称机密过的消息体** 都发送给**receiver**
    - **receiver**在接收到消息时，先使用自己**私钥** 解密出 **随机密钥**
    - 再使用**随机密钥**, 解密出消息体

## **信息防篡改**

- 但是使用过上述**加解密**方法后，依然会存在信息**被篡改**的风险
  - **sender** 发送的内容可能会被**篡改**，或者有人**伪装**发送消息
  - 问题来了，如何确定**消息的真实性**？防止**被篡改**，**伪装，否认**？
- 解决方法 : **数字签名**
  - **sender** 使用 自己的**私钥** 加密消息(签名)
  - **receiver** 使用 **sender的公钥** 解密消息
  - 可以看出来，**数字签名 ，其实就是将消息加密反过来使用**
  - 消息发送过程就变成这样：
    - **sender** 将 **加密后的消息**发送给**receiver**
    - 并且，用**自己的私钥加密** **消息体的散列值**
    - 将**加密后的消息**，以及**数字签名**一块发送给**receiver**
    - **reveiver** 解密出消息后，对消息进行**单向散列函数**计算出散列值,并解密出**数字签名**中的**散列值**，如果一致，说明不是被篡改。 否则消息被篡改。
- **数字签名**解决的问题
  - 确认消息的**完整性**
  - 识别消息是否**被篡改**
  - 防止消息发送者**抵赖**

## **防伪装**

- **数字签名**无法解决的问题？
  - 要使用 **数字签名**， 前提得保证 用于验证签名的**公钥**，**必须是真正的发送者的。**
  - 如果遇到了**中间人攻击**(**Man-In-MiddleAttack)(MITM)**
    - 中间人伪装再 **sender** 和 **receiver**之间, 发送伪造的**公钥**
    - 那么，数字签名，就是去了q签名的效果
  - 所以要**保证数字签名有效**， 首先得验证**公钥的合法性**
  - 如何保证**公钥的合法性**呢?
  - 答案是： **证书**
- **证书**
  - 也叫**公钥证书**, (Public-key Certificate，PKC)
    - 其中包含**姓名，邮箱**等个人信息，以及此人的**公钥**
    - 并由 认证机构 (Certificate Authoriry, CA机构)施加**数字签名**
  - **CA**就是能证明**公钥却属此人**，且能够**生成数字签名**的组织
    - 有国际性组织，政府设立的组织
  - **证书**如何使用？
    - **receiver** 生成 **密钥对**
    - **receiver** 在**认证机构** 注册自己的**公钥**
    - **认证机构**用自己的私钥对**receiver**的公钥进行**数字签名**
    - **sender**得到带有**数字签名**的 **receiver** 公钥(也就是**证书**)
    - **receiver**使用**认证机构的公钥**验证签名，验证**公钥的合法性**
    - **sender** 开始用 **receiver** 的公钥加密消息并开始通信

**综合以上**, **https** 可以做到了 **防窃听，防止信息被篡改， 防伪装**， 确保了通信过程的安全。

# 为何 Charles 能抓包 https?

既然**Https**使用了上述一系列的能保证 **防止窃听，防止信息被篡改，防止伪装**，那么为什么 Charles 还能抓 **Https**请求呢？

这是不是和 **Https** 的初衷相悖？

下边我们来看一下，**Chales官网**给出的**SSL代理**的解释

> ### SSL Proxying
>
> Charles can be used as a man-in-the-middle HTTPS proxy, enabling you to view in plain text the communication between web browser and SSL web server.
>
> Charles does this by becoming a man-in-the-middle. Instead of your browser seeing the server’s certificate, Charles dynamically generates a certificate for the server and signs it with its own root certificate (the Charles CA Certificate). Charles receives the server’s certificate, while your browser receives Charles’s certificate. Therefore you will see a security warning, indicating that the root authority is not trusted. If you add the Charles CA Certificate to your trusted certificates you will no longer see any warnings – see below for how to do this.
>
> Charles still communicates via SSL to the web server. The communication is SSL (encrypted) from web browser to Charles and also SSL (encrypted) from Charles to the web server.
>
> This functionality is essential for debugging secure (SSL) web applications.
>
> You may turn on or off this SSL proxying in the Proxy Preferences. With SSL proxying turned off Charles just forwards all SSL traffic directly to the target web server.
>
> #### Choosing hosts to SSL Proxy
>
> You must specifically identify the host names you want to enable SSL Proxying on. The list is in the Proxy Settings, SSL tab. You can also right-click on a host name in the structure view and turn on or off SSL Proxying.
>
> After adding a host name to the SSL Proxying list you may need to restart Charles for existing browser sessions to change.
>
> If you want to SSL Proxy all host names then enter * into the host names list in the SSL Proxying Settings.
>
> #### Trusting Charles's SSL Certificates
>
> Charles generates its own certificates for sites, which it signs using a Charles Root Certificate. You will see a warning in your browser, or other application, when it receives that certificate because the Charles Root Certificate is not in your list of trusted root certificates. See [SSL Certificates](https://www.charlesproxy.com/documentation/using-charles/ssl-certificates/) for instructions for trusting Charles’s Root Certificate.

英语翻译水平比较差，配合欧路词典的的翻译，试着翻译了一下:



## SSL代理

- Charles可以作为中间人https代理，让你可以能够以纯文本的方式查看Web浏览器 和 SSL Web服务器之间的通信。

- Charles通过成为中间人来做到这一点。 Charles不会让你的浏览器看到服务器的证书，而是动态地为服务器生成了一个证书，并且用Charles的根证书(Charles CA证书)对其进行签名。

- Charles收到服务器的证书，而浏览器会收到Charles的证书。因此你将看到一个安全警告，表明根证书不被信任。如果你将Charles的CA证书添加到你手信任的证书中，你将再看不到任何的警告⚠️。

- Charles仍然通过SSL与服务器通信。从Web浏览器到Charles的通信是SSL(加密)， 从Charles到服务器通信仍然是SSL(加密).

- 此功能对于调试安全(SSL) Web应用程序至关重要。

- 你可以在“代理选项”中打开或者关闭SSL代理。 禁用SSL代理后，Charles会将所有的SSL流量直接转发到目标Web服务器。

## 选择主机使用SSL代理

- 你必须明确标识要启用SSL代理的主机名。改列表位于”代理设置”的”SSL” 选项卡中。您也可以在结构视图中右键单击主机名，然后打开或关闭SSL代理。

- 将主机名添加到SSL代理列表后，你可能需要重新启动Charles以更改现有的浏览器会话。

- 如果对所有主机名进行SSL代理，则在SSL代理设置中的主机名列表输入 “*”。

## 信任Charles 的 SSL证书

- Charles会为站点生成自己的证书， 并使用Charles 根证书进行签名。 当浏览器或其他应用程序收到改证书时，您会看到警告，因为 Charles根证书不在您的受信任列表中。有关信任Charles的根证书说明，请参阅SSL证书。



## 总结：

- 客户端向服务器发起HTTPS请求

- Charles拦截客户端的请求，伪装成客户端向服务器进行请求

- 服务器向“客户端”（实际上是Charles）返回服务器的CA证书

- Charles拦截服务器的响应，获取服务器证书公钥，然后自己制作一张证书，将服务器证书替换后发送给客户端。（这一步，Charles拿到了服务器证书的公钥）

- 客户端接收到“服务器”（实际上是Charles）的证书后，生成一个对称密钥，用Charles的公钥加密，发送给“服务器”（Charles）

- Charles拦截客户端的响应，用自己的私钥解密对称密钥，然后用服务器证书公钥加密，发送给服务器。（这一步，Charles拿到了对称密钥）

- 服务器用自己的私钥解密对称密钥，向“客户端”（Charles）发送响应

- Charles拦截服务器的响应，替换成自己的证书后发送给客户端

- 至此，连接建立，Charles拿到了 服务器证书的公钥 和 客户端与服务器协商的对称密钥，之后就可以解密或者修改加密的报文了。

- 简单来说，就是Charles作为“中间人代理”，拿到了 **服务器证书公钥** 和 HTTPS连接的**对称密钥**，

  - 前提是客户端选择**信任并安装Charles的CA证书**，否则客户端就会“报警”并中止连接。这样看来，HTTPS还是很安全的。

  ![image-20200609142726710](https://tva1.sinaimg.cn/large/007S8ZIlly1gfm0slm1fsj31420qmtgb.jpg)