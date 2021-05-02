channel在selector上注册的时候，需要标记当前服务通道状态
如果是OP_ACCEPT和OP_READ，需要外部网络来触发
this.selector.select()才会继续往下走
如果是OP_WRITE，this.selector.select()直接就会往下走
this.selector.select()往下走，this.selector.selectedKeys()就有值了