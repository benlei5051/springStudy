## HttpURLConnection 

    在JDK的java.net包中已经提供了访问HTTP协议的基本功能的类：HttpURLConnection。
    HttpURLConnection是Java的标准类，它继承自URLConnection，可用于向指定网站发送GET请求、POST请求。它在URLConnection的基础上提供了如下便捷的方法：
    int getResponseCode(); // 获取服务器的响应代码。
    String getResponseMessage(); // 获取服务器的响应消息。
    String getResponseMethod(); // 获取发送请求的方法。
    void setRequestMethod(String method); // 设置发送请求的方法。
  
    HttpURLConnection对象不能直接构造，需要通过URL类中的openConnection()方法来获得。
    HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的TCP连接，并没有实际发送HTTP请求。HTTP请求实际上直到我们获取服务器响应数据（如调用getInputStream()、getResponseCode()等方法）时才正式发送出去。
    对HttpURLConnection对象的配置都需要在connect()方法执行之前完成。
    HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。
    HTTP正文的内容是通过OutputStream流写入的， 向流中写入的数据不会立即发送到网络，而是存在于内存缓冲区中，待流关闭时，根据写入的内容生成HTTP正文。
    调用getInputStream()方法时，返回一个输入流，用于从中读取服务器对于HTTP请求的返回信息。
    我们可以使用HttpURLConnection.connect()方法手动的发送一个HTTP请求，但是如果要获取HTTP响应的时候，请求就会自动的发起，比如我们使用HttpURLConnection.getInputStream()方法的时候，所以完全没有必要调用connect()方法。
    