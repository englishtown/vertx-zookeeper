package com.englishtown.vertx.zookeeper.builders;

import org.apache.zookeeper.CreateMode;

/**
 * ZooKeeper operation builder to create a z-node
 */
public interface CreateBuilder extends
        ZooKeeperOperationBuilder,
        Pathable<CreateBuilder>,
        ACLable<CreateBuilder>,
        Dataable<CreateBuilder> {

    CreateBuilder creatingParentsIfNeeded();

    CreateBuilder withMode(CreateMode mode);

    CreateBuilder withProtection();

}