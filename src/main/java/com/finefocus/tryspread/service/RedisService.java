package com.finefocus.tryspread.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;


@Service("redisService")
public class RedisService {

    private static final String SYNLOCK_PREFFIX = "SYNLOCK_";
    protected Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Autowired(required = false)
    private JedisPool jedisPool;

    public String set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            boolean flag = true;
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    return jedis.set(key, value);
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String set(String key, String value, Integer seconds) throws Exception {
        Jedis jedis = null;
        try {
            boolean flag = true;
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    String set = jedis.set(key, value);
                    jedis.expire(key, seconds);
                    return set;
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            boolean flag = true;
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    return jedis.get(key);
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Long delete(String key) {
        Jedis jedis = null;
        try {
            boolean flag = true;
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    Long del = jedis.del(key);
                    return del;
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("redis删除异常", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void exprie(String key, int i) throws Exception {
        Jedis jedis = null;
        try {
            boolean flag = true;
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    jedis.expire(key, i);
                    break;
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public boolean lock(String key, int seconds) throws Exception {
        boolean result = false;
        Jedis jedis = null;
        int index = 1;
        try {
            boolean flag = true;
            outter:
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    String synlock = SYNLOCK_PREFFIX + key;
                    while (true) {
                        Transaction tx = jedis.multi();
                        Response<Long> res = tx.setnx(synlock, synlock);
                        tx.expire(synlock, seconds);
                        tx.exec();
                        if (res.get() == 1) {
                            result = true;
                            break outter;
                        }
                        if (index > 20) {
                            break outter;
                        }
                        Thread.sleep(300);
                        index++;
                    }
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }


    public void unlock(String key) throws Exception {
        Jedis jedis = null;
        try {
            boolean flag = true;
            while (true) {
                try {
                    jedis = jedisPool.getResource();
                    String synlock = SYNLOCK_PREFFIX + key;
                    jedis.del(synlock);
                    break;
                } catch (Exception e) {
                    if (flag) {
                        flag = false;
                        Thread.sleep(6000);
                        continue;
                    } else {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
