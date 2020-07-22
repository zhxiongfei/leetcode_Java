---
title: "PointInside:withEvent:"
date: 2020-07-21T17:32:04+08:00
draft: true
tags: ["ios", "Apple Developer documentation翻译计划"]
---

# pointInside:withEvent:

[苹果官方文档原文地址](https://developer.apple.com/documentation/uikit/uiview/1622533-pointinside?language=occ)

> Returns a Boolean value indicating whether the receiver contains the specified point.
>
> 返回一个布尔值, 表示接收方是否包含指定点.



## Declaration

```objc
- (BOOL)pointInside:(CGPoint)point withEvent:(UIEvent *)event;
```



## Parameters

> - `point`
>
>   A point that is in the receiver’s local coordinate system (bounds).
>
>   - 接收者本地坐标系 (bounds) 的一个点
>
> - `event`
>
>   The event that warranted a call to this method. If you are calling this method from outside your event-handling code, you may specify `nil`.
>
>   - 需要调用此方法的事件. 如果你是从事件处理代码外部调用此方法, 则可以指定 nil



## Return Value

> `YES` if `point` is inside the receiver’s bounds; otherwise, `NO`.
>
> 如果 point 在接收者的 bounds 范围内, return YES; 否则 return NO;



## See Also

> ### Hit Testing in a View
>
> [`- hitTest:withEvent:`](https://developer.apple.com/documentation/uikit/uiview/1622469-hittest?language=occ)
>
> Returns the farthest descendant of the receiver in the view hierarchy (including itself) that contains a specified point.

