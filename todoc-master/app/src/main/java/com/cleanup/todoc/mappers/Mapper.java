package com.cleanup.todoc.mappers;

import java.util.List;

public interface Mapper<I, O> {
    public O map(I in);
    public List<O> maps(List<I> ins);
}
