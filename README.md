# 点餐系统

## 数据库设计

类目表product_category

商品表product_info

订单主表order_master

订单详情表order_detail

卖家信息表seller_info

```sql
create table product_category(
	category_id int not null auto_increment,
	category_name varchar(64) not null comment '类目名称',
	category_type int not null comment '类目编号',
	create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
	primary key(category_id),
	unique key uqe_category_type(category_type)
) comment "类目表";

create table product_info(
	product_id varchar(32) not null,
	product_name varchar(64) not null comment '商品名称',
	product_price decimal(8,2) not null comment '价格',
	product_stock int not null comment '库存',
	product_description varchar(64) comment '描述',
	product_icon varchar(512) comment '小图',
    product_status tinyint(3) not null default '0' comment '商品状态，0正常,1下架',
	category_type int not null comment '类目编号',
	create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
	primary key(product_id)
) comment '商品表'

create table order_master(
	order_id varchar(32) not null,
	buyer_name varchar(32) not null comment '买家名字',
	buyer_phone varchar(32) not null comment '买家电话',
	buyer_address varchar(128) not null comment '买家地址',
	buyer_openid varchar(64) not null comment '买家微信openid',
	order_amount decimal(8,2) not null comment '订单总金额',
	order_status tinyint(3) not null default '0' comment '订单状态，默认新下单',
	pay_status tinyint(3) not null default '0' comment '支付状态,默认未支付',
    create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
	primary key(order_id),
	key idx_buyer_openid(buyer_openid)
) comment '订单表';

create table order_detail(
	detail_id varchar(32) not null,
	order_id varchar(32) not null,
	product_id varchar(32) not null,
	product_name varchar(64) not null comment '商品名称',
	product_price decimal(8,2) not null comment '商品价格',
	product_quantity int not null comment '商品数量',
	product_icon varchar(512) comment '商品小图',
	create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
	update_time timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
	primary key(detail_id),
	key idx_order_id(order_id)
) comment '订单详情表';
```

## application.yaml

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/sell?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
    username: root
    password: 1234
  jpa:
    show-sql: true
server:
  servlet:
    context-path: /sell
```

dto用来进行数据之间的传输

vo用来返回给前端

form用来接受表单验证，作为controller参数

## API

### 商品列表

```
GET /sell/buyer/product/list
```

参数

```
无
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "name": "热榜",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "皮蛋粥",
                    "price": 1.2,
                    "description": "好吃的皮蛋粥",
                    "icon": "http://xxx.com",
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com",
                }
            ]
        }
    ]
}
```

### 创建订单

```
POST /sell/buyer/order/create
```

参数

```
name: "张三"
phone: "18868822111"
address: "上海"
openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]

```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

### 订单列表

```
GET /sell/buyer/order/list
```

参数

```
openid: 18eu2jwk2kse3r42e2e
page: 0 //从第0页开始
size: 10
```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "上海",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerAddress": "上海",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }]
}
```

### 查询订单详情

```
GET /sell/buyer/order/detail
```

参数

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "李四",
          "buyerPhone": "18868877111",
          "buyerAddress": "上海",
          "buyerOpenid": "18eu2jwk2kse3r42e2e",
          "orderAmount": 18,
          "orderStatus": 0,
          "payStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "157875196362360019",
                "productName": "招牌奶茶",
                "productPrice": 9,
                "productQuantity": 2,
                "productIcon": "http://xxx.com",
                "productImage": "http://xxx.com"
            }
        ]
    }
}
```

### 取消订单

```
POST /sell/buyer/order/cancel
```

参数

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

### 获取openid

```
重定向到 /sell/wechat/authorize
```

参数

```
returnUrl: http://xxx.com/abc  //【必填】
```

返回

```
http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
```

### 支付订单

```
重定向 /sell/pay/create
```

参数

```
orderId: 161899085773669363
returnUrl: http://xxx.com/abc/order/161899085773669363
```

返回

```
http://xxx.com/abc/order/161899085773669363
```

## Validate数据校验

```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
</dependency>
```

https://mrbird.cc/Spring-Boot-Hibernate-Validator-Params-Check.html

使用实体传参需要在实体参数前加上@Valid

使用方法参数传参需要在方法上加上@Validate

## JSON注解	

### @JsonSerialize

将时间转为long并除以1000

```java
public class DateLongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
```

```
@JsonSerialize(using = DateLongSerializer.class)
```

### @JsonInclude

```java
//标注在类上	//如果为空不返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)  
```

全局方式

```yaml
jackson:
  default-property-inclusion: non_null
