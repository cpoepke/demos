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
 * $Last modified: Do, 29 Aug 2013 17:01:41 +0200 $
 * $Author: cpoepke $
 */

package de.cpoepke.demos.neo4j.querydsl;

import de.cpoepke.demos.neo4j.querydsl.domain.QUser;
import de.cpoepke.demos.neo4j.querydsl.domain.User;
import de.cpoepke.demos.neo4j.querydsl.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.neo4j.cypherdsl.expression.StartExpression;
import org.neo4j.cypherdsl.grammar.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.typerepresentation.IndexingNodeTypeRepresentationStrategy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.neo4j.cypherdsl.CypherQuery.*;
import static org.neo4j.cypherdsl.querydsl.CypherQueryDSL.*;
import static org.neo4j.helpers.collection.MapUtil.map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Before
    @Transactional
    public void setUp() {
        createUser("hwurst", "123pass");
        createUser("jsmith", "pass123");
    }

    @After
    @Transactional
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testFindByUsernameAndPasswordSpringData() {
        assertNull(repository.findByUsernameAndPassword("hwurst", "somepassword"));
        assertNotNull(repository.findByUsernameAndPassword("hwurst", "123pass"));
    }

    @Test
    public void testFindByUsernameAndPasswordQueryDSL() {
        QUser qUser = QUser.user;
        Execute execute = start(lookup("user", "users", "username", "hwurst"))
                .where(toBooleanExpression(qUser.password.eq("somepassword")))
                .returns(node("user"));
        assertNull(repository.query(execute, map()).singleOrNull());

        qUser = QUser.user;
        execute = start(lookup("user", "users", "username", "hwurst"))
                .where(toBooleanExpression(qUser.password.eq("123pass")))
                .returns(node("user"));

        User user = repository.query(execute, map()).singleOrNull();
        assertNotNull(user);
        assertEquals("hwurst", user.getUsername());

        execute = start(domainStartNodes("user", User.class))
                .where(toBooleanExpression(qUser.username.eq("hwurst").and(qUser.password.eq("somepassword"))))
                .returns(node("user"));
        assertNull(repository.query(execute, map()).singleOrNull());

        qUser = QUser.user;
        execute = start(domainStartNodes("user", User.class))
                .where(toBooleanExpression(qUser.username.eq("hwurst").and(qUser.password.eq("123pass"))))
                .returns(node("user"));

        user = repository.query(execute, map()).singleOrNull();
        assertNotNull(user);
        assertEquals("hwurst", user.getUsername());
    }

    /**
     * Test data helper methods
     */
    private StartExpression domainStartNodes(String name, Class clazz) {
        return lookup(name, IndexingNodeTypeRepresentationStrategy.INDEX_NAME, IndexingNodeTypeRepresentationStrategy.INDEX_KEY, clazz.getCanonicalName());
    }

    private User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        repository.save(user);
        return user;
    }
}
