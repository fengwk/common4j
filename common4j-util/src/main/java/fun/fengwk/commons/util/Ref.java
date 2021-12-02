package fun.fengwk.commons.util;

/**
 * 对象引用。
 *
 * @author fengwk
 */
public class Ref<T> {

    public T value;

    private Ref(T value) {
        this.value = value;
    }

    /**
     * 构造空引用。
     * 
     * @param <T>
     * @return
     */
    public static <T> Ref<T> empty() {
        return new Ref<>(null);
    }

    /**
     * 构造指向指定value的引用。
     * 
     * @param <T>
     * @param value
     * @return
     */
    public static <T> Ref<T> of(T value) {
        return new Ref<>(value);
    }

}
