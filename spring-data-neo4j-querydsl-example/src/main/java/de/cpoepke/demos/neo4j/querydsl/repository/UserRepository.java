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
 * $Last modified: Mi, 28 Aug 2013 01:44:22 +0200 $
 * $Author: cpoepke $
 */

package de.cpoepke.demos.neo4j.querydsl.repository;

import de.cpoepke.demos.neo4j.querydsl.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface UserRepository extends GraphRepository<User>, CypherDslRepository<User> {

    User findByUsernameAndPassword(String username, String password);

    @Query("")
    User findByUsernameAndPasswordQuery(String username, String password);
}