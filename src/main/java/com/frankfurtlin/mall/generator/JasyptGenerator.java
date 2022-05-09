package com.frankfurtlin.mall.generator;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/5/8 21:41
 * java -jar -Djasypt.encryptor.password=frankfurtlin xxx.jar
 */
public class JasyptGenerator {
    public static void main(String[] args) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        // 设置加密所需的 salt
        basicTextEncryptor.setPassword("frankfurtlin");

        // 要加密的数据
        String username = basicTextEncryptor.encrypt("root");
        String password = basicTextEncryptor.encrypt("123456");

        // 打印加密后的数据
        System.out.println(username);
        System.out.println(password);

        // 解密并输出
        System.out.println(basicTextEncryptor.decrypt(username));
        System.out.println(basicTextEncryptor.decrypt(password));
    }
}
