package com.hyx.queue.priorityqueue;

/**
 * @author hyx
 */
public interface Priority {

    /**
     * 返回对象的优先级，约定数字越大，优先级越高
     */
    int priority();

}
