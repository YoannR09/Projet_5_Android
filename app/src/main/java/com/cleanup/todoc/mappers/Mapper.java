package com.cleanup.todoc.mappers;

import java.util.List;

public interface Mapper<I, O> {
    O map(I in);
    List<O> maps(List<I> ins);
}
