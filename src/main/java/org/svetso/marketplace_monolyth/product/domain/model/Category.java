package org.svetso.marketplace_monolyth.product.domain.model;

public class Category {
    private Long id;
    private String name;
    private Long parentId;

    public Category(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }
}


