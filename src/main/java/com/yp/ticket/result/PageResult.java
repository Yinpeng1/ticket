package com.yp.ticket.result;

import lombok.Getter;

import java.util.List;


class PageResult<T> extends ListResult<T> {

    private static final long serialVersionUID = 7887542373165496853L;

    @Getter
    private Long total;

    public PageResult() {
    }

    PageResult(Long total, List<T> entities) {
        super(entities);
        this.total = total;
    }
}