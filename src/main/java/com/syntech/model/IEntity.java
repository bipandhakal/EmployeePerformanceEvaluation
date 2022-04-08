package com.syntech.model;

import java.io.Serializable;

/**
 *
 * @author bipan
 */
public interface IEntity extends Serializable {

    public Long getId();

    public void setId(Long id);
}
