package com.leaderli.liutil.meta;

public class LiMeta<T> {

    T  value;

    public void set(T value){
        this.value  = value;
    }

    public T get(){
        return this.value;
    }
}
