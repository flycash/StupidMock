package cn.com.flycash.stupidmock.stub.args;


import com.google.common.collect.Lists;

import java.util.List;

public class OneOfArgMatcherImpl implements ArgMatcher {

    private final List<Object> candidates;

    public OneOfArgMatcherImpl(Object... candidates) {
        this.candidates = Lists.newArrayList(candidates);
    }

    @Override
    public boolean match(Object target) {
        return candidates.stream().anyMatch(candidate -> candidate.equals(target));
    }
}
