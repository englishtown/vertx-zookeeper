package com.englishtown.vertx.curator.builders.impl;

import com.englishtown.vertx.curator.CuratorClient;
import com.englishtown.vertx.curator.CuratorOperation;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.SetDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultSetDataBuilderTest {

    @Mock
    CuratorClient client;

    @Mock
    CuratorFramework framework;

    @Mock
    SetDataBuilder builder;

    String path = "/test/path";

    byte[] data = new byte[0];

    int version = 1;

    @Mock
    Handler<AsyncResult<CuratorEvent>> handler;

    @Test
    public void testBuild_With_Data() throws Exception{

        DefaultSetDataBuilder target = new DefaultSetDataBuilder();

        CuratorOperation operation = target.forPath(path)
                .data(data)
                .withVersion(version)
                .build();

        when(client.getCuratorFramework()).thenReturn(framework);
        when(framework.setData()).thenReturn(builder);

        operation.execute(client, handler);

        verify(builder).inBackground(any(BackgroundCallback.class));
        verify(builder).forPath(path, data);
    }

    @Test
    public void testBuild_Without_Data() throws Exception{

        DefaultSetDataBuilder target = new DefaultSetDataBuilder();

        CuratorOperation operation = target.forPath(path)
                .withVersion(version)
                .build();

        when(client.getCuratorFramework()).thenReturn(framework);
        when(framework.setData()).thenReturn(builder);

        operation.execute(client, handler);

        verify(builder).inBackground(any(BackgroundCallback.class));
        verify(builder).forPath(path);
    }
}
