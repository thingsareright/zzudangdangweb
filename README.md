# ZZUDANGDANGWEB 说明文档 
============================



   
   经过长时间的调试和整理，现在zzudangdang的web端代码已经上传到了GITHUB上。地址：[项目GITHUB地址](https://github.com/thingsareright/zzudangdangweb)
    这个项目一共分下面几个部分：

    
- 初始化数据（目前只初始化了book, book_boss, book_boss_picture, boss四个表）
- 进行图书的查询，支持商品的模糊查询，而且可以根据传过来的参数进行数据库的分页



    那么，我们是如何做到初始化数据库的呢？为了方便测试和调试，我们通过网页访问的方式来初始化数据库。
    
    首先, 开发者需要在自己的数据库中新建一个数据库：
  
    `>mysql create database good`
    
    然后，使用IDEA（笔者用的是2017.3版本）对此项目进行打包，生成jar包后，通过
    `jave -jar ***.jar`
    运行。
    这个时候读者就可以通过访问本地服务器来初始化数据和查询操作了（这里的服务器是指项目里spring boot框架内嵌的Tomcat服务器）。
    
    通过访问[http://127.0.0.1:5002/book/initData](http://127.0.0.1:5002/book/initData)即可进行初始化数据，如果初始化成功，那么会返回1。
    
    通过访问[http://127.0.0.1:5002/book/bookForResult](http://127.0.0.1:5002/book/bookForResult)就可以调用模糊查找某书名的所有书的数据接口。
    如果想获得数据，那么就要通过向这个接口发送一个GET请求，这个请求里要有三个参数（数据库里满足条件的元组集合记为A)：
    - name： 书名，默认是MySQL
    - begin: 一个整数（int），默认是0
    - range： 一个整数（int），默认是10
    那么我们会返回A中从begin,到（begin + range）的数据（本质上是数据库里的分页查询）。
    用做返回数据的载体的java文件代码如下（会返回JSON格式）：


    ```
        package com.example.demo.bean;
    
        /**
         * Created by Administrator on 2017/12/4 0004.
         * 数据库book的映射java文件，超完全映射，中间加了个两个属性，用于向数据库返回东西
         * 用来返回商品搜索结果列表界面
         **/
    
    public class BookToClientOfBookListForResult {
        private int id; //书籍的唯一标识（主键）
        private String book_name;   //书名
        private String writter; //作者
        private String press;   //出版社
        private int version;    //版本号
        private String ISBN;    //ISBN号
        private int format;     //开本
        private int paper;      //纸张（0表示胶版纸）
        private int book_package;   //包装方式（0表示平装，1表示精装，必须向下兼容）
        private int book_kind;  //书籍分类（具体的类型还没有定义）
        private float book_price;   //为了方便，这里与数据库表并不一样
        private String book_picture;    //同上，这里存储第一张搜索到的图片
        private int boss_id;
    
        public int getBoss_id() {
            return boss_id;
        }
    
        public void setBoss_id(int boss_id) {
            this.boss_id = boss_id;
        }
    
        public BookToClientOfBookListForResult(int id, String book_name, String writter, String press, int version, String ISBN, int format, int paper, int book_package, int book_kind, float book_price, String book_picture) {
            this.id = id;
            this.book_name = book_name;
            this.writter = writter;
            this.press = press;
            this.version = version;
            this.ISBN = ISBN;
            this.format = format;
            this.paper = paper;
            this.book_package = book_package;
            this.book_kind = book_kind;
            this.book_price = book_price;
            this.book_picture = book_picture;
        }
    
        public BookToClientOfBookListForResult() {
    
        }
    
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public String getBook_name() {
            return book_name;
        }
    
        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }
    
        public String getWritter() {
            return writter;
        }
    
        public void setWritter(String writter) {
            this.writter = writter;
        }
    
        public String getPress() {
            return press;
        }
    
        public void setPress(String press) {
            this.press = press;
        }
    
        public int getVersion() {
            return version;
        }
    
        public void setVersion(int version) {
            this.version = version;
        }
    
        public String getISBN() {
            return ISBN;
        }
    
        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }
    
        public int getFormat() {
            return format;
        }
    
        public void setFormat(int format) {
            this.format = format;
        }
    
        public int getPaper() {
            return paper;
        }
    
        public void setPaper(int paper) {
            this.paper = paper;
        }
    
        public int getBook_package() {
            return book_package;
        }
    
        public void setBook_package(int book_package) {
            this.book_package = book_package;
        }
    
        public int getBook_kind() {
            return book_kind;
        }
    
        public void setBook_kind(int book_kind) {
            this.book_kind = book_kind;
        }
    
        public float getBook_price() {
            return book_price;
        }
    
        public void setBook_price(float book_price) {
            this.book_price = book_price;
        }
    
        public String getBook_picture() {
            return book_picture;
        }
    
        public void setBook_picture(String book_picture) {
            this.book_picture = book_picture;
        }
    }

    ```
    
    通过访问[http://127.0.0.1:5002/book/bookForSingle](http://127.0.0.1:5002/book/bookForSingle)就可以查找某本商家卖的某本书的详情。
    如果想获得数据，那么就要通过向这个接口发送一个GET请求，这个请求里要有两个参数)：
    - bookid: 一个整数（int），书籍id
    - bossid： 一个整数（int），商家id
    用做返回数据的载体的java文件代码如下（会返回JSON格式）
    ```
    package com.example.demo.bean;
    
    import org.apache.catalina.LifecycleState;
    
    import java.io.Serializable;
    import java.util.List;
    
    /**
     * 这个类是用于向小建哥提供的页面注入数据
     */
    public class BookToClientforSingleBook implements Serializable{
        private int bookid;
        private int bossid;
        private List<String> imageAddressList;      //商品图片地址列表
        private String book_name;                   //书的名字
        private float price;                        //书的价格
        private String writter;                     //书的作者
        private String press;                       //出版社
        private String boss_image;                  //商家头像
        private String boss_name;                   //商家名字
    
    
        public BookToClientforSingleBook() {
        }
    
        public BookToClientforSingleBook(int bookid, int bossid, List<String> imageAddressList, String book_name, float price, String writter, String press, String boss_image, String boss_name) {
            this.bookid = bookid;
            this.bossid = bossid;
            this.imageAddressList = imageAddressList;
            this.book_name = book_name;
            this.price = price;
            this.writter = writter;
            this.press = press;
            this.boss_image = boss_image;
            this.boss_name = boss_name;
        }
    
        public List<String> getImageAddressList() {
            return imageAddressList;
        }
    
        public void setImageAddressList(List<String> imageAddressList) {
            this.imageAddressList = imageAddressList;
        }
    
        public String getBook_name() {
            return book_name;
        }
    
        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }
    
        public float getPrice() {
            return price;
        }
    
        public void setPrice(float price) {
            this.price = price;
        }
    
        public String getWritter() {
            return writter;
        }
    
        public void setWritter(String writter) {
            this.writter = writter;
        }
    
        public String getPress() {
            return press;
        }
    
        public void setPress(String press) {
            this.press = press;
        }
    
        public String getBoss_image() {
            return boss_image;
        }
    
        public void setBoss_image(String boss_image) {
            this.boss_image = boss_image;
        }
    
        public String getBoss_name() {
            return boss_name;
        }
    
        public void setBoss_name(String boss_name) {
            this.boss_name = boss_name;
        }
    
        public int getBookid() {
            return bookid;
        }
    
        public void setBookid(int bookid) {
            this.bookid = bookid;
        }
    
        public int getBossid() {
            return bossid;
        }
    
        public void setBossid(int bossid) {
            this.bossid = bossid;
        }
    }


    ```
    
    --------
    文档书写人：thingsareright
    日期：2017/12/20 0:22
    
    
    




