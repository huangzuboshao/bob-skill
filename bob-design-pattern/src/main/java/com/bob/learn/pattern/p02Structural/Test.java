package com.bob.learn.pattern.p02Structural;


import java.util.ArrayList;
import java.util.List;

/**
 * @author huangzuboshao
 * @date 2022/12/23 10:17
 */
public class Test {
    public static void main(String[] args) {
        Interfacer<String> template = new TemplateAAA<>();
        System.out.println(template.get());
        template.add("123");
        System.out.println(template.get());
    }
}
interface Interfacer<T>{
    /**
     * 添加
     * @param element
     */
    void add(T element);

    /**
     * 移除
     * @param element
     */
    void remove(T element);

    /**
     * 获取
     * @return
     */
    T get();

    /**
     * 大小
     * @return
     */
    int size();
}
abstract class TemplateA<T> implements Interfacer<T>{
    protected List<T> list = new ArrayList<>();

    @Override
    public void add(T element) {
        throw new IllegalStateException("异常操作");
    }

    @Override
    public void remove(T element) {
        throw new IllegalStateException("异常操作");
    }

    public abstract T getGet(Class<? extends T> clazz);
}
class TemplateAA<T> extends TemplateA<T>{
    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public void remove(T element) {
        list.remove(element);
    }

    @Override
    public T getGet(Class<? extends T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public T get() {
        if (list.size() == 0 ) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public int size() {
        return list.size();
    }
}


class TemplateAAA<T> extends TemplateAA<T> {
    @Override
    public T getGet(Class<? extends T> clazz) {
        return super.getGet(clazz);
    }
}