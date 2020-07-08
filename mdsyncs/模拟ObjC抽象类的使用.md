---
title: "模拟ObjC抽象类的使用"
date: 2020-07-08T14:18:30+08:00
draft: true
---

# 模拟ojbc中抽象类的使用

## NSCoder

- NSCoder 是一个抽象类
- 不能被直接使用，只能实例化子类并使用子类。
- 以下内容来自苹果官方文档

> # NSCoder
>
> An abstract class that serves as the basis for objects that enable archiving and distribution of other objects.
>
> Language
>
> - [Swift](apple-reference-documentation://hs-QohqVnU)
> - Objective-C
>
> SDKs
>
> - iOS 2.0+
> - macOS 10.0+
> - tvOS 9.0+
> - watchOS 2.0+
> - Mac Catalyst 13.0+
>
> Framework
>
> - Foundation
>
> On This Page
>
> - [Declaration](apple-reference-documentation://hc-QohqVnU#declarations)
> - [Overview](apple-reference-documentation://hc-QohqVnU#overview)
> - [Topics](apple-reference-documentation://hc-QohqVnU#topics)
> - [Relationships](apple-reference-documentation://hc-QohqVnU#relationships)
>
> ------
>
> ## Declaration
>
> ```
> @interface NSCoder : NSObject
> ```
>
> ## Overview
>
> `NSCoder` declares the interface used by concrete subclasses to transfer objects and other values between memory and some other format. This capability provides the basis for archiving (storing objects and data on disk) and distribution (copying objects and data items between different processes or threads). The concrete subclasses provided by Foundation for these purposes are [`NSArchiver`](apple-reference-documentation://hcV2yyhcOH), [`NSUnarchiver`](apple-reference-documentation://hcciqXyX7U), [`NSKeyedArchiver`](apple-reference-documentation://hc0T6C3j_2), [`NSKeyedUnarchiver`](apple-reference-documentation://hcADiXUh9J), and [`NSPortCoder`](apple-reference-documentation://hcx8TY-cmz). Concrete subclasses of `NSCoder` are “coder classes”, and instances of these classes are “coder objects” (or simply “coders”). A coder that can only encode values is an “encoder”, and one that can only decode values is a “decoder”.
>
> `NSCoder` operates on objects, scalars, C arrays, structures, strings, and on pointers to these types. It doesn’t handle types whose implementation varies across platforms, such as `union`, `void *`, function pointers, and long chains of pointers. A coder stores object type information along with the data, so an object decoded from a stream of bytes is normally of the same class as the object that was originally encoded into the stream. An object can change its class when encoded, however; this is described in [Archives and Serializations Programming Guide](https://developer.apple.com/library/archive/documentation/Cocoa/Conceptual/Archiving/Archiving.html#//apple_ref/doc/uid/10000047i).
>
> The AVFoundation framework adds methods to the [`NSCoder`](apple-reference-documentation://hc-QohqVnU) class to make it easier to create archives including Core Media time structures, and extract Core Media time structure from archives.
>
> -----引用自苹果官方文档

