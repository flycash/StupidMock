package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.Invocation;
import com.google.common.base.Defaults;

/**
 * 该实现将返回各个类型的默认值
 */
public enum  DefaultValueAnswer implements Answer {
    INSTANCE
    ;
    @Override
    public Object answer(Invocation invocation) {
        Class<?> returnClz = invocation.getMethod().getReturnType();
        if (returnClz == null) {
            return null;
        }
        return Defaults.defaultValue(returnClz);
    }
}
