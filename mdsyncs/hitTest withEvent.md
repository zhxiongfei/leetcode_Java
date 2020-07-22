---
title: "HitTest:withEvent:"
date: 2020-07-21T17:06:53+08:00
draft: true
tags: ["ios", "Apple Developer ducomentation翻译计划"]
---

# hitTest:withEvent:

苹果官方文档原文地址 : [hitTest:withEvent:](https://developer.apple.com/documentation/uikit/uiview/1622469-hittest?language=occ)

> Returns the farthest descendant of the receiver in the view hierarchy (including itself) that contains a specified point.
>
> 返回包含指定点的视图层次结构中接收者的最远后代 (包括自身).

## Declaration

```objective-c
- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event;
```



## Parameters

> - `point`
>
>   A point specified in the receiver’s local coordinate system (bounds). 
>
>   接收者本地坐标系 (bounds)  中指定的点.
>
> - `event`
>
>   The event that warranted a call to this method. If you are calling this method from outside your event-handling code, you may specify `nil`.
>
>   需要调用此方法的事件. 如果你要从事件代码外部调用此方法，则可以指定 nil.



## Return Value

> The view object that is the farthest descendent of the current view and contains `point`. Returns `nil` if the point lies completely outside the receiver’s view hierarchy.
>
> - 包含指定点的视图层次结构中接收者的最远后代 (包括自身). 
> - 如果该点完全位于接收者视图层次之外, 则返回 nil



## Discussion

> This method traverses the view hierarchy by calling the pointInside:withEvent: method of each subview to determine which subview should receive a touch event.
>
> If pointInside:withEvent: returns YES, then the subview’s hierarchy is similarly traversed until the frontmost view containing the specified point is found.
>
> If a view does not contain the point, its branch of the view hierarchy is ignored. You rarely need to call this method yourself, but you might override it to hide touch events from subviews.
>
> This method ignores view objects that are hidden, that have disabled user interactions, or have an alpha level less than 0.01. 
>
> This method does not take the view’s content into account when determining a hit. Thus, a view can still be returned even if the specified point is in a transparent portion of that view’s content.
>
> Points that lie outside the receiver’s bounds are never reported as hits, even if they actually lie within one of the receiver’s subviews. 
>
> This can occur if the current view’s clipsToBounds property is set to NO and the affected subview extends beyond the view’s bounds.
>
> - 此方法通过调用每个子视图的  pointInSide: withEvent: 方法来遍历层次结构，以确定哪个子视图应接收触摸事件
> - 如果 pointInsize:withEvent: 返回 YES, 则类似地遍历其子视图的层次结构, 直到找到包含指定点的最前面的视图
> - 如果当前view不包含指定点， 则将忽略其视图层次上所有的子视图. 你很少需要自己调用此方法, 但是您可以重写它以在子视图中隐藏触摸事件
> - 此方法忽略隐藏的视图，禁用用户交互的视图, 以及alpha < 0.01的视图.
> - 确定点击时, 此方法不会考虑视图的内容。 因此, 即使指定点位于该视图内容的透明部分中, 该视图仍然可以返回
> - 即使位于接收者范围外的点实际上位于接收者的子视图之一内, 也不会被报告为匹配
> - 当当前视图的 clipsToBounds 属性设置为 NO, 并且受影响的子视图超出了视图的范围，则可能发生这种情况。



## See Also

> ### Hit Testing in a View
>
> [`- pointInside:withEvent:`](https://developer.apple.com/documentation/uikit/uiview/1622533-pointinside?language=occ)
>
> Returns a Boolean value indicating whether the receiver contains the specified point.