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
 * $Last modified: Di, 27 Aug 2013 19:56:30 +0200 $
 * $Author: cpoepke $
 */

package de.cpoepke.demos.neo4j.querydsl.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: Conrad Pöpke
 * Date: 20.08.13
 * Time: 22:39
 */
@EnableTransactionManagement
@Configuration
@EnableNeo4jRepositories(basePackages = "de.cpoepke")
public class Neo4JConfiguration extends Neo4jConfiguration {

    private Logger LOGGER = LoggerFactory.getLogger(Neo4JConfiguration.class);

    private static final String DB_PATH = "target/neo4j.db";

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        LOGGER.debug("DB_PATH=" + DB_PATH);
        return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
    }

    @Bean
    public WrappingNeoServerBootstrapper neo4jWebServer() {
        WrappingNeoServerBootstrapper server = new WrappingNeoServerBootstrapper((EmbeddedGraphDatabase) graphDatabaseService());
        server.start();
        return server;
    }
}
