package fun.fengwk.commons.idgen.snowflakes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

import java.util.List;
import java.util.Objects;

/**
 * @author fengwk
 */
public class JedisPoolScriptExecutor implements RedisScriptExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(JedisPoolScriptExecutor.class);

    private final Pool<Jedis> pool;

    /**
     *
     * @param pool not null
     */
    public JedisPoolScriptExecutor(Pool<Jedis> pool) {
        this.pool = Objects.requireNonNull(pool);
    }

    @Override
    public void close() throws Exception {
        pool.close();
    }

    @Override
    public <T> T execute(String script, List<String> keys, List<String> args, Class<T> returnType) throws Exception {
        try (Jedis jedis = pool.getResource()) {
            Long begin = null;
            if (LOG.isDebugEnabled()) {
                begin = System.currentTimeMillis();
            }

            Object res = jedis.eval(script, keys, args);

            if (begin != null) {
                LOG.debug("eval cost {} ms", System.currentTimeMillis() - begin);
            }

            return returnType.cast(res);
        }
    }

}
