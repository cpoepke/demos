/*
 * Project       neo4j-spring-data-querydsl-example
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Conrad Poepke
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 * $Last modified: Mi, 28 Aug 2013 21:52:28 +0200 $
 * $Author: cpoepke $
 */

package de.cpoepke.demos.neo4j.querydsl.domain;

import com.mysema.query.annotations.QueryEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.io.Serializable;

@NodeEntity
@QueryEntity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Indexed
    @GraphId
    private Long id;

    @Indexed(indexType = IndexType.FULLTEXT, unique = true, indexName = "users")
    private String username;

    private String password;

    private String roles;

    public User() {
        this(null, null, null);
    }

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(id, rhs.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(id)
                .append(username)
                .append(password)
                .toString();
    }

}
