### 1.Long 类型的相等比较
```java
/**
* 注意不能直接使用 ==, 一定要先转换成基本数据类型, 所有以后的返回值 还是老老实实的基本数据类型
*/
for (GoodsInfo goodsInfo: goodsInfoList) {
    for(OrderItem orderItem : orderItemList) {
        if(goodsInfo.getGoodsId().longValue() == orderItem.getGoodsId().longValue()){
            BeanUtils.copyProperties(goodsInfo,orderItem);
        }
    }
}
```
### 2.关于mybatis insert的时 返回自增逐渐值

```xml
<insert id="create">
     <!-- 
        将insert插入的数据的主键返回到User对象中;
        select last_insert_id():得到刚insert进去记录的主键值，只适用于自增主键;
        keyProperty:将查询到的主键值，设置到parameterType指定的对象的那个属性
        order:select last_insert_id()执行顺序，相对于insert语句来说它的执行顺序。
        resultType:指定select last_insert_id()的结果类型，也就是id的类型;
     -->
    <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
        select last_insert_id()
    </selectKey>
    insert into user(id,name,password,phone,is_lock) values (#{id},#{name},#{password},#{phone},#{is_lock})
</insert>
```
```java
public Long save(Order order) {
    int count = orderMapper.insertSelective(order);
    if(count > 0) {
        log.info("【用户下单成功】=> {}",count);
    }else {
        log.info("【用户下单失败】=> {}",count);
    }
    return order.getOrderId();
}
```
注意: 此处并不是直接返回,而是将其存放在了对象中,取出即可

### 3.@JsonProperty("tel") 问题
@JsonProperty("xxx") 是指对后端的字段的输出格式有要求的时候使用。
但是,当前端发送过来的数据 使用的是`@RequestBody`接收时,前端也应该发`tel`字段。
### 4.实现websocket
1.添加依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```
2.添加配置类
```java
package cn.lovingliu.lovingmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author：LovingLiu
 * @Description: websocket配置类
 * @Date：Created in 2019-11-24
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
```
3.编写server
```java
package cn.lovingliu.lovingmall.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-24
 */
@ServerEndpoint("/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收userId
    private String userId = "";
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新用户开始监听:"+userId+",当前在线人数为" + getOnlineCount());
        this.userId = userId;
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+ userId +"的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("推送消息到窗口"+userId+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(userId == null) {
                    item.sendMessage(message);
                }else if(item.userId.equals(userId)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

```
###  mvn clean package -Dmaven.test.skip=true 被Killed

相关命令：

1.创建/home/swap这么一个分区文件。文件大小是512000个block，一般情况下1个block为1k，所以这里空间是512M，这个空间大小自己随意定义。

dd if=/dev/zero of=/home/swap bs=1024 count=512000

2.将这个分区变成swap分区。

/sbin/mkswap /home/swap

3.使用swap分区，使其生效。

/sbin/swapon /home/swap

4.查看swap分区大小。

free -m

备注：系统重启后swap分区会还原，解决办法：修改/etc/fstab文件，增加一行

/home/swap           swap                 swap       defaults              0 0
