package com.fx;

import com.fx.circle.CircleLinkList;
import com.fx.circle.SingleCircleLinkList;
import com.fx.single.SingleLinkList;


public class Main {

    public static void main(String[] args) {

        Stack stack = new Stack();
        stack.push(11);
        stack.push(22);
        stack.push(33);

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
