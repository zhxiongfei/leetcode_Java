package 栈;

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

        列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
        示例 1:

        输入: [[1,1],2,[1,1]]
        输出: [1,1,2,1,1]
        解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
        示例 2:

        输入: [1,[4,[6]]]
        输出: [1,4,6]
        解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _341_扁平化嵌套列表迭代器 {

    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    List<Integer>stack = new ArrayList<>();
    int i = 0;
    public _341_扁平化嵌套列表迭代器(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()){
                stack.add(nestedInteger.getInteger());
            }else {
                addToStack(nestedInteger);
            }
        }
    }

    private void addToStack(NestedInteger nestedInteger){
        if (nestedInteger.isInteger()){
            stack.add(nestedInteger.getInteger());
            return;
        }
        for (NestedInteger integer : nestedInteger.getList()) {
            addToStack(integer);
        }
    }

    public Integer next() {
        return stack.get(i++);
    }

    public boolean hasNext() {
        return i < stack.size();
    }
}
