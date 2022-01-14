package com.leaderli.liutil.meta;

/**
 *  wrapper a object, so it can be assign new value in lambda function
 * @param <T>
 */
public class LiMeta<T> {

    T  value;

    public void set(T value){
        this.value  = value;
    }

    public T get(){
        return this.value;
    }
}
