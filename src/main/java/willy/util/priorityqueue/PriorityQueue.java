
package willy.util.priorityqueue;

import willy.util.queue.Queue;

public interface PriorityQueue<E> extends Queue<E> {
    @Override
    public boolean insert(E element);
    public boolean insert(int index, E element);
}
