# 空处理       

方式一

//老方法

    if(user.isPresent()) {
      return user.get().getOrders();
    } else {
      return Collections.emptyList();
    }
//新方法
    
    return user.map(u -> u.getOrders()).orElse(Collections.emptyList())

方式二

//老方法

    User user = .....
    if(user != null) {
      String name = user.getUsername();
      if(name != null) {
        return name.toUpperCase();
      } else {
        return null;
      }
    } else {
      return null;
    }

//新方法

    return user.map(u -> u.getUsername())
           .map(name -> name.toUpperCase())
           .orElse(null);
    
           
    //不用逐层写if( result != null)     
    String getChampionName(Competition comp) throws IllegalArgumentException {
        return Option.ofNullable(comp)
                  .map(c -> c.getResult())
                  .map(r -> r.getChampion())
                  .map(u -> u.getName())
                  .orElseThrow(() -> new IllegalArgumentException("The value of param comp isn't available."));
    }