```

## 业务service

### 类目业务

```java
public interface ProductCategoryService {
    //查询单个类目
    ProductCategory findOne(Integer categoryId);
    //查询所有类目
    List<ProductCategory> findAll();
    //通过指定的类目类型查找类目
    List<ProductCategory> findByCateTypeIn(List<Integer> cateTypeList);
    //保存类目
    ProductCategory save(ProductCategory productCategory);
}
```

### 商品业务

```java
public interface ProductInfoService{
    //查询单个商品
    ProductInfo findOne(String productId);
    //查询在架商品
    List<ProductInfo> findUpAll();
    //查询所有商品
    Page<ProductInfo> findAll(Pageable pageable);
    //保存商品
    ProductInfo save(ProductInfo productInfo);
    //加库存
    void increaseStock(List<CartDto> cartDtoList);
    //减库存
    void decreaseStock(List<CartDto> cartDtoList);
}
```

#### 减库存

```java
//decreaseStock业务	cartDto购物车实体类 两个字段String productId Integer productQuantity
//判断购物车中的每个商品是否在数据库中能找到，如果能找到，判断减完库存是否小于0
        for (CartDto cartDto : cartDtoList) {
            Optional<ProductInfo> optional = productInfoRepository.findById(cartDto.getProductId());
            if(!optional.isPresent()){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = optional.get();
            int num = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(num<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }else{
                productInfo.setProductStock(num);
                productInfoRepository.save(productInfo);
            }
        }
```

### 订单业务

```java
public interface OrderService {
    //创建订单
    OrderDto create(OrderDto orderDto);
    //查询单个订单
    OrderDto findOne(String orderId);
    //根据openid查询订单列表
    List<OrderDto> findList(String buyerOpenid, Pageable pageable);
    //取消订单
    OrderDto cancel(OrderDto orderDto);
    //完结订单  改状态
    OrderDto finish(OrderDto orderDto);
    //支付订单  改状态
    OrderDto paid(OrderDto orderDto);
}
```

#### 创建订单

判断商品是否存在
计算商品金额
订单详情入库
订单入库
减库存

```java
@Override
@Transactional
public OrderDto create(OrderDto orderDto) {

    //商品总价
    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
    String orderId = KeyUtil.generateUniqueKey();

    //判断商品是否存在
    for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
        ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //计算商品金额    价格不能用前端传过来的，需要从数据库中取出
        orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                .add(orderAmount);

        //订单详情入库
        orderDetail.setOrderId(orderId);
        orderDetail.setDetailId(KeyUtil.generateUniqueKey());
        BeanUtils.copyProperties(productInfo,orderDetail);
        orderDetailRepository.save(orderDetail);
    }

    //订单入库
    OrderMaster orderMaster = new OrderMaster();
    orderDto.setOrderId(orderId);
    BeanUtils.copyProperties(orderDto,orderMaster);
    orderMaster.setOrderAmount(orderAmount);
    orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
    orderMasterRepository.save(orderMaster);


    //减库存
    List<CartDto> collect = orderDto.getOrderDetailList().stream().map(e -> {
        return new CartDto(e.getProductId(), e.getProductQuantity());
    }).collect(Collectors.toList());

    productInfoService.decreaseStock(collect);

    return orderDto;
}
```

#### 查询单个订单

```java
//返回单个订单和订单详情
@Override
public OrderDto findOne(String orderId) {
    Optional<OrderMaster> optional = orderMasterRepository.findById(orderId);
    if(!optional.isPresent()){
       throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
    OrderMaster orderMaster = optional.get();
    List<OrderDetail> detailList = orderDetailRepository.findByOrderId(orderId);
    if(CollectionUtils.isEmpty(detailList)) {
        throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
    }
    OrderDto orderDto = new OrderDto();
    BeanUtils.copyProperties(orderMaster,orderDto);
    orderDto.setOrderDetailList(detailList);
    return orderDto;
}
```

#### 根据openid查询订单列表

```java
@Override
public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
    Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
    //不需要判断，查不到返回空
    List<OrderMaster> orderMasterList = page.getContent();

    List<OrderDto> orderDtoList = OrderMasterOrderDtoConvert.convert(orderMasterList);

    return new PageImpl<OrderDto>(orderDtoList,pageable,page.getTotalElements());
}
```

#### 取消订单

判断订单状态

修改订单状态

返还库存

如果已支付，需要退款

```java
//orderMaster改状态，productInfo表增加库存
@Transactional
@Override
public OrderDto cancel(OrderDto orderDto) {
    OrderMaster orderMaster = new OrderMaster();

    //判断订单是否是新建订单,只有新建订单才能修改状态
    if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
        log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
        throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    //修改订单状态
    orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
    BeanUtils.copyProperties(orderDto,orderMaster);

        OrderMaster master = orderMasterRepository.save(orderMaster);
    if(master == null){
        log.error("【取消订单】订单更新失败,orderMaster={}",orderMaster);
        throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }

    //返还库存
    if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
        log.error("【取消订单】订单中没有商品");
        throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
    }

    List<CartDto> cartDtos = orderDto.getOrderDetailList().stream().map(e ->
            new CartDto(e.getProductId(), e.getProductQuantity())
    ).collect(Collectors.toList());

    productInfoService.increaseStock(cartDtos);

    //如果已支付，需要退款
    if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
        // TODO
    }
    return orderDto;
}
```

#### 完结订单

判断订单状态 

修改订单状态

```java
@Override
@Transactional
public OrderDto finish(OrderDto orderDto) {
    //判断订单状态    新订单才能完结
    if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
        log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
        throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    //修改订单状态
    orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDto,orderMaster);
    OrderMaster master = orderMasterRepository.save(orderMaster);
    if(master == null){
        log.error("【完结订单】订单更新失败,orderMaster={}",orderMaster);
        throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    return orderDto;
}
```

#### 支付订单

判断订单状态

判断支付状态

修改支付状态

```java
@Override
@Transactional
public OrderDto paid(OrderDto orderDto) {
    //判断订单状态
    if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
        log.error("【订单支付成功】订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
        throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }
    //判断支付状态
    if(!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
        log.error("【订单支付成功】订单支付状态不正确，orderDto={}",orderDto);
        throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
    }
    //修改支付状态
    OrderMaster orderMaster = new OrderMaster();
    orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
    BeanUtils.copyProperties(orderDto,orderMaster);
    OrderMaster master = orderMasterRepository.save(orderMaster);
    if(master == null){
        log.error("【订单支付成功】订单更新失败,orderMaster={}",orderMaster);
        throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
    return orderDto;
}
```

## controller

### 商品列表

```
/buyer/product
```

```java
	@GetMapping("list")
    public ResultVo list(){
        //查询所有上架商品
        List<ProductInfo> list = productInfoService.findUpAll();
        System.out.println("所有商品:"+list);

        //收集所有类目id
        List<Integer> collect = list.stream()
                .map(ProductInfo::getCategoryType)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("所有商品中的类目id:"+collect);

        //根据类目id查找类目信息
        List<ProductCategory> productCategories = productCategoryService.findByCateTypeIn(collect);

        List<ProductCategoryVo> productCategoryVoList = new ArrayList<>();

        for (ProductCategory productCategory : productCategories) {
            ProductCategoryVo productCategoryVo = new ProductCategoryVo();
            productCategoryVo.setCategoryName(productCategory.getCategoryName());
            productCategoryVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : list) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productCategoryVo.setProductInfoList(productInfoVoList);
            productCategoryVoList.add(productCategoryVo);
        }
        return ResultUtil.success(productCategoryVoList);
    }
}
```

### 创建订单

```
/buyer/order
```

表单校验

创建订单

```java
@PostMapping("create")
public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        log.error("【创建订单】参数不正确,orderForm={}",orderForm);
        throw new SellException(ResultEnum.PORM_ERROR,
                bindingResult.getFieldError().getDefaultMessage());
    }
    OrderDto orderDto = OrderFormOrderDtoConverter.convert(orderForm);
    if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
        log.error("【创建订单】购物车不能为空");
        throw new SellException(ResultEnum.CART_EMPTY);
    }
    OrderDto dto = orderService.create(orderDto);

    Map<String,String> map = new HashMap<>();
    map.put("orderId",dto.getOrderId());

    return ResultUtil.success(map);
}
```

### 订单列表

```java
 @RequestParam将请求参数绑定到你控制器的方法参数上
