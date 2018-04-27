package org.andy.beans.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author: andy
 * @Date: 2018/4/19 10:33
 * @Description:
 */
public class I18nTest {
    public static void main(String[] args){
        Locale defaultLocal= Locale.US;
        ResourceBundle resourceBundle =  ResourceBundle.getBundle("MessageBundle",defaultLocal);
        System.out.println("k1: "+resourceBundle.getString("k1") );
        System.out.println("k2: "+resourceBundle.getString("k2") );

        ResourceBundle rb1 = ResourceBundle.getBundle("MessageBundle",Locale.US);
        Object[] params = {"John", new GregorianCalendar().getTime()};
        Object[] params2 = {"John", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())};

        String str1 = new MessageFormat(rb1.getString("greeting.common"),Locale.US).format(params2);
        System.out.println(str1);

    }
}
