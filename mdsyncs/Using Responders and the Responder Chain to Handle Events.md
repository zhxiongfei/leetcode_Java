---
title: "Using Responders and the Responder Chain to Handle Events"
date: 2020-07-21T11:52:08+08:00
draft: true
tags: ["ios", "Apple Developer documentation翻译计划"]
---

## Using Responders and the Responder Chain to Handle Events

原文地址 : [Using Responders and the Responder Chain to Handle Events](https://developer.apple.com/documentation/uikit/touches_presses_and_gestures/using_responders_and_the_responder_chain_to_handle_events?language=objc)


> 使用响应者和响应链来处理事件
>
> Learn how to handle events that propagate through your app.
>
> 了解如何处理应用事件的传递.

## Overview

> Apps receive and handle events using *responder objects*. A responder object is any instance of the [`UIResponder`](https://developer.apple.com/documentation/uikit/uiresponder) class, and common subclasses include [`UIView`](https://developer.apple.com/documentation/uikit/uiview), [`UIViewController`](https://developer.apple.com/documentation/uikit/uiviewcontroller), and [`UIApplication`](https://developer.apple.com/documentation/uikit/uiapplication). Responders receive the raw event data and must either handle the event or forward it to another responder object. When your app receives an event, UIKit automatically directs that event to the most appropriate responder object, known as the *first responder*. 
>
> - 应用使用相应对象responder来接收和处理事件。
> - 响应对象是UIResponder的任何子类，公共子类包括 UIView,UIViewController 和 UIApplication.
> - 响应对象接收原始事件数据，并且必须处理事件或者转发到另一个响应放对象
> - 当你的应用接收到事件时，UIKit自动将该事件引导到最合适的响应者对象, 称为第一响应者.
>
> Unhandled events are passed from responder to responder in the active *responder chain*, which is the dynamic configuration of your app’s responder objects. [Figure 1](https://developer.apple.com/documentation/uikit/touches_presses_and_gestures/using_responders_and_the_responder_chain_to_handle_events#3004381) shows the responders in an app whose interface contains a label, a text field, a button, and two background views. The diagram also shows how events move from one responder to the next, following the responder chain.
>
> - 未处理的事件会通过响应者链传递到下一级响应者
> - 响应者链是应用程序响应者对象动态配置的
> - Figure1 显示了应用程序的响应者包含, label, text field, button, 两个背景视图. 该图还显示了事件如何按照响应者链从一个响应者移动到下一个响应者
>
> ![image-20200721134705759](/Users/liuxiaoyong/Library/Application Support/typora-user-images/image-20200721134705759.png)

> If the text field does not handle an event, UIKit sends the event to the text field’s parent `UIView`object, followed by the root view of the window. 
>
> From the root view, the responder chain diverts to the owning view controller before directing the event to the window. 
>
> If the window cannot handle the event, UIKit delivers the event to the `UIApplication` object, and possibly to the app delegate if that object is an instance of `UIResponder` and not already part of the responder chain.
>
> - 如果 text field 没有处理事件, UIKit 会把事件发送给 text field 的父视图 UIView 对象, 其次是 根视图
> - 从根视图开始, 响应者链条在将事件转发到UIWindow之前，先转发到拥有该视图的视图控制器
> - 如果 UIWindow 无法处理事件, UIKit 将事件转发到 UIApplication对象,  也可能转发给  AppDelegate , 前提是 AppDelegate 是 UIResponder 的子类，并且还不是响应者链的一部分.



### Determining an Event's First Responder

> UIKit designates an object as the first responder to an event based on the type of that event. Event types include:
>
> UIKit 根据事件的类型, 将对象指定为事件的第一响应者. 事件类型包括 : 
>
> | Event type            | First responder                           |
> | :-------------------- | :---------------------------------------- |
> | Touch events          | The view in which the touch occurred.     |
> | Press events          | The object that has focus.                |
> | Shake-motion events   | The object that you (or UIKit) designate. |
> | Remote-control events | The object that you (or UIKit) designate. |
> | Editing menu messages | The object that you (or UIKit) designate. |

> | 事件类型     | 第一响应者               |
> | :----------- | :----------------------- |
> | 点击事件     | 发生触摸的视图           |
> | 长按事件     | 具有焦点的对象           |
> | 震动事件     | 你 或者 UIKit 指定的对象 |
> | 远程控制事件 | 你 或者 UIKit 指定的对象 |
> | 编辑菜单消息 | 你 或者 UIKit 指定的对象 |

> Note
>
> Motion events related to the accelerometers, gyroscopes, and magnetometer do not follow the responder chain. Instead, Core Motion delivers those events directly to the designated object. For more information, see [Core Motion Framework](https://developer.apple.com/library/archive/documentation/Miscellaneous/Conceptual/iPhoneOSTechOverview/CoreServicesLayer/CoreServicesLayer.html#//apple_ref/doc/uid/TP40007898-CH10-SW27)
>
> 注意:
>
> 与加速计，陀螺仪和磁力计有关的运动事件不遵循响应者链条。相反，Core Motion 会将这些事件直接传递到指定的对象。有关更多信息，参见Core Motion框架



> Controls communicate directly with their associated target object using action messages. 
>
> When the user interacts with a control, the control sends an action message to its target object. 
>
> Action messages are not events, but they may still take advantage of the responder chain. 
>
> When the target object of a control is nil, UIKit starts from the target object and traverses the responder chain until it finds an object that implements the appropriate action method.
>
> For example, the UIKit editing menu uses this behavior to search for responder objects that implement methods with names like cut(_:), copy(_:), or paste(_:).
>
> Gesture recognizers receive touch and press events before their view does.
>
> If a view's gesture recognizers fail to recognize a sequence of touches, UIKit sends the touches to the view. 
>
> If the view does not handle the touches, UIKit passes them up the responder chain. 
>
> For more information about using gesture recognizer’s to handle events, see Handling UIKit Gestures.
>
> - 控件使用动作消息直接与其关联的目标对象进行通信。
> - 当用户与控件交互时，控件会将操作消息发送到其目标对象。
> - 动作消息不是事件，但它们仍可以利用响应者链。
> - 当控件的目标对象为nil时，UIKit从目标对象开始并遍历响应者链，直到找到实现适当操作方法的对象为止。
>   - 例如，UIKit编辑菜单使用此行为来搜索响应者对象，这些对象实现了诸如cut（_ :)，copy（_ :)或paste（_ :)之类的方法。
>
> - 手势识别器会先接收触摸和按下事件，然后再进行查看。
> - 如果视图的手势识别器无法识别一系列触摸，则UIKit会将触摸发送到视图。
> - 如果视图无法处理触摸，UIKit会将它们向上传递到响应者链。
> - 有关使用手势识别器处理事件的更多信息，请参见处理UIKit手势。

### Determining Which Responder Contained a Touch Event

> UIKit uses view-based hit-testing to determine where touch events occur. 
> Specifically, UIKit compares the touch location to the bounds of view objects in the view hierarchy. 
> The hitTest(_:with:) method of UIView traverses the view hierarchy, looking for the deepest subview that contains the specified touch, which becomes the first responder for the touch event.
>
> - UIKit 使用基于视图的命中测试 hit-testing 来确定触摸事件发生的位置.
> - 具体来说, UIKit 将触摸位置和视图层次结构中的视图对象的边界进行比较
> - UIView 的 hittest(_: with : ) 方法遍历视图层次结构, 查找包含指定触摸的最深子视图, 该子视图成为触摸事件的第一响应者
>
> 
>
> Note
>
> If a touch location is outside of a view’s bounds, the [`hitTest:withEvent:`](https://developer.apple.com/documentation/uikit/uiview/1622469-hittest?language=objc) method ignores that view and all of its subviews.
>
> As a result, when a view’s [`clipsToBounds`](https://developer.apple.com/documentation/uikit/uiview/1622415-clipstobounds?language=objc)property is `NO`, subviews outside of that view’s bounds are not returned even if they happen to contain the touch.
>
> For more information about the hit-testing behavior, see the discussion of the [`hitTest:withEvent:`](https://developer.apple.com/documentation/uikit/uiview/1622469-hittest?language=objc) method in [`UIView`](https://developer.apple.com/documentation/uikit/uiview?language=objc).
>
> - 如果触摸位置在视图范围之外, 则 hitTest: withEvent: 方法将忽略该视图及其所有的子视图
> - 因此, 如果视图的 clipsToBounds 属性为 false, 则即使该视图恰好包含触摸，也不会反悔该视图范围之外的子视图
>
> When a touch occurs, UIKit creates a UITouch object and associates it with a view. 
>
> As the touch location or other parameters change, UIKit updates the same UITouch object with the new information. 
>
> The only property that does not change is the view. (Even when the touch location moves outside the original view, the value in the touch’s view property does not change.) When the touch ends, UIKit releases the UITouch object.
>
> - 发生触摸时，UIKit将创建一个UITouch对象并将其与视图关联。
> - 随着触摸位置或其他参数的更改，UIKit会使用新信息更新相同的UITouch对象。
> - 唯一不变的属性是视图。 （即使触摸位置移至原始视图之外，触摸的view属性中的值也不会更改。）触摸结束时，UIKit会释放UITouch对象。



### Altering the Responder Chain

> You can alter the responder chain by overriding the [`nextResponder`](https://developer.apple.com/documentation/uikit/uiresponder/1621099-nextresponder?language=objc) property of your responder objects. When you do this, the next responder is the object that you return.
>
> Many UIKit classes already override this property and return specific objects, including: 
>
> - [`UIView`](https://developer.apple.com/documentation/uikit/uiview?language=objc) objects. If the view is the root view of a view controller, the next responder is the view controller; otherwise, the next responder is the view’s superview.
> - [`UIViewController`](https://developer.apple.com/documentation/uikit/uiviewcontroller?language=objc) objects.
>   - If the view controller’s view is the root view of a window, the next responder is the window object.
>   - If the view controller was presented by another view controller, the next responder is the presenting view controller.
> - [`UIWindow`](https://developer.apple.com/documentation/uikit/uiwindow?language=objc) objects. The window's next responder is the [`UIApplication`](https://developer.apple.com/documentation/uikit/uiapplication?language=objc) object.
> - [`UIApplication`](https://developer.apple.com/documentation/uikit/uiapplication?language=objc) object. The next responder is the app delegate, but only if the app delegate is an instance of [`UIResponder`](https://developer.apple.com/documentation/uikit/uiresponder?language=objc) and is not a view, view controller, or the app object itself.

> - 你可以覆盖响应者的 nextResponder 属性来更改响应者链。执行此操作时，下一个响应者是你返回的对象。
> - 许多 UIKit 类已经重写此属性并且返回特定的对象. 包括 : 
>   - UIView 对象, 如果此view是控制器的根视图，则下一个响应者是视图控制器；否则，下一个响应者是视图的父视图.
>   - UIViewController 对象, 如果视图控制器的视图是窗口的根视图, 则下一个响应者是窗口对象
>   - 如果控制器是由另一个视图控制器呈现的， 则下一个响应者是呈现视图控制器
>   - UIWindow 对象, 窗口的下一个响应者是 UIApplication 对象
>   - UIApplication 对象, 下一个响应者是 AppDelegate, 但仅当AppDelegate 是UIResponder的子类，并且不是视图，视图控制器或者 UIApplication本身。



## Question

- [What is hitTest:withEvent: ? How this method implemented?]()
- [What is pointInside:withEvent: ? How this method implemented?]()



## See Also

### First Steps

[`UIResponder`](https://developer.apple.com/documentation/uikit/uiresponder?language=objc)

An abstract interface for responding to and handling events. 

[`UIEvent`](https://developer.apple.com/documentation/uikit/uievent?language=objc)

An object that describes a single user interaction with your app.