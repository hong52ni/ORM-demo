package pers.hong.orm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.hong.annotation.Column;
import pers.hong.annotation.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: hong
 * @Date: 2018/09/12
 */
@Component
public class ORMUtil {
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${jdbcDriver}")
    private String driver;
    @Value("${url}")
    private String url;

    public void save(Object object) throws Exception {
        //加载驱动
        Class.forName(driver);
        //建立连接
        Connection conn = DriverManager.getConnection(url, user,password);
        //预编译语句
        PreparedStatement pstmt = conn.prepareStatement(prepareSql(object));
        //执行
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    private String prepareSql(Object object) throws Exception {
        StringBuffer str = new StringBuffer("INSERT INTO ");
        //反射获得类对象
        Class<?> clazz = object.getClass();
        String tableName = clazz.getSimpleName().toUpperCase();
        //判断是否加了@Table注解
        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            tableName = table.name().toUpperCase();
        }
        str.append(tableName + "(");
        //获取字段
        Field[] fields = clazz.getDeclaredFields();
        // 定义存储字段对应值的集合
        List<Object> valueList = new ArrayList<>();
        for (Field field : fields) {
            String fieldName = field.getName().toUpperCase();
            //判断是否使用@Column注解
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                fieldName = column.name().toUpperCase();
            }
            //开启权限
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null) {
                str.append(fieldName + ",");
                valueList.add(value);
            }
        }
        //删除最后一个逗号
        str.deleteCharAt(str.length() - 1);
        str.append(") VALUES (");
        for (Object value : valueList) {
            Object temp = value;

            // 判断value是否为String类型
            if (value instanceof String) {
                temp = "'"+value+"'";
            }

            str.append(temp+",");
        }
        //删除最后一个逗号
        str.deleteCharAt(str.length() - 1);
        str.append(")");
        return str.toString();
    }
}