@GetMapping("list")
public ResultVo<List<OrderDto>> orderList(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue = "0") Integer page,
                                          @RequestParam(value = "size",defaultValue = "10") Integer size){
    if(StringUtils.isEmpty(openid)){
        log.error("【查询订单列表】openid为空");
        throw new SellException(ResultEnum.PORM_ERROR);
    }
    Page<OrderDto> list = orderService.findList(openid, PageRequest.of(page, size));
    return ResultUtil.success(list.getContent());
}
```

### 查询订单详情

通过openid判断是否是当前用户

```java
@GetMapping("detail")
public ResultVo<OrderDto> orderDetail(@RequestParam("openid") String openid,
                                      @RequestParam("orderId") String orderId){

    OrderDto orderDto = buyerService.findOneOrder(openid, orderId);
    return ResultUtil.success(orderDto);
}
```

### 取消订单

通过openid判断是否是当前用户

取消订单

```java
@PostMapping("cancel")
public ResultVo<OrderDto> orderCancel(@RequestParam("openid") String openid,
                                      @RequestParam("orderId") String orderId) {

    buyerService.cancelOrder(openid,orderId);
    return ResultUtil.success();
}
```

## 微信

https://github.com/Wechat-Group/WxJava

## WebSocket

WebSocket使得客户端和服务器之间的数据交换变得更加简单，允许**服务端主动向客户端推送数据**

浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输

官网文档如下:

https://developer.mozilla.org/zh-CN/docs/Web/API/WebSocket

### 实际业务场景

前台系统下单——服务器创建订单后，推送到后台系统中——后台系统显示有新订单了，并播放音乐

#### 前端

##### JavaScript代码

```javascript
<script>
    let websocket = null;
    if('WebSocket' in window){
        //创建websocket，与指定地址通信
        websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
    }else{
        alert("该浏览器不支持websocket!")
    }

    websocket.onopen = function (event){
		//客户端向服务端发送消息
        websocket.send("客户端向服务端发送消息-----")
        console.log('建立连接')
    }

    websocket.onclose = function (event){
        console.log('连接关闭')
    }

