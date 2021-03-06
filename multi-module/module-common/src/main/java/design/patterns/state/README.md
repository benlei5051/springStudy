八、状态模式与策略模式区别以及联系

策略模式与状态模式及其相似，但是二者有其内在的差别，策略模式将具体策略类暴露出去，
调用者需要具体明白每个策略的不同之处以便正确使用。

而状态模式状态的改变是由其内部条件来改变的，与外界无关，二者在思想上有本质区别。

##优点
策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族。
恰当使用继承可以把公共的代码移到父类里面，从而避免代码重复。
使用策略模式可以避免使用多重条件(if-else)语句。
多重条件语句不易维护，它把采取哪一种算法让子类实现

##缺点
通过上面Demo我们会发现调用者必须知道所有的策略类，并自行决定使用哪一个策略类。
这就意味着客户端必须理解这些算法的区别，
以便适时选择恰当的算法类并且由于策略模式把每个具体的策略实现都单独封装成为类，
如果备选的策略很多的话，那么对象的数目就会很可观。