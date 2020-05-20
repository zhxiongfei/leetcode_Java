package Interview;

/*
动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。

        enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。

        dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。

        示例1:

        输入：
        ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
        [[], [[0, 0]], [[1, 0]], [], [], []]
        输出：
        [null,null,null,[0,0],[-1,-1],[1,0]]
        示例2:

        输入：
        ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
        [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
        输出：
        [null,null,null,null,[2,1],[0,0],[1,0]]
        说明:

        收纳所的最大容量为20000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/animal-shelter-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
*
* 用两个队列
* 🐶队列，🐱队列
* 入队时，根据 animal[1] 区分，分别进入对应队列
* 出队any时， 根据 animal[0]序号，出队两个队列中先入队的
* */
public class _面试题_03_06_动物收容所 {

    private Queue<int[]> catQueue;
    private Queue<int[]> dogQueue;

    public _面试题_03_06_动物收容所() {

        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        if (animal[1] == 1){
            // 🐶
            dogQueue.offer(animal);
        }else {
            // 🐱
            catQueue.offer(animal);
        }
    }

    public int[] dequeueAny() {

        int[] x = {-1,-1};
        if (dogQueue.isEmpty() && catQueue.isEmpty()) return x;

        if (dogQueue.isEmpty()) return catQueue.remove();
        if (catQueue.isEmpty()) return dogQueue.remove();

        if (dogQueue.peek()[0] > catQueue.peek()[0]){
            return catQueue.remove();
        }
        return dogQueue.remove();
    }

    public int[] dequeueDog() {
        int[] x = {-1,-1};
        if (dogQueue.isEmpty()) return x;
        return dogQueue.remove();
    }

    public int[] dequeueCat() {
        int[] x = {-1,-1};
        if (catQueue.isEmpty()) return x;
        return catQueue.remove();
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {


    }
}
