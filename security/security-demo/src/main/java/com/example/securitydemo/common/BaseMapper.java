package com.example.securitydemo.common;

import java.util.List;

public interface BaseMapper<D,E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> entities);

    List<E> toEntity(List<D> dtos);
}