//收到服务器发来的消息
    websocket.onmessage = function (event){
        console.log('收到消息'+event.data)
        //bootstrap模态框写法 modal() //弹窗提醒
        $("#myModal").modal('show')
       //播放音乐
        document.getElementById('notice').play();
    }

    websocket.onerror = function (event) {
        alert('websocket通道发生错误！')
    }
    //在窗口关闭的时候关闭websocket
    window.onbeforeunload = function () {
        websocket.close();
    }
</script>
```

##### html代码

```html
<!--    弹框-->
            <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title" id="myModalLabel">
                                提醒
                            </h4>
                        </div>
                        <div class="modal-body">
                            你有新的订单
                        </div>
                        <div class="modal-footer">
                            <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button onclick="location.reload()" type="button" class="btn btn-primary">查看新订单</button>
                        </div>
                    </div>
                </div>
            </div>
<!--    博放音乐-->
            <audio id="notice" loop="loop">
                <source th:src="@{/mp3/song.mp3}" type="audio/mpeg"/>
            </audio>
```

#### 后端

##### 依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

##### 配置

```java
@Configuration
public class WebSocketConfig {
    
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
```

##### webSocket代码

```java
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接，总数：{}",webSocketSet.size());
    }
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数：{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息：{}",message);
    }

    //服务端向客户端发送消息
    public void sendMessage(String message){
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播消息: {}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### 创建订单向客户端推送消息

```java
    @Autowired
    private WebSocket  webSocket;

	@Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
		
       ....
           
        //发送websocket消息
        webSocket.sendMessage(orderDto.getOrderId());

        return orderDto;
    }
```

### WebSocket单元测试注意

在SpringBoot项目中集成了WebSocket，在进行单元测试的时候，出现了以下错误：

javax.websocket.server.ServerContainer not available

单元测试改成如下

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
```

## mysql表被锁住

原因postman发送一条查询请求，此时我改了数据库中的字段

https://blog.csdn.net/Frankltf/article/details/82976493

由于改数据的操作为DML操作，采用了表锁，因此查询会一直等待

Waiting for table metadata lock 

解决:

show processlist

进入mysql    

kill  表中的第一列id

## MyBatis

启动类@MapperScan(basePackages = "com.example.sell.mapper")

### mapper注解方式

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
```

```java
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName},#{categoryType})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results(
            {
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
         }
    )
    ProductCategory findByCategoryType(Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Delete("delete from product_category where category_type=#{categoryType} ")
    int deleteByCategory(Integer categoryType);
    
    //采用xml方式配置
    ProductCategory selectByCategoryType(Integer categoryType);
}
```

### xml方式

```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
logging:
  level:
    com.example.sell.mapper: trace
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.sell.mapper.ProductCategoryMapper">
											//jdbcType需要大写
    <resultMap id="BaseResult" type="com.example.sell.entity.ProductCategory">
        <result column="category_id" property="categoryId" jdbcType="INTEGER"/>
        <result column="category_name" property="categoryName" jdbcType="VARCHAR"/>
        <result column="category_type" property="categoryType" jdbcType="INTEGER"/>
        <result column="create_time" property="createTime" jdbcType="TIMESTAMP"/>
        <result column="update_time" property="updateTime" jdbcType="TIMESTAMP"/>
    </resultMap>

    <select id="selectByCategoryType" resultMap="BaseResult" parameterType="integer">
        select *
        from product_category
        where category_type = #{category_type,jdbcType=INTEGER}
    </select>
</mapper>
```

### 测试

```java
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName","销量榜");
        map.put("category_type",1234);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("巅峰榜");
        productCategory.setCategoryType(12345);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(1234);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    void updateByCategoryType(){
        int update = mapper.updateByCategoryType("巅峰", 12345);
        Assert.assertEquals(1,update);
    }

    @Test
    void deleteByCategoryType(){
        int delete = mapper.deleteByCategory(12345);
        Assert.assertEquals(1,delete);
    }
    //xml方式
	 @Test
    void select(){
        ProductCategory productCategory = mapper.selectByCategoryType(1234);
        System.out.println(productCategory);
        Assert.assertNotNull(productCategory);
    }

}
```

## 秒杀

synchronized可以解决

缺点：无法做到细粒度控制，只适合单点情况(Sychronized是JVM层面的)

### Redis分布式锁实现	

```java
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //加锁
    public boolean lock(String productId,String time){
        //对应setnx命令，如果productId为空，才会设置time，否则什么都不做
        //true 表示可以设置，productId为空
        if(stringRedisTemplate.opsForValue().setIfAbsent(productId,time)){
            return true;
        }
        //如果productId不为空，可能time是过期的，需要判断
        String expireTime = stringRedisTemplate.opsForValue().get(productId);
        //如果不为空 且 当前时间超过了过期时间，表示已经过期了
            if(!StringUtils.isEmpty(expireTime)&& Long.parseLong(expireTime)<System.currentTimeMillis()){
            //获取旧的时间  并设置新的时间
            String oldTime = stringRedisTemplate.opsForValue().getAndSet(productId, time);
            //判断是否和原来时间一致，不一致表示另一个线程已经修改过了,加锁失败
            // 如果一致加锁成功
            if(!StringUtils.isEmpty(oldTime) && oldTime.equals(expireTime)){
                return true;
            }
        }
         return false;
        }

        //解锁
    public void unlock(String productId,String time){
        try {
            String expireTime = stringRedisTemplate.opsForValue().get(productId);
            if(!StringUtils.isEmpty(expireTime) && expireTime.equals(time)){
                stringRedisTemplate.opsForValue().getOperations().delete(productId);
            }
        } catch (Exception e) {
            log.info("【分布式Redis锁】解锁异常：{}",e);
        }

    }
}
```

SeckillController

```java
@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/query/{productId}")
    public String querySeckill(@PathVariable String productId) {
        return seckillService.getProductInfo(productId);
    }

    @GetMapping("/seckill/{productId}")
    public String seckill(@PathVariable String productId){
        seckillService.seckill(productId);

        return seckillService.getProductInfo(productId);
    }
}
```

SeckillService

```java
public interface SeckillService {

    String getProductInfo(String productId);

    void seckill(String productId);
}
```

SeckillServiceImpl

```java
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {

    private static final int TIMEOUT = 10*1000; //10s超时

    private static Map<String,String> orders;
    private static Map<String,Integer> products;
    private static Map<String,Integer> stocks;

    static{
        products = new HashMap<>();
        orders =  new HashMap<>();
        stocks = new HashMap<>();
        products.put("1234",100000);       //秒杀10万份商品
        stocks.put("1234",100000);         //库存10万份
    }

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getProductInfo(String productId) {
        log.info("【秒杀】,一共秒杀{}份商品,库存剩余:{},已卖订单数:{}",products.get(productId),stocks.get(productId),orders.size());
        return "一共秒杀"+products.get(productId)+"份商品,库存剩余:"+stocks.get(productId)+",已卖订单数:"+orders.size();
    }

    //synchronized实现
//    @Override
//    public synchronized void seckill(String productId) {
//        try {
//            Integer stockNum = stocks.get(productId);
//            if(stockNum == null){
//                throw new SellException(ResultEnum.STOCK_ERROR);
//            }
//            //创建订单
//            orders.put(KeyUtil.generateUniqueKey(),productId);
//            //减库存
//            stocks.put(productId,stockNum - 1);
//        } catch (SellException e) {
//            e.printStackTrace();
//        }
//    }

    //redis分布式锁实现
    @Override
    public void seckill(String productId) {
        long expiretime = TIMEOUT + System.currentTimeMillis();
        try {
            //加锁
            if(!redisUtil.lock(productId,String.valueOf(expiretime))){
                throw new SellException(100,"没抢到，哈哈~~");
            }
            Integer stockNum = stocks.get(productId);
            if(stockNum == null){
                throw new SellException(ResultEnum.STOCK_ERROR);
            }
            //创建订单
            orders.put(KeyUtil.generateUniqueKey(),productId);
            //减库存
            stocks.put(productId,stockNum - 1);
        } catch (SellException e) {
            e.printStackTrace();
        }
//        }finally {//解锁
            redisUtil.unlock(productId,String.valueOf(expiretime));
//        }
    }
}
```

## Redis实现缓存

[https://mrbird.cc/Spring-Boot%20cache.html](https://mrbird.cc/Spring-Boot cache.html)

启动类EnableCaching

类上使用CacheConfig(cacheName="指定名称")

cacheable(key="123")		指定key	查询	

cachePut(key="123")	修改

cachePut(key="123")	删除

![image-20200927143242496](C:\Users\lujunjie\AppData\Roaming\Typora\typora-user-images\image-20200927143242496.png)

## 部署

使用finalName指定打成jar包的名字

```xml
<build>
    <finalName>sell</finalName>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

例如：

```shell
-Dserver.port指定启动端口

-Dspring.profiles.active=pro	需要有application-pro.yaml

java -Dserver.port=8090 sell.jar

后台启动:

nohup java -jar sell.jar > /dev/null 2>&1 &
```





















