【最简单的加密】
1.简单的概念

明文：加密前的信息

密文：机密后的信息

算法：加密或解密的算法

密钥：算法使用的钥匙（读作miyao，正确应该是miyue，但是大家都读miyao）



2.简单的例子

将123456每位数字都加1后得到234567，

其中123456就是明文，234567就是密文，加密密钥就是1，加密算法是每位加



3.对称加密和非对称加密

以上为例，

123456-->234567的加密密钥就是1，加密算法是每位+

234567-->123456的解密密钥也是1，解密算法是每位-

其中加密算法（+）和解密算法（-）相对称，这种加密算法就称作对称加密，

同样，如果加密算法和解密算法不对称就称之为非对称加密。



4.算法举例

对称加密算法：DES算法，3DES算法，TDEA算法，Blowfish算法，RC5算法，IDEA算法，AES算法。

非对称加密算法：RSA、Elgamal、背包算法、Rabin、D-H、ECC。

经典的哈希算法：MD2、MD4、MD5 和 SHA-1（目的是将任意长输入通过算法变为固定长输出，且保证输入变化一点输出都不同，且不能反向解密）



5.经典的算法
AES（对称），RSA（非对称），MD5,SHA-1（哈希）


###  泛型
*    Class<T>在实例化的时候，T要替换成具体类
*    Class<?>它是个通配泛型，?可以代表任何类型   
*    <? extends T>受限统配，表示T的一个未知子类。
*    <? super T>下限统配，表示T的一个未知父类。

？ 表示不确定的java类型。 
T  表示java类型。 
K V 分别代表java键值中的Key Value。 
E 代表Element。




 