package fun.fengwk.commons.idgen;

/**
 * 使用命名空间相互隔离的ID生成器。
 *
 * @author fengwk
 */
public interface NamespaceIdGenerator<ID> {

    /**
     * 生成指定命名空间的下一个id。
     *
     * @param namespace
     * @return
     */
    ID next(String namespace);

    /**
     * 使用类的全路径作为命名空间生产下一个id。
     *
     * @param namespace
     * @return
     */
    default ID next(Class<?> namespace) {
        return next(namespace.getName());
    }

}

