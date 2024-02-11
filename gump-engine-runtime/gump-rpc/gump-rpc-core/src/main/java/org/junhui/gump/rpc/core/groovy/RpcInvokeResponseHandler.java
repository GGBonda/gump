package org.junhui.gump.rpc.core.groovy;

public interface RpcInvokeResponseHandler {

    Object handle(RpcResponseContext context);
}
