package com.darren.demo.designmodel.decorator;

/**
 * 测试装饰模式
 *
 * @author : darren
 * @date : 2022/2/11
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Person person = new Durant();
        person = new RichPerson(new RichPerson(new HighPerson(person)));
        person.desc();
    }
}
